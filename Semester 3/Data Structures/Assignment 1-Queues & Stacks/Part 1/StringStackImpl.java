import java.io.PrintStream;
import java.util.*;



public class StringStackImpl<T> implements StringStack<T>{
    private Node<T> head = null;                           // Deikths head ths stoibas (Deixnei panta sthn korufh ths stoibas. Sthn arxh null)
    private int size = 0;                                  // Metablhth pou deixnei to megethos ths stoibas(arithmos stoixeiwn)
    public StringStackImpl(){                              // Kataskeuasths ths stoibas
    
    }

    public boolean isEmpty(){                             // Methodos isEmpty(). Epistrefei true an einai adeia h stoiba alliws epistrefei false
        return head==null;                                // An to head einai null h stoiba einai adeia
    }

    public void push(T item){                             // Methodos push(T item). Eisagei to stoixeio Item sthn korufh ths stoibas
        Node<T> temp = new Node<>(item);                  // Dhmiourgeia Node(item) kai anathesh tou se temp metablhth
        if (isEmpty()){                                   // An h stoiba einai adeia
            head = temp;                                  // Eisagwgh stoixeiou sthn korufh opote to head deixnei sto temp
        }
        else{                                             // Alliws
            temp.setNext(head);                           // Thetoume ws epomeno tou temp to stoixeio pou brisketai sthn korufh (dhladh to head)
            head = temp;                                  // Kai to head deixnei sto temp (Eisagw sthn korufh)
        }
        size+=1;                                          // To size auksanetai kata 1
    }

    public T pop() throws NoSuchElementException{         // Methodos pop. Afairei kai epistrefei to stoixeio pou brisketai sthn korufh
        if (isEmpty()){                                   // An h stoiba einai adeia 
            throw new NoSuchElementException();           // Bgazei Exception 
        }
        T data = head.getData();                          // Apothikeuoume to data tou stoixeiou ths korufhs se antistoixh metablhth                                          
        head=head.getNext(); // To head deixnei pleon sto epomeno stoixeio ths stoibas(sto 2o stoixeio an uparxei, diaforetika deixnei sto null)                                            
        return data;                                      // Epistrefetai to data
    }

    public T peek() throws NoSuchElementException{        // Methodos peek. Epistrefei to data apo to prwto stoixeio xwris na to afairesei
        if (isEmpty()){                                   // An h stoiba einai adeia
            throw new NoSuchElementException();           // Bgazei Exception 
        }   
        return head.getData();                            // Epistrefei to data apo to prwto stoixeio ths stoibas(head)
    }

    public void printStack(PrintStream stream) {          // Methodos printStack(PrintStream stream). Ektupwnei ola ta stoixeia ths stoibas
        Node<T> tem = head;                               // Anathetoume to prwto stoixeio ths stoibas sto tem
        while(tem!=null){                                 // oso to tem einai diaforo tou null
            stream.println(tem.getData());                // Ektupwnei to data tou tem
            tem=tem.getNext();                            // To tem isoutai me to epomeno tou tem
        }
        
    }

    public int size(){                                   // Methodos size(). Epistrefei to megethos ths stoibas
        return size;
    }


}