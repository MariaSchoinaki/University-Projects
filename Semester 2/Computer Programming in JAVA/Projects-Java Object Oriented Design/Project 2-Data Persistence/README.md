# Advanced Bank Commission Management System - Java

## Overview
This project implements an **Advanced Bank Commission Management System** in **Java**. The system manages **bank products**, including **loans and credit cards**, and tracks **sales transactions** performed by **bank employees**. The program calculates commissions based on **loan disbursement** and **credit card transactions** according to predefined commission structures.

### Key Features
- **Product Management:**
  - Loans with **interest rates** and **disbursed amounts**.
  - Credit cards with **transaction limits** and **commission percentages**.
- **Sales Management:**
  - Tracks **bank employees' sales** of loans and credit cards.
  - Allows **recording of transactions for credit card sales**.
- **Commission Calculation:**
  - **Loans:** Commission based on **loan amount and interest rate**.
  - **Credit Cards:** Commission based on **transaction volume**.
- **File I/O Operations:**
  - Saves and loads **products, salesmen, transactions, and commissions** from files.
- **Interactive Console-Based Menu:**
  - Allows **adding sellers, managing products, recording sales, and computing commissions**.

---

## Project Structure
```
├── src/
│   ├── mainApp.java            # Main application with an interactive menu
│   ├── CreateFile.java         # Handles file writing operations
│   ├── ReadFile.java           # Handles file reading operations
│   ├── ListaProduct.java       # Manages the list of bank products
│   ├── ListaPwlhths.java       # Manages the list of bank sellers
│   ├── Product.java            # Base class for all bank products
│   ├── Loan.java               # Defines loan properties and commission rules
│   ├── Card.java               # Defines credit card properties and transaction limits
│   ├── Pwlhths.java            # Represents bank sellers
│   ├── Pwlhsh.java             # Tracks sales of bank products
│   ├── Transaction.java        # Tracks credit card transactions
│
├── Project 2.pdf               # Project description
├── Project 2-Instructions.pdf  # Implementation guidelines
│
├── README.md                   # Project documentation
```

---

## Installation and Execution
### Prerequisites
- **Java 8+** installed.
- A working **command-line interface (CLI)**.

### Compilation
To compile the project, navigate to the `src/` directory and execute:
```sh
javac *.java
```

### Execution
Run the interactive bank commission system:
```sh
java mainApp
```

---

## Implementation Details
### **Bank Products**
- **Loans** (`Loan.java`):
  - Defined by **loan amount** and **interest rate**.
  - Commission is based on the total loan amount:
    | Loan Amount (€) | Commission Rate |
    |---------------|----------------|
    | ≤ 500,000    | 1%             |
    | 500,000 - 2,000,000 | 2% |
    | > 2,000,000  | 2.5%           |
  - Cannot exceed **annual interest earnings**.

- **Credit Cards** (`Card.java`):
  - Defined by **transaction limit**, **annual limit**, and **commission rate**.
  - Commission is calculated as:
    ```
    commission = transaction_value * commission_percentage
    ```

### **Sales Tracking and Commission Calculation**
- Each seller can **sell multiple bank products**.
- Each sale is linked to a **specific product** and **tracked for commission purposes**.
- **Commissions are calculated** based on:
  - The **total value of loans sold**.
  - The **total transaction volume** of credit cards sold.
- **Commission Summary:**
  - Can be displayed per **individual seller**.
  - Can be displayed for **all sellers in the bank**.
  - The **total commission earnings for the bank** can be computed.

### **File Storage and Retrieval**
The system **automatically saves data** into text files for:
- **Bank Products (`products.txt`)**
- **Sellers (`salesmen.txt`)**
- **Sales Records (`sales.txt`)**
- **Transactions (`transactions.txt`)**

---

## Interactive Menu Operations
Upon execution, the user can:
1. **Add a new seller.**
2. **Add a new bank product (Loan or Credit Card).**
3. **Record a sale of a bank product.**
4. **Record a transaction for a credit card.**
5. **View all loans.**
6. **Calculate commission for a specific seller.**
7. **View all credit card transactions for a seller.**
8. **Calculate commissions for all sellers.**
9. **Display total commission earnings.**
10. **Save all data to text files.**
11. **Exit the program.**

---

## Example Execution
### Adding a New Loan
```
Enter product code: 105
Enter customer number: 34879
Enter customer AFM: 987654321
Enter loan amount: 200000
Enter interest rate: 0.025
Loan added successfully.
```

### Recording a Sale
```
Select a seller: 
1. Maria
2. George
3. Chris
Select seller (1-3): 2
Select a product to sell: 
1. Loan - €200,000
2. Credit Card - 1.5% commission
Enter product selection: 1
Enter sale justification: Business Loan
Sale recorded.
```

### Calculating Seller Commission
```
Enter seller number: 2
Loan Commission: €4,000
Credit Card Commission: €350
Total Seller Commission: €4,350
```

---

## System Requirements
- **Java Development Kit (JDK 8 or later)**

---

## Contributors
- **Developer:** [Maria Schoinaki](https://github.com/MariaSchoinaki)
- **Developer:** [Christos Stamoulos](https://github.com/ChristosStamoulos)