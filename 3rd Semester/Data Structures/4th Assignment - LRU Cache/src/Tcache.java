//-----Class : public class Tcache<K, V>-------
/*This class implemnts the Cache interface and its methods as given below :   */
public class Tcache<K, V> implements Cache<K, V> {
    private Hashing<K, Node<K, V>> cache;                                               // cache variable of type Hashing  to intialize  our cache later                                       
    private Node<K, V> MRU;                                                             // Most Recent Used  node
    private Node<K, V> LRU;                                                             // Least Recent Used node
    private int cachesize;                                                              // the amount of cache size
    private int insidecache;                                                            // number of elements inside cache
    private int hits;                                                                   // amount of cache hits
    private int miss;                                                                   // amount of cache misses
//------Class Constructor------
/*Initializes cache size  , Most Recent Used  node , Least Recent Used node  and insidecache  */
    public Tcache(int cachesize) {
        cache = new Hashing<K, Node<K, V>>();                                           // create a Hashing object (cache)
        MRU = new Node<K, V>(null, null, null, null);           // new empty node stored in MRU
        LRU = MRU;                                                                      // LRU is initialized as null
        this.cachesize = cachesize;                                                     // store the cache size
        this.insidecache = 0;                                                           // at first cache items are zero
        this.hits = 0;                                                                  // at first zero hits
        this.miss = 0;                                                                  // at first zero misses
    }
//------Method : public V lookUp(K key);-------
/* This method returns a value (V) associated with the given key  If the requested key is found the we have a cache hit otherwise a miss and the method returns null */ 
    public V lookUp(K key) {
        Node<K, V> node = cache.get(key);                                               // node that contains the value of requested key
        if (node == null) {                                                             // if  node is null (key not found) that means we have a miss
            miss++;                                                                     
            return null;                                                                // having a miss means tha there is nothing to return
        }
        if (node.getKey() == MRU.getKey()) {                                            // if the cache hited the MRU element we leave it as it is
            hits++;                                                                     // raise the number of hits by 1
            return MRU.getValue();                                                      // return the MRU value
        }
        Node<K, V> nextNode = node.getNext();                                           // store the next node that will be used  if we hit LRU or an item between the edges 
        if (node.getKey() == LRU.getKey()) {                                            // determine if LRU is hited
            nextNode.setPrevious(null);                                    
            LRU = nextNode;                                                             // LRU must be updated and get the value of  the  next node
        } else {                                                                        // if none of LRU OR MRU was hited 
            Node<K, V> previousNode = node.getPrevious();                               // intialize the previous node  
            previousNode.setNext(nextNode);                                             // set the predecessor of the node that was hited to have successor  the next node
            nextNode.setPrevious(previousNode);                                         // set the successor of the node that was hited to have predecessor  the previous node
        }
        node.setPrevious(MRU);                                                          // set the set the predecessor to be MRU
        MRU.setNext(node);                                                              // set MRU's successor to be the hited node
        MRU = node;                                                                     // update MRU's value
        MRU.setNext(null);                                                         // set MRU's next node to be null

        hits++;                                                                         // raise the amount of hits by one
        return node.getValue();                                                         // return the node value
    }
//------Method : public void store(K key, V value);-------
/* This void method stores a key with its a associated node containing data */
    public void store(K key, V value) {
        if (cache.get(key) != null) {                                                   // 2 keys associated with on value are forbidden
            return;
        }
        Node<K, V> node = new Node<K, V>(MRU, null, key, value);                   // create a node to store the data 
        MRU.setNext(node);                                                              // link the node with the MRU state
        cache.insert(key, node);                                                        // insert the node with its associated key in the cache
        MRU = node;                                                                     // update the MRU value
        if (insidecache == cachesize) {                                                 // if cache is full we have to delete the item contained in LRU node
            cache.remove(LRU.getKey());                                                 // call the method for removall
            LRU = LRU.getNext();                                                        // update the LRU value
            LRU.setPrevious(null);                                             // the updated node has no previous nodes
        } else {                                                                        // if  cache is not full  we insert the item normaly
            if (insidecache == 0) {                                                     // if we insert an item for the first time the LRU node must be initialized
                LRU = node;                                                             // set the LRU node
            }
            insidecache++;                                                              // raise the counter by 1
        }
    }
//------Getter : public double getHitRatio();------
/*Returns the HitRatio */
    public double getHitRatio() {
        return ((double) getHits() / (double) getNumberOfLookUps());
    }
//------Getter : long getHits();------
/*Returns the amount of hits*/
    public long getHits() {
        return hits;
    }
//------Getter : public long getMisses();------
/*Returns the amount of misses*/
    public long getMisses() {
        return miss;
    }
//------Getter : public long getNumberOfLookUps()------
/*Returns the amount of LooKUps*/
    public long getNumberOfLookUps() {
        return (getMisses() + getHits());
    }
}
