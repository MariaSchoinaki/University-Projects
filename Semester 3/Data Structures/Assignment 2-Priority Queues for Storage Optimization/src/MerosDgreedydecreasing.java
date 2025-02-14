import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
class MerosDgreedydecreasing{

    // MerosDgreedydecreasing: H beltiwmenh methodos Greedy. Akribws idia me th Greedy.java me th monh diafora na einai sth methodo Sort
    // h opoia xrhsimopoeitai gia na taksinomhsei th lista me tous fakelous


    public static long s_total_size=0;// Static metablhth gia to sunoliko megethos twn fakelwn gia kathe fora pou tha treksoume ton algorithmo
    public static int iter=0;// Static metablhth gia to arxeio pou tha diabasoume tous fakelous gia kathe fora pou tha treksoume ton algorithmo(args[i])
    public static int s_total_disk=0;//Static metablhth gia to sunoliko arithmo twn diskwn gia kathe fora pou tha treksoume ton algorithmo

    // ^^^ Oi parapanw metablhtes prostethikan gia to Meros D opou kaloume th MerosDgreedydecreasing.main(args) (MerosDcompare.java) ^^^


    static List<Integer> readfolders(String filename) throws IOException{   // Static methodos readfolder, filename: to onoma tou arxeiou
        List<Integer> fakeloi = new List<>();                               // Arxikopoioume ena List me fakelous (int)
        BufferedReader bf = new BufferedReader(new FileReader(filename));   // Ftiaxnoume to BufferedReader
        String line = bf.readLine();                                        // Diabazoume thn prwth grammh
        while (line != null) {                                              // Oso uparxoun grammes
            int temp = Integer.parseInt(line);                              // Thetoume se mia temp metablhth to periexomeno ths grammhs
            if(temp<0 || temp>1000000){                                     // Elegxoume an to size einai ektos ton oriwn pou theloume
                bf.close();                                                 // An einai ektos twn oriwn stamatame to diabasma
                throw new IOException();                                    // Kai petaei IOException
            }
            fakeloi.insertAtBack(temp);                                     // Diaforetika prosthetei ton fakelo sto List
            line = bf.readLine();                                           // Kai diabazei thn epomenh grammh 
        }
        bf.close();                                                         // Otan teleiwsoun oi grammes kleinoume to buffer
        Sort.sort(fakeloi);                                                 // Kaleitai h methodos Sort pou taksinomei th lista fakeloi
        return fakeloi;                                                     // Kai epistrefoume to List me tous fakelous
    }
    
    public static void main(String []args){
        List<Integer> folders = new List<>();                           // Arxikopoieitai ena list me fakelous
        int length = 0;                                 // Metablhth length krataei ton arithmo twn fakelwn tou txt arxeiou. Arxikopoieitai me 0
        MaxPQ heap = new MaxPQ();                       // Arxikopoieitai to Priority Queue
        boolean sunexeia = true;                        // Thetoume mia boolean metablhth true
        String filename = args[iter];                   // Thetoume to filename ws to argument pou tha dothei
        int total_size = 0 ;                            // Arxikopoieitai mia metablhth pou tha krataei to sunoliko size me 0
        try{
            folders = readfolders(filename);            // Kaleitai h methodos readfolders
            length = folders.getSize();                 // O arithmos twn fakelwn isoutai me ton arithmo twn stoixeiwn tis listas
        }
        catch(IOException e){                           // An uparxei kapoio exception 
            System.out.println("Exoun dothei lathos dedomena sto arxeio input.txt h uparxei lathos sto path pou exei dothei"
            + "(p.x. Keno se kapoio onoma fakelou pou periexetai sto path)");        // Emfanizetai katallhlo munhma
            sunexeia = false;                           // Kai h metablhth sunexeia ginetai false
        }
        if(sunexeia){                                   // An egine kanonika to diabasma apo to arxeio
            heap.insert(new Disk());                       // Prosthetoume sto Priority Queue to prwto Disk
            while(folders.getSize()>0){                 // Oso to list me tous fakelous exei stoixeia
                Disk tempDisk;                          // Metablhth tempDisk gia tous diskous pou tha bgainoun apo to Priority Queue
                int folder = folders.removeFromFront();     // Afaireitai apo th lista to prwto folder kai to thetoume sth metbalhth folder
                if(heap.peek().getFreeSpace()>=folder){     // Elegxoume an to Disk me to megalutero eleuthero xwro xwraei to folder
                    tempDisk = heap.getmax();               // An exei arketo xwro tote pairnoume to Disk
                    tempDisk.add_folder(folder);            // Tou prosthetoume to fakelo
                    heap.insert(tempDisk);                     // Kai to bazoume pali sto Priority Queue
                }
                else{                                       // Diaforetika
                    heap.insert(new Disk());                   // Prosthetoume sto Priority Queue ena neo Disk
                    tempDisk = heap.getmax();               // Kai akolouthoume thn idia diadikasia me prin
                    tempDisk.add_folder(folder);
                    heap.insert(tempDisk);
                }
                total_size+=folder;                         // Prosthetoume sto total size to size tou fakelou
            }
            System.out.println("------ Sort.java(Greedy-Decreasing) -" + filename + "-----------------" );//Emfanizetai o algorithmos pou trexoume
            // kai to onoma tou .txt arxeiou pou trexoume
            System.out.println("Sum of all folders = " + (double)total_size/1000000 + " TB"); // Emfanizetai to athroisma twn fakelwn se TB
            System.out.println("Total number of disks used = " + heap.getSize());   // Emfanizetai o arithmos twn diskwn pou xrhsimopoihthikan
            if(length<100){                           // An o arithmos twn fakelwn einai mikroteros tou 100 emfanizoume analutika ton kathe disko
                while(heap.getSize()>0){              // Oso to heap exei mesa Disks
                    System.out.println(heap.getmax());      // Emfanizoume to periexomeno tou Disk ( methodos toString())
                }
            }

            // Oi parakatw metablhtes aforoun to Meros D opou kaloume th MeroDgreedydecreasing.main(args) (MerosDcompare.java)

            s_total_size+=total_size;       // Prosthetoume sto sunoliko megethos twn fakelwn to trexwn megethos twn fakelwn
            iter+=1;                        // Auksanoume to iter kata 1 ( String filename = args[iter];)
            s_total_disk+=heap.getSize();   // Prosthetoume sto sunoliko arithmo twn diskwn to trexwn arithmo twn diskwn
        }
    }

}