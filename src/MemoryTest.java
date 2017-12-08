import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests Memory.
 * 
 * @author Nate Axt <nate6>
 *         Julie Tran <juliet12>
 * @version 12.7.2017
 */
public class MemoryTest {
    private Memory mem;
    
    /**
     * Sets up memory.
     */
    @Before
    public void setUp() {
        mem = new Memory(1000);
    }

    /**
     * Tests add
     */
    @Test
    public void testAdd() {
        assertEquals(0, mem.add("a"));
        assertEquals(4, mem.add("sandwhich"));
        assertEquals(16, mem.add("Two words."));
        
        assertEquals(29, mem.add("looooooooooooooooooooooooooooooooooooooooo" +
                "ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo" +
                "ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo" +
                "ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo" +
                "ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo" +
                "ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo" +
                "ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo" +
                "ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo" +
                "ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo" +
                "ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo" +
                "ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo" +
                "oooooooooooooooooooooooooooooooooooooooooooooooooooooooong"));
        
        assertEquals(722, mem.add("o"));
    }

    /**
     * Tests Remove
     */
    @Test
    public void testRemove() {
        mem.add("a");
        assertTrue(mem.remove(0));
        assertFalse(mem.isActive(0));
        assertFalse(mem.remove(0));
        assertFalse(mem.remove(10));
        assertFalse(mem.remove(-1));
    }

    /**
     * Tests IsActive
     */
    @Test
    public void testIsActive() {
        mem.add("a");
        assertTrue(mem.isActive(0));
        mem.remove(0);
        assertFalse(mem.isActive(0));
        assertFalse(mem.isActive(10));
        assertFalse(mem.isActive(-1));
    }

    /**
     * Tests GetString
     */
    @Test
    public void testGetString() {
        int i = mem.add("a");
        assertEquals("a", mem.getString(i));
        i = mem.add("sandwhich");
        assertEquals("sandwhich", mem.getString(i));
        i = mem.add("Two words.");
        assertEquals("Two words.", mem.getString(i));
        i = mem.add("o");
        assertEquals("o", mem.getString(i));
        assertEquals(null, mem.getString(700));
        assertEquals(null, mem.getString(-1));
    }

}
