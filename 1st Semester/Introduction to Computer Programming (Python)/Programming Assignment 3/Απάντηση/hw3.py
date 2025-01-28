#--------------------- Askisi 1 -----------------------

def location(name, lat, lon, type):
    """Kataskeuazei syn8eto dedomeno topo8esias (location).

    name -- onoma (str)
    lat -- gewfrafiko platos (se moires)
    lon -- gewgrafiko mikos (se moires)
    type -- eidos topo8esias (str)

    Epistrefei dedomeno pou anaparista tin topo8esia me onoma name h opoia
    brisketai sto gewgrafiko platos kai mikos lat kai lon antistoixa. To type
    einai string pou perigrafei to eidos tis topo8esias, p.x., 'monument',
    'bus station'.
    """
    return [name, lat, lon, type]


def name(loc):
    """Epistrefei to onoma mias topo8esias.

    loc -- topo8esia (typou location)

    Epistrefei to onoma (str) tis topo8esias loc.

    >>> monast = location('Monastiraki', 37.976362, 23.725947, 'square')
    >>> name(monast)
    'Monastiraki'
    """
    return loc[0]

def longitude(loc):
    """Gewgrafiko mikos.

    loc -- dedomeno location

    Epistrefei gewgrafiko mikos tis topo8esias loc

    >>> monast = location('Monastiraki', 37.976362, 23.725947, 'square')
    >>> longitude(monast)
    23.725947
    """
    return loc[2]

def lattitude(loc):
    """Gewgrafiko platos.

    loc -- dedomeno location

    Epistrefei gewgrafiko mikos tis topo8esias loc

    >>> monast = location('Monastiraki', 37.976362, 23.725947, 'square')
    >>> lattitude(monast)
    37.976362
    """
    return loc[1]


def type(loc):
    """Eidos topo8esias.

    loc -- dedomeno location

    Epistrefei string pou perigrafei to eidos tis topo8esias loc, p.x.,
    'monument', 'bus station'.

    >>> monast = location('Monastiraki', 37.976362, 23.725947, 'square')
    >>> type(monast)
    'square'
    """
    return loc[3]


#--------------------- Askisi 2 -----------------------

def print_location(loc):
    """Emfanizei stoixeia topo8esias.

    loc -- dedomeno location

    Emfanizei stoixeia gia tin topo8esia loc opws sta paradeigmata:

    >>> monast = location('Monastiraki', 37.976362, 23.725947, 'square')
    >>> print_location(monast)
    Monastiraki (square) at coordinates 37.976362, 23.725947
    >>> print_location(location('North Pole', 90.0, 135.0, 'pole'))
    North Pole (pole) at coordinates 90.0, 135.0
    """
    print(name(loc) + ' (' + type(loc) + ') at coordinates ' \
          + str(lattitude(loc)) + ', ' + str(longitude(loc)))


def distance(a, b):
    """Apostasi meta3y topo88esiwn.

    a -- topo8esia A (dedomeno typou location)
    b -- topo8esia B (dedomeno typou location)

    Epistrefei tin apostasi (Manhattan distance) 
    meta3y ths topo8esias A kai B se xiliometra.

    >>> aueb = location('AUEB', 37.994097, 23.732253, 'university campus')
    >>> monast = location('Monastiraki', 37.976362, 23.725947, 'square')
    >>> distance(aueb, monast)
    2.5224714882938657
    >>> distance(aueb, aueb)
    0.0
    """
    a_lat = lattitude(a) # gewgrafiko platos (lattitude) a
    a_lon = longitude(a) # gewgrafiko mikos (longitude) a
    b_lat = lattitude(b) # gewgrafiko platos (lattitude) b
    b_lon = longitude(b) # gewgrafiko mikos (longitude) b

    from math import pi, cos
    phi_m = pi/180 * (a_lat + b_lat) / 2
    k1 = 111.13209 - 0.56605 * cos(2*phi_m) + 0.00120 * cos(4*phi_m)
    k2 = 111.41513 * cos(phi_m) - 0.0945 * cos(3*phi_m) + 0.00012*cos(5*phi_m)
    lat_dist = (a_lat - b_lat) * k1
    lon_dist = (a_lon - b_lon) * k2
    return abs(lon_dist) + abs(lat_dist)


