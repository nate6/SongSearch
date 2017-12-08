
public class KVPair {
    private Handle key;
    private Handle value;
    private KVPair left;
    private KVPair right;
    
    public KVPair(int khash, int vhash) {
        key = new Handle(khash);
        value = new Handle(vhash);
        left = null;
        right = null;
    }
    
    public KVPair(KVPair data) {
        key = new Handle(data.getKey().getHash());
        value = new Handle(data.getValue().getHash());
    }
    
    public Handle getKey() {
        return key;
    }
    
    public Handle getValue() {
        return value;
    }
    
    public void setLeft(KVPair left) {
        this.left = left;
    }
    
    public KVPair getLeft() {
        return left;
    }
    
    public void setRight(KVPair right) {
        this.right = right;
    }
    
    public KVPair getRight() {
        return right;
    }

    class Handle implements Comparable<Handle> {
        private int hash;
        
        public Handle(int hash) {
            this.hash = hash;
        }
        
        public int getHash() {
            return hash;
        }

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
