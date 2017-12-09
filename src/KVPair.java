
/**
 * Key value pair of handles for a record.
 * 
 * @author Nate Axt <nate6>
 *         Julie Tran <juliet12>
 * @version 12.7.2017
 */
public class KVPair {
    private Handle key;
    private Handle value;
    private KVPair left;
    private KVPair right;
    
    /**
     * Creates pair from ints
     * @param khash key handle
     * @param vhash value handle
     */
    public KVPair(int khash, int vhash) {
        key = new Handle(khash);
        value = new Handle(vhash);
        left = null;
        right = null;
    }
    
    /**
     * Creates pair from existing pair
     * @param data pair
     */
    public KVPair(KVPair data) {
        key = new Handle(data.getKey().getHash());
        value = new Handle(data.getValue().getHash());
    }
    
    /**
     * Gets the key
     * @return key
     */
    public Handle getKey() {
        return key;
    }
    
    /**
     * Gets the value
     * @return value
     */
    public Handle getValue() {
        return value;
    }
    
    /**
     * Sets the left pair
     * @param left pair
     */
    public void setLeft(KVPair left) {
        this.left = left;
    }

    /**
     * Gets the left pair
     * @return left pair
     */
    public KVPair getLeft() {
        return left;
    }
    
    /**
     * Sets the right pair
     * @param right pair
     */
    public void setRight(KVPair right) {
        this.right = right;
    }

    /**
     * Gets the right pair
     * @return right pair
     */
    public KVPair getRight() {
        return right;
    }

    /**
     * Handle of a key or value that is comparable.
     * 
     * @author Nate Axt <nate6>
     *         Julie Tran <juliet12>
     * @version 12.7.2017
     */
    class Handle implements Comparable<Handle> {
        private int hash;
        
        /**
         * Sets handle
         * @param hash handle
         */
        public Handle(int hash) {
            this.hash = hash;
        }
        
        /**
         * Gets handle
         * @return handle
         */
        public int getHash() {
            return hash;
        }

        /**
         * Compares handle values.
         * @param o given handle
         * @return 1 if o is greater, -1 if less than, 0 if same
         */
        @Override
        public int compareTo(Handle o) {
            if (o.getHash() == hash) {
                return 0;
            }
            else if (o.getHash() > hash) {
                return 1;
            }
            return -1;
        }
        
    }
}