def nearest_location(loc, loc_list, loc_type=None):
    """Epistrefei plisiesteri topo8esia.

    loc -- topo8esia (dedomeno typoy location)
    loc_list -- lista pou periexei topo8esies (dedomena location)
    loc_type -- eidos topo8esias (str)

    Epistrefei tin plisiesteri topo8esia stin loc apo autes pou briskonai sti
    lista loc_list tou eidous loc_type.

    Paradeigmata:
    >>> llist = [location('AUEB', 37.994097, 23.732253, 'university campus'),\
                  location('Acropolis', 37.971584, 23.725912, 'monument'), \
                  location('Syntagma', 37.975560, 23.734691, 'square'), \
                  location('National Garden', 37.973116, 23.736483, 'park'), \
                  location('Monastiraki', 37.976362, 23.725947, 'square')]
    >>> name(nearest_location(llist[2], llist, 'monument'))
    'Acropolis'
    >>> name(nearest_location(llist[1], llist, 'square'))
    'Monastiraki'
    >>> name(nearest_location(llist[2], llist))
    'National Garden'
    >>> name(nearest_location(llist[2], llist, 'square'))
    'Monastiraki'
    """
    return min([x for x in loc_list if x != loc and (type(x) == loc_type or loc_type==None)], \
               key = lambda y: distance(y, loc)) 


#--------------------- Askisi 3 -----------------------

def pick_cherries_only():
    """Emfanizei string pou briskontai se fwliasmenes listes.

    Prepei na exei to akolou8o apotelesma:

    >>> pick_cherries_only()
    cherry1
    cherry2
    cherry3
    cherry4
    Yay!!!
    """
    fruits = ['cherry1', 'orange', \
              ['grape', 'cherry2', ['cherry3'], 'banana'], \
              None, 'cherry4', [[['Yay!!!']]]]

    print(fruits[0])
    print(fruits[2][1])
    print(fruits[2][2][0])
    print(fruits[4])
    print(fruits[5][0][0][0])


#--------------------- Askisi 4 -----------------------

def pick_cherries_onebyone():
    """Emfanizei string pou briskontai se fwliasmenes listes.

    Prepei na exei to akolou8o apotelesma:

    >>> pick_cherries_onebyone()
    cherry1
    cherry2
    cherry3
    cherry4
    last cherry
    """
    cherry_field = ['cherry1', ['cherry2', ['cherry3', ['cherry4', ['last cherry', None]]]]]

    print(cherry_field[0])
    cherry_field = cherry_field[1]
    print(cherry_field[0])
    cherry_field = cherry_field[1]
    print(cherry_field[0])
    cherry_field = cherry_field[1]
    print(cherry_field[0])
    cherry_field = cherry_field[1]
    print(cherry_field[0])


#--------------------- Askisi 5 -----------------------

def pick_cherries(field):
    """Emfanizei string pou briskontai se fwliasmenes listes.

    field -- lista me fwliasmena string. Ka8e lista exei dyo stoixeia: 
    to prwto einai string kai to deutero einai eite lista ths idias 
    morfhs 'h None. (Opws kai h cherry_field sto swma ths synarthshs 
    pick_cherries_onebyone()).

    Leitoyrgei opws i pick_cherries_onebyone, omws gia au8aireta polles
    fwliasmenes listes stin field.

    Paradeigmata:

    >>> cherry_field = ['cherry1', ['cherry2', ['cherry3', ['cherry4', ['last cherry', None]]]]]
    >>> pick_cherries(cherry_field)
    cherry1
    cherry2
    cherry3
    cherry4
    last cherry
    >>> pick_cherries(['Hello', ['world', None]])
    Hello
    world
    """
    while field != None:
        print(field[0])
        field = field[1]


#--------------------- Askisi 6 -----------------------

def all_iter(func, ls):
    """True mono ean i klisi func(x) epistrefei True 
    gia ola ta stoixeia x tis ls.

    func -- synartisi enos orismatos
    ls -- lista

    Paradeigmata:
    >>> all_iter(lambda x: x >= 0, [1, 2, 3, 0, 4])
    True
    >>> all_iter(lambda x: x >= 0, [1, 2, -3, 0, 4])
    False
    >>> all_iter(lambda x: x % 2 == 0, [100, 10, 2022, 12])
    True
    """
    """XRHSIMOPOIHSTE EPANALHPTIKO YPOLOGISMO (for 'h while).
    MHN XRHSIMOPOIHSETE LIST COMPREHENSIONS 'h ANADROMI."""
    """GRAPSTE TON KWDIKA SAS APO KATW."""
    for x in ls:
        if not func(x):
            return False
    return True


