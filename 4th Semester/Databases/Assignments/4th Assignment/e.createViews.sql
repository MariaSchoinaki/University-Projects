Create View Actor AS
    SELECT DISTINCT person_id, gender, name
    FROM movie_cast

Create View CrewMember AS
    SELECT DISTINCT person_id, gender, name
    FROM movie_crew

Create View Person AS
    SELECT * FROM Actor
    UNION
    SELECT * FROM CrewMember