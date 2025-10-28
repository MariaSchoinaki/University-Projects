# Arithmetic Logic Unit (ALU) Design - VHDL Project

## Overview
This project involves the **hierarchical design and implementation of an Arithmetic Logic Unit (ALU) using VHDL in Quartus Prime**. The ALU is capable of performing both **arithmetic operations (addition, subtraction)** and **logical operations (AND, OR, XOR, NOR, NAND)** on **16-bit signals**.

### Key Features
- **1-bit ALU Implementation** as a fundamental building block.
- **16-bit ALU Construction** using multiple 1-bit ALUs in a hierarchical structure.
- **Arithmetic Operations:**
  - Addition (**ADD**) with overflow detection.
  - Subtraction (**SUB**) using Two’s Complement.
- **Logical Operations:**
  - AND, OR, XOR, NOR, NAND.
- **Hierarchical Design:**
  - The **1-bit ALU** is first implemented separately.
  - The **16-bit ALU** is built using multiple 1-bit ALUs connected in a ripple carry manner.
- **Simulation & Testing in Quartus Prime** using waveform analysis.

---

## Project Structure
```
├── src/
│   ├── Project2.vhd           # VHDL implementation of the ALU
│   ├── Project2B.vhd          # Hierarchical 16-bit ALU using 1-bit ALU components
│   ├── Project2.qpf           # Quartus Prime project file
│   ├── Project2.qsf           # Quartus settings file
│   ├── Project2.qws           # Quartus workspace file
│   ├── Waveform.vwf           # Functional simulation waveform for verification
│   ├── Waveform1.vwf          # Additional simulation waveform file
│
├── Project 2.pdf              # Project guidelines and description
├── Report.pdf                 # Final report with implementation details
│
├── README.md                  # Project documentation
```

---

## Installation and Execution
### Prerequisites
- **Quartus Prime (Lite or Standard Edition)** installed.
- Basic knowledge of **VHDL and Digital Logic Design**.

### Steps to Run the Project
1. **Open Quartus Prime** and create a new project.
2. **Import the provided VHDL files** (`Project2.vhd`, `Project2B.vhd`).
3. **Compile the project** to check for syntax errors.
4. **Run functional simulations** using the waveform files (`Waveform.vwf`).
5. **Verify the output waveforms** against expected results.
6. **Analyze the RTL diagram** for circuit correctness.

---

## Implementation Details
### **1. 1-bit ALU Implementation** (`Project2.vhd`)
- The 1-bit ALU is implemented with a **Multiplexer-based structure**.
- It performs basic **AND, OR, ADD, SUB, NAND, NOR, XOR** operations.
- The addition and subtraction functions are implemented using a **full adder circuit**.
- The **Multiplexer selects** the operation based on control signals.

### **2. 16-bit ALU Implementation** (`Project2B.vhd`)
- The **16-bit ALU** is designed by connecting **16 instances of the 1-bit ALU**.
- Uses a **Ripple Carry Adder (RCA)** architecture for multi-bit addition/subtraction.
- Includes an **overflow detection mechanism**.
- The **control unit** selects the required operation based on an opcode.

### **Control Unit for ALU Operations**
| Operation | Opcode | Ainvert | Binvert | CarryIn |
|-----------|--------|---------|---------|---------|
| AND       | 000    | 0       | 0       | 0       |
| OR        | 001    | 0       | 0       | 0       |
| ADD       | 010    | 0       | 0       | 0       |
| SUB       | 011    | 0       | 1       | 1       |
| NOR       | 100    | 1       | 1       | 0       |
| NAND      | 101    | 1       | 1       | 0       |
| XOR       | 110    | 0       | 0       | 0       |

---

## Simulation & Verification
### **Waveform Analysis**
- The ALU design is tested using **functional simulations** in Quartus Prime.
- **Waveform outputs (`Waveform.vwf`) confirm the expected behavior**.
- Each ALU operation is tested against different input values to verify correctness.

### **RTL Diagrams**
- The RTL diagram confirms that the ALU is correctly synthesized with **hierarchical components**.
- The **1-bit ALU is reused as a component** to construct the 16-bit ALU efficiently.

---

## Example Outputs
### **Arithmetic Operations**
#### **Addition (OPCODE = 010)**
```
A = 0010011100011100
B = 0110110011001101
A + B = 1001001111101001  (With Overflow Check)
```

#### **Subtraction (OPCODE = 011)**
```
A = 0010011100011100
B = 0110110011001101
A - B = 1011101001001111
```

### **Logical Operations**
#### **AND Operation (OPCODE = 000)**
```
A = 0101010101010001
B = 1111010101010001
A AND B = 0101010101010001
```

#### **OR Operation (OPCODE = 001)**
```
A = 0101010101010001
B = 1111010101010001
A OR B = 1111010101010001
```

#### **XOR Operation (OPCODE = 110)**
```
A = 1001001100001010
B = 1011001100101101
A XOR B = 0010000000100111
```

---

## System Requirements
- **Quartus Prime Lite or Standard Edition**

---

## Contributors
- **Developer:** [Maria Schoinaki](https://github.com/MariaSchoinaki)
- **Developer:** [Christos Stamoulos](https://github.com/ChristosStamoulos)