#--------------------- Askisi 7 -----------------------

def all_rec(func, ls):
    """True mono ean i klisi func(x) epistrefei True 
    gia ola ta stoixeia x tis ls.

    func -- synartisi enos orismatos
    ls -- lista

    Paradeigmata:
    >>> all_rec(lambda x: x >= 0, [1, 2, 3, 0, 4])
    True
    >>> all_rec(lambda x: x >= 0, [1, 2, -3, 0, 4])
    False
    >>> all_rec(lambda x: x % 2 == 0, [100, 10, 2022, 12])
    True
    """
    """XRHSIMOPOIHSTE ANADROMH.
    MHN XRHSIMOPOIHSETE LIST COMPREHENSIONS 'h EPANALHPTIKO YPOLOGISMO 
    (for 'h while)."""
    """GRAPSTE TON KWDIKA SAS APO KATW."""
    if len(ls) == 0:
        return True
    else:
        return func(ls[0]) and all_rec(func, ls[1:])

    
#--------------------- Askisi 8 -----------------------

def all_lc(func, ls):
    """True mono ean i klisi func(x) epistrefei True 
    gia ola ta stoixeia x tis ls.

    func -- synartisi enos orismatos
    ls -- lista

    Paradeigmata:
    >>> all_lc(lambda x: x >= 0, [1, 2, 3, 0, 4])
    True
    >>> all_lc(lambda x: x >= 0, [1, 2, -3, 0, 4])
    False
    >>> all_lc(lambda x: x % 2 == 0, [100, 10, 2022, 12])
    True
    """
    """XRHSIMOPOIHSTE EPE3ERGASIA AKOLOU8IWN ME LIST COMPREHENSIONS.
    MHN XRHSIMOPOIHSETE ANADROMH 'h EPANALHPTIKO YPOLOGISMO 
    (for 'h while)."""
    """GRAPSTE TON KWDIKA SAS APO KATW."""
    return sum([func(x) for x in ls]) == len(ls)


#--------------------- Askisi 9 -----------------------

def all_hof(func, ls):
    """True mono ean i klisi func(x) epistrefei True 
    gia ola ta stoixeia x tis ls.

    func -- synartisi enos orismatos
    ls -- lista

    Paradeigmata:
    >>> all_hof(lambda x: x >= 0, [1, 2, 3, 0, 4])
    True
    >>> all_hof(lambda x: x >= 0, [1, 2, -3, 0, 4])
    False
    >>> all_hof(lambda x: x % 2 == 0, [100, 10, 2022, 12])
    True
    """
    """XRHSIMOPOIHSTE EPE3ERGASIA AKOLOU8IWN ME SYNARTHSEIS ANWTEROY EPIPEDOY
    (map, filter, reduce).
    MHN XRHSIMOPOIHSETE ANADROMH, EPANALHPTIKO YPOLOGISMO (for 'h while)
    'h LIST COMPREHENSIONS."""
    """GRAPSTE TON KWDIKA SAS APO KATW."""
    from functools import reduce
    return reduce(lambda x, y: x and y, map(func, ls))


#--------------------- Askisi 10 -----------------------

def primes_up_to(n):
    """Prwtoi ari8moi ews to n.

    n -- akeraios >= 2

    Epistrefei lista me olous tous prwtous ari8mous mikroterous 'h isous me n.

    Paradeigmata:
    >>> primes_up_to(10)
    [2, 3, 5, 7]
    >>> primes_up_to(20)
    [2, 3, 5, 7, 11, 13, 17, 19]
    >>> len(primes_up_to(10000))
    1229
    """
    """XRHSIMOPOIHSTE EPE3ERGASIA AKOLOU8IWN ME LIST COMPREHENSIONS 'h
    SYNARTHSEIS ANWTEROY EPIPEDOY (map, reduce, filter)."""
    """GRAPSTE TON KWDIKA SAS APO KATW."""
    ls, i = list(range(2, n + 1)), 0
    while True:
        ls, i = list(filter(lambda x: x % ls[i] != 0 or x == ls[i], ls)), i + 1
        if i == len(ls):
            break
    return ls
