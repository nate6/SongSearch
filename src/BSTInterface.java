
/**
 * BST interface for KVPair nodes.
 * 
 * @author Nate Axt <nate6>
 *         Julie Tran <juliet12>
 * @version 12.7.2017
 */
public interface BSTInterface {

    /**
     * Insert a record into the tree
     * @param pair KVPair to be inserted
     * @return if properly inserted
     */
    public boolean insert(KVPair pair);

    /**
     * Remove a record from the tree.
     * @param pair KVPair of record to be removed 
     * @return Records removed or null if there are none 
     */
    public KVPair[] remove(int key);

    /**
     * Deletes a node with the same key/value pair.
     * @param key of record
     * @param value of record
     * @return if deleted
     */
    public boolean delete(int key, int value);

    /**
     * Searches for an array of records that hold the given key.
     * @param key of record
     * @return Records matching key
     */
    public KVPair[] search(int key);

    /**
     * Gets the KVPair for the given key/value combo.
     * @param key of record
     * @param value of record
     * @return the KVPair of this combo
     */
    public KVPair get(int key, int value);
}
