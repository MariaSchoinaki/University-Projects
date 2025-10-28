# **Python Programming - Assignment 4**

## **Overview**
This assignment involves implementing **Conway's Game of Life**, a well-known cellular automaton. The assignment requires constructing a **grid-based simulation**, defining update rules, and implementing various functions to control the game's state. The objective is to reinforce knowledge of **data structures, dictionaries, recursion, and iterative processing**.

---

## **Topics Covered**
### **1. Board Representation**
- Constructing an **n×n board** using a dictionary where each cell is mapped to a boolean (`True` for alive, `False` for dead).

### **2. Board Operations**
- Implementing functions to **check if a cell is alive, update cell states, and determine the board size**.

### **3. Copying Board State**
- Creating a deep copy of the game board to track state changes between iterations.

### **4. Board Iteration & Display**
- Implementing an **iterator** that scans through the board row-wise.
- Printing the board using **Unicode characters** to visualize live and dead cells.

### **5. Neighborhood & Cell Updates**
- Determining the **neighbors of a given cell** and updating its state according to Conway’s rules:
  - A live cell with fewer than **2** neighbors dies.
  - A live cell with **2 or 3** neighbors survives.
  - A live cell with more than **3** neighbors dies.
  - A dead cell with exactly **3** live neighbors becomes alive.

### **6. Predefined Patterns**
- Implementing functions to **place a Blinker and a Glider**, two common patterns in Game of Life.

### **7. Game Progression**
- Advancing the game by one step, applying update rules to all cells.

### **8. Running a Simulation**
- Implementing a **loop to run 100 generations** of Game of Life, displaying the board after each step.

---

## **Project Structure**
```
├── Assignment 4.html          # Problem set description
├── Assignment 4-Solved.py     # Completed solutions to all exercises
├── README.md                  # Project documentation
```

---

## **Installation and Execution**
### **Prerequisites**
- **Python 3.x** installed.
- Familiarity with **dictionaries, iteration, and recursion**.

### **Running the Code**
To execute the simulation, open a terminal or command prompt and run:
```sh
python Assignment4-Solved.py
```

To test specific functions interactively:
```sh
python
>>> from Assignment4_Solved import function_name
>>> function_name(arguments)
```

---

## **Testing the Implementation**
The solutions contain **doctests** for validation. To run the tests:
```sh
python -m doctest -v Assignment4-Solved.py
```
This will output whether each function's expected and actual results match.

---

## **Applications**
- **Artificial Life Simulations**: Conway’s Game of Life demonstrates emergent behavior in **biological and artificial systems**.
- **Algorithm Development**: Understanding **grid-based simulations** and **cellular automata**.
- **Complex Systems Modeling**: Used in theoretical **mathematics, physics, and computer science**.

---

## Contributors
- **Developer:** [Maria Schoinaki](https://github.com/MariaSchoinaki)