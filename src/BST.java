import java.util.LinkedList;
import java.util.Stack;

/**
 * BST for KVPair nodes.
 * 
 * @author Nate Axt <nate6>
 *         Julie Tran <juliet12>
 * @version 12.7.2017
 */
public class BST implements BSTInterface {
    private KVPair root;

    /**
     * Sets up tree.
     */
    public BST() {
        root = null;
    }

    /**
     * Insert a record into the tree
     * @param pair KVPair to be inserted
     * @return if properly inserted
     */
    @Override
    public boolean insert(KVPair pair) {
        if (root == null) {
            root = pair;
            return true;
        }
        
        KVPair curr = root;
        KVPair parent = null;
        
        while (curr != null) {
            parent = curr;

            if (curr.getKey().compareTo(pair.getKey()) > 0) {
                curr = curr.getRight();
                if (curr == null) {
                    parent.setRight(pair);
                }
            }
            else if (curr.getKey().compareTo(pair.getKey()) < 0) {
                curr = curr.getLeft();
                if (curr == null) {
                    parent.setLeft(pair);
                }
            }
            else if (curr.getKey().compareTo(pair.getKey()) == 0) {
                if (curr.getValue().compareTo(pair.getValue()) > 0) {
                    curr = curr.getRight();
                    if (curr == null) {
                        parent.setRight(pair);
                    }
                }
                else if (curr.getValue().compareTo(pair.getValue()) < 0) {
                    curr = curr.getLeft();
                    if (curr == null) {
                        parent.setLeft(pair);
                    }
                }
                else {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Remove a record from the tree.
     * @param pair KVPair of record to be removed 
     * @return Records removed or null if there are none 
     */
    @Override
    public KVPair[] remove(int key) {
        Iterator it = getIterator(root);
        LinkedList<KVPair> list = new LinkedList<KVPair>();
        KVPair p = new KVPair(key, 0);
        
        while (it.hasNext()) {
            KVPair n = it.next();
            if (n.getKey().compareTo(p.getKey()) == 0) {
                list.add(n);
                deleteNode(root, n);
            }
        }
        return list.toArray(new KVPair[list.size()]);
    }

    /**
     * Deletes a node with the same key/value pair.
     * @param key of record
     * @param value of record
     * @return if deleted
     */
    @Override
    public boolean delete(int key, int value) {
        KVPair d = get(key, value);
        if (d != null) {
            KVPair del = deleteNode(root, d);
            return del != null;
        }
        return false;
    }

    /**
     * Deletes a node recursively from the tree
     * @param curr node
     * @param pair node
     * @return the removed node
     */
    private KVPair deleteNode(KVPair curr, KVPair pair) {
        if (curr.getKey().compareTo(pair.getKey()) > 0) {
            curr.setLeft(deleteNode(curr.getLeft(), pair));
        }
        else if (curr.getKey().compareTo(pair.getKey()) < 0) {
            curr.setRight(deleteNode(curr.getRight(), pair));
        }
        else {
            if (curr.getValue().compareTo(pair.getValue()) > 0) {
                curr.setLeft(deleteNode(curr.getLeft(), pair));
            }
            else if (curr.getValue().compareTo(pair.getValue()) < 0) {
                curr.setRight(deleteNode(curr.getRight(), pair));
            }
            else {
                if (curr.getLeft() == null) {
                    return curr.getRight();
                }
                if (curr.getRight() == null) {
                    return curr.getLeft();
                }
                KVPair replace = curr.getRight();
                while (replace.getLeft() != null) {
                    replace = replace.getLeft();
                }
                curr.setRight(deleteNode(curr.getRight(), replace));
            }
        }
        return null;
    }

    /**
     * Gets the KVPair for the given key/value combo.
     * @param key of record
     * @param value of record
     * @return the KVPair of this combo
     */
    @Override
    public KVPair get(int key, int value) {
        KVPair currPair = root;
        KVPair p = new KVPair(key, value);
        if (currPair == null) {
            return null;
        }

        while(currPair.getKey().compareTo(p.getKey()) != 0) {
            if (currPair.getKey().compareTo(p.getKey()) > 0) {
                currPair = currPair.getRight();
            }
            else if (currPair.getKey().compareTo(p.getKey()) < 0) {
                currPair = currPair.getLeft();
            }
            else {
                if (currPair.getValue().compareTo(p.getValue()) > 0) {
                    currPair = currPair.getRight();
                }
                else if (currPair.getValue().compareTo(p.getValue()) < 0) {
                    currPair = currPair.getLeft();
                }
            }

            if (currPair == null) {
                return null;
            }
        }
        return currPair;
    }

    /**
     * Searches for an array of records that hold the given key.
     * @param key of record
     * @return Records matching key
     */
    @Override
    public KVPair[] search(int key) {
        Iterator it = getIterator(root);
        LinkedList<KVPair> list = new LinkedList<KVPair>();
        KVPair p = new KVPair(key, 0);
        
        while (it.hasNext()) {
            KVPair n = it.next();
            if (n.getKey().compareTo(p.getKey()) == 0) {
                list.add(n);
            }
        }
        return list.toArray(new KVPair[list.size()]);
    }

    /**
     * Gets a new iterator at the root.
     * @return iterator
     */
    public Iterator getIterator() {
        return new Iterator(root);
    }

    /**
     * Gets a new iterator at starting node.
     * @param root starting node
     * @return iterator
     */
    public Iterator getIterator(KVPair p) {
        return new Iterator(p);
    }

    
    /**
     * Iterator in In Order Traversal 
     * Visit the leftsubtree, then the node, then the
     * right subtree
     */
    public class Iterator {
        private Stack<KVPair> nodeStk;
        private KVPair curr;

        /**
         * Creates iterator stack.
         */
        public Iterator() {
            nodeStk = new Stack<KVPair>();
            if (root != null) {
                goLeftFrom(root);               
            }
        }


        /**
         * Creates iterator stack.
         * @param rt starting node
         */
        public Iterator(KVPair rt) {
            nodeStk = new Stack<KVPair>();
            if (rt != null) {
                goLeftFrom(rt);               
            }
        }

        /**
         * Gets the next node in the iterative process.
         * @return next node
         */
        public KVPair next() {
            if (nodeStk.empty()) {
                curr = null;
                return null;
            }

            curr = nodeStk.peek();
            nodeStk.pop();
            if (curr.getRight() != null) {
                goLeftFrom(curr.getRight());
            }
            
            return curr;
        }

        /**
         * If there are more nodes in the iterator.
         * @return if not empty
         */
        public boolean hasNext() {
            return !nodeStk.empty();
        }
        
        /**
         * Searches as far left as possible pushing parents along the way.
         * @param from starting node
         */
        private void goLeftFrom(KVPair from) {
            while (from != null) {
                nodeStk.push(from);
                from = from.getLeft();
            }
        }
    }
}