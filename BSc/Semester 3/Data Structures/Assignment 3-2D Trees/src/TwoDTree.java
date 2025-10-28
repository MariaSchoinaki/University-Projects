import java.io.BufferedReader;                                      // Xrhsimopoieitai gia to diabasma tou arxeiou txt
import java.io.FileReader;                                          // Xrhsimopoieitai gia to diabasma tou arxeiou txt
import java.io.IOException;                                         // Xrhsimopoieitai gia to diabasma tou arxeiou txt
import java.util.*;                                                 // Xrhsimopoieitai gia thn dhmiourgia Scanner

public class TwoDTree {                                             // Klash TwoDTree
    private TreeNode head;                                          // Deikths sth riza tou dentrou
    int size = 0;                                                   // Metablhth pou diathrei to megethos tou dentrou (plhthos kombwn)
    
    TwoDTree(){                                                     // Kataskeuasths TwoDtree, arxikopoiei ena keno dentro
            
    }

    public boolean isEmpty(){                                       // Methodos isEmpty()
        return (size==0);                                           // Epistrefei true otan to dentro einai adeio (dld otan size=0)
    }

    public int size(){                                              // Methodos size()
        return size;                                                // Epistrefei to size tou dentrou
    }

    public void insert(Point p){                                    // Methodos insert()
        if (head == null){                                          // An to head isoutai me null (Keno dentro)
            head = new TreeNode(p);                                 // Topothetei to Point sth riza
            size++;                                                 // Auskanei to size kata 1
            return;                                                 // Epistrefei
        }
        // Diaforetika
        TreeNode temp = head;                                       // Thetei mia temp metablhth ish me to head
        TreeNode prev = null;                                       // Thetei mia metablhth prev ish me null
        int count = 0;                                              // Arixkopoiei mia metablhth count me 0
        while(temp!=null){                                          // Oso to temp einai diaforo tou null
            if(count%2==0){                                         // An to count einai artios arithmos
                if(p.x()==temp.getItem().x() && p.y()==temp.getItem().y()){                     // Elegxoumai an uparxei hdh to stoixeio
                    System.out.println("O kombos pou eisagagate uparxei hdh sto dentro");    // An uparxei emfanizei katallhlo munhma
                    return;                                                                     // kai epistrefei
                }
                // To count einai artios ara sugkrinoume me bash th suntetagmenh x
                if(p.x()<temp.getItem().x()){       // An to x tou Point p einai mikrotero apo to x tou kombou, phgaine sto aristero upodentro
                    prev = temp;                    // To prev ginetai iso me to temp
                    temp = temp.getL();             // To temp ginetai iso me th riza tou aristerou upodentrou
                    if(temp==null){                 // An to temp einai null tote exoume brei th thesh pou tha topothethsoume to Point p
                        prev.setL(new TreeNode(p)); // Opote eisagoume san aristero paidi tou prev to Point p
                    }
                }
                else{                               // Diaforetika (line 39)
                    prev = temp;                    // To prev ginetai iso me to temp
                    temp = temp.getR();             // To temp ginetai iso me th riza tou deksiou upodentrou
                    if(temp==null){                 // An to temp einai null tote exoume brei th thesh pou tha topothethsoume to Point p
                        prev.setR(new TreeNode(p)); // Opote eisagoume san deksi paidi tou prev to Point p
                    }
                }
                count++;                            // Auksanoume to count kata 1
            }
            else{                                   // Diaforetika (line 33)
                if(p.x()==temp.getItem().x() && p.y()==temp.getItem().y()){                     // Elegxoumai an uparxei hdh to stoixeio
                    System.out.println("O kombos pou eisagagate uparxei hdh sto dentro");    // An uparxei emfanizei katallhlo munhma
                    return;                                                                     // kai epistrefei
                }
                // To count einai perittos ara sugkrinoume me bash th suntetagmenh y
                if(p.y()<temp.getItem().y()){       // An to y tou Point p einai mikrotero apo to y tou kombou, phgaine sto aristero upodentro
                    prev = temp;                    // To prev ginetai iso me to temp
                    temp = temp.getL();             // To temp ginetai iso me th riza tou aristerou upodentrou
                    if(temp==null){                 // An to temp einai null tote exoume brei th thesh pou tha topothethsoume to Point p
                        prev.setL(new TreeNode(p)); // Opote eisagoume san aristero paidi tou prev to Point p
                    }
                }
                else{                               // Diaforetika (line 60)
                    prev = temp;                    // To prev ginetai iso me to temp
                    temp = temp.getR();             // To temp ginetai iso me th riza tou deksiou upodentrou
                    if(temp==null){                 // An to temp einai null tote exoume brei th thesh pou tha topothethsoume to Point p
                        prev.setR(new TreeNode(p)); // Opote eisagoume san deksi paidi tou prev to Point p
                    }
                }
                count++;                            // Auksanoume to count kata 1
            }
        }
        size++;                                     // Telika an ginei h eisagwgh auksanoume to size kata 1
    }

