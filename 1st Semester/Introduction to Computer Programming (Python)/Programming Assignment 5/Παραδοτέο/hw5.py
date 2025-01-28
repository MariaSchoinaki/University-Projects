from clock import *

#-------------------- Askisi 1 ----------------------------
class RomanCascadeCounter(CascadeCounter):
    """Metritis CascadeCounter me endei3eis me rwmaikous ari8mous."""
    def __str__(self):
        s, i, x = '', 0, self.value
        roman_10, roman_5 = 'IXCM', 'VLD'
        while x > 0:
            if i == 3:
                s = x * roman_10[i] + s
                break
        
            d = x % 10 
            if d == 9:
                s =  roman_10[i] + roman_10[i + 1] + s
            elif d == 4:
                s = roman_10[i] + roman_5[i] + s
            else:
                s = (d // 5) * roman_5[i] + (d % 5) * roman_10[i] + s

            x, i = x // 10, i + 1

        return (9 - len(s)) * '-' + s


class RomanClock(Clock):
    """Roloi me endei3eis me rwmaika noumera.

    >>> c = RomanClock(23, 59, 58)
    >>> str(c)
    '----XXIII:---LVIIII:----LVIII'
    >>> c.advance()
    >>> print(c)
    ----XXIII:---LVIIII:---LVIIII
    >>> c.advance()
    >>> print(c)
    ---------:---------:---------
    >>> c.advance()
    >>> print(c)
    ---------:---------:--------I
    >>> c.advance()
    >>> print(c)
    ---------:---------:-------II
    """
    def __init__(self, h, m, s):
        self._h = RomanCascadeCounter(None, 24, h)
        self._m = ___________________________________
        _____________________________________________
    

#-------------------- Askisi 2 ----------------------------
class DayCounter(CyclicCounter):
    """Metritis hmeras.

    Paradeigmata:
    >>> d = DayCounter()
    >>> str(d)
    'Sunday'
    >>> d.advance()
    >>> print(d)
    Monday
    >>> d2 = DayCounter('Saturday')
    >>> str(d2)
    'Saturday'
    >>> d2.advance()
    >>> str(d2)
    'Sunday'
    >>> d2.advance()
    >>> str(d2)
    'Monday'
    """
    _days = ['Sunday', 'Monday', 'Tuesday', 'Wednesday',\
            'Thursday', 'Friday', 'Saturday']
    def __init__(self, day = 'Sunday'):
        CyclicCounter.__init__(self,________________________)

    def __str__(self):
        return ___________________________


#-------------------- Askisi 3 ----------------------------
class DayClock(Clock):
    """Roloi me endei3h hmeras.

    >>> c = DayClock(23, 59, 58, 'Sunday')
    >>> str(c)
    '23:59:58 Sunday'
    >>> c.advance()
    >>> str(c)
    '23:59:59 Sunday'
    >>> c.advance()
    >>> str(c)
    '00:00:00 Monday'
    >>> c.advance()
    >>> str(c)
    '00:00:01 Monday'

    An paralhf8ei to onoma imeras (teleytaio orisma ston kataskeyasti)
    tote pairnei timi 'Sunday', px.:
    >>> c = DayClock(6, 35, 0)
    >>> print(c)
    06:35:00 Sunday
    """
    def __init__(self, h = 0, m = 0, s = 0, day = 'Sunday'):
        self._d = DayCounter(day)
        ___________________________________________
        ___________________________________________
        ___________________________________________

    def __str__(self):
        ___________________________________________



#-------------------- Askisi 4 ----------------------------
class __________________:
    """Antistrofos xronometritis.

    >>> c = Timer(0, 0, 2)
    >>> str(c)
    '00:00:02'
    >>> c.advance()
    >>> str(c)
    '00:00:01'
    >>> c.advance()
    >>> str(c)
    '00:00:00'
    >>> c.advance()
    >>> str(c)
    'TI DI DI DI'
    >>> c.advance()
    >>> str(c)
    'TI DI DI DI'
    """
    """GRAPSTE TON KWDIKA SAS APO KATW."""
