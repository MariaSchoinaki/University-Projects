import os
import json
import time
import uuid
import logging
import tempfile
import subprocess
from dataclasses import dataclass
from typing import List, Dict, Any

from azure.storage.blob import BlobServiceClient, ContentSettings
from azure.storage.queue import QueueClient
from azure.core.exceptions import ResourceExistsError

logging.basicConfig(level=logging.INFO)
logging.getLogger("azure.core.pipeline.policies.http_logging_policy").setLevel(logging.WARNING)


# =========================
# DATA MODEL
# =========================
@dataclass
class SplitJob:
    blob_name: str
    request_id: str
    segment_seconds: int
    input_container: str
    output_container: str


# =========================
# HELPERS
# =========================
def must_env(name: str) -> str:
    v = os.getenv(name)
    if not v:
        raise RuntimeError(f"Missing required env var: {name}")
    return v


def ensure_queue(conn_str: str, queue_name: str) -> QueueClient:
    q = QueueClient.from_connection_string(conn_str, queue_name)
    try:
        q.create_queue()
    except ResourceExistsError:
        pass
    return q


def enqueue_json(q: QueueClient, payload: Dict[str, Any]) -> None:
    q.send_message(json.dumps(payload, ensure_ascii=False))


def run_ffmpeg_split(ffmpeg: str, input_path: str, out_dir: str, segment_seconds: int) -> List[str]:
    """
    Produces deterministic filenames:
    part0000.mp4, part0001.mp4, ...
    """
    output_pattern = os.path.join(out_dir, "part%04d.mp4")

    cmd = [
        ffmpeg,
        "-hide_banner",
        "-loglevel", "error",
        "-i", input_path,
        "-an",
        "-c:v", "libx264",
        "-preset", "veryfast",
        "-crf", "23",
        "-f", "segment",
        "-segment_time", str(segment_seconds),
        "-reset_timestamps", "1",
        output_pattern
    ]

    subprocess.run(cmd, check=True)

    parts = sorted(
        os.path.join(out_dir, f)
        for f in os.listdir(out_dir)
        if f.startswith("part") and f.endswith(".mp4")
    )

    if not parts:
        raise RuntimeError("FFmpeg produced no output segments.")

    return parts


def upload_if_absent(container_client, blob_name: str, local_path: str) -> None:
    bc = container_client.get_blob_client(blob_name)

    if bc.exists():
        logging.info("Skip upload (exists): %s", blob_name)
        return

    with open(local_path, "rb") as f:
        bc.upload_blob(
            f,
            overwrite=False,
            content_settings=ContentSettings(content_type="video/mp4")
        )

    logging.info("Uploaded: %s", blob_name)


