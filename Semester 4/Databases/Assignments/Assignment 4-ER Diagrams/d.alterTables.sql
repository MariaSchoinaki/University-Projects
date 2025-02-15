ALTER TABLE hasGenre
ADD CONSTRAINT hasGenre_pk
PRIMARY KEY (movie_id, genre_id);

ALTER TABLE hasKeyword
ADD CONSTRAINT hasKeyword_pk
PRIMARY KEY (movie_id, key_id);

ALTER TABLE belongsTocollection
ADD CONSTRAINT belongsTocollection_pk
PRIMARY KEY (movie_id);

ALTER TABLE hasProductioncompany
ADD CONSTRAINT hasProductioncompany_pk
PRIMARY KEY (movie_id, pc_id);

ALTER TABLE ratings
ADD CONSTRAINT ratings_pk
PRIMARY KEY (movie_id, user_id);