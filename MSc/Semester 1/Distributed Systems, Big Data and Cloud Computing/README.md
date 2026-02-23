# 🚦 Distributed Video Traffic Analytics System

A cloud-native distributed pipeline that ingests traffic videos, splits them into chunks, performs AI vehicle tracking and speed estimation using YOLOv8, generates real-time alerts, and exposes Prometheus metrics for observability.

---

## Architecture Overview

### Pipeline Flow

1. Video uploaded to **Azure Blob Storage**
2. Producer sends a message to the **split queue**
3. **Splitter Worker** downloads the video and splits into segments using FFmpeg
4. Segments uploaded back to Blob Storage
5. **Azure Function Blob Trigger** processes each chunk
6. YOLOv8 detects vehicles and estimates speed
7. Metrics exposed to Prometheus → Grafana dashboards
8. High-speed vehicles trigger alerts via Azure Service Bus

```
Blob Upload → Split Queue → Splitter Worker → Chunk Blobs
        → Azure Function Processor → Metrics + Alerts
```

---

## Project Structure

```
video_preprocessing/
 └── splitter_worker/
      ├── Dockerfile
      ├── requirements.txt
      └── splitter_worker.py

video_processing/
 ├── function_app.py
 ├── host.json
 ├── requirements.txt
 └── yolov8n.pt

docker-compose.yml
prometheus.yml
producer.py
README.md
```

---

## Components

### Splitter Worker (Docker)

Responsibilities:

- Listens to `split-requests` queue
- Downloads video blobs
- Splits videos into fixed segments
- Uploads chunk files
- Sends processing messages
- Generates `manifest.json`
- Poison queue handling + retries

Environment variables:

```
AZURE_STORAGE_CONNECTION_STRING
SPLIT_QUEUE_NAME=split-requests
PROCESSING_QUEUE_NAME=processing-queue
INPUT_CONTAINER=storagecontainer
OUTPUT_CONTAINER=outputcontainer
SEGMENT_SECONDS=120
```

Run with:

```
docker compose up splitter_worker
```

---

### Azure Function - Video Processor

Blob Trigger:

```
outputcontainer/{name}
```

Responsibilities:

- Downloads chunk to temp storage
- Runs YOLOv8 tracking
- Estimates vehicle speed
- Emits Prometheus metrics
- Sends real-time alerts via Service Bus
- Writes a `.done` marker blob to avoid double-counting on retries

Metrics endpoint:

```
GET /api/metrics
```

Local URL:

```
http://localhost:7071/api/metrics
```

---

## AI Model

Uses **Ultralytics YOLOv8**.

Detects:

- Car (class 2)
- Truck (class 7)

Speed estimation is based on time spent inside a calibrated ROI band.

---

## Prometheus Metrics

Example metrics exposed:

| Metric | Description |
|---|---|
| `traffic_blobs_processed_total` | Processed chunks |
| `traffic_vehicles_total` | Vehicles detected (when speed computed) |
| `traffic_vehicles_over_limit_total` | Speeding vehicles (car>90, truck>80) |
| `traffic_realtime_alerts_total` | Real-time alerts (speed>130) |
| `traffic_chunk_total_latency_seconds` | Total latency per chunk |
| `traffic_cv_latency_seconds` | CV inference latency per chunk |
| `traffic_speed_kmh_sum` / `traffic_speed_kmh_count` | Avg speed per 5-min bucket |

---

## Real-Time Alerts

Vehicles exceeding:

```
130 km/h
```

are sent to Azure Service Bus queue:

```
alertsqueue
```

Example alert payload:

```json
{
  "timestamp": 12345,
  "stream": "Inbound",
  "vehicle_type": "Car",
  "speed": 142,
  "source": "video1",
  "confidence": 0.87
}
```

---

## Local Development

### 1) Start Observability Stack

```
docker compose up -d
```

Services:

- Prometheus → http://localhost:9090
- Grafana → http://localhost:3000
- Pushgateway → http://localhost:9091

### 2) Run Azure Functions

```
cd video_processing
func start
```

### 3) Run the Splitter Worker

```
docker compose up splitter_worker
```

### 4) Send a Split Request

```
python producer.py
```

Example message:

```json
{
  "blob_name": "test.mp4",
  "request_id": "uuid",
  "segment_seconds": 120,
  "input_container": "storagecontainer",
  "output_container": "outputcontainer"
}
```

---

## Azure Requirements

You need:

- Azure Storage Account
- Blob Containers:
  - `storagecontainer`
  - `outputcontainer`
- Queue Storage:
  - `split-requests`
  - `processing-queue`
- Azure Service Bus:
  - `alertsqueue`

---

## Environment Variables

### Splitter Worker

```
AZURE_STORAGE_CONNECTION_STRING=<storage_connection>
SPLIT_QUEUE_NAME=split-requests
PROCESSING_QUEUE_NAME=processing-queue
INPUT_CONTAINER=storagecontainer
OUTPUT_CONTAINER=outputcontainer
SEGMENT_SECONDS=120
```

### Azure Function

```
AzureWebJobsVideoConnectionString=<storage_connection>
ServiceBusConnectionString=<service_bus_connection>
YOLO_MODEL_PATH=yolov8n.pt
SEGMENT_SECONDS=120
SOURCE_LABEL=video1
```

---

## Speed Calculation

```
speed = REAL_ROI_DISTANCE_M / time_inside_roi
```

Constants (from code):

- ROI Distance = **17 meters**
- Car Limit = **90 km/h**
- Truck Limit = **80 km/h**
- Alert Threshold = **130 km/h**
- Outliers ignored above **200 km/h**
- 5-minute buckets use **300 seconds**

Stability guards:

- Minimum frames inside ROI (`MIN_FRAMES_IN_ROI`)
- Optional frame skipping (`FRAME_SKIP`)

---

## Scaling Notes

This architecture supports horizontal scaling:

- Multiple splitter workers consuming the split queue
- Parallel Azure Function executions per chunk
- Independent chunk processing and metrics

---

## Troubleshooting

### Metrics not appearing

Check:

- Azure Functions running locally
- `http://localhost:7071/api/metrics`

### Worker not splitting videos

Verify:

- FFmpeg installed inside container
- `AZURE_STORAGE_CONNECTION_STRING` is valid
- Input blob exists in `storagecontainer`

### Alerts not sent

Ensure:

- `ServiceBusConnectionString` is configured
- `alertsqueue` exists
- Service Bus namespace accessible