# =========================
# MAIN PROCESSING
# =========================
def process_job(job: SplitJob) -> None:
    conn_str = must_env("AZURE_STORAGE_CONNECTION_STRING")
    ffmpeg = os.getenv("FFMPEG_PATH", "ffmpeg")

    split_queue_name = os.getenv("SPLIT_QUEUE_NAME", "split-requests")
    processing_queue_name = os.getenv("PROCESSING_QUEUE_NAME", "processing-queue")

    blob_service = BlobServiceClient.from_connection_string(conn_str)

    in_container = blob_service.get_container_client(job.input_container)
    out_container = blob_service.get_container_client(job.output_container)

    try:
        out_container.create_container()
    except ResourceExistsError:
        pass

    processing_q = ensure_queue(conn_str, processing_queue_name)

    stable_request = job.request_id or "no-request-id"
    base = os.path.splitext(os.path.basename(job.blob_name))[0]
    output_prefix = f"{base}/{stable_request}/"

    with tempfile.TemporaryDirectory() as tmp:
        local_in = os.path.join(tmp, os.path.basename(job.blob_name))

        logging.info("Downloading blob: %s/%s", job.input_container, job.blob_name)

        in_blob = in_container.get_blob_client(job.blob_name)

        if not in_blob.exists():
            raise RuntimeError(f"Input blob does not exist: {job.blob_name}")

        with open(local_in, "wb") as f:
            f.write(in_blob.download_blob().readall())

        out_dir = os.path.join(tmp, "out")
        os.makedirs(out_dir, exist_ok=True)

        t0 = time.time()
        parts = run_ffmpeg_split(ffmpeg, local_in, out_dir, job.segment_seconds)
        logging.info("Split complete: %d parts in %.2fs", len(parts), time.time() - t0)

        # =========================
        # Upload + enqueue processing jobs
        # =========================
        for idx, part_path in enumerate(parts):
            part_name = f"{output_prefix}part{idx:04d}.mp4"
            upload_if_absent(out_container, part_name, part_path)

            payload = {
                "chunk_blob": part_name,
                "chunk_index": idx,
                "chunk_start_sec": idx * job.segment_seconds,
                "output_container": job.output_container,
                "request_id": job.request_id,
                "segment_seconds": job.segment_seconds
            }

            enqueue_json(processing_q, payload)
            logging.info("Enqueued processing job: %s (start=%ss)", part_name, payload["chunk_start_sec"])

        # =========================
        # Write manifest
        # =========================
        manifest = {
            "blob_name": job.blob_name,
            "request_id": job.request_id,
            "segment_seconds": job.segment_seconds,
            "input_container": job.input_container,
            "output_container": job.output_container,
            "parts": [
                f"{output_prefix}part{idx:04d}.mp4"
                for idx in range(len(parts))
            ],
            "num_parts": len(parts)
        }

        manifest_blob = out_container.get_blob_client(f"{output_prefix}manifest.json")

        if not manifest_blob.exists():
            manifest_blob.upload_blob(
                json.dumps(manifest, ensure_ascii=False).encode("utf-8"),
                overwrite=False,
                content_settings=ContentSettings(content_type="application/json")
            )
            logging.info("Manifest written: %smanifest.json", output_prefix)

        logging.info("Job finished: %s (sent %d processing messages)", job.request_id, len(parts))


# =========================
# QUEUE WORKER LOOP
# =========================
def main_loop():
    conn_str = must_env("AZURE_STORAGE_CONNECTION_STRING")
    queue_name = os.getenv("SPLIT_QUEUE_NAME", "split-requests")

    q = ensure_queue(conn_str, queue_name)

    max_dequeue = int(os.getenv("MAX_DEQUEUE", "5"))
    visibility_timeout = int(os.getenv("VISIBILITY_TIMEOUT", "300"))

    logging.info("Worker started. Listening on queue: %s", queue_name)

    while True:
        msgs = q.receive_messages(messages_per_page=1, visibility_timeout=visibility_timeout)
        got_any = False

        for page in msgs.by_page():
            for m in page:
                got_any = True
                try:
                    payload = json.loads(m.content)

                    job = SplitJob(
                        blob_name=payload["blob_name"],
                        request_id=payload.get("request_id") or str(uuid.uuid4()),
                        segment_seconds=int(payload.get("segment_seconds", 120)),
                        input_container=payload.get("input_container", "storagecontainer"),
                        output_container=payload.get("output_container", "outputcontainer"),
                    )

                    process_job(job)
                    q.delete_message(m)
                    logging.info("Split job completed: %s", job.request_id)

                except Exception:
                    logging.exception("Split job failed.")

                    if m.dequeue_count >= max_dequeue:
                        poison_name = os.getenv("POISON_QUEUE_NAME", "split-requests-poison")
                        poison_q = ensure_queue(conn_str, poison_name)

                        enqueue_json(poison_q, {"raw": m.content})
                        q.delete_message(m)

                        logging.error("Moved message to poison queue: %s", poison_name)

        if not got_any:
            time.sleep(2)


# =========================
# ENTRYPOINT
# =========================
if __name__ == "__main__":
    logging.info("=== splitter_worker.py started ===")

    blob_name = os.getenv("BLOB_NAME")

    if blob_name:
        job = SplitJob(
            blob_name=blob_name,
            request_id=os.getenv("REQUEST_ID", str(uuid.uuid4())),
            segment_seconds=int(os.getenv("SEGMENT_SECONDS", "120")),
            input_container=os.getenv("INPUT_CONTAINER", "storagecontainer"),
            output_container=os.getenv("OUTPUT_CONTAINER", "outputcontainer"),
        )
        process_job(job)
    else:
        main_loop()