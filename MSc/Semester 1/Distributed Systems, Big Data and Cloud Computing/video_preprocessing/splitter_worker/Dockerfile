FROM python:3.11-slim

# Install ffmpeg
RUN apt-get update && apt-get install -y --no-install-recommends ffmpeg \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app

COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt

COPY splitter_worker.py .

# Default envs (override at runtime)
ENV FFMPEG_PATH=ffmpeg
ENV SEGMENT_SECONDS=120
ENV INPUT_CONTAINER=storagecontainer
ENV OUTPUT_CONTAINER=outputcontainer
ENV SPLIT_QUEUE_NAME=split-requests

CMD ["python", "splitter_worker.py"]