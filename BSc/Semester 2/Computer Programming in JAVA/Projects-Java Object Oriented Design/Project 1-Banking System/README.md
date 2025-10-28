# Bank Commission Management System - Java

## Overview
This project implements a **Bank Commission Management System** in **Java**. The system handles **bank products**, including **loans and credit cards**, and calculates commissions for **bank sellers**. The commission structure follows predefined rules based on the total value of **loans** and **credit card transactions**.

### Key Features
- **Bank Product Management:**
  - Loans with interest rates.
  - Credit cards with transaction limits and commission percentages.
- **Seller Management:**
  - Tracks each seller’s transactions and commissions.
- **Commission Calculation:**
  - Based on **loan sales** and **credit card transactions**.
  - Implements **loan commission rules** with percentage tiers.
  - Computes **credit card commissions** based on transaction values.
- **Interactive Menu System:**
  - Allows users to **add new sellers, bank products, sales, and transactions**.
  - Computes and displays commissions per seller and bank-wide.

---

## Project Structure
```
├── src/
│   ├── mainApp.java             # Main program with interactive menu
│   ├── ListaProduct.java        # Manages the list of bank products
│   ├── ListaPwlhths.java        # Manages the list of bank sellers
│   ├── Product.java             # Base class for bank products
│   ├── Loan.java                # Represents loans with interest rates
│   ├── Card.java                # Represents credit cards with transaction limits
│   ├── Pwlhths.java             # Represents bank sellers
│   ├── Pwlhsh.java              # Tracks sales of bank products
│   ├── Transaction.java         # Tracks credit card transactions
│
├── Project 1.pdf                # Project description
├── Project 1-Instructions.pdf   # Implementation guidelines
├── README.md                    # Project documentation
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
  - Commission is a percentage of the total loan amount:
    | Loan Amount (€) | Commission Rate |
    |---------------|----------------|
    | ≤ 500,000    | 1%             |
    | 500,000 - 2,000,000 | 2% |
    | > 2,000,000  | 2.5%           |
  - Cannot exceed **annual interest amount**.

- **Credit Cards** (`Card.java`):
  - Defined by **transaction limit**, **annual limit**, and **commission rate**.
  - Commission is calculated as:
    ```
    commission = transaction_value * commission_percentage
    ```

### **Seller Commission Calculation**
- Commission = **Loan commissions + Credit Card commissions**.
- Can be calculated **per seller** or **for the entire bank**.

### **Interactive Menu Operations**
1. **Add a new seller.**
2. **Add a new bank product (Loan or Credit Card).**
3. **Record a sale of a bank product.**
4. **Add a credit card transaction.**
5. **View all loans.**
6. **Calculate commission for a specific seller.**
7. **View all credit card transactions for a seller.**
8. **Calculate commissions for all sellers.**
9. **Display total commission earnings.**
10. **Exit the program.**

---

## Example Execution
### Adding a New Loan
```
Enter product code: 101
Enter customer number: 23456
Enter customer AFM: 123456789
Enter loan amount: 150000
Enter interest rate: 0.02
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
1. Loan - €150,000
2. Credit Card - 2% commission
Enter product selection: 1
Enter sale justification: Business Loan
Sale recorded.
```

### Calculating Seller Commission
```
Enter seller number: 2
Loan Commission: €3,000
Credit Card Commission: €250
Total Seller Commission: €3,250
```

---

## System Requirements
- **Java Development Kit (JDK 8 or later)**

---

## Contributors
- **Developer:** [Maria Schoinaki](https://github.com/MariaSchoinaki)
- **Developer:** [Christos Stamoulos](https://github.com/ChristosStamoulos)