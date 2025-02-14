public class MaxPQ{
    private Disk[] heap;                                                            // Int Array heap
    private int size;                                                               // To size tou heap
    private static final int DEFAULT_CAPACITY = 4;
    private static final int AUTOGROW_SIZE = 4;
    
    public MaxPQ() {                                                                // Kataskeuasths tou MaxIntPQ
        this.heap = new Disk[DEFAULT_CAPACITY + 1];                                 // Dhmiourgeitai to Array
        this.size = 0;                                                              // Arxikopoieitai to size ws 0
    }
        
    public void insert(Disk item) {                                   // Methodos insert , item: auto pou theloume na eisagoume
        if (size == heap.length - 1)                               // An o arithmos twn stoixeiwn isoutai me to length tou array meiwmenw kata 1
            grow();                                                // Tote ekteleitai h grow 
        heap[++size] = item;                                       // Auksanetai to size kata 1 kai to item eisagetai sto heap[size]
        swim(size);                                                // Ekteleitai h swim
    }

    public Disk peek() {                                            // Methodos peek
        if (size == 0)                                              // An to megethos einai 0
            return null;                                            // epistrefei null
        return heap[1];                                             // Diaforetika epistrefetai to prwto stoixeio tou array (to heap[0] einai keno)
    }

    public Disk getmax() {                                          // Methodos getmax
        if (size == 0)                                              // An to size einai 0
            return null;                                            // Epistrefetai null
        Disk root = heap[1];                                        // H metablhth root isoutai me to heap[1]
        heap[1] = heap[size];                                       // To heap[1] isoutai me to teleutaio stoixeio tou heap (heap[size])
        size--;                                                     // To size meiwnetai kata 1
        sink(1);                                                 // Ekteleitai h sink                
        return root;                                                // Epistrefetai to root
    }

    private void swim(int i) {                                      // Methodos swim, i: to index tou stoixeiou
        if (i == 1)                                                 // An to i isoutai me 1 (1 stoixeio)
            return;                                                 // Den kanei tipota
         int parent = i / 2;                                        // H parent isoutai me to i/2
         while (i != 1 && (heap[i]).compareTo(heap[parent]) > 0) {  // Oso to i einai diaforo tou 1 kai to heap[i]>heap[parent]
            swap(i, parent);                                        // Ekteleitai h swap
             i = parent;                                            // To i isoutai me to parent
             parent = i / 2;                                        // To parent isoutai me to i/2
          }
    }
    
    private void sink(int i) {                                      // Methodos sink
        int left = 2 * i;                                           // To left ginetai 2*i
        int right = left + 1;                                       // To right ginetai left+1
        if (left > size)                                            // An to left>size
            return;                                                 // Tote den kanei tipota
        while (left <= size) {                                      // Oso left<=size
            int max = left;                                         // H metablhth size ginetai left
            if (right <= size) {                                    // An heap[left]<heap[right]
                if (heap[left].compareTo(heap[right]) < 0)          // An heap[left]<heap[right]
                    max = right;                                    // Tote to max ginetai to right
            }
            if (heap[i].compareTo(heap[max]) >= 0)                  // An heap[i]>=heap[max]
                return;                                             // Stamataei thn ektelesh
            else {                                                  // Diaforetika
                swap(i, max);                                       // Ekteleitai h swap
                i = max;                                            // To i ginetai max
                left = i * 2;                                       // To left ginetai i*2
                right = left + 1;                                   // To right ginetai left+1
            }
        }
    }
    
    private void swap(int i, int j) {                               // Methodos swap, i,j : index twn stoixeiwn pou theloume na antimetathesoume
        Disk tmp = heap[i];                                         // To tmp isoutai me to heap[i]
        heap[i] = heap[j];                                          // To heap[i] isoutai me to heap[j]
        heap[j] = tmp;                                              // To heap[j] isoutai me to tmp
    }
    
    private void grow() {                                           // Methodos grow
        Disk[] newHeap = new Disk[heap.length + AUTOGROW_SIZE];     // Dhmiourgeitai neo array
        for (int i = 0; i <= size; i++) {
            newHeap[i] = heap[i];                                   // Antigrafontai ta stoixeia tou paliou array sto kainourgio
        }   
        heap = newHeap;                                             // To palio array pleon isoutai me to kainourgio
    }   

    public int getSize(){                                           // Methodos getSize
        return size;                                                // Epistrefei to size
    }
}
    