    public boolean search(Point p){                 // Methodos search()
        TreeNode temp = head;                       // Thetoume mia temp metablhth ish me to head
        int count = 0;                              // Arxikopoioume mia metablhth count me 0 
        while(temp!=null){                          // Oso to temp einai diaforo tou null
            if(count%2==0){                         // An to count einai artios arithmos
                if(p.x()==temp.getItem().x() && p.y()==temp.getItem().y()){// Elegxoume an to Point isoutai me th riza tou upodentrou pou briskomaste
                    return true;                                           // An isxuei epistrefoume true
                }
                // To count einai artios ara sugkrinoume me bash th suntetagmenh x
                if(p.x()<temp.getItem().x()){          // An to x tou Point p einai mikrotero apo to x ths rizas tou upodentrou
                    temp = temp.getL();                // To temp ginetai iso me th riza tou aristerou upodentrou(Psaxnoume sto aristero upodentro)
                }
                else{                                  // Diaforetika (line 90)
                    temp = temp.getR();                // To temp ginetai iso me th riza tou deksiou upodentrou(Psaxnoume sto deksi upodentro)
                }
                count++;                                // Auksanoume to count kata 1
            }
            else{                                       // Diaforetika (line 85)
                if(p.x()==temp.getItem().x() && p.y()==temp.getItem().y()){// Elegxoume an to Point isoutai me th riza tou upodentrou pou briskomaste
                    return true;                                           // An isxuei epistrefoume true
                }
                // To count einai perittos ara sugkrinoume me bash th suntetagmenh y
                if(p.y()<temp.getItem().y()){           // An to y tou Point p einai mikrotero apo to y ths rizas tou upodentrou
                    temp = temp.getL();                 // To temp ginetai iso me th riza tou aristerou upodentrou(Psaxnoume sto aristero upodentro)
                }
                else{                                   // Diaforetika (line 103)
                    temp = temp.getR();                 // To temp ginetai iso me th riza tou deksiou upodentrou(Psaxnoume sto deksi upodentro)
                }
                count++;                                // To count auksanetai kata 1
            }
        }
        return false;                                   // An bgei apo th while (den brethike to Point p) epistrefei false
    }


    public Point nearestNeighbor(Point p){
        Point clospoint;        // Metablhth me to Point tou dentrou pou brisketai pio konta sto Point p
        if(isEmpty()){          // An to dentro einai adeio
            return null;        // Epistrefei null
        }
        clospoint = head.getItem();     // To clospoint ginetai iso arxika me th riza tou dentrou
        clospoint = nearestNeighbor(head,p,clospoint,0,0,100,0,100);// Kaleitai h anadromikh methodos gia thn euresh
        // tou Point tou dentrou pou brisketai pio konta sto Point p
        return clospoint;   // Epistrefoume to Point
    }


