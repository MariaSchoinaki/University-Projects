# MIPS32 Postfix Expression Evaluator

## Overview
This project implements a **Postfix Expression Evaluator** in **MIPS32 assembly language**. The program reads a **postfix (Reverse Polish Notation, RPN) expression**, evaluates it using a **stack-based approach**, and prints the result. 

Postfix notation eliminates the need for parentheses, making evaluation efficient using a **Last-In-First-Out (LIFO) stack**.

---

## Project Structure
```
├── Code.s                 # MIPS assembly implementation of the Postfix Evaluator
│
├── Assignment 3.pdf       # Project description and requirements
│
├── README.md              # Project documentation
```

---

## Installation and Execution
### Prerequisites
- **SPIM or QtSPIM MIPS Simulator** installed.
- Basic understanding of **MIPS assembly programming**.

### Running the Program
1. Open **SPIM** or **QtSPIM**.
2. Load the `Code.s` file.
3. Click `Run` to execute the program.

---

## Input Format
- The input expression is entered in **postfix notation**.
- Operands and operators are **separated by spaces**.
- The expression ends with the `=` character.
- Supported operators: `+`, `-`, `*`, `/`.
- Operands are **unsigned integers**.

### Example Inputs and Outputs
#### **Valid Case**
```
Input: 5 1 2 + 4 * + 3 - =
Output: 14
```

#### **Invalid Case (Incorrect Expression)**
```
Input: 5 1 + * 3 =
Output: Invalid Postfix
```

#### **Divide by Zero Case**
```
Input: 10 0 / =
Output: Error: Divide by zero
```

---

## Implementation Details
### **Stack-Based Computation**
- **Operands are pushed** onto the stack.
- **Operators pop** two operands, compute the result, and push it back.
- If the stack contains a **single value at the end**, it is the final result.
- If more values remain, the input was incorrect.

### **Operations Implemented**
| Operator | Operation Performed |
|----------|---------------------|
| `+` | Addition |
| `-` | Subtraction |
| `*` | Multiplication |
| `/` | Division (with zero-check) |

### **Error Handling**
- **Invalid expressions** (e.g., missing operands) trigger an error.
- **Division by zero** is detected and reported.
- **Operands are restricted to valid unsigned integers**.

---

## System Requirements
- **SPIM or QtSPIM MIPS Simulator**.
- Compatible with **MIPS32 architecture**.

---

## Contributors
- **Developer:** [Maria Schoinaki](https://github.com/MariaSchoinaki)