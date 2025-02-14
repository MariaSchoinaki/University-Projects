import java.io.PrintStream;
import java.util.*;

public class StringQueueWithOnePointer<T> implements StringQueue<T>{
    Node<T> tail = null;                                        // Deikths tail sto telos ths ouras. Arxika einai null
    int size = 0;                                               // Metablhth pou deixnei posa stoixeia exei h oura


    public boolean isEmpty() {                                  // Methodos isEmpty. Epistrefei true an h oura einai adeia alliws false
        return tail==null;                                      // An to tail einai null tote den exei stoixeia h oura
    }

    
    public void put(T item) {                                   // Methodos put. Eisagei to item sto telos ths ouras
        Node<T> temp = new Node<>(item);                        // Dhmiourgeia Node(item) kai anathesh sto temp
        if(isEmpty()){                                          // An h oura einai adeia
            tail=temp;                                          // To tail deixnei sto temp
            tail.setNext(temp);                                 // To epomeno tou tail einai to temp (Lista kuklikhs sundeshs)
        }
        else{                                                   // Alliws
            temp.setNext(tail.getNext());                       // Thetoume ws epomeno tou temp to epomeno tou tail(1o stoixeio)
            tail.setNext(temp);                                 // Thetoume ws epomeno tou tail to temp
            tail=temp;                                          // To tail deixnei sto temp
        }
        size+=1;                                                // To size auksanete kata 1
    }

    public T get() throws NoSuchElementException {              // Methodos get. Epistrefei to data tou prwtoi stoixeiou ths ouras kai to afairei
        if (isEmpty()){                                         // An h oura einai adeia
            throw new NoSuchElementException();                 // Bgazei Exception
        }
        T data = tail.getNext().getData();                      // Thetoume sto data to data tou epomenou stoixeiou tou tail(1o stoixeio)
        size-=1;                                                // To size meiwnetai kata 1
        if(tail==tail.getNext()){                               // An to tail isoutai me to epomeno tou tail (Mono ena stoixeio sthn oura)
            tail=null;                                          // To tail ginetai null
        }
        else{                                                   // Alliws
            tail.setNext(tail.getNext().getNext());             // Thetoume ws epomeno tou tail to epomeno tou epomenou tou tail
        }                                                       // tail.getNext()--> 1o stoixeio ouras tail.getNext().getNext()--> 2o stoixeio
        return data;                                            // Epistrefei to data
    }

    
    public T peek() throws NoSuchElementException {             // Methodos peek . Epistrefei to data tou prwtou stoixeiou ths ouras
        if (isEmpty()){                                         // An h oura einai adeia
            throw new NoSuchElementException();                 // Bgazei Exception
        }
        return tail.getNext().getData();                        // Epistrefei to data tou prwtou stoixeiou( tail.getNext()--> 1o stoixeio )
    }

    
    public void printQueue(PrintStream stream) {                // Methodos printQueue. Emfanizei ta stoixeia ths ouras
        if(tail!=null){                                         // An h oura den einai adeia
            Node<T> tem = tail.getNext();                       // To tem ginetai to prwto stoixeio (tail.getNext-->1o stoixeio)
            do{                                                 
                stream.println(tem.getData());                  // Emfanizei to data tou tem
                tem=tem.getNext();                              // To tem ginetai to epomeno tou tem
            }while(tem!=tail.getNext());                        // Ekteleitai oso to tem einai diaforou tou prwtou stoixeiou (tail.getNext-->1o stoixeio)
        }
    }

    public int size() {                                         // Methodos size. Epistrefei to arithmo twn stoixeiwn ths ouras
        return size;
    }
}