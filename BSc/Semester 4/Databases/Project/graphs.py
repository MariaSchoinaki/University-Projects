#-------------------------------------------------------------------Project Βάσεις δεδομένων 2023---------------------------------------------------------------------
#|----------------------------------|
#| ΣΧΟΙΝΑΚΗ ΜΑΡΙΑ        : 3210191  |
#| ΒΕΡΟΥΧΗΣ ΕΛΕΥΘΕΡΙΟΣ   : 3200019  |
#|----------------------------------| 
#----------------------------------------------------------------------------Γραφήματα--------------------------------------------------------------------------------
import pyodbc
from mpl_toolkits.mplot3d import axes3d
import matplotlib.pyplot as plt
import numpy as np
#---------------------------------------------------------------------------------2d-------------------------------------------------------------------------------------
def twodbar(x, y, query):
    cursor = cnxn.cursor()
    cursor.execute(query)

    tx = []
    ty = []
    for i in cursor:
        tx.append(i[0])
        ty.append(i[1])
    
    plt.bar(tx, ty)
    plt.xlabel(x)
    plt.ylabel(y)
    plt.show()
#---------------------------------------------------------------------------------3d-------------------------------------------------------------------------------------
def threedbar(x, y, z, query):
    cursor = cnxn.cursor()
    cursor.execute(query)

    tx = []
    ty = []
    tz = []
    for i in cursor:
        tx.append(i[0])
        ty.append(i[1])
        tz.append(i[2])

    xDict = {}
    xtable = []
    for genre in tx:
        if genre not in xDict:
            xDict[genre] = len(xDict)
            xtable.append(len(xDict) - 1)
        else:
            xtable.append(xDict[genre])

    dz = np.zeros(len(tx))
    dx = np.ones(len(tx)) * 0.01
    dy = np.ones(len(tx))

    fig = plt.figure()
    ax1 = fig.add_subplot(111, projection='3d')
    ax1.set_facecolor((1.0, 1.0, 1.0))
    ax1.set_xlabel(x)
    ax1.set_ylabel(y)
    ax1.set_zlabel(z)

    ax1.bar3d(xtable, ty, dz, dx, dy, tz)

    plt.xticks(range(len(xDict.values())), xDict.keys())
    plt.show()
#---------------------------------------------------------------------------------scatter-------------------------------------------------------------------------------------
def scatter(x, y, query):
    cursor = cnxn.cursor()
    cursor.execute(query)

    tx = []
    ty = []
    for i in cursor:
        tx.append(i[0])
        ty.append(i[1])

    plt.xlabel(y)
    plt.ylabel(x)
    plt.scatter(ty, tx, s=1)
    plt.show()
#---------------------------------------------------------------------------------Data-Base-------------------------------------------------------------------------------------
server = 'mysqlserver32101913200019.database.windows.net'
database = 'ExercisesDatabase'
username = 'examiner'
password = '3210191.!AA&3200019'

cnxn = pyodbc.connect('DRIVER={ODBC Driver 17 for SQL Server};SERVER='+server+';DATABASE='+database+';UID='+username+';PWD='+ password)
#---------------------------------------------------------------------------------1-------------------------------------------------------------------------------------
twodbar("year", "movies_per_year", "SELECT YEAR(m.release_date) AS year, COUNT(m.id) AS  movies_per_year FROM movie m WHERE budget > 1000000 GROUP BY YEAR(m.release_date) ORDER BY YEAR(m.release_date)")
#---------------------------------------------------------------------------------2-------------------------------------------------------------------------------------
twodbar("genre", "movies_per_genre", "SELECT g.name AS genre, COUNT(hg.movie_id) AS movies_per_genre FROM movie m INNER JOIN hasGenre hg ON m.id = hg.movie_id INNER JOIN Genre g ON g.id = hg.genre_id WHERE (m.budget > 1000000 OR runtime > 120) GROUP BY g.name ORDER BY g.name")
#---------------------------------------------------------------------------------3-------------------------------------------------------------------------------------
threedbar("genre", "year", 'movies_per_gy', "SELECT g.name AS genre, YEAR(m.release_date) AS year, COUNT(hg.movie_id) AS movies_per_gy FROM movie m INNER JOIN hasGenre hg ON m.id = hg.movie_id INNER JOIN Genre g ON g.id = hg.genre_id WHERE YEAR(m.release_date) IS NOT NULL GROUP BY g.name, YEAR(m.release_date) ORDER BY g.name, YEAR(m.release_date)")
#---------------------------------------------------------------------------------4-------------------------------------------------------------------------------------
twodbar("year", "revenues_per_year", "SELECT YEAR(m.release_date) AS year, SUM(CAST(m.revenue AS bigint)) AS revenues_per_year FROM movie m INNER JOIN movie_cast mc ON m.id = mc.movie_id WHERE (mc.name = 'Tom Hanks') GROUP BY YEAR(m.release_date), mc.name ORDER BY YEAR(m.release_date)")
#---------------------------------------------------------------------------------5-------------------------------------------------------------------------------------
twodbar("year", "max_budget", "SELECT YEAR(m.release_date) AS year, MAX(m.budget) AS max_budget FROM movie m WHERE m.budget <> 0 GROUP BY YEAR(m.release_date) ORDER BY YEAR(m.release_date)")
#---------------------------------------------------------------------------------7-------------------------------------------------------------------------------------
scatter("avg_rating", "rating_count", "SELECT AVG(r.rating) AS avg_rating, COUNT(*) AS rating_count FROM ratings r GROUP BY r.user_id ORDER BY r.user_id")