# Logic Circuit Design - VHDL Project

## Overview
This project involves the **design and simulation of digital logic circuits** using **VHDL and Quartus Prime**. The circuits implement Boolean functions and are verified through **functional simulations**. The project is structured into three problems, each requiring a different circuit implementation, optimization, and simulation.

### Key Features
- **Design of Boolean functions using Karnaugh maps for SOP and POS representations**.
- **Implementation of logic circuits in VHDL**.
- **Simulation and verification using waveform analysis in Quartus Prime**.
- **Generation of RTL diagrams to confirm the correctness of implementations**.

---

## Project Structure
```
├── src/
│   ├── Cirquit1.vhd         # VHDL implementation for problem 1
│   ├── Cirquit2.vhd         # VHDL implementation for problem 2 (if applicable)
│   ├── Cirquit3.vhd         # VHDL implementation for problem 3
│   ├── Waveform.vwf         # Functional simulation waveform for validation
│   ├── Waveform1.vwf        # Additional waveform simulation files
│   ├── Waveform ask1.png    # Waveform output for problem 1
│   ├── Waveform ask2.png    # Waveform output for problem 2
│   ├── Waveform ask3.png    # Waveform output for problem 3
│
├── Project 1.pdf            # Project description and guidelines
├── Report.pdf               # Final report with implementations and results
│
├── README.md                # Project documentation
```

---

## Installation and Execution
### Prerequisites
- **Quartus Prime**
- **Basic knowledge of VHDL and digital logic design**.

### Steps to Run the Project
1. **Open Quartus Prime** and create a new project.
2. **Import the provided VHDL files** (`Cirquit1.vhd`, `Cirquit2.vhd`, etc.).
3. **Compile the project** to check for syntax errors.
4. **Run functional simulations** using the waveform files (`.vwf`).
5. **Verify the output waveforms** against expected results.
6. **Check RTL diagrams** to ensure correct circuit synthesis.

---

## Implementation Details
### **Problem 1: Boolean Function Implementation**
- **Given function:**
  ```
  F(x1,x2,x3,x4,x5) = Σm(0,2,6,10,11,15,16,26,27,30) + D(4,8,14,17,20,21,31)
  ```
- **Steps:**
  1. Use Karnaugh maps to determine **minimal-cost SOP and POS**.
  2. Implement the function in **VHDL using Quartus Prime**.
  3. **Simulate** the circuit and verify its correctness.
  4. Analyze **waveform outputs and RTL diagrams**.

### **Problem 2: Function with a Given Truth Table**
- **Given Condition:** Function outputs `1` when `x1=0` and exactly **two out of (x2,x3,x4) are 1**.
- **Steps:**
  1. Derive the function using **Karnaugh maps**.
  2. Implement the circuit using **schematic design in Quartus**.
  3. **Simulate and verify** using waveform outputs.

### **Problem 3: Waveform-Based Function Extraction**
- **Given:** A pre-defined waveform.
- **Steps:**
  1. Extract the function in **simplest Sum of Products (SOP) form**.
  2. Write a **VHDL implementation**.
  3. Simulate and compare results with the given waveform.
  4. Generate the **RTL schematic**.

---

## Simulation & Verification
### **Waveform Analysis**
- Each circuit is tested using a **functional simulation in Quartus Prime**.
- The simulation results are compared with expected outputs.
- **Waveform images (`Waveform ask1.png`, etc.)** showcase correct implementations.

### **RTL Diagrams**
- **RTL diagrams are generated** for structural verification.
- They confirm **logical correctness and hardware feasibility**.

---

## Example Waveform Output (Problem 1)
```
x1   x2   x3   x4   x5   |  F
--------------------------
0    0    0    0    0    |  1
0    0    0    1    0    |  1
0    0    1    1    0    |  1
...
```
- The function's output is verified against the provided truth table.
- The RTL diagram confirms the **correct synthesis** of the circuit.

---

## System Requirements
- **Quartus Prime Lite or Standard Edition**
- **Intel FPGA Board (Optional for physical testing)**

---

## Contributors
- **Developer:** [Maria Schoinaki](https://github.com/MariaSchoinaki)
- **Developer:** [Christos Stamoulos](https://github.com/ChristosStamoulos)