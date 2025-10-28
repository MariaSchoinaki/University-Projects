# **Amazon Books Reviews - Data Analysis Project**

## **Overview**
This project analyzes **Amazon book reviews** to identify trends in customer ratings, helpfulness votes, and sentiment analysis. The data is preprocessed and structured for storage in a **Data Warehouse**, enabling comprehensive business intelligence insights.

## **Key Topics Covered**
- **Data Cleaning & Preparation**
  - Handling missing values and redundant columns
  - Transforming data for efficient querying
- **Database Management & SQL Processing**
  - Storing book reviews and metadata in SQL Server
  - Designing an optimized **star schema** for analysis
- **Data Analysis & Visualization**
  - Sentiment analysis of user reviews
  - Trend analysis on book popularity and ratings

## **Tasks & Concepts**
### **1. Data Preprocessing & Cleaning**
- Processed datasets **books_rating.csv** and **books_data.csv**
- Removed unnecessary fields and handled missing values
- Standardized date formats and structured categories

### **2. Data Warehouse & SQL Implementation**
- Designed a **Star Schema** for efficient querying:
  - **Fact Table**: `fact_reviews` containing scores and helpfulness metrics
  - **Dimension Tables**: `dim_books`, `dim_users`, `dim_time`
- Imported cleaned data into **SQL Server** using Import Wizard

### **3. Data Analysis & Insights**
- **Trend Analysis**:
  - Identified spikes in reviews and publishing activity over time
  - Examined helpfulness of reviews based on user votes
- **Sentiment Analysis**:
  - Analyzed polarity of customer reviews (positive, neutral, negative)
  - Categorized books based on user sentiment
- **Association Rule Mining**:
  - Found relationships between book categories and high/low ratings
  - Used **Apriori Algorithm** to detect strong correlations

---

## **Tools & Technologies Used**
- **Python (Pandas, NumPy, Matplotlib, Seaborn)** for data analysis
- **SQL Server** for data storage and querying
- **Power BI** for data visualization
- **MLxtend (Apriori Algorithm)** for rule mining

---

## **Project Team**
- **Maria Schoinaki**
- **Sofia Papaioannou**