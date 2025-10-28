# **MovieLens Database - Assignment 5**

## **Overview**
This assignment focuses on **query optimization and triggers** in SQL using **Azure Data Studio**. It consists of two main parts:
1. **Optimizing query execution time using indexes**.
2. **Implementing triggers to update database attributes dynamically**.

---

## **Objectives**
- **Query Optimization:** Enhancing SQL query efficiency by adding indexes.
- **Execution Plans:** Understanding and analyzing SQL execution plans.
- **Using GROUP BY & HAVING:** Aggregating data efficiently.
- **Trigger Implementation:** Automating updates on database changes.

---

## **Implementation Steps**
### **Part A: Indexing for Query Optimization**
#### **1. Execution Plan Analysis**
- Run the following query and capture its execution plan:
  ```sql
  SELECT name, character
  FROM movie
  JOIN movie_cast ON movie_id = id
  WHERE title = 'Armageddon';
  ```
- Save a **screenshot** of the execution plan before indexing.

#### **2. Creating Indexes**
- Create two indexes to speed up execution:
  ```sql
  CREATE INDEX title_index ON movie(title);
  CREATE INDEX movie_id_index ON movie_cast(movie_id);
  ```
- Run the query again and **compare execution plans**.
- Document the **performance improvements**.

#### **3. Grouping & Aggregation**
- Write a query to return the **average rating per movie**, filtering for movies with an **average rating above 4**:
  ```sql
  SELECT m.title, AVG(r.rating) AS avgRating
  FROM movie m
  INNER JOIN ratings r ON m.id = r.movie_id
  GROUP BY m.id, m.title
  HAVING AVG(r.rating) > 4;
  ```
- Capture the execution plan and create an **index to improve performance**:
  ```sql
  CREATE INDEX rating_and_movie_id_index ON ratings(movie_id, rating);
  ```

---

### **Part B: Triggers & Automated Updates**
#### **4. Adding an AVG_Rating Attribute**
- Add an **AVG_Rating** column to store movie ratings:
  ```sql
  ALTER TABLE movie ADD AVG_Rating FLOAT;
  ```
- Initialize values using:
  ```sql
  UPDATE movie
  SET AVG_Rating = (
      SELECT AVG(r.rating)
      FROM ratings r
      WHERE movie.id = r.movie_id
      GROUP BY r.movie_id
  );
  ```

#### **5. Creating a Trigger for Automatic Updates**
- Write a **trigger** to update `AVG_Rating` when new ratings are inserted:
  ```sql
  CREATE TRIGGER AVG_UPDATE
  ON ratings
  AFTER INSERT
  AS
  BEGIN
      UPDATE movie
      SET AVG_Rating = (
         SELECT AVG(r.rating)
         FROM ratings r
         WHERE movie.id = r.movie_id
         GROUP BY r.movie_id
      )
      WHERE movie.id IN (SELECT movie_id FROM inserted);
  END;
  ```
- Test the trigger by inserting sample ratings:
  ```sql
  INSERT INTO ratings VALUES (17, 2, 4);
  INSERT INTO ratings VALUES (12, 2, 1);
  INSERT INTO ratings VALUES (17, 33, 4);
  ```

#### **6. Alternative Approach to Triggers**
- Instead of recomputing `AVG_Rating` for all movies, use:
  - `count_ratings`: Tracks the number of ratings per movie.
  - `sum_ratings`: Stores the total sum of ratings.
  - Compute **AVG_Rating dynamically**:
  ```sql
  UPDATE movie
  SET count_ratings = count_ratings + 1,
      sum_ratings = sum_ratings + new_rating,
      AVG_Rating = sum_ratings / count_ratings
  WHERE movie.id = movie_id;
  ```

---

## **Project Structure**
```
├── Assignment 5.pdf         # Problem description
├── Report.pdf               # Execution plans & results
├── README.md                # Project documentation
```

---

## **Installation & Usage**
### **Prerequisites**
- **Azure Data Studio** installed.
- **SQL Server Database** with MovieLens dataset imported.

### **Executing SQL Scripts**
1. **Run queries and capture execution plans.**
2. **Create indexes and analyze performance improvements.**
3. **Define and test triggers for automated updates.**

---

## **Applications**
- **Database Optimization:** Enhancing query efficiency.
- **Automated Data Management:** Using triggers for real-time updates.
- **Big Data Processing:** Handling large datasets effectively.

---

## Contributors
- **Developer:** [Maria Schoinaki](https://github.com/MariaSchoinaki)