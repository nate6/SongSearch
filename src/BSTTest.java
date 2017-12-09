import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * 
 * @author Nate Axt <nate6>
 *         Julie Tran <juliet12>
 * @version 12.7.2017
 */
public class BSTTest {
    private BST newBST;
    private KVPair pair;
    private KVPair pair1;
    private KVPair pair2;
    private KVPair pair3;
    private KVPair pair4;
    private KVPair pair5;

    @Before
    public void setUp() {
        newBST = new BST();
    }

    /**
     * Tests Insert
     */
    @Test
    public void testInsert() {
        assertEquals(0, 0);

        newBST.insert(pair);
        pair.getKey();
        pair.getValue();
        // System.out.print(); this line needs to declare the pair is null
        newBST.getIterator(pair);

        // Insert Test
        pair1 = new KVPair(1, 1);
        pair2 = new KVPair(2, 2);
        newBST.insert(pair1);
        newBST.insert(pair2);
        // this line needs to return how much/size of the tree currently

        assertEquals(1, 1);
        assertEquals(2, 2);

        // Duplicates case
        pair3 = new KVPair(2, 2);
        pair4 = new KVPair(2, 3);
        pair5 = new KVPair(3, 2);
        newBST.insert(pair3);
        // assertEquals()
        newBST.insert(pair4);
        newBST.insert(pair5);
    }

    /**
     * Tests Remove
     */
    @Test
    public void testRemove() {
        assertNull(newBST.remove(pair.getKey().getHash()));

        pair = new KVPair(pair1);
        pair3 = new KVPair(3, 3);
        pair4 = new KVPair(4, 4);
        pair5 = new KVPair(5, 5);

        pair.setLeft(pair3);
        assertEquals(pair3, newBST.remove(pair3.getKey().getHash()));

        newBST.insert(pair4);
        newBST.insert(pair3);
        assertEquals(pair3, newBST.remove(pair3.getKey().getHash()));
        assertEquals(pair3, newBST.remove(pair4.getKey().getHash()));

        newBST.insert(pair3);
        newBST.insert(pair4);
        newBST.insert(pair5);
        assertEquals(pair3, newBST.remove(pair3.getKey().getHash()));
        assertEquals(pair4, newBST.remove(pair4.getKey().getHash()));
        assertEquals(pair5, newBST.remove(pair5.getKey().getHash()));
    }

    /**
     * Tests Iterator
     */
    @Test
    public void testIterator() {
        BST bst = new BST();
        bst.insert(pair);
        bst.insert(pair1);
        bst.insert(pair2);
        bst.insert(pair3);
        bst.insert(pair4);
        bst.insert(pair5);

        BST.Iterator it = bst.getIterator();
        assertTrue(it.hasNext());
        assertEquals(pair, it.next());
        assertEquals(pair1, it.next());
        assertEquals(pair2, it.next());
        assertEquals(pair3, it.next());
        assertEquals(pair4, it.next());
        assertEquals(pair5, it.next());
        assertFalse(it.hasNext());

        assertTrue(it.equals(it));
        BST.Iterator nit = null;
        assertFalse(it.equals(nit));
        assertFalse(it.getClass().equals(BST.Iterator.class));

        BST.Iterator i1 = bst.getIterator();
        assertFalse(it.equals(i1));
        for (int i = 0; i < 6; i++) {
            i1.next();
        }
        assertTrue(it.equals(i1));

        BST.Iterator ia = bst.getIterator();
        ia.next();
        BST.Iterator ib = bst.getIterator();
        assertFalse(ia.equals(ib));
        ib.next();
        assertTrue(ia.equals(ib));
    }
}