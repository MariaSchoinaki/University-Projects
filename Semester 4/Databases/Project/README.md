# **MovieLens Database - Final Project**

## **Overview**
This project involves **data analysis and visualization** using the **MovieLens database**, focusing on **SQL aggregation queries, ranking queries, statistical visualizations, and recommendation systems**. The project aims to extract meaningful insights from movie-related data and present them using **SQL and Python**.

---

## **Objectives**
- **SQL Data Aggregation:** Extract statistical summaries using `GROUP BY`, `HAVING`, and `ORDER BY`.
- **Movie Ranking Analysis:** Identify top-performing movies based on budget and revenue.
- **Visualization of Movie Statistics:** Generate meaningful **2D and 3D charts**.
- **Recommendation System:** Identify **popular movie pairs** based on user ratings.

---

## **Implementation Steps**
### **Part A: Aggregation Queries in SQL**
#### **1. Number of Movies Per Year**
- Count the number of movies released per year for films with a budget above $1M:
  ```sql
  SELECT year, COUNT(*) AS movies_per_year
  FROM movie
  WHERE budget > 1000000
  GROUP BY year
  ORDER BY year;
  ```

#### **2. Number of Movies Per Genre**
- Count the number of movies per genre with either a budget above $1M or a runtime over 2 hours:
  ```sql
  SELECT genre, COUNT(*) AS movies_per_genre
  FROM movie
  JOIN hasGenre ON movie.id = hasGenre.movie_id
  WHERE budget > 1000000 OR runtime > 120
  GROUP BY genre;
  ```

#### **3. Revenue of Favorite Actor Per Year**
- Calculate the total revenue for movies featuring **Tom Hanks**, grouped by year:
  ```sql
  SELECT year, SUM(revenue) AS revenue_per_year
  FROM movie
  JOIN movie_cast ON movie.id = movie_cast.movie_id
  WHERE movie_cast.name = 'Tom Hanks'
  GROUP BY year;
  ```

---

### **Part B: Movie Ranking Queries**
#### **4. Top 10 Movies by Budget**
- Identify the **10 movies with the highest budgets**, handling ties appropriately:
  ```sql
  SELECT TOP 10 title, budget
  FROM movie
  ORDER BY budget DESC;
  ```

#### **5. Movies with Maximum Budget per Year**
- Retrieve the movies with the **highest budget in each year**:
  ```sql
  SELECT year, title, budget
  FROM movie
  WHERE budget = (SELECT MAX(budget) FROM movie m WHERE m.year = movie.year);
  ```

---

### **Part C: Visualization & Statistical Analysis**
#### **6. Python Visualization of Movie Data**
- Use **Matplotlib** to generate visual representations of the SQL results:
  - **Bar charts** for movie counts per year and genre.
  - **Scatter plots** for user rating distributions.
  - **3D visualizations** for genre-based movie distributions.
- Example Python code to retrieve and visualize data:
  ```python
  import matplotlib.pyplot as plt
  import pyodbc
  
  conn = pyodbc.connect("DRIVER={SQL Server};SERVER=myserver;DATABASE=movielens;UID=examiner;PWD=pass")
  cursor = conn.cursor()
  
  cursor.execute("SELECT year, COUNT(*) FROM movie WHERE budget > 1000000 GROUP BY year")
  data = cursor.fetchall()
  
  years = [row[0] for row in data]
  counts = [row[1] for row in data]
  
  plt.bar(years, counts)
  plt.xlabel("Year")
  plt.ylabel("Number of Movies")
  plt.title("Number of Movies per Year with Budget > $1M")
  plt.show()
  ```

---

### **Part D: Movie Recommendation System**
#### **7. Identifying Popular Movie Pairs**
- Find movie pairs that have been **rated above 4 by at least 10 users**:
  ```sql
  CREATE VIEW Popular_Movie_Pairs AS
  SELECT r1.movie_id AS movie1, r2.movie_id AS movie2, COUNT(*) AS popularity
  FROM ratings r1
  JOIN ratings r2 ON r1.user_id = r2.user_id AND r1.movie_id < r2.movie_id
  WHERE r1.rating > 4 AND r2.rating > 4
  GROUP BY r1.movie_id, r2.movie_id
  HAVING COUNT(*) > 10;
  ```

---

## **Project Structure**
```
├── Project.pdf             # Problem description
├── project-queries.sql     # SQL queries for aggregation & ranking
├── visualization.py        # Python script for data visualization
├── Report.pdf              # Charts & explanations
├── README.md               # Project documentation
```

---

## **Installation & Usage**
### **Prerequisites**
- **Azure Data Studio** installed.
- **Python 3.x** with `pyodbc` and `matplotlib` installed.
- **SQL Server Database** with MovieLens dataset imported.

### **Executing the Project**
1. **Run SQL queries in `project-queries.sql`** in Azure Data Studio.
2. **Generate visualizations** using:
   ```sh
   python visualization.py
   ```

---

## **Applications**
- **Business Intelligence:** Analyzing movie performance and trends.
- **Data Science & Visualization:** Using Python to interpret SQL data.
- **Recommendation Systems:** Identifying movie similarities through user behavior.

---

## Contributors
- **Developer:** [Maria Schoinaki](https://github.com/MariaSchoinaki)