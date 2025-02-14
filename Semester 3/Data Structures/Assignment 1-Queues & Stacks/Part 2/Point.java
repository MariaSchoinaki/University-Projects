class Point{
    
    private int x;                      // h grammh sthn opoia brisketai to stoixeio
    private int y;                      // h sthlh sthn opoia brisketai to stoixeio

    Point(int x, int y){               // Kataskeuasths Point
        this.x=x;   
        this.y=y;
    }

    int getX(){                         // Methodos getX epistrefei to x (th grammh)
        return x;
    }

    int getY(){                         // Methodos getY epistrefei to y (th sthlh)
        return y;
    }
}