#-----------------------------------------------------------list------------------------------------------------------------------------
def ls(s):
    """
    This function creates the initial list
    """
    ls1 = []
    ls1=[[" " for i in range(s + 1)] for j in range(s + 1)]
    for i in range(s + 1):
        ls1[0][i] = str(i)
        if i == 0:
            ls1[0][0] = " "   
    ls1[1][0] = "A"
    ls1[2][0] = "B"
    ls1[3][0] = "C"
    ls1[4][0] = "D"
    ls1[5][0] = "E"
    if s >= 6:
        ls1[6][0] = "F"
        if s >= 7:
            ls1[7][0] = "G"
            if s >= 8:
                ls1[8][0] = "H"
                if s >= 9:
                    ls1[9][0] = "I"
                    if s >= 10:
                        ls1[10][0] = "J"
    return ls1[:s + 1]
#-----------------------------------------------------------table-----------------------------------------------------------------------
def table(col, ls1):
    """
    This function creates the board
    """
    #header 
    output = ""
    for i in range(1, col + 1):
        output += "    " + str(i)
    output += "\n"    
    output += "-" * 5 * col  
    #each row   
    for i in range(col):
        output +="\n"
        for j in range(col + 1):
            output += str(ls1[i + 1][j])   
            output +="|   "      
    output += "\n"    
    output += "-" * 5 * col
    return output 
#---------------------------------------------------------next-free-row-----------------------------------------------------------------
def next_row(ls1, pioni, col):
    """
    This function returns the next free row, in the column the player chooses
    """
    i = col
    while i >= 1:
        if ls1[i][pioni] == " ":
            return i   
        i = i - 1
#------------------------------------------------------------valid----------------------------------------------------------------------
def is_val(ls1, pioni, row):
    """
    This function checks if the column chosed can be selected
    """
    if row == None:
        return False
    return ls1[row][pioni] == " "               
#---------------------------------------------------putting-"X"-or-"O"-in-table---------------------------------------------------------
def drop_piece(ls1, row, pioni, gramma):
    """
    This function updates the list with the players checker
    """
    ls1[row][pioni] = gramma
    return ls1
#--------------------------------------------------------------win----------------------------------------------------------------------       
def win(col, gramma, ls1):
    """
    This function checks if a player wins
    """
    #horizontally
    for i in range(col, 0, -1):
        for j in range(1, col - 2):
            if ls1[i][j] == gramma and ls1[i][j + 1] == gramma and ls1[i][j + 2] == gramma and ls1[i][j + 3] == gramma:
                return True
    #vertically
    for i in range(1, col - 2):
        for j in range(1, col):
            if ls1[i][j] == gramma and ls1[i + 1][j] == gramma and ls1[i + 2][j] == gramma and ls1[i + 3][j] == gramma:
                return True
    #diagonally up
    for i in range(-1, -(col - 2), -1):
        for j in range(1, col - 2):
            if ls1[i][j] == gramma and ls1[i - 1][j + 1] == gramma and ls1[i - 2][j + 2] == gramma and ls1[i - 3][j + 3] == gramma:
                return True            
    #diagonally down
    for i in range(1, col - 2):
        for j in range(1, col - 2):
            if ls1[i][j] == gramma and ls1[i + 1][j + 1] == gramma and ls1[i + 2][j + 2] == gramma and ls1[i + 3][j + 3] == gramma:
                return True                                                                     
#-------------------------------------------------------------star1---------------------------------------------------------------------
def star1(col, ls1, gramma):
    """
    This function returns the values i, j from who, the basic tetrads start
    """
    #horizontally
    for i in range(col, 0, -1):
        for j in range(1, col - 2):
            if ls1[i][j] == gramma and ls1[i][j + 1] == gramma and ls1[i][j + 2] == gramma and ls1[i][j + 3] == gramma:
                return i, j 
    #vertically
    for i in range(1, col - 2):
        for j in range(1, col):
            if ls1[i][j] == gramma and ls1[i + 1][j] == gramma and ls1[i + 2][j] == gramma and ls1[i + 3][j] == gramma:
                return i, j
    #diagonally up
    for i in range(-1, -(col - 2), -1):
        for j in range(1, col - 2):
            if ls1[i][j] == gramma and ls1[i - 1][j + 1] == gramma and ls1[i - 2][j + 2] == gramma and ls1[i - 3][j + 3] == gramma:
                return i, j            
    #diagonally down
    for i in range(1, col - 2):
        for j in range(1, col - 2):
            if ls1[i][j] == gramma and ls1[i + 1][j + 1] == gramma and ls1[i + 2][j + 2] == gramma and ls1[i + 3][j + 3] == gramma:
                return i, j                                      
