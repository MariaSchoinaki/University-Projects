from azure.storage.queue import QueueClient
import os
import json
import uuid

AZURE_STORAGE_CONNECTION_STRING = "put_your_key_here"

QUEUE_NAME = "split-requests"

def send_split_request(blob_name):
    queue = QueueClient.from_connection_string(
        AZURE_STORAGE_CONNECTION_STRING,
        QUEUE_NAME
    )

    from azure.core.exceptions import ResourceExistsError

    try:
        queue.create_queue()
    except ResourceExistsError:
        pass


    payload = {
        "blob_name": blob_name,
        "request_id": str(uuid.uuid4()),
        "segment_seconds": 120,
        "input_container": "storagecontainer",
        "output_container": "outputcontainer"
    }

    queue.send_message(json.dumps(payload))
    print("Message sent to queue:", payload)

if __name__ == "__main__":
    send_split_request("test.mp4")