# Java Programming Exercises - Object-Oriented & Algorithmic Problems

## Overview
This project contains multiple **Java exercises** focused on **object-oriented programming (OOP), mathematical calculations, and data structure manipulations**. The assignments cover **abstract classes, polymorphism, inheritance, arrays, and student record management**.

### Key Features
- **Mathematical Series Computation** (Squares Sum)
- **Election Results Analysis using 2D Arrays**
- **Polymorphism & Shape Drawing**
- **Animal Hierarchy with Abstract Classes**
- **Clock & AM/PM Clock Implementation**
- **Student Records Management System**

---

## Project Structure
```
├── src/
│   ├── App1.java               # Computes sum of squares using an abstract class
│   ├── App2.java               # Election results processing with a 2D array
│   ├── App3.java               # Demonstrates polymorphism with shapes
│   ├── App4.java               # Implements an Animal class hierarchy with abstract methods
│   ├── Clock.java              # Base digital clock implementation
│   ├── AMPMClock.java          # Extends Clock to support AM/PM formatting
│   ├── AMPMClockApp.java       # Runs a real-time ticking AM/PM digital clock
│   ├── Student.java            # Defines student attributes and methods
│   ├── StudentList.java        # Implements a list of students with insert and search operations
│   ├── StudentApp.java         # Provides a menu-driven interface for student records
│
├── Assignment 2.pdf            # Problem statement and requirements
│
├── README.md                   # Project documentation
```

---

## Installation and Execution
### Prerequisites
- **Java 8+** installed.
- A working **command-line interface (CLI)**.

### Compilation
To compile all Java files, navigate to the `src/` directory and run:
```sh
javac *.java
```

### Execution
Run the respective Java programs as needed:
```sh
java App1        # Sum of squares computation
java App2        # Election results analysis
java App3        # Shape polymorphism demonstration
java App4        # Animal hierarchy with abstract methods
java AMPMClockApp  # Digital AM/PM clock simulation
java StudentApp   # Student records management system
```

---

## Implementation Details
### **1. Sum of Squares Computation** (`App1.java`)
- Uses an **abstract class** `Akolou8ia` to compute `1^2 + 2^2 + ... + 100^2`.

### **2. Election Results Processing** (`App2.java`)
- Uses a **2D array** to store votes for three candidates across five districts.
- Computes total votes and determines the winner.

### **3. Shape Drawing with Polymorphism** (`App3.java`)
- Implements **polymorphism** with a `Shape` base class and `Circle`, `Square`, `Triangle` subclasses.
- Uses **random shape generation** and **dynamic method dispatch** for `draw()`.

### **4. Animal Hierarchy with Abstract Classes** (`App4.java`)
- Implements an **abstract class `Animal`** with subclasses `Dog` and `Cat`.
- Tracks total number of animals and implements the `speak()` method.

### **5. Digital Clock & AM/PM Clock** (`Clock.java`, `AMPMClock.java`, `AMPMClockApp.java`)
- **Clock.java**: Implements a digital clock with `tick()` functionality.
- **AMPMClock.java**: Extends `Clock` to support **AM/PM formatting**.
- **AMPMClockApp.java**: Simulates a **real-time clock** updating every second.

### **6. Student Records Management System** (`Student.java`, `StudentList.java`, `StudentApp.java`)
- **Student.java**: Stores student name, registration number, and grade.
- **StudentList.java**: Implements a **fixed-size student record list**.
- **StudentApp.java**: Provides a **menu-driven interface** for managing students.

---

## Example Outputs
### **Election Results Analysis** (`App2.java`)
```
Election Results:
-----------------
Candidate A: 887 votes
Candidate B: 176 votes
Candidate C: 1406 votes
Winner: Candidate C (over 50%)
```

### **Polymorphism - Shape Drawing** (`App3.java`)
```
Circle.draw()
Square.draw()
Triangle.draw()
Circle.draw()
...
```

### **Student Records Management** (`StudentApp.java`)
```
1. Insert Student
2. Lookup Student
3. Display List
0. Exit
Enter choice: 1
Enter student name: Maria
Enter register number: 3210191
Enter student's grade: 85
Student added successfully!
```

### **Digital Clock Simulation** (`AMPMClockApp.java`)
```
16:28:58
04:28:58 PM
04:29:00 PM
16:29:01
...
```

---

## System Requirements
- **Java Development Kit (JDK 8 or later)**

---

## Contributors
- **Developer:** [Maria Schoinaki](https://github.com/MariaSchoinaki)