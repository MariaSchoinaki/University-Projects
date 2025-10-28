# MIPS32 Arithmetic Expression Evaluator

## Overview
This project implements an **arithmetic expression evaluator** in **MIPS32 assembly language**. The program reads a mathematical expression from user input and computes the result, following **left-to-right evaluation without operator precedence**.

### Key Features
- **Supports integer operations**: Addition (`+`), Subtraction (`-`), Multiplication (`*`), Division (`/`), and Modulus (`%`).
- **Handles signed decimal integer inputs**.
- **Interactive loop execution**: The user can compute multiple expressions continuously.
- **Error handling**:
  - **Invalid operators** trigger an error message.
  - **Division by zero** is prevented with an appropriate error message.
- **Runs on the SPIM MIPS Simulator**.

---

## Project Structure
```
├── Code.s                 # MIPS assembly implementation of the calculator
│
├── Assignment 1.pdf       # Project description and requirements
│
├── README.md              # Project documentation
```

---

## Installation and Execution
### Prerequisites
- **SPIM MIPS Simulator** installed on your system.
- Basic understanding of **MIPS assembly programming**.

### Running the Program
To execute the MIPS assembly program:
1. Open SPIM or QtSPIM.
2. Load the `Code.s` file.
3. Click `Run` to execute the program.

---

## Input Format
The program prompts the user for input in sequential order:
1. Enter the first number.
2. Enter an operator (`+`, `-`, `*`, `/`, `%`, `=`).
3. Enter the next number.
4. Repeat until the `=` operator is entered, signaling expression evaluation.

### Example Interaction
```
Number: 10
Operator: +
Number: 5
Operator: -
Number: -5
Operator: =
The result is: 20
Do you want to continue with a new expression? (y/n) y
```

---

## Implementation Details
- The program maintains a **running total** of the computed expression.
- Operators are processed in **left-to-right order** (no precedence rules).
- When `=` is entered, the final result is displayed.
- **Error checks** ensure:
  - Operators are valid.
  - Division and modulus operations do not involve zero.
  
### Error Handling
#### **Invalid Operator**
```
Number: 10
Operator: 2
Error: Invalid operator.
```
#### **Divide by Zero**
```
Number: 10
Operator: /
Number: 0
Error: Divide by zero.
```

---

## System Requirements
- **SPIM or QtSPIM MIPS Simulator**.
- Compatible with **MIPS32 architecture**.

---

## Contributors
- **Developer:** [Maria Schoinaki](https://github.com/MariaSchoinaki)