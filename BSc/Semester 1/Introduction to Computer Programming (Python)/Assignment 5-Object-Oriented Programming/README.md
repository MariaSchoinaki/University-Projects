# **Python Programming - Assignment 5**

## **Overview**
This assignment focuses on implementing **advanced counter systems** and **digital clock simulations** in Python. It explores the use of **class-based design, inheritance, modular programming, and object-oriented principles** to develop different types of counters and clocks.

---

## **Topics Covered**
### **1. Roman Numeral Clock**
- Implementing a **Roman numeral counter** using a custom class.
- Converting standard numerical time representations into **Roman numerals**.

### **2. Day Counter**
- Creating a **CyclicCounter** that tracks days of the week.
- Implementing a class to increment the day based on clock transitions.

### **3. Clock with Day Display**
- Extending a digital clock to include a **day tracker**.
- Implementing a **Cascade Counter mechanism** to update days when time cycles past midnight.

---

## **Project Structure**
```
├── Assignment 5-Solved.py     # Completed solutions to all exercises
├── clock.py                   # Core clock and counter implementations
├── README.md                  # Project documentation
```

---

## **Installation and Execution**
### **Prerequisites**
- **Python 3.x** installed.
- Understanding of **object-oriented programming, class inheritance, and modular programming**.

### **Running the Code**
To execute the clock simulation, open a terminal or command prompt and run:
```sh
python Assignment5-Solved.py
```

To test specific functions interactively:
```sh
python
>>> from Assignment5_Solved import class_name
>>> instance = class_name(arguments)
>>> print(instance)
```

---

## **Testing the Implementation**
The solutions contain **doctests** for validation. To run the tests:
```sh
python -m doctest -v Assignment5-Solved.py
```
This will output whether each function's expected and actual results match.

---

## **Applications**
- **Software Development:** Implementing **event-driven counters**.
- **Time Simulation:** Understanding **clock mechanics and modular counter interactions**.

---

## Contributors
- **Developer:** [Maria Schoinaki](https://github.com/MariaSchoinaki)