    public Point nearestNeighbor(TreeNode current,Point p,Point currclospoint,int count,int xmin,int xmax,int ymin ,int ymax){
        // Anadromikh methodos gia thn euresh tou point
        // TreeNode current: To TreeNode sto opoio briskomaste
        // Point p: To point pou dinei o xrhsths
        // Point currclospoint: To kontinotero point sto p pou exoume brei mexri stigmhs
        // int count: Metrhths pou kathorizei an sugkrinoume me to x h me to y
        // int xmin-xmax-ymin-ymax : oi 4 suntetagmenes tou rectangle tou kombou pou brisketai o current kombos

        if(current == null){        // An to current isoutai me null
            return currclospoint;   // epistrefoume to kontinotero point sto p pou exoume brei mexri stigmhs ( Bash Anadromhs )
        }
        if(p.squareDistanceTo(current.getItem())<p.squareDistanceTo(currclospoint)){ // An h apostash tou p apo to Point tou current einai
            // mikroterh apo thn apostash tou p apo to to kontinotero point sto p pou exoume brei mexri stigmhs
            currclospoint = current.getItem();  // Tote to currclospoint tha isoutai me to Point tou current
        }
        Rectangle temprect = new Rectangle(xmin,xmax,ymin,ymax);                  // Dhmiourgoume to Rectangle pou brisketai mesa o current
        //System.out.println(temprect);                                             // Gia debug
        if(p.squareDistanceTo(currclospoint)<temprect.squareDistanceTo(p)){       // Elegxos sunthikhs pou zhteitai sthn ekfwnhsh
            return currclospoint;                   // An isxuei h sunthikh den anazhtoume parakatw sto dentro kai epistrefoume to currclospoint
        }
        if(count%2==0){                                     // An to count einai artios (sugkrinoume me th suntetagmenh x)
            if(p.x()<current.getItem().x()){                // An to x tou p einai mikrotero apo to x tou current
                count++;                                    // Auksanoume to count kata 1
                currclospoint = nearestNeighbor(current.getL(),p,currclospoint,count,xmin,current.getItem().x(),ymin,ymax);
                // Psaxnoume na broume to kontinotero shmeio anadromika sto aristero upodentro tou current
                currclospoint = nearestNeighbor(current.getR(),p,currclospoint,count,current.getItem().x(),xmax,ymin,ymax);
                // Psaxnoume na broume to kontinotero shmeio anadromika sto deksi upodentro tou current
            }
            else{                                     // Diaforetika (line 149)
                count++;                              // Auksanoume to count kata 1
                currclospoint = nearestNeighbor(current.getR(),p,currclospoint,count,current.getItem().x(),xmax,ymin,ymax);
                // Psaxnoume prwta na broume to kontinotero shmeio anadromika sto deksi upodentro tou current
                currclospoint = nearestNeighbor(current.getL(),p,currclospoint,count,xmin,current.getItem().x(),ymin,ymax);
                // Psaxnoume na broume to kontinotero shmeio anadromika sto aristero upodentro tou current
            }
        }
        else{                                               // Diaforetika (line 148) (sugkrinoume me th suntetagmenh y)
            if(p.y()<current.getItem().y()){                // An to y tou p einai mikrotero apo to x tou current
                count++;                                    // Auksanoume to count kata 1
                currclospoint = nearestNeighbor(current.getL(),p,currclospoint,count,xmin,xmax,ymin,current.getItem().y());
                // Psaxnoume na broume to kontinotero shmeio anadromika sto aristero upodentro tou current
                currclospoint = nearestNeighbor(current.getR(),p,currclospoint,count,xmin,xmax,current.getItem().y(),ymax);
                // Psaxnoume na broume to kontinotero shmeio anadromika sto deksi upodentro tou current
            }
            else{
                count++;                                    // Auksanoume to count kata 1
                currclospoint = nearestNeighbor(current.getR(),p,currclospoint,count,xmin,xmax,current.getItem().y(),ymax);
                // Psaxnoume prwta na broume to kontinotero shmeio anadromika sto deksi upodentro tou current
                currclospoint = nearestNeighbor(current.getL(),p,currclospoint,count,xmin,xmax,ymin,current.getItem().y());
                // Psaxnoume na broume to kontinotero shmeio anadromika sto aristero upodentro tou current
            }
        }
        return currclospoint;   // Epistrefoume to currclospoint
    }

    public List<Point> rangeSearch(Rectangle rect){     // Methodos rangeSearch()
        List<Point> points = new List<>();              // Arxikopoieitai h lista pou periexei Point
        if(isEmpty()){                                  // An to dentro einai adeio
            return points;                              // Epistrefete h adeia lista
        }
        points = rangeSearch(rect,head,0,0,100,0,100,points);   // Kaleitai h anadromikh methodos rangeSearch
        return points;                                  // Epistrefete h lista points
    }

