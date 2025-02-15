# **Algorithms - Assignment 2**

## **Overview**
This assignment is part of the **Algorithms** course at the **Athens University of Economics and Business (AUEB)**. It involves problems related to **graph theory, dynamic programming, and optimization algorithms**.

---

## **Topics Covered**
### **1. Graph Theory & Guest Selection Problem**
- Designing a **polynomial-time algorithm** for selecting the maximum number of guests in a reception.
- Ensuring that each guest knows at least **4 other invitees** and does not know at least **4 other guests**.
- **Graph representation and adjacency matrices**.

### **2. Coin Change Problem (Knapsack Variation)**
- Given `n` different coin values and a target amount `E`, find the minimum number of coins needed to form `E`.
- **0-1 Knapsack dynamic programming approach**.
- Determining **impossible cases** where change cannot be made.

### **3. Road Trip Optimization (Minimizing Penalty)**
- Planning a car trip from `a0 = 0` to `an`, making strategic stops to **minimize penalty costs**.
- Defining a cost function where **traveling more or less than 40km incurs a penalty**.
- Implementing an **optimal dynamic programming solution**.

### **4. Chessboard Value Optimization (Greedy vs Dynamic Programming)**
- Selecting exactly **one cell per row** in an `n x n` chessboard where each cell has a value.
- Constraint: No two selected cells in consecutive rows can be from the same column.
- Comparing **greedy and dynamic programming approaches** to maximize total value.
- **Proving the optimality** of the DP approach and analyzing worst-case performance of the greedy method.

---

## **Project Structure**
```
├── Assignment 2.pdf         # Problem statement
├── Assignment 2-Solved.pdf  # Completed solutions
├── README.md                # Project documentation
```

---

## **Installation & Usage**
### **Prerequisites**
- Basic understanding of **graph algorithms, dynamic programming, and greedy methods**.
- Familiarity with **Knapsack problems, recurrence relations, and graph representations**.

---

## **Applications**
- **Graph Algorithms:** Modeling relationships using adjacency matrices.
- **Dynamic Programming:** Solving optimization problems efficiently.
- **Greedy vs DP Comparisons:** Understanding why DP provides optimal solutions in certain cases.
- **Knapsack-Type Problems:** Applying **subset sum** and **decision-based recursion** for computational efficiency.