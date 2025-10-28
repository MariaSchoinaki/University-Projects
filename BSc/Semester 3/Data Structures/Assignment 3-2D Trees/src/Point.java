class Point{                                            // Klash Point
    
    private int x;                                      // H suntetagmenh x
    private int y;                                      // H suntetagmenh y
    
    Point(int x, int y){                                // Kataskeuasths Point
        this.x = x;                                     // Arxikopoiei to x pou dinei o xrhsths
        this.y = y;                                     // Arxikopoiei to y pou dinei o xrhsths
    }

    public int x(){                                     // Methodos x()                                     
        return x;                                       // Epistrefei th suntetagmenh x tou point
    }

    public int y(){                                     // Methodos y()
        return y;                                       // Epistrefei th suntetagmenh y tou point
    }

    public double distanceTo(Point z){                  // Methodos distanceTo()
        return Math.sqrt(Math.pow((this.x-z.x()),2) + Math.pow((this.y-z.y()),2));  // Epistrefei thn eukleideia apostash apo to z
    }

    public int squareDistanceTo(Point z){               // Methodos squareDistanceTo()
        return (this.x-z.x())*(this.x-z.x()) + (this.y-z.y())*(this.y-z.y());           // Epistrefei thn eukleideia apostash sto tetragwno
    }

    public String toString(){                           // Methodos toString()
        return "(" + this.x + "," + this.y + ")";       // Epistrefei to String me to opoio theloume na emfanistei to Point (e.g. (3,2) )
    }
}