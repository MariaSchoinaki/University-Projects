# Java Programming Assignments

## Overview
This project consists of multiple Java programming assignments designed to enhance understanding of **basic and intermediate Java programming concepts**, including **loops, recursion, file handling, OOP principles, and mathematical operations**.

### Key Features
- **Factorial Calculation (Iterative and Static Methods)**
- **Basic Statistics Computation (Average, Max, Min, Positive & Negative Count)**
- **Quadratic Equation Solver**
- **Fibonacci Sequence Checker**
- **Digital Clock Simulation**
- **Bank Account Management System**

---

## Project Structure
```
├── src/
│   ├── App1.java             # Factorial computation using an instance method
│   ├── App1_2.java           # Factorial computation using a static method
│   ├── App2.java             # Reads integers and computes statistical data
│   ├── App3.java             # Solves quadratic equations
│   ├── App4.java             # Checks if a number belongs to the Fibonacci sequence
│   ├── Clock.java            # Digital clock implementation
│   ├── clockApp.java         # Simulates a real-time ticking clock
│   ├── Account.java          # Bank account class with deposit/withdrawal methods
│   ├── bankApp.java          # Simulates a banking system with account management
│
├── Assignment 1.pdf          # Detailed problem statements for all tasks
│
├── README.md                 # Project documentation
```

---

## Installation and Execution
### Prerequisites
- **Java 8+** installed on your system.
- A working **command-line interface (CLI)**.

### Compilation
Navigate to the `src/` directory and compile all Java files:
```sh
javac *.java
```

### Execution
Run the respective Java applications:
```sh
java App1        # Factorial computation (instance method)
java App1_2      # Factorial computation (static method)
java App2        # Computes basic statistics
java App3        # Solves quadratic equations
java App4 21     # Checks if 21 is a Fibonacci number
java clockApp    # Runs the digital clock simulation
java bankApp     # Runs the banking system simulation
```

---

## Implementation Details
### **1. Factorial Calculation** (`App1.java`, `App1_2.java`)
- Computes the **factorial** of a user-provided number.
- `App1.java` uses an **instance method**, whereas `App1_2.java` uses a **static method**.

### **2. Integer Statistics Computation** (`App2.java`)
- Reads **positive and negative integers** from user input.
- Computes:
  - **Total count of numbers**
  - **Average value**
  - **Positive & Negative counts**
  - **Maximum & Minimum values**
- Input stops when `0` is entered.

### **3. Quadratic Equation Solver** (`App3.java`)
- Computes the **real roots** of a quadratic equation using:
  ```
  x = (-b ± sqrt(b² - 4ac)) / (2a)
  ```
- Handles cases where **no real solutions exist**.

### **4. Fibonacci Sequence Checker** (`App4.java`)
- Checks whether a **given number** is a Fibonacci number.
- Accepts input via **command-line arguments**.

### **5. Digital Clock Simulation** (`Clock.java`, `clockApp.java`)
- **Clock.java**:
  - Implements an **OOP-based digital clock**.
  - Methods to **set time**, **increment time** (`tick()`), and **return formatted time (`toString()`)**.
- **clockApp.java**:
  - Simulates a real-time ticking clock, updating every second.

### **6. Bank Account Management System** (`Account.java`, `bankApp.java`)
- **Account.java**:
  - Models a **bank account** with deposit, withdrawal, and interest calculation.
  - Supports **error handling for invalid transactions**.
- **bankApp.java**:
  - Creates three **bank accounts**.
  - Executes **deposits, withdrawals, and interest updates**.

---

## Example Outputs
### **Factorial Calculation** (`App1.java` / `App1_2.java`)
```
Enter a number:
5
120
```

### **Quadratic Equation Solver** (`App3.java`)
```
Enter coefficients a, b, c:
1 5 6
The first solution is: -2.000
The second solution is: -3.000
```

### **Digital Clock Simulation** (`clockApp.java`)
```
16:28:58
16:28:59
16:29:00
...
```

### **Bank Account Management System** (`bankApp.java`)
```
New accounts

Account Number: 100-00
Name: Togantzi Maria
Balance: 188.46

Account Number: 100-01
Name: Kalergis Christos
Balance: 140.21

Deposit @ Account 100-01
Balance: 140.21
Requested: 500.10
New Balance: 640.31
```

---

## System Requirements
- **Java Development Kit (JDK 8 or later)**

---

## Contributors
- **Developer:** [Maria Schoinaki](https://github.com/MariaSchoinaki)