ALTER TABLE genre
ADD CONSTRAINT genre_pk
PRIMARY KEY (id);

ALTER TABLE movie
ADD CONSTRAINT movie_pk
PRIMARY KEY (id);

ALTER TABLE productioncompany
ADD CONSTRAINT productioncompany_pk
PRIMARY KEY (id);

ALTER TABLE movie_cast
ADD CONSTRAINT movie_cast_pk
PRIMARY KEY (cid);

ALTER TABLE collection
ADD CONSTRAINT collection_pk
PRIMARY KEY (id);

ALTER TABLE Keyword
ADD CONSTRAINT Keyword_pk
PRIMARY KEY (id);

ALTER TABLE movie_crew
ADD CONSTRAINT movie_crew_pk
PRIMARY KEY (cid);


ALTER TABLE belongsTocollection
 ADD CONSTRAINT FK_movie_id_belongsTocollection FOREIGN
KEY (movie_id)
 REFERENCES movie(id);

 ALTER TABLE belongsTocollection
 ADD CONSTRAINT FK_collection_id FOREIGN
 KEY (collection_id)
 REFERENCES collection(id);


 ALTER TABLE hasGenre
 ADD CONSTRAINT FK_movie_id_hasGenre FOREIGN
KEY (movie_id)
 REFERENCES movie(id);

 ALTER TABLE hasGenre
 ADD CONSTRAINT FK_genre_id FOREIGN
KEY (genre_id)
 REFERENCES genre(id);



 ALTER TABLE hasKeyword
 ADD CONSTRAINT FK_movie_id_hasKeyword FOREIGN
KEY (movie_id)
 REFERENCES movie(id);

ALTER TABLE hasKeyword
 ADD CONSTRAINT FK_key_id FOREIGN
KEY (key_id)
 REFERENCES Keyword(id);


 ALTER TABLE hasProductioncompany
 ADD CONSTRAINT FK_movie_id_hasProductioncompany FOREIGN
KEY (movie_id)
 REFERENCES movie(id);

 ALTER TABLE hasProductioncompany
 ADD CONSTRAINT FK_pc_id FOREIGN
KEY (pc_id)
 REFERENCES productioncompany(id);
 
 
 ALTER TABLE ratings
 ADD CONSTRAINT FK_ratings FOREIGN
KEY (movie_id)
 REFERENCES movie(id);


 ALTER TABLE movie_cast
 ADD CONSTRAINT FK_movie_cast FOREIGN
KEY (movie_id)
 REFERENCES movie(id);

ALTER TABLE movie_crew
 ADD CONSTRAINT FK_movie_crew FOREIGN
KEY (movie_id)
 REFERENCES movie(id);

