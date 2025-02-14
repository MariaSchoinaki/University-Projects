class Disk implements Comparable<Disk> {
    static int i = 0;                                       // Static metablhth gia to id tou kathe diskou
    List<Integer> folders = new List<>();                   // Lista me tous fakelous pou periexei kathe Disk
    private int id;                                         // To id tou Disk
    private int rem_size = 1000000;                         // To size tou Disk

    Disk(){                                                 // Kataskeuatsths
        id = i;                                             // Thetei to i ws to id tou disk
        i++;                                                // Kai auksanei to i kata 1
    }

    public int getFreeSpace(){                              // Methodos getFreeSpace
        return rem_size;                                    // Epistrefei ton xwro pou apomenei sto disko
    }

    public void add_folder(int folder){                     // Methodos add_folder , folder : to megethos tou fakelou
        folders.insertAtBack(folder);                       // Prostithete o fakelos sto telos ths listas
        rem_size-=folder;                                   // Enhmeronoume to megethos tou diskou
    }

    public int compareTo(Disk D) {                          // Methodos compareTo, D : diskos me ton opoio sugkrinoume
        if (this.rem_size>D.getFreeSpace()){                // An to size tou diskou einai megalutero apo tou D tote
            return 1;                                       // epistrefei 1
        }
        else if(this.rem_size<D.getFreeSpace()){            // An to size tou diskou einai mikrotero apo tou D tote
            return -1;                                      // epistrefei -1
        }
        return 0;                                           // Diaforetika (an exoun idio size) epistrefei 0
    }

    public String toString(){                                   // Methodos toString() gia thn emfanish tou diskou
        String temp = "id ";                                    // Thetoume to string temp ws "id "
        temp = temp + this.id + " " + this.rem_size + ": ";     // To temp ginetai "id (id Disk): "
        for (int i=0; i<folders.getSize() ; i++){               // Gia kathe fakelo sto List
            int temp1 = folders.removeFromFront();              // Afairoume to fakelo kai to apothikeuoume sto temp1
            temp += temp1 + " ";                                // Enhmerwnoume to String
            folders.insertAtBack(temp1);                        // Ksanaprosthetoume to fakelo sto list
        }
        return temp;                                            // Epistrefoume to String
    }
}