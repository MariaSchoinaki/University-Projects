"""Conway\'s Game of Life."""

#---------------------- Askisi 1 ------------------------
def board(n):
    """Kataskeuastis board (pinaka paixnidiou).

    n -- parametros diastasis pinaka

    Kataskeuazei anaparastasi pinaka paixnidiou me n x n kelia, opou
    kanena keli den einai zwntano arxika. 

    O pinakas anaparistatai ws le3iko (dict) me n * n stoixeia.
    Ka8e keli antistoixei se ena stoixeio me key (kleidi) to tuple (i,j), 
    opou i o ari8mos grammis kai j o ari8mos stilis pou brisketai to keli. 
    (H ari8misi grammwn kai sthlwn einai apo 0 ews n-1. To panw aristera keli
    brisketai sto (0,0).)
    H timi (value) tou stoixeiou einai True 'h False analoga 
    ean to keli einai zwntano 'h oxi.

    Paradeigmata:

    >>> game = board(3)
    >>> len(game)
    9
    >>> game
    {(0, 0): False, (0, 1): False, (0, 2): False, (1, 0): False, (1, 1): False, (1, 2): False, (2, 0): False, (2, 1): False, (2, 2): False}
    >>> game[2,1]
    False
    """
    value = {}
    for i in range(n):
        for j in range(n):
            value[(i, j)] = False
    return value

#---------------------- Askisi 2 ------------------------
def is_alive(board, p):
    """Elegxei ean ena keli einai zwntano.

    board -- o pinakas paixnidiou opou brisketai to keli
    p -- h 8esh tou keliou.

    To orisma p einai tuple tis morfis (i,j).
    Epistrefei True ean to keli einai zwntano, alliws False.

    Paradeigma:

    >>> is_alive(board(4), (3,2))
    False
    """
    if board[p]:
        return True
    else:
        return False 

def set_alive(board, p, alive):
    """Dimioyrgei 'h afairei zwi apo ena keli.

    board -- o pinakas paixnidiou opou brisketai to keli
    p -- h 8esh tou keliou (tuple tis morfis (i,j))
    alive -- logiki timi.

    To keli ginetai zwntano ean h alive einai True. An h alive einai False,
    to keli pe8ainei.

    Paradeigmata:

    >>> game = board(4)
    >>> is_alive(game, (3,2))
    False
    >>> set_alive(game, (3,2), True)
    >>> is_alive(game, (3,2))
    True
    >>> set_alive(game, (3,2), False)
    >>> is_alive(game, (3,2))
    False
    """
    if alive:
        board[p] = True
    else:    
        board[p] = False

def get_size(board):
    """Mege8os pinaka paixnidiou.

    board -- pinakas paixnidiou.

    Epistrefei n (timi int) ean to board anaparista pinaka paixnidiou n x n.

    Paradeigmata:

    >>> get_size(board(4))
    4
    >>> get_size(board(10))
    10
    """
    return int(len(board) ** 0.5)  

#---------------------- Askisi 3 ------------------------
def copy_board(board):
    """Kataskeui antigrafou pinaka paixnidiou.

    board -- pinakas paixnidiou.

    Epistrefei ena neo pinaka paixnidiou pou einai antigrafo tou board.

    Paradeigmata:

    >>> game = board(10)
    >>> set_alive(game, (4,7), True)
    >>> game2 = copy_board(game)
    >>> game2 is game
    False
    >>> is_alive(game2, (4,7))
    True
    """
    copy = dict(board)
    return copy

#---------------------- Askisi 4 ------------------------
def get_iterator(board):
    """Iterator gia sarwsi stoixeiwn pinaka paixnidiou.

    board -- pinakas paixnidiou.

    Epistrefei iterator pou dinei ta kelia tou board arxizontas apo 
    ta kelia tis grammis 0: (0,0), (0,1), (0,2),..., meta akolou8oun ta 
    kelia tis grammis 1: (1,0), (1,1), (1,2),... ktl. mexri na e3antli8oun
    ola ta kelia. Gia ka8e keli, o iterator epistrefei tin 8esi tou kai
    logiki timi True 'h False analogws ean einai zwntano 'h oxi.

    Paradeigma:

    >>> game = board(3)
    >>> set_alive(game, (2, 1), True)
    >>> for cell in get_iterator(game):
    ...     print(cell)
    ... 
    ((0, 0), False)
    ((0, 1), False)
    ((0, 2), False)
    ((1, 0), False)
    ((1, 1), False)
    ((1, 2), False)
    ((2, 0), False)
    ((2, 1), True)
    ((2, 2), False)
    """
    ls1 = []
    for i in range(get_size(copy_board(board))):
        for j in range(get_size(copy_board(board))):
            ls1.append(((i, j), board[(i, j)]))
    re = map(lambda x: x, ls1)
    return re

