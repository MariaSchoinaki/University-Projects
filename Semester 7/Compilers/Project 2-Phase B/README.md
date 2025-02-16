# **MiniPython Compiler - Semantic Analysis & Syntax Checking**

## **Overview**
This project involves implementing a **semantic analysis phase** for a MiniPython compiler. The goal is to detect **undeclared variables, incorrect function calls, type mismatches, and other semantic errors** through an Abstract Syntax Tree (AST) traversal using **visitor pattern-based symbol table management**.

## **Key Topics Covered**
- **Symbol Table Management**
  - `methodTable` for storing function declarations
  - `variableTable` for tracking variable assignments
- **Semantic Analysis & Error Detection**
  - Detecting **use of undeclared variables**
  - Checking **function call correctness**
  - Enforcing **data type consistency**
- **Abstract Syntax Tree (AST) Traversal**
  - Implementing **visitor patterns** for AST node handling
  - Using **depth-first traversal** for semantic validation

## **Project Breakdown**
### **1. Symbol Table Construction**
- **Visitor 1 (tableFillVisitor.java)**:
  - Populates `methodTable` and `variableTable`
  - Ensures function and variable declarations are stored properly
- **Visitor 2 (Visitor.java)**:
  - Conducts **semantic checks** using the pre-filled symbol tables
  - Flags **undeclared identifiers and incorrect expressions**

### **2. Semantic Error Detection**
- **Undeclared Variable Usage**:
  - Detected in `caseAIdentifier()` by checking `variableTable`
- **Function Call Errors**:
  - Checked in `caseAFunctionCall()`, ensuring functions exist in `methodTable`
- **Incorrect Function Arguments**:
  - Validated against method signatures stored in `methodTable`
- **Type Checking for Expressions**:
  - Ensured in `caseAAdditionExpression()` and `caseASubtractionExpression()`
- **Invalid Operations on None Values**:
  - Tracked using a stack-based approach (`operatorStack`)

### **3. Handling Function Overloading & Recursive Calls**
- **Function Re-declaration with Identical Arguments**:
  - Checked using **encoded function signatures** (`arg_count + function_name`)
- **Recursive Function Call Analysis**:
  - Stored in `stackFunctions` to track function calls and return types

---

## **Tools & Technologies Used**
- **Java** for implementing the compiler visitors
- **ANTLR / SableCC** for parsing MiniPython code
- **Data Structures (HashMaps, Stacks)** for symbol tables

---

## **Project Team**
- **Giorgos Andritsos**
- **Christos Stamoulos**
- **Anthippi Fatsea**
- **Maria Schoinaki**