import azure.functions as func
import logging
import os
import re
import json
import time
import tempfile

from azure.servicebus import ServiceBusClient, ServiceBusMessage
from azure.storage.blob import BlobServiceClient

import cv2
from ultralytics import YOLO

from prometheus_client import (
    Counter, Histogram, Gauge,
    generate_latest, CONTENT_TYPE_LATEST
)

# ============================================================
# CONFIG
# ============================================================
REALTIME_ALERT_THRESHOLD = 130   # km/h (req 4)
OUTLIER_THRESHOLD = 200          # ignore >200 km/h
SPEED_LIMIT_CAR = 90             # km/h (req 3)
SPEED_LIMIT_TRUCK = 80           # km/h (req 3)
FIVE_MIN_SECONDS = 300           # 5-min buckets (req 5,6)

# Distance travelled inside ROI band
REAL_ROI_DISTANCE_M = 17.0

# From the splitter, used to compute chunk_start_sec from partXXXX.mp4
SEGMENT_SECONDS = int(os.getenv("SEGMENT_SECONDS", "120"))

# Speed calculation stability guards
MIN_FRAMES_IN_ROI = int(os.getenv("MIN_FRAMES_IN_ROI", "5"))  # ignore tiny crossings
FRAME_SKIP = int(os.getenv("FRAME_SKIP", "1"))                # 1 = no skipping, 5 = 5x faster

# Single video source label
SOURCE_LABEL = os.getenv("SOURCE_LABEL", "video1")

# Service Bus
ALERTS_QUEUE_NAME = os.getenv("ALERTS_QUEUE_NAME", "alertsqueue")
SERVICEBUS_RETRIES = int(os.getenv("SERVICEBUS_RETRIES", "3"))

# YOLO
YOLO_MODEL_PATH = os.getenv("YOLO_MODEL_PATH", "yolov8n.pt")
YOLO_CONF = float(os.getenv("YOLO_CONF", "0.4"))

# ============================================================
# Azure Function App
# ============================================================
app = func.FunctionApp()

# ============================================================
# Prometheus Metrics (scraped from /api/metrics)
# ============================================================
BLOBS_PROCESSED = Counter(
    "traffic_blobs_processed_total",
    "Total number of video chunks processed",
    ["source"]
)

BLOB_BYTES = Counter(
    "traffic_blob_bytes_total",
    "Total bytes processed from blobs",
    ["source"]
)

CHUNK_TOTAL_LATENCY_SECONDS = Histogram(
    "traffic_chunk_total_latency_seconds",
    "Total processing latency per chunk (seconds)",
    ["source"]
)

# Requirement (7): histogram
CV_LATENCY_SECONDS = Histogram(
    "traffic_cv_latency_seconds",
    "Computer vision processing latency per chunk (seconds)",
    ["source"]
)

# Requirement (2): vehicles per stream
VEHICLES_TOTAL = Counter(
    "traffic_vehicles_total",
    "Total vehicles measured (counted when speed computed)",
    ["stream", "vehicle_type", "source"]
)

# Requirement (3): vehicles exceeding speed limit per type
VEHICLES_OVER_LIMIT_TOTAL = Counter(
    "traffic_vehicles_over_limit_total",
    "Total vehicles exceeding speed limit (car>90, truck>80)",
    ["stream", "vehicle_type", "source"]
)

# Requirement (4): real-time alerts
REALTIME_ALERTS_TOTAL = Counter(
    "traffic_realtime_alerts_total",
    "Total real-time alerts (speed > 130 km/h)",
    ["stream", "vehicle_type", "source"]
)

# Requirement (5): vehicles per 5 minutes
VEHICLES_5MIN_TOTAL = Counter(
    "traffic_vehicles_5min_total",
    "Vehicles per stream per 5-minute bucket",
    ["stream", "vehicle_type", "source", "bucket_5min"]
)

# Requirement (6): average speed per 5 minutes => sum + count
SPEED_KMH_SUM = Counter(
    "traffic_speed_kmh_sum",
    "Sum of speeds (km/h) per 5-minute bucket",
    ["stream", "vehicle_type", "source", "bucket_5min"]
)

SPEED_KMH_COUNT = Counter(
    "traffic_speed_kmh_count",
    "Count of speeds per 5-minute bucket",
    ["stream", "vehicle_type", "source", "bucket_5min"]
)