#---------------------- Askisi 5 ------------------------
def print_board(board):
    """Emfanizei pinaka paixnidiou.

    board -- pinakas paixnidiou.

    Emfanizei ton pinaka paixnidiou board opou me mauro tetragwno 
    (xaraktiras unicode 11035 'h xaraktiras 'x' and den emfanizetai swsta) 
    dinontai ta zwntana kelia, kai me aspro (xaraktiras unicode 11036 'h
    keno ' ' an den emfanizetai swsta) ta pe8amena. 
    To panw aristera keli einai auto sti 8esi (0,0).

    Paradeigma:

    >>> game = board(5)
    >>> set_alive(game, (0,0), True)
    >>> set_alive(game, (2,2), True)
    >>> set_alive(game, (4,4), True)
    >>> set_alive(game, (3,4), True)
    >>> set_alive(game, (0,4), True)
    >>> print_board(game)
    ⬛⬜⬜⬜⬛
    ⬜⬜⬜⬜⬜
    ⬜⬜⬛⬜⬜
    ⬜⬜⬜⬜⬛
    ⬜⬜⬜⬜⬛
    """ 
    ls = [[" " for i in range(get_size(copy_board(board)))] for j in range(get_size(copy_board(board)))]
    for i in range(get_size(copy_board(board))):
        for j in range(get_size(copy_board(board))):
            if is_alive(copy_board(board), (i, j)):
                ls[i][j] = chr(11035)
            else:
                ls[i][j] = chr(11036)
    for i in range(get_size(copy_board(board))):
        sum = ""
        for j in range(get_size(copy_board(board))):
            sum += ls[i][j]
        print(sum)

#---------------------- Askisi 6 ------------------------
def neighbors(p):
    """Geitonika kelia.

    p -- 8esh keliou (tuple tis morfis (i,j)).

    Epistrefei synolo (set) pou periexontai oi 8eseis twn 8 geitonikwn
    keliwn tou p. Sto epistrefomeno synolo den periexetai to keli p.

    Paradeigmata:

    >>> neighbors((3,2)) == {(3, 3), (3, 1), (2, 1), (2, 3), (4, 3), (2, 2), (4, 2), (4, 1)}
    True
    >>> neighbors((0,0)) == {(0, 1), (-1, 1), (-1, 0), (-1, -1), (0, -1), (1, 0), (1, -1), (1, 1)}
    True
    >>> neighbors((0,10)) == {(-1, 9), (1, 10), (-1, 11), (0, 11), (-1, 10), (1, 9), (0, 9), (1, 11)}
    True
    """
    value = set()
    m, k = p
    for i in range(m - 1, m + 2):
        value.add((i, k))
        for j in range(k - 1, k + 2):
            value.add((i, j))
    value.remove((m, k))
    return value  

#---------------------- Askisi 7 ------------------------
def place_blinker(board, p = (0,0)):
    """Topo8etisi blinker.

    board -- pinakas paixnidiou.
    p -- keli topo8etisis (tuple (i,j) me proka8orismeni timi (0,0))

    Topo8etei 3 zwntanous organismous sto board 
    se geitonika kelia tis idias stilis, arxizontas apo ti 8esi p kai
    proxwrontas stis parakatw grammes.

    Paradeigmata:
    
    >>> game = board(5)
    >>> place_blinker(game)
    >>> print_board(game)
    ⬛⬜⬜⬜⬜
    ⬛⬜⬜⬜⬜
    ⬛⬜⬜⬜⬜
    ⬜⬜⬜⬜⬜
    ⬜⬜⬜⬜⬜
    >>> place_blinker(game, (1,2))
    >>> print_board(game)
    ⬛⬜⬜⬜⬜
    ⬛⬜⬛⬜⬜
    ⬛⬜⬛⬜⬜
    ⬜⬜⬛⬜⬜
    ⬜⬜⬜⬜⬜
    >>> place_blinker(game, (4,4))
    >>> print_board(game)
    ⬛⬜⬜⬜⬜
    ⬛⬜⬛⬜⬜
    ⬛⬜⬛⬜⬜
    ⬜⬜⬛⬜⬜
    ⬜⬜⬜⬜⬛
    """
    m, k = p
    for i in range(m, m + 3):
        if i == get_size(copy_board(board)):
            break
        set_alive(board, (i, k), True)

