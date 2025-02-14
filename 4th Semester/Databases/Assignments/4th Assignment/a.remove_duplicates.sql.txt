/** Αφαιρεί διπλότυπα από τον πίνακα hasGenre**/
with tempTable as(
  Select *,ROW_NUMBER()  OVER (partition by movie_id,genre_id ORDER BY movie_id) counter
  from hasGenre)
delete from tempTable where counter>1;

/** Αφαιρεί διπλότυπα από τον πίνακα hasKeyword**/
with tempTable as(
  Select *,ROW_NUMBER()  OVER (partition by movie_id,key_id ORDER BY movie_id) counter
  from hasKeyword)
delete from tempTable where counter>1;

/** Αφαιρεί διπλότυπα από τον πίνακα belongsTocollection**/
with tempTable as(
  Select *,ROW_NUMBER()  OVER (partition by movie_id,collection_id ORDER BY movie_id) counter
  from belongsTocollection)
delete from tempTable where counter>1;

/** Αφαιρεί διπλότυπα από τον πίνακα hasProductioncompany**/
with tempTable as(
  Select *,ROW_NUMBER()  OVER (partition by movie_id,pc_id ORDER BY movie_id) counter
  from hasProductioncompany)
delete from tempTable where counter>1;
