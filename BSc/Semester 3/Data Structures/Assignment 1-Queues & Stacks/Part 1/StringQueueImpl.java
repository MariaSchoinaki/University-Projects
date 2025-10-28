import java.util.*;
import java.io.PrintStream;

public class StringQueueImpl<T> implements StringQueue<T>{
    private Node<T> head = null;                           // Deikths head ths ouras (Deixnei panta sthn arxh ths ouras. Sthn arxh null)
    private Node<T> tail = null;                           // Deikths tail ths ouras (Deixnei panta sto telos ths ouras. Sthn arxh null)
    private int size = 0;                                  // Metablhth pou deixnei to megethos ths ouras(arithmos stoixeiwn)

    public StringQueueImpl(){                              // Kataskeuasths ths ouras
    
    }

    public boolean isEmpty(){                              // Methodos isEmpty(). Epistrefei true an einai adeia h oura alliws epistrefei false
        return head==null;                                 // An to head einai null h oura einai adeia
    }

    public void put(T item){                               // Methodos put(T item). Eisagei to stoixeio item sthn arxh ths ouras
        Node<T> temp = new Node<>(item);                   // Dhmiourgeia Node(item) kai anathesh tou se temp metablhth
        if(isEmpty()){                                     // An h oura einai adeia
            head = temp;                                   // To head deixnei sto temp(item pou eisagame)
            tail = temp;                                   // To tail deixnei sto temp(item pou eisagame)
        }
        else{                                              // Alliws
            tail.setNext(temp);                            // Thetoume ws epomeno tou teleutaiou stoixeiou ths ouras to temp
            tail = temp;                                   // To tail deixnei sto temp(Eisagwgh temp sto telos)
        }
        size+=1;                                           // Auksanete to size kata 1 (megethos ouras)
    }

    public T get() throws NoSuchElementException{          // Methodos get. Epistrefei to data tou prwtou stoixeiou kai to afairei apo thn oura
        if (isEmpty()){                                    // An h oura einai adeia
            throw new NoSuchElementException();            // Bgazei Exception
        }
        T data = head.getData();                           // Apothikeuoume to data tou prwtou stoixeiou ths ouras se antistoixh metablhth
        size-=1;                                           // To size meiwnetai kata 1 (megethos ouras)
        if (head==tail){                                   // An to head isoutai me to tail (Dhladh h oura exei 1 stoixeio)
            head = null;                                   // To head ginetai null
            tail = null;                                   // To tail ginetai null
        }
        else{                                              // Alliws
            head=head.getNext();                           // To head deixnei sto epomeno stoixeio (2o stoixeio ths ouras)
        }
        return data;                                       // Epistrefoume to data
    }

    public T peek() throws NoSuchElementException{         // Methodos peek. Epistrefei to data tou prwtou stoixeiou xwris na to afairesei
        if (isEmpty()){                                    // An h oura einai adeia
            throw new NoSuchElementException();            // Bgazei Exception
        }
        return head.getData();                             // Epistrefei to data tou head
    }

    public void printQueue(PrintStream stream){            // Methodos printQueue. Emfanizei ta stoixeia ths ouras
        Node<T> tem = head;                                // To tem deixnei sto prwto stoixeio ths ouras
        while (tem!=null){                                 // Oso to tem den einai null
            stream.println(tem.getData());                 // Emfanish tou data tou tem
            tem=tem.getNext();                             // To tem deixnei sto epomeno apo to tem
        }
    }

    public int size(){                                     // Methodos size. Epistrefei to size ths ouras
        return size;
    }

}