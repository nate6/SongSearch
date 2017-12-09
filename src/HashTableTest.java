import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests HashTable.
 * 
 * @author Nate Axt <nate6>
 *         Julie Tran <juliet12>
 * @version 12.7.2017
 */
public class HashTableTest {
    private HashTable t;
    
    /**
     * Creates a table.
     */
    @Before
    public void setUp() {
        t = new HashTable(100000, 1000);
    }

    /**
     * Test insert
     */
    @Test
    public void testInsert() {
        assertEquals(97, t.insert("a"));
        assertEquals(864, t.insert("hdga"));
    }

    /**
     * Test delete
     */
    @Test
    public void testDelete() {
        t.insert("a");
        assertTrue(t.delete("a"));
        assertFalse(t.delete("a"));
    }

    /**
     * Test find
     */
    @Test
    public void testFind() {
        t.insert("a");
        assertEquals(97, t.find("a"));
        assertEquals(-1, t.find("b"));
        assertTrue(t.delete("a"));
        assertEquals(-1, t.find("a"));
    }

    /**
     * Test get
     */
    @Test
    public void testGet() {
        t.insert("a");
        assertEquals("a", t.get(97));
        assertEquals(0, t.getMemPos(97));
        t.insert("hsfd");
        assertEquals(4, t.getMemPos(t.find("hsfd")));
        assertEquals(null, t.get(98));
        t.delete("a");
        assertEquals(null, t.get(97));
    }

}
