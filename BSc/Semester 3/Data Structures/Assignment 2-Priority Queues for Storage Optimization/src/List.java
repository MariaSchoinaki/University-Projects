public class List<T>{                   
    private Node<T> head = null;                                // Deikths Head
    private Node<T> tail = null;                                // Deikths Tail
    private int size = 0 ;                                      // Megethos ths listas

    public List() {
    }

    public boolean isEmpty() {                                  // Methodos isEmpty
        return head == null;                                    // Epistrefei true an einai adeia (head == null) alliws false
    }

    public void insertAtBack(T data) {                          // Methodos insertAtBack , data : auto pou theloume na eisagoume
        Node<T> n = new Node<>(data);                           // Dhmiourgeitai neo node

        if (isEmpty()) {                                        // An h lista einai adeia
            head = n;                                           // To head ginetai to neo node
            tail = n;                                           // To tail ginetai to neo node
        } else {                                                // Diaforetika
            tail.setNext(n);                                    // To n eisagetai meta to tail
            tail = n;                                           // To n ginetai to tail
        }
        size++;                                                 // To size auksanetai kata 1
    }

    public T removeFromFront(){                                 // Methodos removeFromFront
        if (isEmpty())                                          // An einai adeia
            return null;                                        // Epistrefei null

        T data = head.getData();                                // H metablhth data isoutai me to data tou head
        if (head == tail){                                      // An to head isoutai me to tail (H lista exei ena stoixeio)
            head = null;                                        // To head ginetai null                
            tail = null;                                        // To tail ginetai null
        }
        else{                                                   // Diaforetika
            head = head.getNext();                              // To head ginetai to epomeno node apo to head
        }
        size--;                                                 // To size meiwnetai kata 1
        return data;                                            // Epistrefetai to data
    }

    public int getSize(){                                       // Methodos getSize
        return size;                                            // Epistrefei to size
    }
}
