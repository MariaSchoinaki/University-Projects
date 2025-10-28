# **Project 2 - Data Warehouse Implementation & SQL Query Optimization**

## **Overview**
This project focuses on **building a data warehouse for restaurant health inspections** and implementing SQL queries to analyze inspection data. The objective is to design a **star schema**, load data into a structured database, and generate insights using SQL queries and reporting tools.

## **Key Topics Covered**
- **Data Warehousing & Star Schema Design**
  - Designing dimension and fact tables
  - Implementing primary keys and foreign key constraints
- **SQL Query Processing & Optimization**
  - Writing aggregation and analytical queries
  - Creating and managing indexes for efficient query execution
- **Data Analysis & Reporting**
  - Using SQL to generate statistical insights
  - Visualizing data in Power BI

## **Tasks & Concepts**
### **1. Database Schema Design & Data Loading**
- Create a **data warehouse** named `INSDW` for inspection data.
- Design a **star schema** with **fact and dimension tables**.
- Load the dataset from `inspections_data.txt` into the database.

### **2. SQL Query Implementation & Performance Optimization**
- Write SQL queries to:
  - Count inspections per **year and inspection type**.
  - Aggregate **critical and non-critical violations per restaurant**.
  - Generate a **data cube** summarizing violations by year, inspection type, and category.
- Optimize performance using **indexes and efficient query structuring**.

### **3. Data Analysis & Visualization with Power BI**
- Generate **a report showing inspection trends per year and type**.
- Map **locations of the 20 most-violating restaurants**.
- Create visualizations for **inspection frequency and violation distribution**.

---

## **Tools Recommended**
- **SQL Server Management Studio (SSMS)** for database implementation.
- **Power BI** for data visualization and reporting.
- **Draw.io** for database schema diagrams.