#----------------------------------------------------------win-location-----------------------------------------------------------------
def win_loc(col, ls1, gramma):
    """
    This function returns whether the win is vertically, horizontally, down diagonally or up diagonally
    """
    #horizontally
    for i in range(col, 0, -1):
        for j in range(1, col - 2):
            if ls1[i][j] == gramma and ls1[i][j + 1] == gramma and ls1[i][j + 2] == gramma and ls1[i][j + 3] == gramma:
                return "horizontal"
    #vertically
    for i in range(1, col - 2):
        for j in range(1, col):
            if ls1[i][j] == gramma and ls1[i + 1][j] == gramma and ls1[i + 2][j] == gramma and ls1[i + 3][j] == gramma:
                return "vertical"
    #diagonally up
    for i in range(-1, -(col - 2), -1):
        for j in range(1, col - 2):
            if ls1[i][j] == gramma and ls1[i - 1][j + 1] == gramma and ls1[i - 2][j + 2] == gramma and ls1[i - 3][j + 3] == gramma:
                return "diagonal-up"            
    #diagonally down
    for i in range(1, col - 2):
        for j in range(1, col - 2):
            if ls1[i][j] == gramma and ls1[i + 1][j + 1] == gramma and ls1[i + 2][j + 2] == gramma and ls1[i + 3][j + 3] == gramma:
                return "diagonal-down"                                       
#------------------------------------------------------------table1---------------------------------------------------------------------
def table1(col, ls1, gramma, win_loc, star1):
    """
    This function puts the stars at the winning possitions
    """
    if win_loc(col, ls1, gramma) == "horizontal":
        n, k = star1(col, ls1, gramma)
        ls1[n][k] = "*"
        ls1[n][k + 1] = "*"
        ls1[n][k + 2] = "*"
        ls1[n][k + 3] = "*"
    elif win_loc(col, ls1, gramma) == "vertical":
        n, k = star1(col, ls1, gramma)
        ls1[n][k] = "*"
        ls1[n + 1][k] = "*"
        ls1[n + 2][k] = "*"
        ls1[n + 3][k] = "*"
    elif win_loc(col, ls1, gramma) == "diagonal-up":
        n, k = star1(col, ls1, gramma)
        ls1[n][k] = "*"
        ls1[n - 1][k + 1] = "*"
        ls1[n - 2][k + 2] = "*"
        ls1[n - 3][k + 3] = "*"
    else:
        n, k = star1(col, ls1, gramma)
        ls1[n][k] = "*"
        ls1[n + 1][k + 1] = "*"
        ls1[n + 2][k + 2] = "*"
        ls1[n + 3][k + 3] = "*"     
    return ls1
#------------------------------------------------------------table2---------------------------------------------------------------------     
def table2(col, ls1, gramma, win_loc, star1, star2):
    """
    This function abstracts the winning checkers
    """
#horizontally
    if win_loc(col, ls1, star2) == "horizontal":
        n, k = star1(col, ls1, star2)
        for i in range(n, 0, -1):
            for j in range(k, k + 4):
                if i == 1:
                    ls1[i][j] = " "
                else:
                    ls1[i][j] = ls1[i - 1][j]
            for j in range(1, k):
                if ls1[i][j] == gramma:
                    if i == 1:
                        ls1[i][j] = " "
                    else:
                        ls1[i][j] = ls1[i - 1][j]
                else:
                    break
            for j in range(k + 4, col + 1):
                if ls1[i][j] == gramma:
                    if i == 1:
                        ls1[i][j] = " "
                    else:
                        ls1[i][j] = ls1[i - 1][j]
                else:
                    break                            
#vertically                
    elif win_loc(col, ls1, star2) == "vertical":
        n, k = star1(col, ls1, star2)
        ls1[n][k] = " "
        ls1[n + 1][k] = " "
        ls1[n + 2][k] = " "
        ls1[n + 3][k] = " "
