class Counter:
    def __init__(self, start = 0):
        self.value = start

    def advance(self):
        self.value = self.value + 1

    def __str__(self):
        return str(self.value)

class CyclicCounter(Counter):
    """Ta3i tis opoias ta antikeimena anaparistoun metrites me 
    akeraies times 0, 1, 2, ..., self.period - 1. 
    H epomenh timi tis self.period - 1 einai 0.

    >>> c = CyclicCounter(100, 97)
    >>> str(c)
    '97'
    >>> c.advance()
    >>> str(c)
    '98'
    >>> c.advance()
    >>> str(c)
    '99'
    >>> c.advance()
    >>> str(c)
    '00'
    >>> c.advance()
    >>> str(c)
    '01'
    >>> c2 = CyclicCounter(12, 8)
    >>> str(c2)
    '08'
    >>> c2.advance()
    >>> str(c2)
    '09'
    >>> c2.advance()
    >>> str(c2)
    '10'
    >>> c2.advance()
    >>> str(c2)
    '11'
    >>> c2.advance()
    >>> str(c2)
    '00'
    """
    def __init__(self, period, start = 0):
        """Arxikopoihsh kyklikou metriti.
    
        period -- dinei tin timi tis self.period
        start -- arxiki timi (an den do8ei einai 0)
        """
        self.period = period
        Counter.__init__(self, start)

    def advance(self):
        self.value = (self.value + 1) % self.period

    def __str__(self):
        s = Counter.__str__(self)
        return (len(str(self.period-1))-len(s))*'0' + s


class CascadeCounter(CyclicCounter):
    """Ta3i tis opoias ta antikeimena anaparistoum kyklikous metrites 
    pou epipleon au3anoun ton metriti self.next 
    (kaleitai i me8odos self.next_counter.advance()), 
    otan symbei anakyklwsi.

    >>> c2 = CascadeCounter(None, 10, 5)
    >>> c1 = CascadeCounter(c2, 100, 98)
    >>> str(c2)
    '5'
    >>> str(c1)
    '98'
    >>> c1.advance()
    >>> str(c1)
    '99'
    >>> str(c2)
    '5'
    >>> c1.advance()
    >>> str(c1)
    '00'
    >>> str(c2)
    '6'
    """
    def __init__(self, next, period, start = 0):
        """Arxikopoihsh metriti.

        next -- dinei ton metriti self.next tou opoiou kaleitai
                i me8odos advance otan anakyklw8ei i timi tou metriti
        period -- dinei tin timi tis self.period
        start -- arxiki timi (an den do8ei einai 0)
        """
        CyclicCounter.__init__(self, period, start)
        self.next = next

    def advance(self):
        CyclicCounter.advance(self)
        if self.next and self.value == 0:
            self.next.advance()


class Clock(Counter):
    """Ta3i tis opoias ta antikeimena anaparistoun pshfiako roloi 
    me endei3eis wras, leptwn, deuteroleptwn.

    H me8odos self.advance() exei ws apotelesma thn ay3isi kata 
    ena deuterolepto.

    >>> c = Clock(23, 59, 58)
    >>> str(c)
    '23:59:58'
    >>> c.advance()
    >>> print(c)
    23:59:59
    >>> c.advance()
    >>> print(c)
    00:00:00
    >>> c.advance()
    >>> str(c)
    '00:00:01'
    >>> c.advance()
    >>> print(c)
    00:00:02
    """
    def __init__(self, h, m, s):
        """Arxikopoiisi rologiou.

        h -- arxiki endei3h wras (akeraios 0 ews 23)
        m -- arxiki endei3h leptwn (akeraios 0 ews 59)
        s -- arxiki endei3h deuteroleptwn (akeraios 0 ews 59)

        An paraleif8ei kapoia apo tis parapanw times, 8evreitai oti 
        einai 0.
        """
        self._h = CyclicCounter(24, h)
        self._m = CascadeCounter(self._h, 60, m)
        self._s = CascadeCounter(self._m, 60, s)

    def advance(self):
        """O xronos pou anaparista to roloi ay3anei kata ena 
        deyterolepto."""
        self._s.advance()
        
    def __str__(self):
        return '{0}:{1}:{2}'.format(self._h, self._m, self._s)


if __name__ == '__main__':
    from time import sleep

    c = Clock(23, 59, 45)
    while True:
        print(c, end = '\r')
        sleep(1)
        c.advance()
