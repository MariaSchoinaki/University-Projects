from operator import *
from math import sqrt

#>>>>>>>>>>>>>>>>>>>>> Askhsh 1 <<<<<<<<<<<<<<<

def f(x):
    """ 
    x -- ari8mos

    Epistrefei tin timi 1/(2 + 3/(x+4) + 1/x).

    >>> f(-1)
    0.5
    >>> round(f(-2), 4)
    0.3333
    >>> round(f(-8), 4)
    0.8889
    """
    """ Xrhsimopoihste MONO ekfraseis klhshs,
        p.x., stis add, mul, pow, sqrt, truediv, ...
        OXI en8ematikous telestes (+, /, ...) """

    return truediv(1,add(2,truediv(add(4,mul(4,x)),mul(x,add(x,4)))))


#>>>>>>>>>>>>>>>>>>>>> Askhsh 2 <<<<<<<<<<<<<<<

def g(x, y):
    """
    x,y -- ari8moi

    Epistrefei timi isi me sqrt(add(pow(add(x, y), 2), pow(sub(x, y), 2)))

    >>> g(1, 1)
    2.0
    >>> g(1.5, 1.5)
    3.0
    >>> round(g(3, 1), 5)
    4.47214
    """

    """ Xrhsimopoihste en8emetikous telestes(+, -, *, **, ...)
        OXI ekfraseis klhshs """

    return (2*x**2+2*y**2)**(1/2)



#>>>>>>>>>>>>>>>>>>>>> Askhsh 3 <<<<<<<<<<<<<<<
def how():
    """Symplhrwste ta kena wste na didetai to
       akolou8o apotelesma

    >>> how()
    1 2 3
    """
    x = -2
    y = -3
    z =  1

    x, y, z = y, z, -x
    x, y, z = y, z, -x

    print(x, y, z)



#>>>>>>>>>>>>>>>>>>>>> Askhsh 4 <<<<<<<<<<<<<<<

def func0(s):
    """Symplhrwste ta kena wste na didetai to
       akolou8o apotelesma

    >>> func0('I') + ' ' + func0('Bi') + ' ' + 'Spider'
    'Itsy Bitsy Spider'
    """

    return s + 'tsy'



#>>>>>>>>>>>>>>>>>>>>> Askhsh 5 <<<<<<<<<<<<<<<

def func1(n, c, s):
    """Symplhrwste ta kena wste:

    >>> func1(1, '*', '-')
    '*'
    >>> func1(2, '*', '-')
    '**-**'
    >>> func1(3, '*', '-')
    '***-***-***'
    >>> func1(4, 'z', 'Z')
    'zzzzZzzzzZzzzzZzzzz'
    >>> func1(1, '-', '*') == '-'
    True
    """
    return (n-1)*(n*c+s)+n*c



#>>>>>>>>>>>>>>>>>>>>> Askhsh 6 <<<<<<<<<<<<<<<

def func2(s):
    """Symplhrwste ta kena wste na didetai to
       akolou8o apotelesma

    >>> func2('la')
    'la-la'
    >>> func2(func2('la'))
    'la-la-la-la'
    >>> func2(func2(func2('la')))
    'la-la-la-la-la-la-la-la'
    """
    return s+'-'+s



#>>>>>>>>>>>>>>>>>>>>> Askhsh 7 <<<<<<<<<<<<<<<

def add_inputs():
    """Ypologizei to a8roisma dyo akeraiwn pou dinei o xrhsths.

    >>> add_inputs() #doctest: +SKIP
    3
    4
    7
    >>> print(add_inputs()) #doctest: +SKIP
    9
    10
    19

    H eisodos apo ton xristi einai oi ari8moi poy briskontai sthn 1h kai 2h
    seira meta thn klisi add_inputs().
    """
    return int(input()) + int(input())



#>>>>>>>>>>>>>>>>>>>>> Askhsh 8 <<<<<<<<<<<<<<<

def parrot():
    """Epanalambanei tin eisodo tou xristi gia 4 fores, opws
    akribws sta paradeigmata:

    >>> parrot() #doctest: +SKIP
    - Hello
    - "Hello"
    - Who are you?
    - "Who are you?"
    - I'm losing my patience...
    - "I'm losing my patience..."
    - OK, forget it
    - "OK, forget it"

    H eisodos apo ton xristi einai i symboloseira stin 1h, 3h, 5h kai 7h 
    grammi meta tin klisi parrot(). H eisodos den perilambanei to - kai to
    keno pou akolou8ei.
    """

    print('- "' + input('- "' + input('- "' + input('- "' + input("") + '"\n') + '"\n') + '"\n') + '"')



#>>>>>>>>>>>>>>>>>>>>> Askhsh 9 <<<<<<<<<<<<<<<

def draw_number(x):
    """Emfanizei ton xarakthra + 'h - x fores

    >>> draw_number(5)
    +++++
    >>> draw_number(0)
    >>> draw_number(-3)
    ---
    >>> draw_number(2) == None
    ++
    True
    """
    """ GRAPSTE TON KWDIKA SAS APO KATW """
    if x<0:
       print(-x*('-'))
    elif x>0:
       print(x*('+'))
       
       



#>>>>>>>>>>>>>>>>>>>>> Askhsh 10 <<<<<<<<<<<<<<<

def fix_me():
    """Alla3te ti synartisi poy apokou8ei wste na min
    epanalambanetai o idios ypologismos/kwdikas polles fores.
    Mporeite na xrhsimopoihsete klhseis se synarthseis poy 8a orisete
    amesws meta to swma tis fix_me.

    >>> fix_me()
    Emvado kyklou aktinas 1 = 3.141592653589793 me perifereia = 6.283185307179586
    Emvado kyklou aktinas 2 = 12.566370614359172 me perifereia = 12.566370614359172
    Emvado isoskelous or8ogwniou trigwnou me ka8eti pleura 1 = 0.5 me perifereia = 3.414213562373095
    Emvado isoskelous or8ogwniou trigwnou me ka8eti pleura 5 = 12.5 me perifereia = 17.071067811865476
    """
    """ ALLA3TE TON PARAKATW KWDIKA """
    """print('Emvado kyklou aktinas', 1, '=', 3.141592653589793 * 1**2,
          'me perifereia =', 2 * 3.141592653589793 * 1)
    print('Emvado kyklou aktinas', 2, '=', 3.141592653589793 * 2**2,
          'me perifereia =', 2 * 3.141592653589793 * 2)
    print('Emvado isoskelous or8ogwniou trigwnou me ka8eti pleura', 1,
          '=', 0.5 * 1 ** 2,
          'me perifereia =', 2 * 1 + sqrt(2 * 1**2))
    print('Emvado isoskelous or8ogwniou trigwnou me ka8eti pleura', 5,
          '=', 0.5 * 5 ** 2,
          'me perifereia =', 2 * 5 + sqrt(2 * 5**2)) """
          
    print('Emvado kyklou aktinas',1,'=',em1(1),'me perifereia =',per1(1))
    print('Emvado kyklou aktinas',2,'=',em1(2),'me perifereia =',per1(2))
    print('Emvado isoskelous or8ogwniou trigwnou me ka8eti pleura',1,
          '=',em2(1),
          'me perifereia =',per2(1))
    print('Emvado isoskelous or8ogwniou trigwnou me ka8eti pleura',5,
          '=',em2(5),
          'me perifereia =',per2(5))
          
          
def em1(x):
    return 3.141592653589793*x**2

def em2(x):
    return 0.5*x**2

def per1(x):
    return 2*3.141592653589793*x

def per2(x):
    return 2*x+sqrt(2*x**2)    

   





            