#diagonally up    
    elif win_loc(col, ls1, star2) == "diagonal-up":
        n, k = star1(col, ls1, star2)
        for i in range(n, -col, -1):
            ls1[i][k] = ls1[i - 1][k]
        for i in range(n - 1, -col, -1):
            ls1[i][k + 1] = ls1[i - 1][k + 1]
        for i in range(n - 2, -col, -1):
            ls1[i][k + 2] = ls1[i - 1][k + 2]
        for i in range(n - 3, -col, -1):
            ls1[i][k + 3] = ls1[i - 1][k + 3]
        for i in range(k, k + 4):
            ls1[1][i] = " "         
        i = k + 4           
        for j in range(n - 4, -(col + 1), -1):
            if i == col + 1:
                break
            if ls1[j][i] == gramma:
                for l in range(j, -col, -1):
                    ls1[l][i] = ls1[l - 1][i]
                ls1[1][i] = " "    
            i += 1          
#diagonally down
    else:
        n, k = star1(col, ls1, star2) 
        for i in range(n, 1, -1):
            ls1[i][k] = ls1[i - 1][k]
        for i in range(n + 1, 1, -1):
            ls1[i][k + 1] = ls1[i - 1][k + 1]
        for i in range(n + 2, 1, -1):
            ls1[i][k + 2] = ls1[i - 1][k + 2]
        for i in range(n + 3, 1, -1):
            ls1[i][k + 3] = ls1[i - 1][k + 3]
        for i in range(k, k + 4):
            ls1[1][i] = " " 
        i = k + 4 
        for j in range(n + 1, col):
            if i == col + 1:
                break
            if ls1[j][i] == gramma:
                for l in range(j, 1, -1):
                    ls1[l][i] = ls1[l - 1][i]
                ls1[1][i] = " "    
            i += 1                       
    return ls1 
#------------------------------------------------------------points---------------------------------------------------------------------     
def point(col, ls1, gramma, win_loc, star1, star2):
    """
    This function counts the points
    """
    points = 4
#horizontally
    if win_loc(col, ls1, star2) == "horizontal":
        n, k = star1(col, ls1, star2)
        for i in range(k - 1, 0, -1):
            if ls1[n][i] == gramma:
                points += 1
            else:
                break    
        for i in range(k + 4, col + 1):
            if ls1[n][i] == gramma:
                points += 1
            else:
                break    
#diagonally up    
    elif win_loc(col, ls1, star2) == "diagonal-up":
        n, k = star1(col, ls1, star2)        
        i = k + 4           
        for j in range(n - 4, -(col + 1), -1):
            if i == col + 1:
                break
            if ls1[j][i] == gramma:
                points += 1
            i += 1
#diagonally down
    else:
        n, k = star1(col, ls1, star2)
        i = k + 4 
        for j in range(n + 1, col):
            if i == col + 1:
                break
            if ls1[j][i] == gramma:
                points += 1
            i += 1                   
    return points
#-------------------------------------------------------stoixeia-ston-pinaka------------------------------------------------------------
def stoixeia(col, ls1):
    """
    This function counts the elements of the table
    """
    s = 0
    for i in range(col, 0, -1):
        for j in range(1, col + 1):
            if ls1[i][j] != " ":
                s += 1
    return s            
#-----------------------------------------------------------isopalia--------------------------------------------------------------------
def end(col, ls1):
    """
    This function checks if there is a draw
    """
    s = 0
    for i in range(1, col + 1):
        for j in range(1, col + 1):
            if ls1[i][j] != " ":
                s += 1
    if s == col ** 2:
        return True            
#-----------------------------------------------------------save-list-------------------------------------------------------------------         
def save_list(ls1, col):
    """
    This function makes the saved list
    """
    ls2 = []
    ls2=[[" " for i in range(col)] for j in range(col)]
    for i in range(col):
        for j in range(col):
            if ls1[i + 1][j + 1] == " ":
                ls2[i][j] = 0
            elif ls1[i + 1][j + 1] == "O":
                ls2[i][j] = 1
            elif ls1[i + 1][j + 1] == "X":
                ls2[i][j] = 2
    return ls2 
#-----------------------------------------------------------save-game-------------------------------------------------------------------                                            
def save(ls2, score, name):
    """
    This function stores the state of the game
    """
    import csv
    with open(name, 'w', newline='') as csvfile:
        writer = csv.writer(csvfile)
        for i in range(len(ls2)):    
            writer.writerow(ls2[i])
        writer.writerow(score)    
    print("The game has been saved at the file with name", name)