    public List<Point> rangeSearch(Rectangle rect,TreeNode current,int count,int xmin,int xmax,int ymin ,int ymax,List<Point> list){
        // Anadromikh methodos rangeSearch()
        // Rectangle rect: To rectangle pou dinei o xrhsths
        // TreeNode current: To TreeNode sto opoio briskomaste twra
        // int count: Metrhths count gia na gnwrizei an prepei na sugkrinei me bash to x h to y
        // int xmin-xmax-ymin-ymax: oi 4 suntetagmenes tou rectangle sto opoio brisketai to current
        // List<Point> list: h lista sthn opoia prosthetoume ta Points
          
        if(current == null){        // An o current einai null
            return list;            // Epistrefoume th lista ( Bash Anadromhs)
        }
        if(rect.contains(current.getItem())){                       // An to rect periexei to Point tou current
            list.insertAtBack(current.getItem());                   // Tote prosthetoume to Point tou current sth lista
        }
        Rectangle temprect = new Rectangle(xmin,xmax,ymin,ymax);    // Dhmiourgoume to rectangle sto opoio anhkei o current kombos
        //System.out.println(temprect);                 // Gia Debug
        if(temprect.intersects(rect) == false){                     //An to rectangle den exei kanena koino shmeio me to rect tou xrhsth
            //System.out.println(temprect);             // Gia Debug
            return list;                                            // Epistrefoume th lista kai den sunexizoume thn anazhths parakatw
        }
        if(count%2==0){// An to count einai artios arithmos (Xreiazetai gia na enhmerwnetai katallhla to rectangle pou anhkei o current)
            count++;                                                // Auksanoume to count kata 1
            list = rangeSearch(rect,current.getL(),count,xmin,current.getItem().x(),ymin,ymax,list);    // Enhmerwnoume th lista kalwntas thn
            // Anadromikh rangeSearch gia to aristero upodentro tou current
            list = rangeSearch(rect,current.getR(),count,current.getItem().x(),xmax,ymin,ymax,list);    // Enhmerwnoume th lista kalwntas thn
            // Anadromikh rangeSearch gia to deksi upodentro tou current
        }
        else{                                                       // Diaforetika (line 212)
            count++;                                                // Auksanoume to count kata 1
            list = rangeSearch(rect,current.getL(),count,xmin,xmax,ymin,current.getItem().y(),list);    // Enhmerwnoume th lista kalwntas thn
            // Anadromikh rangeSearch gia to aristero upodentro tou current
            list = rangeSearch(rect,current.getR(),count,xmin,xmax,current.getItem().y(),ymax,list);    // Enhmerwnoume th lista kalwntas thn
            // Anadromikh rangeSearch gia to deksi upodentro tou current
        }
        return list;    // Epistrefoume th lista
    }


