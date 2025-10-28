# **MovieLens Database - Assignment 3**

## **Overview**
This assignment involves **exploring the MovieLens dataset** through **SQL queries** using **Azure Data Studio**. The task requires formulating and executing **12 structured SQL queries** to analyze movie data.

---

## **Objectives**
- Execute **complex SQL queries** using `INNER JOIN`, `OUTER JOIN`, `WHERE`, `ORDER BY`, `GROUP BY`, and `TOP`.
- Utilize **SQL aggregation functions** like `MIN`, `MAX`, `AVG`.
- Apply filtering techniques using `DISTINCT`, `LIKE`, `BETWEEN`.
- Ensure that each of the following tables is used at least once:
  - `Movie`, `Genre`, `Keyword`, `Movie_cast`, `hasGenre`, `hasKeyword`, `Ratings`
- Ensure at least **8 queries use JOINs** and **2 queries use OUTER JOINs**.

---

## **Implementation Steps**
### **1. Query Formulation & Execution**
- Write **12 SQL queries** to extract meaningful insights.
- Add **explanations** as comments before each query.
- Run queries in **Azure Data Studio** to validate correctness.

### **2. Handling Data Types**
- If a column has an incorrect type, modify it using:
  ```sql
  ALTER TABLE ratings
  ALTER COLUMN rating FLOAT;
  ```
- Ensure queries extract **movie titles, not just IDs**, for better readability.

### **3. File Submission**
- Store all SQL queries in **`simple_queries.sql`**.
- Include **short descriptions** and the **row count** of each query result as comments.
  ```sql
  /* "Find movie titles with an average user rating above 4."
  Output: 205 rows */
  SELECT m.title, AVG(r.rating) as avgRating
  FROM movie m
  INNER JOIN ratings r ON m.id = r.movie_id
  GROUP BY m.id, m.title
  HAVING AVG(r.rating) > 4;
  ```

---

## **Project Structure**
```
├── Assignment 3.pdf         # Problem description
├── simple_queries.sql       # SQL queries with comments
├── README.md                # Project documentation
```

---

## **Installation & Usage**
### **Prerequisites**
- **Azure Data Studio** installed.
- **SQL Server Database** with MovieLens dataset imported.

### **Running Queries**
1. Open `simple_queries.sql` in **Azure Data Studio**.
2. Execute each query sequentially.
3. Verify results and correct any errors.

---

## **Applications**
- **Data Analysis:** Extracting insights from relational databases.
- **SQL Query Optimization:** Writing efficient and structured queries.
- **Business Intelligence:** Using SQL for trend analysis in large datasets.

---

## Contributors
- **Developer:** [Maria Schoinaki](https://github.com/MariaSchoinaki)