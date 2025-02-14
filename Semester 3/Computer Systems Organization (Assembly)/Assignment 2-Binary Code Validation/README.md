# MIPS32 String Processing - Binary Code Validation

## Overview
This project implements a **binary code validator** in **MIPS32 assembly language**. The program reads an input string and determines whether it contains a **syntactically correct code**, which is defined as:
- A `#` symbol followed by exactly **four binary digits (0 or 1)**.

If a valid code is found, the program outputs **"ok"**; otherwise, it outputs **"not ok"**.

---

## Project Structure
```
├── Code.s                 # MIPS assembly implementation of the string validator
│
├── Assignment 2.pdf       # Project description and requirements
│
├── README.md              # Project documentation
```

---

## Installation and Execution
### Prerequisites
- **SPIM or QtSPIM MIPS Simulator** installed.
- Basic knowledge of **MIPS assembly programming**.

### Running the Program
1. Open **SPIM** or **QtSPIM**.
2. Load the `Code.s` file.
3. Click `Run` to execute the program.

---

## Input Format
The program reads an **ASCII string** from standard input.
- It scans the string until it finds the `#` character.
- It then checks the next **four characters**.
- If they are all `0` or `1`, the program outputs **"ok"**.
- Otherwise, it outputs **"not ok"**.

### Example Interactions
#### **Valid Cases**
```
Input: #0101
Output: ok

Input: abtv4h#0101
Output: ok

Input: abtv4h#1110k2s
Output: ok
```

#### **Invalid Cases**
```
Input: abtv4h#1131k2s
Output: not ok

Input: abtv4hm0101k2s
Output: not ok

Input: abtv4h#010
Output: not ok

Input: abtv4h#b0101
Output: not ok
```

---

## Implementation Details
- The program **iterates through the string** character by character.
- It **ignores all characters until `#` is found**.
- It then **validates the next four characters**.
- **Remaining characters are ignored** after the check.
- Uses **MIPS system calls** to read user input and print results.

---

## System Requirements
- **SPIM or QtSPIM MIPS Simulator**.
- Compatible with **MIPS32 architecture**.

---

## Contributors
- **Developer:** [Maria Schoinaki](https://github.com/MariaSchoinaki)