    public static void main(String []args) throws IOException{          // main
        String file = "input.txt";                                          // To onoma tou arxeiou
        TwoDTree tree = new TwoDTree();                                 // Dhmiourgoume ena keno tree
        Scanner input = new Scanner(System.in);                         // Dhmiourgoume ena Scanner gia to diabasma apo ton xrhsth
        int sunt1,sunt2,sunt3,sunt4;                                    // Metablhtes gia tis suntetagmenes twn Point kai Rectangle
        String xrect,yrect;                                             //              -//-    -//-    -//-    -//-
        int answer;                                                     // Metablhth pou tha krataei thn apanthsh tou xrhsth
        List<Point> points;                                             // Lista me antikeimena typou Point
        Point point;                                                    // Mia metablhth tupou Point
        String[] borders;                                               // Pinakas me String (Gia to diabasma apo txt kai to xwrismo tou String)
        BufferedReader bf = new BufferedReader(new FileReader(file));   // Dhmiourgeitai o Buffered Reader gia to diabasma tou txt
        String line = bf.readLine();                                    // Diabasma prwths grammhs
        int num = Integer.parseInt(line);                               // H prwth grammh periexei ton arithmo twn Point ton opoio apothikeuoume
        line = bf.readLine();                                           // Diabasma deuterhs grammhs
        while (line != null) {                                          // While loop gia to diabasma tou arxeiou
            borders = line.split(" ");                            // Spame to String tis grammhs sto keno
            int x = Integer.parseInt(borders[0]);                       // To prwto String tha einai to x tou Point
            int y = Integer.parseInt(borders[1]);                       // To deutero String tha einai to y tou Point
            if(x>100 || x<0 || y>100 || y<0){                           // Elegxos egkurothtas tou point
                System.out.println("Exoun dothei lathos dedomena sto arxeio");      // Emfanish katallhlou munhmatos an parabiazetai
                bf.close();                                             // Kleisimo tou bufferedReader
                System.exit(0);                                 // Exodos apo to programma
            }
            tree.insert(new Point(x,y));                                // Diaforetika eisagwgh tou Point sto dentro
            line = bf.readLine();                                       // Diabasma epomenhs grammhs
        }
        bf.close();                                                     // Kleisimo bufferedReader
        if(num!=tree.size()){                                           // Elegxos an to megethos tou tree isoutai me ton arithmo pou mas dothike
            System.out.println("O arithmos twn Point den antistoixei se auton pou exei dhlwthei");  // Emfanish katallhlou mhnumatos
            System.exit(0);                                     // Exodos apo to programma
        }   
        while(true){                                                    // Ena sunexomeno loop
            System.out.println("1.Compute the size of the tree");
            System.out.println("2.Insert a new point");
            System.out.println("3.Search if a given point exists in the tree"); 
            System.out.println("4.Provide a query rectangle");                      // Emfanish tou menu
            System.out.println("5.Provide a query point");
            System.out.println("6.Exit");
            System.out.print("Choose a number from the menu: ");
            answer = input.nextInt();                                                   // Diabasma apanthshs
            if(answer<1 || answer>6){                                                   // Elegxos egkurothtas gia thn apanthsh
                continue;
            }
            switch (answer){                                                            // Elegxos periptwsewn
                case 1:
                    System.out.println("The size of the 2d Tree is: " + tree.size());   // Emfanish tou size tou dentrou
                    break;
                case 2:
                    do{
                        System.out.println("Dwste thn prwth suntetagmenh");         // Diabasma prwths suntetagmenhs
                        sunt1 = input.nextInt();
                        System.out.println("Dwste thn deuterh suntetagmenh");       // Diabasma deuterhs suntetagmenhs
                        sunt2 = input.nextInt();
                    }while(sunt1>100 || sunt1<0 || sunt2>100 || sunt2<0);              // Elegxos egkurothtas gia x kai y
                    tree.insert(new Point(sunt1,sunt2));                               // Eisagwgh tou point sto dentro
                    break;
                case 3:
                    do{
                        System.out.println("Dwste thn prwth suntetagmenh");         // Diabasma prwths suntetagmenhs
                        sunt1 = input.nextInt();
                        System.out.println("Dwste thn deuterh suntetagmenh");       // Diabasma deuterhs suntetagmenhs
                        sunt2 = input.nextInt();
                    }while(sunt1>100 || sunt1<0 || sunt2>100 || sunt2<0);              // Elegxos egkurothtas gia x kai y
                    if(tree.search(new Point(sunt1,sunt2))){                           // An brethike to point sto dentro
                        System.out.println("To Point me suntetagmenes (" + sunt1 + "," + sunt2 + ") brethike sto dentro" );
                        // Emfanish katallhlou munhmatos
                    }
                    else{                                                              // Diaforetika
                        System.out.println("To Point me suntetagmenes (" + sunt1 + "," + sunt2 + ") den brethike sto dentro" );
                        // Emfanish katallhlou munhmatos
                    }
                    break;
                case 4:
                    do{
                        System.out.println("Dwste to xmin kai to xmax tou rectangle (p.x. 20 30) ");
                        xrect = input.nextLine();                       // Diabasma xmin kai xmax
                        xrect+= input.nextLine();
                        borders = xrect.split(" ");              // Spame to String sto keno
                        sunt1 = Integer.parseInt(borders[0]);           // Apothikeuetai to xmin
                        sunt2 = Integer.parseInt(borders[1]);           // Apothikeuetai to xmax
                        System.out.println("Dwste to ymin kai to ymax tou rectangle (p.x. 40 80) ");
                        yrect = input.nextLine();                       // Diabasma ymin kai ymax
                        borders = yrect.split(" ");              // Spame to String sto keno
                        sunt3 = Integer.parseInt(borders[0]);           // Apothikeuetai to ymin
                        sunt4 = Integer.parseInt(borders[1]);           // Apothikeuetai to ymax
                    }while(sunt1<0 || sunt1>100 || sunt2<0 || sunt2>100 || sunt3<0 || sunt3>100 || sunt4>100 || sunt4<0);// Elegxos egkurothtas
                    points = tree.rangeSearch(new Rectangle(sunt1, sunt2, sunt3, sunt4));// H lista points isoutai me th lista pou epistrefei h rangeSearch
                    System.out.println("Ta Points tou dentrou pou briskontai sto rectangle pou dwsate einai: " + points.getSize());
                    while(points.getSize()!=0){
                        System.out.println(points.removeFromFront());           // Emfanish twn Point
                    }
                    break;
                case 5:
                    do{
                        System.out.println("Dwste tis suntetagmenes tou Point (p.x 34 76)");
                        line = input.nextLine();                    // Diabasma suntetagmenwn
                        line+= input.nextLine();
                        borders = line.split(" ");            // Spame to String sto keno
                        sunt1 = Integer.parseInt(borders[0]);       // Apothikeuetai to x
                        sunt2 = Integer.parseInt(borders[1]);       // Apothikeuetai to y
                    }while(sunt1>100 || sunt1<0 || sunt2>100 || sunt2<0);       // Elegxos egkurothtas
                    point = tree.nearestNeighbor(new Point(sunt1,sunt2));       // To point isoutai me to Point pou epistrefei h nearestNeighbor
                    System.out.println("To pio kontino point tou dentrou sto point pou dwsate einai to: " + point);
                    // Emfanish katallhlou munhmatos
                    break;
                case 6:
                    input.close();                              // Kleisimo tou Scanner
                    System.exit(0);                     // Exodos apo to programma
            }
        }
    }
}