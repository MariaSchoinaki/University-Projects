//-------------Class : public class Node<K, V>-------------
/*This class implements Nodes for the types K,V (keys, values) with setters and getters for each field */
public class Node<K, V> {
    private Node<K, V> previous;                                            // previous node
    private Node<K, V> next;                                                // next node
    private K key;                                                          // key of the node
    private V value;                                                        // value of the node

//------Class Constructor------
/*Every node must have its predecessors , successors, keys  and values */
    public Node(Node<K, V> previous, Node<K, V> next, K key, V value) {       
        this.previous = previous;                                          // set the previous node
        this.next = next;                                                  // set the next node
        this.key = key;                                                    // set the key
        this.value = value;                                                // set the value
    }

//------Getter : public Node<K, V> getNext() ------
/*Returns the next node */
    public Node<K, V> getNext() {
        return next;
    }

//------Getter : public Node<K, V> getPrevious() ------
/*Returns the predecessor of the node */
    public Node<K, V> getPrevious() {
        return previous;
    }

//------Setter : public void setNext(Node<K, V> next) ------
/*Sets the successor of the node */
    public void setNext(Node<K, V> next) {
        this.next = next;
    }

//------Setter : public void setPrevious(Node<K, V> previous) ------
/*Sets the predecessor of the node */
    public void setPrevious(Node<K, V> previous) {
        this.previous = previous;
    }

//------Getter : public K getKey() ------
/*Returns the key of the node */
    public K getKey() {
        return key;
    }

//------Getter : public V getValue() ------
/*Returns the value of the node */
    public V getValue() {
        return value;
    }
}