#---------------------------------------------------------continue-game-----------------------------------------------------------------         
def continue_game(name):
    """
    This function gets the state of a saved game
    """
    import csv
    with open(name, newline='') as csvfile:
        reader = csv.reader(csvfile)
        i = 1
        score = []
        for row in reader:
            if i == 1:
                j = len(row)
                ls1 = ls(j)
            for k in range(1, len(ls1)):
                if i == len(ls1):
                    score = row
                else:    
                    ls1[i][k] = row[k - 1]
            i += 1                           
        for i in range(1, len(ls1)):
            for k in range(1, len(ls1)):     
                if ls1[i][k] == '0':
                    ls1[i][k] = " "
                elif ls1[i][k] == '1':
                    ls1[i][k] = "O"
                else:
                    ls1[i][k] = "X"                           
        return ls1, score
def game():
    game_over = False
    turn = 0
    score = [0, 0]
    print("Welcome to the game!")
    dec = input("Do you wish to start a new game (N) or load a saved one (S); ")
    while dec != "N" and dec != "Ν" and dec != "S":
        print("Choose (Ν) or (S)!")
        dec = input("Type (N) to start a new game or (S) to load a saved one: ")
    #Φόρτωση παλιού παιχνιδιού
    if dec == "S":
        name = input("Type files name: ")
        ls1, score = continue_game(name)
        print(table(len(ls1) - 1, ls1))
        points1, points2 = int(score[0]), int(score[1])
        col = len(ls1) - 1
        gyros = int(input("Choose the number of rounds you want to play: "))
        gyros1 = 1
    #Καινούριο παιχνίδι        
    elif dec == "N" or dec == "Ν":
        gyros = int(input("Choose the number of rounds you want to play: "))
        gyros1 = 1
        col = int(input("Choose number of columns to start the game!(5-10): "))
        while col < 5 or col > 10:
            print("Out of bounds. Try again!")
            col = int(input("Choose a number between 5-10: "))
        ls1 = ls(col)
#Points
        points1, points2 = 0, 0
        print(table(col, ls1))        
    while not game_over:
        i = stoixeia(col, ls1)
        while i <= col ** 2:
#Player 1 input
            if turn == 0:
                print("Press any key to continue.")
                paush = input("For the game to be paused press s: ")
                if paush == "s":
                    name = input("Give a name for the file: ")
                    ls2 = save_list(ls1, col)
                    save(ls2, score, name)
                    game_over = True
                    break   
                print("Round", gyros1)
#-------------------------------limits-for-pioni---------------------------------------------------------            
                pioni = int(input("Player 1 is your turn. Choose column: "))
                while pioni < 1 and pioni > col:
                    print("Choose a column between:1-"+ str(col) + "!")
                    pioni = int(input("Player 1 try again: "))      
                turn = 1
                gramma = "O"
                row = next_row(ls1, pioni, col)
                if is_val(ls1, pioni, row):
                    drop_piece(ls1, row, pioni, gramma)
                else:
                    while is_val(ls1, pioni, row) == False:
                        print("This column is full!")
                        pioni = int(input("Player 1 try again: "))
                        row = next_row(ls1, pioni, col)
                    drop_piece(ls1, row, pioni, gramma) 
    #Player 2 input       
            else:
                print("Round", gyros1)
#-------------------------------limits-for-pioni---------------------------------------------------------            
                pioni = int(input("Player 2 is your turn. Choose column: "))
                while pioni < 1 and pioni > col:
                    print("Choose a column between:1-"+ str(col) + "!")
                    pioni = int(input("Player 2 try again: "))  
                turn = 0
                gramma = "X"
                row = next_row(ls1, pioni, col)
                if is_val(ls1, pioni, row):
                    drop_piece(ls1, row, pioni, gramma)
                else:
                    while is_val(ls1, pioni, row) == False:
                        print("This column is full!")
                        pioni = int(input("Player 2 try again: "))
                        row = next_row(ls1, pioni, col)
                    drop_piece(ls1, row, pioni, gramma)   
            print(table(col, ls1))
            if win(col, gramma, ls1) == True:
                i = 100000
                print(table(col, table1(col, ls1, gramma, win_loc, star1)))
                gyros1 += 1
                if gramma == "O":
                    points1 += point(col, ls1, gramma, win_loc, star1, "*")
                else:
                    points2 += point(col, ls1, gramma, win_loc, star1, "*")
                score = [points1, points2]        
                print(table(col, table2(col, ls1, gramma, win_loc, star1, "*")))        
            if end(col, ls1) == True:
                i = 100000
                print("Board is full!")
                gyros1 += 1
                game_over = True
            if gyros1 == gyros + 1:
                game_over = True                 
                i += 1                    
    if points1 > points2:
        print("Player 1 wins!")
    elif points2 > points1:
        print("Player 2 wins!")
    else:
        print("Draw!")
    game()                 
game()