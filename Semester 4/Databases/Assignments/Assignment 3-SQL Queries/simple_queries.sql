/*-----------------------------------------------------------------3η Εργασία Βάσεις δεδομένων 2023--------------------------------------------------------------------*/
/*|----------------------------------|
  | ΣΧΟΙΝΑΚΗ ΜΑΡΙΑ        : 3210191  |
  | ΒΕΡΟΥΧΗΣ ΕΛΕΥΘΕΡΙΟΣ   : 3200019  |
/*|----------------------------------|                              
/*---------------------------------------------------------------------------------1-----------------------------------------------------------------------------------*/

/*Βρες μου τους τίτλους όλων των ταινιών, που είναι στα αγγλικά μαζί με 
τον μέσο όρο βαθμολογίας τους, ταξινομημένους ανάλογα τον μέσο όρο
βαθμολογίας τους και σε ισοβαθμία, αλφαβητικά. Όσες τιμές δεν
έχουν βαθμολογίες εμφάνισέ τες με μέσο 
όρο βαθμολογίας "null"
Output: 8.366 rows
*/
SELECT m.title, AVG(r.rating) AS avgRating
FROM movie m
LEFT OUTER JOIN ratings r ON r.movie_id = m.id
WHERE m.original_language = 'en'
GROUP BY m.id, m.title
ORDER BY avgRating DESC, m.title

/*---------------------------------------------------------------------------------2-----------------------------------------------------------------------------------*/

/*Βρες μου τους τίτλους όλων των ταινιών που να περιλαμβάνουν το keyword
'star' ή μια φράση/λέξη keyword που να περιέχει το 'star', ταξινομημένους
αλφαβητικά
Output: 103 rows 
*/
SELECT DISTINCT m.title
FROM movie m
INNER JOIN hasKeyword hk ON m.id = hk.movie_id
INNER JOIN Keyword k ON k.id = hk.key_id
WHERE (k.name LIKE '%star%')
ORDER BY m.title

/*---------------------------------------------------------------------------------3-----------------------------------------------------------------------------------*/

/*Βρες μου τους τίτλους όλων των ταινιών μαζί με τον ηθοποιό 'Henry Cavill'
ταξινομημένους κατά φθίνουσα σειρά κατά την δημοτικότητα τους και σε 
ισοβαθμία, αλφαβητικά
Output: 2 rows 
*/
SELECT m.title, m.popularity
FROM movie m
INNER JOIN movie_cast mc ON mc.movie_id = m.id
WHERE (mc.name LIKE 'Henry Cavill')
ORDER BY m.popularity DESC, m.title

/*---------------------------------------------------------------------------------4-----------------------------------------------------------------------------------*/

/*Βρες μου τους τίτλους των 50 καλύτερων ταινιών που ανήκουν στο 'Drama'
Genre μαζί με το popularity τους, ταξινομημένους ανάλογα
το popularity τους και σε ισοβαθμία, αλφαβητικά.
Output: 50 rows 
*/
SELECT DISTINCT TOP(50) m.title, m.popularity
FROM movie m
INNER JOIN hasGenre hg ON m.id = hg.movie_id
RIGHT OUTER JOIN Genre g ON g.id = hg.genre_id
WHERE (g.name = 'Drama')
ORDER BY m.popularity DESC, m.title

/*---------------------------------------------------------------------------------5-----------------------------------------------------------------------------------*/

/*Βρες μου πόσες ταινίες συνολικά ανήκουν σε κάθε είδος και ταξηνόμισέ τα
πρώτα με το #των ταινιών και σε ισοβαθμία, αλφαβητικά(genre)
Output: 32 rows 
*/
SELECT g.name, COUNT(DISTINCT hg.movie_id) AS #movies
FROM movie m
INNER JOIN hasGenre hg ON m.id = hg.movie_id
RIGHT OUTER JOIN Genre g ON g.id = hg.genre_id
GROUP BY g.name
ORDER BY COUNT(DISTINCT hg.movie_id) DESC, g.name

/*---------------------------------------------------------------------------------6-----------------------------------------------------------------------------------*/

/*Βρες μου τα ονόματα όλων των ηθοποιών, το πλήθος των ταινιών που έχει παίξει 
ο καθένας και τον μέσο όρο βαθμολογίας για κάθε σετ ταινιών που παίζει ο κάθε
ηθοποιός, ταξινομημένα με φθίνουσα σειρά κατά το πλήθος και σε ισοβαθμία,
αλφαβητικά
Output: 69.009 rows 
*/
SELECT mc.name, COUNT(DISTINCT mc.movie_id) AS #movies, AVG(r.rating) AS avgRating
FROM movie m
INNER JOIN movie_cast mc ON m.id = mc.movie_id
LEFT OUTER JOIN ratings r ON r.movie_id = m.id
GROUP BY mc.name
ORDER BY COUNT(DISTINCT mc.movie_id) DESC, mc.name

/*---------------------------------------------------------------------------------7-----------------------------------------------------------------------------------*/

