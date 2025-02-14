public class Rectangle {        // Klash Rectangle
    
    private int xmin;           // To elaxisto x tou rectangle
    private int xmax;           // To megisto x tou rectangle
    private int ymin;           // To elaxisto y tou rectangle
    private int ymax;           // To megisto y tou rectangle
    
    Rectangle(int xmin,int xmax,int ymin, int ymax){    // Kataskeuasths ths Rectangle
        this.xmin = xmin;                               // Arxikopoiei to xmin tou rectangle me auto pou dinei o xrhsths
        this.xmax = xmax;                               // Arxikopoiei to xmax tou rectangle me auto pou dinei o xrhsths        
        this.ymin = ymin;                               // Arxikopoiei to ymin tou rectangle me auto pou dinei o xrhsths        
        this.ymax = ymax;                               // Arxikopoiei to ymax tou rectangle me auto pou dinei o xrhsths        
    }

    public boolean contains(Point p){                   // Methodos contains()
        if(p.x()<=xmax && p.x()>=xmin && p.y()>=ymin && p.y()<=ymax){   // An to x tou Point einai mikrotero apo to xmax kai megalutero apo xmin
            return true;                                    // kai to y einai mikrotero apo ymax kai megalutero apo ymin epestrepse true
        }
        return false;                                       // diaforetika epestrepse false
    }

    public boolean intersects(Rectangle that){              // Methodos intersects
        if(xmin>that.xmax || xmax<that.xmin || ymin>that.ymax || ymax<that.ymin){
            // An to xmin einai megalutero apo to xmax tou that h to xmax einai mikrotero apo xmin tou that
            // h to ymin einai megalutero apo ymax tou that h to ymax einai mikrotero apo ymin tou that
            return false;   // Epestrepse false
        }
        return true;        // Diaforetika epestrepse true
    }

    public double distanceTo(Point p){                          // Methodos distanceTo()
        if (p.x() < this.xmin) {        // An to x tou p einai mikrotero apo to xmin tou rectangle(To point brisketai aristera apo to rectangle)
            if (p.y() < this.ymin) {    // An to y tou p einai mikrotero apo to ymin tou rectangle(To point brisketai aristera kai katw apo to rect)
                return p.distanceTo(new Point(this.xmin, this.ymin));   // Epestrepse thn apostash tou p apo thn katw aristerh gwnia tou rectangle
            }
            if (p.y() <= this.ymax){    // An to y tou p einai anamesa sto ymin kai to ymax (To point einai akribws aristera apo to rectangle )
                return this.xmin - p.x();   // Tote h apostash tou p apo to rectangle tha einai h diafora xmin(Rectangle) - x (Point)
            }
            // Se diaforetikh periptwsh to Point brisketai aristera kai panw apo to rectangle
            return p.distanceTo(new Point(this.xmin, this.ymax));   // Opote epistrefei thn apostash tou p apo thn panw aristerh gwnia
        } else if (p.x() <= this.xmax) {    // An to x tou p einai mikrotero apo to xmax (To point einai kapou anamesa sto rectangle)
            if (p.y() <  this.ymin){        // An to y tou p einai mikrotero apo to ymin (To point einai katw apo to rectangle)
                return this.ymin - p.y();   // Tote h apostash tha einai h diafora twn y tou point kai tou ymin tou rectangle
            }
            if (p.y() <= this.ymax){        // An to y einai anamesa sto ymin kai to ymax (To point brisketai mesa sto rectangle)
                return 0;                   // Tote h apostash einai 0
            }
            // Diaforetika to Point p brisketai panw apo to rectangle
            return p.y() - this.ymax;       // Ara h apostash einai h diafora twn y tou point kai tou ymax tou rectangle
        } else {                            // An to x tou p einai megalutero apo to xmax tou rect(To point brisketai deksia apo to rectangle)
            if (p.y() <  this.ymin){        // An to y tou p einai mikrotero apo to ymin tou rect(To point einai deksia kai katw apo rectangle)
                return p.distanceTo(new Point(this.xmax, this.ymin)); // Ara h apostash einai h apostash toy p apo thn katw deksia gwnia
            }
            if (p.y() <= this.ymax){        // An to y tou p einai mikrotero apo ymax tou rect (To point brisketai akribws deksia apo to rectangle)
                return p.x() - this.xmax;   // Tote h apostash tha einai h diafora twn x tou p kai tou xmax tou rectangle
            }
            // Diaforetika to p brisketai deksia kai panw apo to rectangle
            return p.distanceTo(new Point(this.xmax, this.ymax));// Ara h apostash tha einai ish me thn apostash tou p apo thn panw deksia gwnia
        }
    }

    public int squareDistanceTo(Point p){   // Methodos squareDistanceTo()
        if (p.x() < this.xmin) {    // An to x tou p einai mikrotero apo to xmin tou rectangle(To point brisketai aristera apo to rectangle)
            if (p.y() < this.ymin){ // An to y tou p einai mikrotero apo to ymin tou rectangle(To point brisketai aristera kai katw apo to rect)
                return p.squareDistanceTo(new Point(this.xmin, this.ymin));// Epestrepse thn apostash tou p apo thn katw aristerh gwnia tou rectangle
            }
            if (p.y() <= this.ymax){    // An to y tou p einai anamesa sto ymin kai to ymax (To point einai akribws aristera apo to rectangle )
                return (this.xmin - p.x())*(this.xmin - p.x());// Tote h apostash tou p apo to rectangle tha einai h diafora xmin(Rectangle) - x (Point) sto tetragwno
            }
            // Se diaforetikh periptwsh to Point brisketai aristera kai panw apo to rectangle
            return p.squareDistanceTo(new Point(this.xmin, this.ymax));   // Opote epistrefei thn apostash tou p apo thn panw aristerh gwnia
        } else if (p.x() <= this.xmax) {    // An to x tou p einai mikrotero apo to xmax (To point einai kapou anamesa sto rectangle)
            if (p.y() <  this.ymin){        // An to y tou p einai mikrotero apo to ymin (To point einai katw apo to rectangle)
                return (this.ymin - p.y())*(this.ymin - p.y());// Tote h apostash tha einai h diafora twn y tou point kai tou ymin tou rectangle sto tetragwno
            }
            if (p.y() <= this.ymax){        // An to y einai anamesa sto ymin kai to ymax (To point brisketai mesa sto rectangle)
                return 0;                   // Tote h apostash einai 0
            }
            // Diaforetika to Point p brisketai panw apo to rectangle
            return (p.y() - this.ymax)*(p.y() - this.ymax);// Ara h apostash einai h diafora twn y tou point kai tou ymax tou rectangle sto tetragwno
        } else {                            // An to x tou p einai megalutero apo to xmax tou rect(To point brisketai deksia apo to rectangle)
            if (p.y() <  this.ymin){        // An to y tou p einai mikrotero apo to ymin tou rect(To point einai deksia kai katw apo rectangle)
                return p.squareDistanceTo(new Point(this.xmax, this.ymin)); // Ara h apostash einai h apostash toy p apo thn katw deksia gwnia
            }
            if (p.y() <= this.ymax){        // An to y tou p einai mikrotero apo ymax tou rect (To point brisketai akribws deksia apo to rectangle)
                return (p.x() - this.xmax)*(p.x() - this.xmax);// Tote h apostash tha einai h diafora twn x tou p kai tou xmax tou rectangle sto tetragwno
            }
            // Diaforetika to p brisketai deksia kai panw apo to rectangle
            return p.squareDistanceTo(new Point(this.xmax, this.ymax));// Ara h apostash tha einai ish me thn apostash tou p apo thn panw deksia gwnia
        }
    }

    public String toString(){       // Methodos toString()
        return "[" + this.xmin + "," + this.xmax + "]" + " x [" + this.ymin + "," + this.ymax + "]";
        // Epistrefei to String me to opoio theloume na emfanistei to Rectangle (e.g. [0,100]x[0,100] )
    }
}