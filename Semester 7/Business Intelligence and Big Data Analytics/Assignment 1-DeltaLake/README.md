# **Delta Lake - Big Data Processing & Storage**

## **Overview**
This project focuses on **Delta Lake**, an **open-source storage framework** that enhances **Apache Spark** by adding ACID transactions, schema enforcement, and data versioning. Delta Lake is designed to optimize data lakes, overcoming their traditional limitations.

## **Key Topics Covered**
- **What is Delta Lake?**
  - Built on **Apache Spark**
  - Enhances traditional data lakes by addressing **data consistency, reliability, and performance issues**
  - Open-source and widely adopted in **big data environments**
- **Core Features**
  - **Delta Transaction Log**: Manages data changes with ACID guarantees
  - **Delta Sharing**: Enables secure data sharing across platforms
  - **Delta Live Tables**: Automates data ingestion and transformation
- **Use Cases**
  - Streaming and batch data processing
  - Real-time analytics and machine learning
  - Enterprise-scale data lakes and cloud-based architectures

## **Project Breakdown**
### **1. Understanding Delta Lake**
- Introduction to **Delta Lake architecture**
- Comparison with traditional **data lakes** and **data warehouses**
- Advantages of **structured, indexed, and versioned data storage**

### **2. Delta Lake Implementation**
- **Data Storage Mechanism**
  - Uses **Parquet files** for data storage
  - Maintains **Delta Logs** to track changes
  - Supports **schema evolution and enforcement**
- **ACID Transactions & Time Travel**
  - Enables rollback to previous versions
  - Ensures data integrity and consistency
- **Batch & Streaming Integration**
  - Supports **real-time data processing**
  - Optimizes **incremental data ingestion**

### **3. Performance Optimization & Compression**
- **Data Compression Methods**
  - Supports **Snappy, GZIP, LZ4** compression for efficient storage
  - Uses **Z-ordering and file compaction** for optimized query performance
- **Query Acceleration**
  - **Indexing & Data Skipping** to improve read efficiency
  - **Auto-optimization for batch and streaming workloads**

---

## **Tools & Technologies Used**
- **Apache Spark** for big data processing
- **Delta Lake** for transactional data lakes
- **Python / PySpark** for data manipulation
- **Cloud Storage (AWS, Azure, GCP)** for scalability

---

## **Project Team**
- **Siopis Stavros**
- **Stathopoulou Alexandra**
- **Sofia Papaioannou**
- **Maria Schoinaki**