LAST_CHUNK_TRACKED_VEHICLES = Gauge(
    "traffic_last_chunk_tracked_vehicles",
    "How many track IDs were seen in the last processed chunk",
    ["source"]
)

ERRORS_TOTAL = Counter(
    "traffic_errors_total",
    "Total errors",
    ["stage", "source"]
)

# ============================================================
# Metrics endpoint
# GET /api/metrics
# ============================================================
@app.function_name("Metrics")
@app.route(route="metrics", auth_level=func.AuthLevel.ANONYMOUS, methods=["GET"])
def metrics(req: func.HttpRequest) -> func.HttpResponse:
    payload = generate_latest()
    return func.HttpResponse(payload, mimetype=CONTENT_TYPE_LATEST, status_code=200)

# ============================================================
# Helpers
# ============================================================
_model = None

def get_model() -> YOLO:
    global _model
    if _model is None:
        logging.info("Loading YOLO model: %s", YOLO_MODEL_PATH)
        _model = YOLO(YOLO_MODEL_PATH)
    return _model

def parse_chunk_index(blob_name: str) -> int:
    """
    Extract idx from .../part0003.mp4
    Returns 0 if not found.
    """
    name = blob_name.replace("\\", "/")
    m = re.search(r"part(\d+)\.mp4$", name, re.IGNORECASE)
    return int(m.group(1)) if m else 0

def get_done_marker_name(blob_name: str) -> str:
    # marker blob to avoid double-counting if trigger retries
    return blob_name.replace("\\", "/") + ".done"

def ensure_env(name: str) -> str:
    v = os.environ.get(name)
    if not v:
        raise RuntimeError(f"Missing required env var: {name}")
    return v

def send_to_service_bus_queue(payload: dict) -> None:
    """
    Only for real-time alerts. Includes basic retries for transient failures.
    """
    conn_str = ensure_env("ServiceBusConnectionString")
    message_payload = json.dumps(payload, ensure_ascii=False)

    last_err = None
    for attempt in range(1, SERVICEBUS_RETRIES + 1):
        try:
            with ServiceBusClient.from_connection_string(conn_str=conn_str) as client:
                with client.get_queue_sender(queue_name=ALERTS_QUEUE_NAME) as sender:
                    sender.send_messages(ServiceBusMessage(message_payload))
            return
        except Exception as e:
            last_err = e
            time.sleep(0.3 * attempt)

    raise last_err

# ============================================================
# Blob Trigger Processor
# Triggered when a chunk lands in outputcontainer/{name}
# ============================================================
@app.function_name("Processor")
@app.blob_trigger(arg_name="myblob", path="outputcontainer/{name}", connection="VideoConnectionString")
def blob_trigger(myblob: func.InputStream):
    blob_name = myblob.name.replace("\\", "/")
    name_lower = blob_name.lower()

    # only mp4 chunks
    if not name_lower.endswith(".mp4"):
        logging.info("Skipping non-mp4 blob: %s", myblob.name)
        return

    source = SOURCE_LABEL
    start_total = time.time()
    file_path = None

    # Avoid double counting on retries
    # Uses the same VideoConnectionString
    bsc = None
    done_blob_client = None

    try:
        conn_str = ensure_env("AzureWebJobsVideoConnectionString")
        bsc = BlobServiceClient.from_connection_string(conn_str)

        # myblob.name often includes container prefix, e.g. "outputcontainer/xxx/part0001.mp4"
        # Split container + path safely:
        parts = blob_name.split("/", 1)
        container_name = parts[0]
        inner_path = parts[1] if len(parts) > 1 else blob_name

        done_name = get_done_marker_name(inner_path)
        done_blob_client = bsc.get_blob_client(container=container_name, blob=done_name)

        if done_blob_client.exists():
            logging.info("Already processed (marker exists). Skipping: %s", blob_name)
            return

        data = myblob.read()
        blob_size = len(data)
        logging.info("Processed blob: %s | size=%d bytes", blob_name, blob_size)

        # chunk index from filename partXXXX.mp4
        chunk_index = parse_chunk_index(inner_path)
        chunk_start_sec = chunk_index * SEGMENT_SECONDS

        BLOBS_PROCESSED.labels(source=source).inc()
        BLOB_BYTES.labels(source=source).inc(blob_size)

        # write blob bytes to a temp file
        temp_dir = tempfile.gettempdir()
        local_file_name = os.path.basename(inner_path)
        file_path = os.path.join(temp_dir, local_file_name)

        with open(file_path, "wb") as f:
            f.write(data)

        # CV processing latency histogram
        cv_start = time.time()
        tracked = tracker(
            video_path=file_path,
            source=source,
            chunk_start_sec=chunk_start_sec
        )
        CV_LATENCY_SECONDS.labels(source=source).observe(time.time() - cv_start)
        LAST_CHUNK_TRACKED_VEHICLES.labels(source=source).set(len(tracked))

        # Write marker only after successful processing
        done_blob_client.upload_blob(b"ok", overwrite=True)
        logging.info("Chunk processed successfully: %s (chunk_index=%d)", blob_name, chunk_index)

    except Exception as e:
        ERRORS_TOTAL.labels(stage="processor", source=source).inc()
        logging.exception("Processor error: %s", e)
        raise
    finally:
        CHUNK_TOTAL_LATENCY_SECONDS.labels(source=source).observe(time.time() - start_total)
        if file_path and os.path.exists(file_path):
            try:
                os.remove(file_path)
            except Exception:
                pass

