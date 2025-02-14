class Sort{
    static void sort(List<Integer> folders){                    // Methodos sort, folders: H lista me tous fakelous pou theloume na taksinomhsoume 
        MaxIntPQ heap_fakeloi = new MaxIntPQ();                 // Dhmiourgeitai ena neo Priority Queue me int
        while(folders.getSize()>0){                             // Oso to list exei fakelous
            int folder = folders.removeFromFront();             // Pairnoume ton prwto fakelo ths listas
            heap_fakeloi.insert(folder);                           // Kai ton prostetoume sto Priority Queue
        }   
        while(heap_fakeloi.getSize()>0){                        // Oso to Priority Queue exei fakelous
            folders.insertAtBack((int)heap_fakeloi.getmax());   // Prosthetoume sto telos ths listas ton max fakelo tou Priority Queue
        }
        
    }
}