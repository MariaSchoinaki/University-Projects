# Sorting and Priority Queues - Bin Packing Problem

## Overview
This project implements the **Bin Packing Problem** using **sorting algorithms** and **priority queues**. The objective is to efficiently allocate large data files into storage disks of **1TB capacity each**, minimizing the number of disks used. 

### Key Features:
- **Implementation of Priority Queues (MaxPQ and MaxIntPQ)**
- **Sorting algorithms (HeapSort)**
- **Greedy and Greedy-Decreasing Algorithms** for disk allocation
- **Experimental evaluation** of algorithm performance

---

## Project Structure
```
├── src/
│   ├── Disk.java                         # Represents a storage disk with a 1TB capacity
│   ├── Greedy.java                       # Greedy algorithm for file allocation
│   ├── List.java                         # Custom linked list implementation
│   ├── MaxIntPQ.java                     # Priority Queue implementation for integers
│   ├── MaxPQ.java                        # Priority Queue implementation for Disk objects
│   ├── MerosDcompare.java                # Compares the performance of Greedy and Greedy-Decreasing
│   ├── MerosDcreate.java                 # Generates random test files for algorithm testing
│   ├── MerosDgreedydecreasing.java       # Implementation of the Greedy-Decreasing algorithm
│   ├── Node.java                         # Node implementation for linked lists
│   ├── Sort.java                         # Sorting implementation (HeapSort)
│
├── data/
│   ├── n100/                             # Test files with 100 folders each
│   ├── n500/                             # Test files with 500 folders each
│   ├── n1000/                            # Test files with 1000 folders each
│   ├── input.txt                         # Sample input file
│
├── Project 2.pdf                         # Original project description
├── Report.pdf                            # Analysis and results of experiments
├── README.md                             # Project documentation
```

---

## Installation and Execution
### Prerequisites
- **Java 8+** installed on your system
- A working **command-line interface (CLI)**

### Compilation
To compile the project, navigate to the `src/` directory and execute:
```sh
javac *.java
```

### Execution
#### Running the Greedy Algorithm:
```sh
java Greedy data/input.txt
```
#### Running the Greedy-Decreasing Algorithm:
```sh
java MerosDgreedydecreasing data/input.txt
```
#### Comparing the Two Algorithms:
```sh
java MerosDcompare
```

---

## Input File Format
Each input file (`input.txt`) consists of:
- **Each line:** A single integer representing a folder size in MB (0 - 1,000,000 MB)

**Example input file:**
```
200000
150000
700000
800000
100000
```

---

## Implementation Details

### **Greedy Algorithm**
- Processes folders **in order of appearance**.
- Stores each folder in the **disk with the most free space**.
- Uses a **Priority Queue (MaxPQ)** to efficiently select the best disk.
- **Time Complexity:** \(O(N \log N)\)

### **Greedy-Decreasing Algorithm**
- **Sorts the folders** in **descending order** before processing.
- Uses the same **Greedy logic** but on sorted data.
- **Optimizes disk usage** compared to the standard Greedy approach.
- **Time Complexity:** \(O(N \log N)\)

### **Sorting Algorithm (HeapSort)**
- Implemented in `Sort.java`
- Used in **Greedy-Decreasing Algorithm** to pre-sort folder sizes.
- **Time Complexity:** \(O(N \log N)\)

---

## System Requirements
- **Java Development Kit (JDK 8 or later)**

---

## Contributors
- **Developer:** [Maria Schoinaki](https://github.com/MariaSchoinaki)