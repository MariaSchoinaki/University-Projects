# LRU Cache Implementation - Java

## Overview
This project implements an **LRU (Least Recently Used) Cache** in Java. The cache is designed to optimize **lookup and storage operations** using a combination of **hashing** and **doubly linked lists**. The implementation follows the principles of **efficient caching** used in **CPU caches, databases, and web browsers**.

### Key Features
- **Efficient data retrieval** using hashing.
- **Least Recently Used (LRU) eviction policy** to remove the least accessed elements.
- **Cache performance evaluation** using hit/miss statistics.
- **Support for customizable cache sizes.**

---

## Project Structure
```
├── src/
│   ├── Cache.java               # Interface defining the cache structure
│   ├── DataSource.java          # Simulates an external data source
│   ├── Hashing.java             # Implements a hash table for fast key lookup
│   ├── Node.java                # Represents a doubly linked list node
│   ├── Tcache.java              # Implements the LRU Cache using hashing & linked list
│   ├── TestCacheSpeed.java      # Benchmarks cache performance
│   ├── WorkloadReader.java      # Reads request workloads
│
├── datasets/
│   ├── dataset-1000/            # Test dataset with 1000 items
│   ├── dataset-5000/            # Test dataset with 5000 items
│
├── Project 4.pdf                # Project description
├── Report.pdf                   # Analysis and performance evaluation
├── output.txt                   # Contains performance results
├── README.md                    # Project documentation
```

---

## Installation and Execution
### Prerequisites
- **Java 8+** installed on your system.
- A working **command-line interface (CLI)**.

### Compilation
To compile the project, navigate to the `src/` directory and execute:
```sh
javac *.java
```

### Execution
Run the cache performance test with a dataset:
```sh
java TestCacheSpeed
```

---

## Input File Format
The input dataset consists of key-value pairs stored in a text file.
- **Each line contains:** `<key> \t <value>`
- The workload file contains a sequence of **keys** to be requested from the cache.

---

## Implementation Details
### **LRU Cache (Tcache.java)**
- Implements **Cache<K, V> interface**.
- Uses **a hash table for quick key lookup**.
- Stores elements in a **doubly linked list** to track usage order.
- Removes the **least recently used (LRU) element** when full.

### **Hashing (Hashing.java)**
- Provides fast **O(1) key lookup**.
- Uses a **simple hash function** to distribute items across a fixed-size table.
- Implements **insertion, retrieval, and deletion** operations efficiently.

### **Performance Testing (TestCacheSpeed.java)**
- Simulates **real-world cache usage scenarios**.
- Tracks cache **hit/miss ratios**.
- Evaluates performance on **different dataset sizes**.

---

## Sample Results
The `output.txt` file logs performance statistics:
```
dataset: dataset-1000/data-1000.dat
requests: dataset-1000/requests-10000.dat
cache size: 100 items (10% of all items)
---------------------------------------------------
Read 10000 items in XXXX ms
Stats: lookups 10000, hits 1030, hit-ratio 0.103000
```

---

## System Requirements
- **Java Development Kit (JDK 8 or later)**

---

## Contributors
- **Developer:** [Maria Schoinaki](https://github.com/MariaSchoinaki)