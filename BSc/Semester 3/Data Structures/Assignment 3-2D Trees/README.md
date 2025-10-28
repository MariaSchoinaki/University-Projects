# 2D Tree Data Structure - Range and Nearest Neighbor Search

## Overview
This project implements **2D Trees**, a **binary search tree** variant for **2D point-based data**. The main operations include:
- **Range Search:** Finding all points within a given rectangle.
- **Nearest Neighbor Search:** Locating the closest point to a given query point.

This data structure is useful in **computer vision, spatial databases, and 2D collision detection in video games**.

---
## Project Structure
```
├── src/
│   ├── Point.java           # 2D Point representation
│   ├── Rectangle.java       # Axis-aligned bounding rectangle
│   ├── TreeNode.java        # Tree node for the 2D Tree
│   ├── TwoDTree.java        # Implementation of the 2D Tree and main operations
│   ├── List.java            # Linked list implementation
│   ├── Node.java            # Node for the linked list
│
├── ├── input.txt            # Sample input file with 2D points
│
├── Project 3.pdf            # Project description
├── Report.pdf               # Analysis and findings
├── README.md                # Project documentation
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
Run the program with an input file:
```sh
java TwoDTree data/input.txt
```

---
## Input File Format
The input file consists of:
- **First line:** Number of points (N)
- **Following N lines:** Each line contains an `(x, y)` coordinate

**Example input file:**
```
5
30 50
15 97
0 36
100 0
100 40
```

---
## Implementation Details
### **Data Structures Implemented**
- `Point.java`: Represents a **2D point** and supports distance calculations.
- `Rectangle.java`: Represents **axis-aligned bounding rectangles** used for range search.
- `TreeNode.java`: Defines **nodes** of the 2D tree.
- `TwoDTree.java`: Implements the **2D Tree** with operations:
  - **Insert**: Adds a new point.
  - **Search**: Checks if a point exists.
  - **Range Search**: Finds points inside a given rectangle.
  - **Nearest Neighbor Search**: Locates the closest point to a query point.

### **2D Tree Construction**
- Each **TreeNode** stores a **2D point**.
- The **tree alternates** between **x and y** coordinates at each level.
- If a point’s **x-coordinate** is smaller than the root’s, it goes to the left subtree; otherwise, it goes to the right.
- On the next level, the **y-coordinate** is used for comparison.
- This alternation ensures a **balanced spatial partitioning**.

### **Search Operations**
- **Range Search**
  - Given a rectangle `[xmin, xmax] × [ymin, ymax]`, retrieve all points inside it.
  - Uses a recursive approach to efficiently prune irrelevant branches.
  
- **Nearest Neighbor Search**
  - Finds the closest point to a given query point `(x, y)`.
  - Utilizes a **recursive backtracking approach** to eliminate unnecessary regions and optimize search time.
  - Instead of checking all \(N\) points (brute force), the **2D tree provides an optimized approach**.

### **Time Complexity**
| Operation              | Worst-Case Complexity |
|-----------------------|----------------------|
| Insert               | \(O(N \log N)\) |
| Search               | \(O(\log N)\) |
| Range Search         | \(O(\sqrt{N} + k)\) (where \(k\) is the number of reported points) |
| Nearest Neighbor     | \(O(\log N)\) (average case) |

---
## System Requirements
- **Java Development Kit (JDK 8 or later)**

---
## Contributors
- **Developer:** [Maria Schoinaki](https://github.com/MariaSchoinaki)