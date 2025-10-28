# **MovieLens Database - Assignment 4**

## **Overview**
This assignment focuses on refining and enhancing the **MovieLens database** by **cleaning data, defining primary keys, designing an ER diagram, and creating database views** using **SQL and Azure Data Studio**.

---

## **Objectives**
- **Data Cleaning:** Removing duplicate records from key tables.
- **Entity-Relationship (ER) Diagram:** Representing database schema with keys and relationships.
- **Defining Primary Keys:** Ensuring referential integrity.
- **Creating SQL Views:** Structuring actor and crew member data.
- **Detecting & Fixing Data Inconsistencies:** Ensuring accurate person records.

---

## **Implementation Steps**
### **1. Data Cleaning - Removing Duplicates**
- Execute `a.remove_duplicates.sql.txt` to eliminate duplicate rows from:
  - `hasGenre`
  - `hasKeyword`
  - `belongsTocollection`
  - `hasProductioncompany`

### **2. Entity-Relationship (ER) Diagram**
- Design an **ER diagram** for the MovieLens database, emphasizing **entity relationships** and **primary keys**.
- Ensure that:
  - `movie_cast` and `movie_crew` have one-to-many relationships with `movie`.
  - The **ER diagram is delivered as** `b.ER.pdf`.

### **3. Answering Conceptual Question**
- Explain why separate tables for `hasCast` and `hasCrew` are unnecessary.
- Answer provided in `c.answear.txt`.

### **4. Adding Primary Keys**
- Use `ALTER TABLE` commands to define primary keys in:
  - `hasGenre`
  - `hasKeyword`
  - `belongsTocollection`
  - `hasProductioncompany`
  - `Ratings`
- SQL commands stored in `d.alterTables.sql`.

### **5. Creating Views**
- Define database views for:
  - `Actor`: Unique `person_id`, `gender`, and `name` from `Movie_Cast`.
  - `CrewMember`: Unique `person_id`, `gender`, and `name` from `Movie_Crew`.
  - `Person`: Union of `Actor` and `CrewMember`.
- SQL view definitions stored in `e.createViews.sql`.

### **6. Detecting & Fixing Data Inconsistencies**
- Identify `person_id` records appearing multiple times with inconsistent `name` or `gender`.
- Update `movie_cast` and `movie_crew` to ensure unique `person_id` entries.
- SQL commands stored in `f.inconsistencies.sql`.

---

## **Project Structure**
```
├── Assignment 4.pdf         # Problem description
├── a.remove_duplicates.sql.txt  # Script to remove duplicates
├── b.ER.pdf                 # ER diagram
├── c.answear.txt            # Conceptual question response
├── d.alterTables.sql        # SQL commands for primary keys
├── e.createViews.sql        # SQL commands for views
├── f.inconsistencies.sql    # SQL commands for fixing inconsistencies
├── README.md                # Project documentation
```

---

## **Installation & Usage**
### **Prerequisites**
- **Azure Data Studio** installed.
- **SQL Server Database** with MovieLens dataset imported.

### **Executing SQL Scripts**
1. **Clean duplicates:**
   ```sh
   Run a.remove_duplicates.sql.txt
   ```
2. **Define primary keys:**
   ```sh
   Run d.alterTables.sql
   ```
3. **Create views:**
   ```sh
   Run e.createViews.sql
   ```
4. **Detect and fix inconsistencies:**
   ```sh
   Run f.inconsistencies.sql
   ```

---

## **Applications**
- **Database Normalization:** Enhancing data structure and reducing redundancy.
- **SQL Schema Design:** Understanding relationships between entities.
- **Data Cleaning & Integrity:** Ensuring high-quality datasets for analysis.

---

## Contributors
- **Developer:** [Maria Schoinaki](https://github.com/MariaSchoinaki)