# ============================================================
# CV tracker
# ============================================================
def tracker(video_path: str, source: str, chunk_start_sec: int) -> dict:
    model = get_model()
    target_classes = [2, 7]

    cap = cv2.VideoCapture(video_path)
    if not cap.isOpened():
        ERRORS_TOTAL.labels(stage="cv_open", source=source).inc()
        logging.error("Error opening video file: %s", video_path)
        return {}

    frame_width = int(cap.get(cv2.CAP_PROP_FRAME_WIDTH)) or 640
    frame_height = int(cap.get(cv2.CAP_PROP_FRAME_HEIGHT)) or 480
    fps = cap.get(cv2.CAP_PROP_FPS)
    if not fps or fps <= 0:
        fps = 25.0

    # Define stream areas (left half outbound, right half inbound)
    outbound_box = (0, 0, frame_width // 2, frame_height)
    inbound_box = (frame_width // 2, 0, frame_width, frame_height)

    # ROI band
    roi_top = int(frame_height * 0.53)
    roi_bottom = int(frame_height * 0.66)
    roi_box = (0, roi_top, frame_width, roi_bottom)

    tracked_vehicles = {}
    frame_index = 0

    try:
        while True:
            ret, frame = cap.read()
            if not ret:
                break

            # frame skipping for speed
            if FRAME_SKIP > 1 and (frame_index % FRAME_SKIP != 0):
                frame_index += 1
                continue

            results = model.track(
                source=frame,
                conf=YOLO_CONF,
                classes=target_classes,
                persist=True,
                verbose=False
            )

            detections = None
            for res in results:
                detections = res.boxes
                break

            if detections is None:
                frame_index += 1
                continue

            for det in detections:
                if det.id is None:
                    continue

                track_id = int(det.id)
                cls_id = int(det.cls[0])  # 2 car, 7 truck
                conf = float(det.conf[0])

                x1, y1, x2, y2 = det.xyxy[0].cpu().numpy()
                x1, y1, x2, y2 = int(x1), int(y1), int(x2), int(y2)

                cx = int((x1 + x2) / 2)
                cy = int((y1 + y2) / 2)

                if is_inside(cx, cy, inbound_box):
                    stream = "Inbound"
                elif is_inside(cx, cy, outbound_box):
                    stream = "Outbound"
                else:
                    stream = "Unknown"

                vehicle_type = "Car" if cls_id == 2 else "Truck"

                if track_id not in tracked_vehicles:
                    timestamp_s = int(chunk_start_sec + (frame_index / fps))
                    tracked_vehicles[track_id] = {
                        "timestamp": timestamp_s,
                        "in_roi": False,
                        "vehicle_type": vehicle_type,
                        "stream": stream,
                        "latest_conf": conf,
                        "speed": 0,
                        "start_frame": None,
                        "end_frame": None
                    }
                else:
                    tracked_vehicles[track_id]["latest_conf"] = conf
                    if tracked_vehicles[track_id].get("stream") in (None, "", "Unknown"):
                        tracked_vehicles[track_id]["stream"] = stream

                in_roi = is_inside(cx, cy, roi_box)
                v = tracked_vehicles[track_id]

                if (not v["in_roi"]) and in_roi:
                    v["in_roi"] = True
                    v["start_frame"] = frame_index
                    v["end_frame"] = None

                if v["in_roi"] and (not in_roi):
                    v["in_roi"] = False
                    v["end_frame"] = frame_index

                    if v["start_frame"] is None or v["end_frame"] is None:
                        continue

                    frames_taken = v["end_frame"] - v["start_frame"]
                    if frames_taken <= 0 or frames_taken < MIN_FRAMES_IN_ROI:
                        continue

                    time_taken_s = frames_taken / fps
                    if time_taken_s <= 0:
                        continue

                    speed_m_s = REAL_ROI_DISTANCE_M / time_taken_s
                    speed_kmh = speed_m_s * 3.6
                    speed_kmh_rounded = int(round(speed_kmh))

                    if speed_kmh_rounded <= 0 or speed_kmh_rounded > OUTLIER_THRESHOLD:
                        v["speed"] = 0
                        continue

                    v["speed"] = speed_kmh_rounded

                    stream_lbl = v.get("stream", "Unknown")
                    vtype_lbl = v.get("vehicle_type", "Unknown")

                    # (2) vehicles per stream
                    VEHICLES_TOTAL.labels(stream=stream_lbl, vehicle_type=vtype_lbl, source=source).inc()

                    # (5) per 5-min bucket
                    ts = int(v.get("timestamp", 0))
                    bucket_5min = str(ts // FIVE_MIN_SECONDS)
                    VEHICLES_5MIN_TOTAL.labels(
                        stream=stream_lbl, vehicle_type=vtype_lbl, source=source, bucket_5min=bucket_5min
                    ).inc()

                    # (6) avg speed per bucket (sum + count)
                    SPEED_KMH_SUM.labels(
                        stream=stream_lbl, vehicle_type=vtype_lbl, source=source, bucket_5min=bucket_5min
                    ).inc(speed_kmh_rounded)
                    SPEED_KMH_COUNT.labels(
                        stream=stream_lbl, vehicle_type=vtype_lbl, source=source, bucket_5min=bucket_5min
                    ).inc()

                    # (3) over speed limit by type
                    speed_limit = SPEED_LIMIT_CAR if vtype_lbl == "Car" else SPEED_LIMIT_TRUCK
                    if speed_kmh_rounded > speed_limit:
                        VEHICLES_OVER_LIMIT_TOTAL.labels(stream=stream_lbl, vehicle_type=vtype_lbl, source=source).inc()

                    # (4) real-time alert
                    if speed_kmh_rounded > REALTIME_ALERT_THRESHOLD:
                        alert_payload = {
                            "timestamp": ts,
                            "stream": stream_lbl,
                            "vehicle_type": vtype_lbl,
                            "speed": speed_kmh_rounded,
                            "source": source,
                            "confidence": v.get("latest_conf", 0.0),
                        }
                        try:
                            send_to_service_bus_queue(alert_payload)
                            REALTIME_ALERTS_TOTAL.labels(stream=stream_lbl, vehicle_type=vtype_lbl, source=source).inc()
                            logging.warning(
                                "REAL-TIME ALERT: %s %s @ %d km/h (t=%ds, bucket5m=%s)",
                                vtype_lbl, stream_lbl, speed_kmh_rounded, ts, bucket_5min
                            )
                        except Exception:
                            ERRORS_TOTAL.labels(stage="service_bus", source=source).inc()
                            logging.exception("Service Bus send failed (alert).")

                    logging.info(
                        "Vehicle %s | %s | %s | %d km/h | t=%ds | 5m_bucket=%s",
                        track_id, vtype_lbl, stream_lbl, speed_kmh_rounded, ts, bucket_5min
                    )

            frame_index += 1

    except Exception:
        ERRORS_TOTAL.labels(stage="cv_loop", source=source).inc()
        logging.exception("CV loop failed.")
        raise
    finally:
        cap.release()

    return tracked_vehicles

def is_inside(cx: int, cy: int, box) -> bool:
    x1, y1, x2, y2 = box
    return (x1 <= cx <= x2) and (y1 <= cy <= y2)