/*Βρες μου τους τίτλους όλων των ταινιών που ανήκουν στο collection 
'Star Wars Collection' και εμφανισέ τους με φθίνουσα σειρά κατά 
την δημοτικότητα και σε ισοδυναμία, αλφαβητικά 
Output: 5 rows 
*/
SELECT DISTINCT m.title, m.popularity
FROM movie m
INNER JOIN belongsTocollection bc ON m.id = bc.movie_id
INNER JOIN collection c ON c.id = bc.collection_id
WHERE (c.name LIKE 'Star Wars Collection')
ORDER BY m.popularity DESC, m.title

/*---------------------------------------------------------------------------------8-----------------------------------------------------------------------------------*/

/*Βρες μου τους σκηνοθέτες όλων των ταινιών που έχουν popularity > 20
και τον μέσο όρο βαθμολογίας για κάθε σετ ταινιών του σκηνοθέτη.
Ταξινόμήσε τους ανάλογα τον μέσο όρο βαθμολογίας και σε ισοτιμία,
αλφαβητικά
Output: 50 rows 
*/
SELECT mc.name AS director, AVG(r.rating) AS avgRating
FROM movie m
INNER JOIN movie_crew mc ON m.id = mc.movie_id
LEFT OUTER JOIN ratings r ON r.movie_id = m.id
WHERE (mc.job = 'Director' AND m.popularity > 20)
GROUP BY mc.name
ORDER BY AVG(r.rating) DESC, mc.name

/*---------------------------------------------------------------------------------9-----------------------------------------------------------------------------------*/

/*Βρες μου για κάθε εταιρία παραγωγής το μικρότερο και μεγαλύτερο καθαρό
κέρδος από τις ταινίες που έχει κάνει με προυπόθεση το χαμηλότερο κέρδος
να ξεπερνά το 1.000.000 και με ταξινόμηση φθίνουσα ως προς το μεγαλύτερο
καθαρό κέρδος. Σε περίπτωση ισοβαθμίας, η ταξινόμηση συνεχίζει αλφαβητικά
Output: 1.100 rows 
*/
SELECT pc.name AS production_company, MIN(m.revenue-m.budget) AS min_total_win, MAX(m.revenue-m.budget) AS max_total_win
FROM movie m
INNER JOIN hasProductioncompany hpc ON hpc.movie_id = m.id
INNER JOIN productioncompany pc ON pc.id = hpc.pc_id
GROUP BY pc.name
HAVING(MIN(m.revenue-m.budget) > 1000000)
ORDER BY max_total_win DESC, pc.name

/*---------------------------------------------------------------------------------10----------------------------------------------------------------------------------*/

/*Βρες μου για την εταιρία παραγωγής 'Warner Bros.' τους τίτλους ταινιών 
που ανήκουν σε αυτήν και έχουν ανακοινωθεί στο διάστημα 2010-2020. 
Η ταξινόμηση είναι φθίνουσα κατά την χρονιά και σε περίπτωση
ισοτιμίας, αλφαβητικά
Output: 81 rows 
*/
SELECT DISTINCT m.title, YEAR(m.release_date) AS year
FROM movie m
INNER JOIN hasProductioncompany hpc ON hpc.movie_id = m.id
INNER JOIN productioncompany pc ON pc.id = hpc.pc_id
WHERE (pc.name = 'Warner Bros.')
GROUP BY YEAR(m.release_date), m.title
HAVING(YEAR(m.release_date) BETWEEN 2000 AND 2010)
ORDER BY YEAR(m.release_date) DESC, m.title

/*---------------------------------------------------------------------------------11----------------------------------------------------------------------------------*/

/*Βρες μου τους τίτλους των ταινιών που έχουν διάρκεια από 60 έως 120 λεπτά
και έχουν γυριστεί στα Ισπανικά('es'), με τον αντίστοιχο μέσο όρο βαθμολογίας.
Η ταξινόμηση είναι αύξουσα ως προς την διάρκεια κάθες ταινίας και σε περίπτωση
ισοτιμίας, αλφαβητικά
Output: 92 rows 
*/
SELECT m.title, m.runtime, AVG(r.rating) AS avgRating
FROM movie m
LEFT OUTER JOIN ratings r ON r.movie_id = m.id
WHERE (m.original_language = 'es')
GROUP BY m.title, m.runtime
HAVING (m.runtime BETWEEN 60 AND 120) 
ORDER BY m.runtime, m.title

/*---------------------------------------------------------------------------------12----------------------------------------------------------------------------------*/

/* Βρες μου ποιες εταιρίες παραγωγής έχουν ταινίες με τζίρο πάνω από 
1/2 billion dolars και πόσες είναι αυτές, ταξιμονημένες με το πλήθος
και σε ισοτιμία, αλφαβητικά
Output: 49 rows 
*/ 
SELECT pc.name AS production_company, COUNT(DISTINCT hpc.movie_id) AS #movies
FROM movie m
INNER JOIN hasProductioncompany hpc ON hpc.movie_id = m.id
INNER JOIN productioncompany pc ON pc.id = hpc.pc_id
WHERE (m.revenue > 500000000)
GROUP BY pc.name
ORDER BY COUNT(DISTINCT hpc.movie_id) DESC, pc.name