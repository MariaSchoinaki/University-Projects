/*-------------------------------------------------------------------Project Βάσεις δεδομένων 2023---------------------------------------------------------------------*/
/*|----------------------------------|
  | ΣΧΟΙΝΑΚΗ ΜΑΡΙΑ        : 3210191  |
  | ΒΕΡΟΥΧΗΣ ΕΛΕΥΘΕΡΙΟΣ   : 3200019  |
/*|----------------------------------|                              
/*---------------------------------------------------------------------------------1-----------------------------------------------------------------------------------*/

SELECT YEAR(m.release_date) AS year, COUNT(m.id) AS  movies_per_year 
FROM movie m 
WHERE m.budget > 1000000 
GROUP BY YEAR(m.release_date) 
ORDER BY YEAR(m.release_date)

/*---------------------------------------------------------------------------------2-----------------------------------------------------------------------------------*/

SELECT g.name AS genre, COUNT(hg.movie_id) AS movies_per_genre 
FROM movie m 
INNER JOIN hasGenre hg ON m.id = hg.movie_id 
INNER JOIN Genre g ON g.id = hg.genre_id 
WHERE (m.budget > 1000000 OR m.runtime > 120) 
GROUP BY g.name ORDER BY g.name

/*---------------------------------------------------------------------------------3-----------------------------------------------------------------------------------*/

SELECT g.name AS genre, YEAR(m.release_date) AS year, COUNT(hg.movie_id) AS movies_per_gy
FROM movie m
INNER JOIN hasGenre hg ON m.id = hg.movie_id
INNER JOIN Genre g ON g.id = hg.genre_id
WHERE YEAR(m.release_date) IS NOT NULL
GROUP BY g.name, YEAR(m.release_date)
ORDER BY g.name, YEAR(m.release_date)

/*---------------------------------------------------------------------------------4-----------------------------------------------------------------------------------*/

SELECT YEAR(m.release_date) AS year, SUM(CAST(m.revenue AS bigint)) AS revenues_per_year
FROM movie m
INNER JOIN movie_cast mc ON m.id = mc.movie_id
WHERE mc.name = 'Tom Hanks'
GROUP BY YEAR(m.release_date), mc.name
ORDER BY YEAR(m.release_date)

/*---------------------------------------------------------------------------------5-----------------------------------------------------------------------------------*/

SELECT YEAR(m.release_date) AS year, MAX(m.budget) AS max_budget 
FROM movie m 
WHERE m.budget <> 0 
GROUP BY YEAR(m.release_date) 
ORDER BY YEAR(m.release_date)

/*---------------------------------------------------------------------------------6-----------------------------------------------------------------------------------*/

SELECT c.name AS trilogy_name
FROM movie m
INNER JOIN belongsTocollection bc ON m.id = bc.movie_id
INNER JOIN collection c ON c.id = bc.collection_id
GROUP BY c.name
HAVING (COUNT(m.id) = 3)
ORDER BY c.name

/*---------------------------------------------------------------------------------7-----------------------------------------------------------------------------------*/

SELECT AVG(r.rating) AS avg_rating, COUNT(*) AS rating_count
FROM ratings r
GROUP BY r.user_id
ORDER BY r.user_id

/*---------------------------------------------------------------------------------8-----------------------------------------------------------------------------------*/

SELECT TOP(10) m.title AS movie_title, m.budget
FROM movie m
ORDER BY m.budget DESC

/*---------------------------------------------------------------------------------9-----------------------------------------------------------------------------------*/

SELECT SQL5.year, m.title AS movies_with_max_budget
FROM (
    SELECT YEAR(m.release_date) AS year, MAX(m.budget) AS max_budget
    FROM movie m
    WHERE m.budget <> 0
    GROUP BY YEAR(m.release_date)
) AS SQL5
INNER JOIN movie m ON ((SQL5.max_budget = m.budget) AND (SQL5.year = (YEAR(m.release_date))))
ORDER BY SQL5.year, m.title

/*---------------------------------------------------------------------------------10-----------------------------------------------------------------------------------*/

CREATE VIEW Popular_Movie_Pairs AS
SELECT R1.movie_id AS movie1_id, R2.movie_id AS movie2_id, COUNT(R1.user_id) AS popularity
FROM ratings R1
INNER JOIN ratings R2 ON R1.user_id = R2.user_id
WHERE R1.rating > 4 AND R2.rating > 4 AND R1.movie_id < R2.movie_id
GROUP BY R1.movie_id, R2.movie_id
HAVING COUNT(R1.user_id) > 10