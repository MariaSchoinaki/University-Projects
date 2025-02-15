# **MovieLens Database - Assignment 2**

## **Overview**
This assignment involves the creation of a **MovieLens database** using **SQL Server** on **Azure Data Studio**. The dataset consists of information about movies, their contributors, and user ratings. The task includes **data preprocessing, database schema design, and SQL table modifications**.

---

## **Objectives**
- Create a **relational database schema** based on MovieLens data.
- Process and clean **CSV files** for structured database storage.
- Implement **primary and foreign keys** using `ALTER TABLE` statements.
- Import data into **SQL Server** using **Azure Data Studio**.
- Ensure **data integrity** and **referential constraints**.

---

## **Dataset Description**
The provided dataset includes the following CSV files:
- `movie.csv` - Movie metadata (ID, title, budget, revenue, description).
- `genre.csv` - Movie genres (ID, name).
- `productioncompany.csv` - Movie production companies.
- `collection.csv` - Movie collections (trilogies, franchises).
- `movie_cast.csv` - Information about cast members.
- `movie_crew.csv` - Information about directors, screenwriters, etc.
- `belongsTocollection.csv` - Mapping between movies and collections.
- `hasGenre.csv` - Mapping between movies and genres.
- `hasProductionCompany.csv` - Mapping between movies and production companies.
- `ratings.csv` - User ratings for movies.
- `keywords.csv` - Movie keywords (requires JSON preprocessing).

---

## **Implementation Steps**
### **1. Data Preprocessing**
- The `keywords.csv` file contains **nested JSON data**.
- Convert JSON data into structured CSV using **Python scripts** (`csvEditing.py`).
- Create two tables:
  - `Keyword (id, name)`
  - `hasKeyword (movie_id, key_id)`

### **2. Database & Table Creation**
- Import CSV files into **SQL Server** using **Azure Data Studio Import Plugin**.
- Ensure **correct data types** for all table columns.
- Normalize data by separating JSON-based attributes.

### **3. Primary & Foreign Key Constraints**
- Define **primary keys** using `ALTER TABLE` for:
  - `movie, genre, productioncompany, collection, movie_cast, movie_crew, keyword`
- Define **foreign keys** for relational integrity:
  - `belongsTocollection, hasGenre, hasProductionCompany, ratings, movie_cast, movie_crew, hasKeyword`

### **4. SQL Execution**
- Store all `ALTER TABLE` statements in `alter_tables.sql`.
- Execute SQL queries in **Azure Data Studio** to apply constraints.

---

## **Project Structure**
```
├── Assignment 2.pdf         # Problem description
├── alter_tables.sql         # SQL statements for constraints
├── csvEditing.py           # Python script for preprocessing keywords.csv
├── Report.pdf              # Summary of implementation steps
├── README.md               # Project documentation
```

---

## **Installation & Usage**
### **Prerequisites**
- **Azure Data Studio** installed.
- **SQL Server Import Plugin** enabled.
- Basic knowledge of **SQL schema design**.

### **Running the Project**
1. **Preprocess JSON data** using:
   ```sh
   python csvEditing.py
   ```
2. **Import CSV files** into SQL Server using **Azure Data Studio**.
3. **Run the SQL script** to apply constraints:
   ```sql
   ALTER TABLE ... ADD PRIMARY KEY (...);
   ALTER TABLE ... ADD FOREIGN KEY (...);
   ```

---

## **Applications**
- **Database Management:** Real-world SQL database design & structuring.
- **Data Science & Analytics:** Organizing structured data for analysis.
- **Big Data Processing:** Handling large-scale movie metadata efficiently.

## Contributors
- **Developer:** [Maria Schoinaki](https://github.com/MariaSchoinaki)