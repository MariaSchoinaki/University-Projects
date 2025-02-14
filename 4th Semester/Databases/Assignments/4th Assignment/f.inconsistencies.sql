SELECT *
FROM Person
WHERE Person.person_id IN(
    SELECT DISTINCT person_id
    FROM Person
    GROUP BY person_id
    HAVING(count(*) > 1)
)
ORDER BY person_id


UPDATE movie_cast
SET gender = 2
WHERE person_id = 47395;

UPDATE movie_crew
SET gender = 2
WHERE person_id = 47395;


UPDATE movie_cast
SET gender = 2
WHERE person_id = 1785844;

UPDATE movie_crew
SET gender = 2
WHERE person_id = 1785844;


UPDATE movie_crew
SET name = 'Cheung Ka-Fai'
WHERE person_id = 63574;