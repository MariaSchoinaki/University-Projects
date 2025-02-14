# Labyrinth Solver - Java Implementation

## Overview
This project is an implementation of a **Labyrinth Solver** using **Stacks and Queues** in Java. The program reads a maze from an input file and determines whether an exit exists using **Depth-First Search (DFS)**.

### Key Features
- Reads a **maze structure** from an external text file.
- Implements **Stack (LIFO) and Queue (FIFO) abstract data types**.
- Uses a **stack-based approach** to explore and navigate the maze.
- Handles **invalid input cases** and provides meaningful error messages.
- Outputs the **exit coordinates** if an exit exists, or an appropriate message if none is found.

---
## Project Structure
```
├── Part 1/
│   ├── Node.java                # Node implementation for linked lists
│   ├── StringQueue.java         # Queue interface (FIFO)
│   ├── StringQueueImpl.java     # Queue implementation using linked list
│   ├── StringStack.java         # Stack interface (LIFO)
│   ├── StringStackImpl.java     # Stack implementation using linked list
│
├── Part 2/
│   ├── file.txt                 # Sample maze input file
│   ├── Node.java                # Node implementation
│   ├── Point.java               # Point representation for coordinates
│   ├── StringStack.java         # Stack interface
│   ├── StringStackImpl.java     # Stack implementation
│   ├── Thiseas.java             # Main execution class
│
├── Part 3/
│   ├── Node.java                # Node class
│   ├── StringQueue.java         # Queue interface
│   ├── StringQueueWithOnePointer.java  # Optimized Queue implementation
│
├── Project 1.pdf                # Project description and guidelines
├── Report.pdf                   # Final report with analysis and findings
├── README.md                    # Project documentation
```

---
## Installation and Execution
### Compilation
Ensure that Java is installed, then compile the project using:
```sh
javac Part1/*.java Part2/*.java Part3/*.java
```

### Execution
Run the program using the following command:
```sh
java Part2.Thiseas file.txt
```
Replace `file.txt` with a valid input file containing a maze.

---
## Input File Format
The input file must be formatted as follows:
- **First line:** `rows cols` (number of rows and columns in the maze)
- **Second line:** `start_x start_y` (coordinates of the entrance 'E')
- **Subsequent lines:** The maze itself, using:
  - `0` for open paths.
  - `1` for walls.
  - `E` for the entrance point.

### Example Input File
```
9 7
0 3
1 1 1 E 1 1 1
1 1 1 0 1 1 1
1 0 0 0 1 0 1
1 0 1 0 1 0 0
1 1 1 0 1 1 1
1 0 0 0 0 0 1
1 0 1 1 1 0 1
1 0 1 1 0 0 1
0 1 1 1 0 1 1
```

---
## Implementation Details
### Data Structures Used
- `StringStackImpl.java`: Implements a **stack** using a **singly linked list**.
- `StringQueueWithOnePointer.java`: Implements a **queue** using a **circular linked list** with a single pointer.

### Labyrinth Solver (Thiseas.java)
- Reads and processes the **maze input** from a file.
- Uses **Depth-First Search (DFS)** via a **stack-based approach**.
- Determines if an **exit exists**, and if found, outputs the **exit coordinates**.
- If **no valid path is found**, provides an appropriate message.

---
## System Requirements
- **Java Development Kit (JDK 8 or later)**

---
## Contributors
- **Developer:** [Maria Schoinaki](https://github.com/MariaSchoinaki)