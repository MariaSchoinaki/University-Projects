public class Node<T> {
    protected T data;                           // Ta data pou exei kathe node
    protected Node<T> next = null;              // Deixnei sto epomeno node. Arxika einai null

    
    Node(T data) {                             // Kataskeuasths node
        this.data = data;
    }

    public T getData() {                       // Methodos getData epistrefei ta data
        return data;
    }

    public Node<T> getNext() {                 // Methodos getNext epistrefei to epomeno Node
        return next;
    }

    public void setNext(Node<T> next) {        // Methodos setNext(Node<T> next) thetei to epomeno node
        this.next = next;
    }
}