def place_glider(board, p = (0,0)):
    """Topo8etisi glider.

    board -- pinakas paixnidiou.
    p -- keli topo8etisis (tuple (i,j) me proka8orismeni timi (0,0))

    Topo8etei 5 zwntanous organismous sto board se sximatismo glider
    arxizontas apo tin topo8esia p, opws fainetai sta parakatw paradeigmata. 
    Simeiwste oti to idio to keli p den einai zwntano.
    
    Paradeigmata:

    >>> game = board(7)
    >>> place_glider(game)
    >>> print_board(game)
    ⬜⬜⬛⬜⬜⬜⬜
    ⬛⬜⬛⬜⬜⬜⬜
    ⬜⬛⬛⬜⬜⬜⬜
    ⬜⬜⬜⬜⬜⬜⬜
    ⬜⬜⬜⬜⬜⬜⬜
    ⬜⬜⬜⬜⬜⬜⬜
    ⬜⬜⬜⬜⬜⬜⬜
    >>> place_glider(game, (3,3))
    >>> print_board(game)
    ⬜⬜⬛⬜⬜⬜⬜
    ⬛⬜⬛⬜⬜⬜⬜
    ⬜⬛⬛⬜⬜⬜⬜
    ⬜⬜⬜⬜⬜⬛⬜
    ⬜⬜⬜⬛⬜⬛⬜
    ⬜⬜⬜⬜⬛⬛⬜
    ⬜⬜⬜⬜⬜⬜⬜
    """ 
    m, k = p
    if k + 2 < get_size(copy_board(board)):
        set_alive(board, (m, k + 2), True)
    if k + 2 < get_size(copy_board(board)) and m + 1 < get_size(copy_board(board)):   
        set_alive(board, (m + 1, k + 2), True)
    if k + 2 < get_size(copy_board(board)) and m + 2 < get_size(copy_board(board)):    
        set_alive(board, (m + 2, k + 2), True)
    if m + 1 < get_size(copy_board(board)):
        set_alive(board, (m + 1, k), True)
    if k + 1 < get_size(copy_board(board)) and m + 2 < get_size(copy_board(board)):
        set_alive(board, (m + 2, k + 1), True)
    
#---------------------- Askisi 8 ------------------------
def tick(board):
    """Proxwraei to paixnidi kata ena bima stin epomeni genea.

    board -- pinakas paixnidiou.

    Allazei ton pinaka board proxwrontas mia genea, 
    symfwna me tous kanones tou Game of Life.

    Paradeigma:

    >>> game = board(6)
    >>> place_glider(game)
    >>> place_blinker(game, (3,4))
    >>> print_board(game)
    ⬜⬜⬛⬜⬜⬜
    ⬛⬜⬛⬜⬜⬜
    ⬜⬛⬛⬜⬜⬜
    ⬜⬜⬜⬜⬛⬜
    ⬜⬜⬜⬜⬛⬜
    ⬜⬜⬜⬜⬛⬜
    >>> tick(game)
    >>> print_board(game)
    ⬜⬛⬜⬜⬜⬜
    ⬜⬜⬛⬛⬜⬜
    ⬜⬛⬛⬛⬜⬜
    ⬜⬜⬜⬛⬜⬜
    ⬜⬜⬜⬛⬛⬛
    ⬜⬜⬜⬜⬜⬜
    """
    make_False = []
    make_True = []
    for i in range(get_size(copy_board(board))):
        for j in range(get_size(copy_board(board))):
            s = 0
            ls = list(neighbors((i, j)))
            for n in range(8):
                m, k = ls[n]
                if m < get_size(copy_board(board)) and m >= 0 and k < get_size(copy_board(board)) and k >= 0:
                    if is_alive(board, ls[n]):
                        s += 1           
            if is_alive(board, (i, j)):
                if s == 2 or s == 3:
                    make_True.append((i, j))
                else:
                    make_False.append((i, j))
            else:
                if s == 3:
                    make_True.append((i, j))
                else:
                    make_False.append((i, j))
    for i in range(len(make_False)):
        set_alive(board, make_False[i], False)
    for i in range(len(make_True)):
        set_alive(board, make_True[i], True)    

#---------------------- Askisi 9 ------------------------

if __name__ == '__main__':
    """Paizei to paixnidi tis zwis gia 100 genies.

    generations -- ari8mos bimatwn.

    Paizei to paixnidi gia mia sygkekrimeni arxiki topo8etisi, gia 
    100 genees. O pinakas tou paixnidiou emfanizetai se  ka8e bima.
    Afiste toulaxiston 2 kenes grammes anamesa se diadoxikous pinakes.
    """

    # Arxikos pinakas
    game = board(10)
    place_blinker(game, (1,2))
    place_glider(game, (2,4))

    from time import sleep

    for i in range(101):
        print_board(game)
        print()
        print()
        print()
        sleep(1)
        tick(game)
