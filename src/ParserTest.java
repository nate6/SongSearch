
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the Text Parser.
 * 
 * @author Nate Axt <nate6>
 *         Julie Tran <juliet12>
 * @version 12.7.2017
 */
public class ParserTest {
    private final ByteArrayOutputStream outContent = 
            new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = 
            new ByteArrayOutputStream();

    /**
     * Sets up printstreams
     */
    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    /**
     * Parses the correct output file.
     * @param out output file
     * @return the entire output file with line breaks
     */
    private String parseOut(String out) {
        Scanner sc = null;
        String text = "";
        try {
            sc = new Scanner(new File(out));
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        while (sc.hasNext()) {
            text += sc.nextLine();
            text += "\n"; // \r\n for windows, \n for webcat 
        }
        return text;
    }
    
    /**
     * Tests syntax1
     */
    @Test
    public void testSyntax1() {
        new Parser("Test1a.txt");
        String out = parseOut("Test1b.txt");
        assertEquals(out, outContent.toString());
        assertEquals("", errContent.toString());
    }
    
    /**
     * Tests syntax2
     */
    @Test
    public void testSyntax2() {
        new Parser("Test2a.txt");
        String out = parseOut("Test2b.txt");
        assertEquals(out, outContent.toString());
        assertEquals("", errContent.toString());
    }

    /**
     * Cleans System out and error settings after tests.
     */
    @After
    public void tearDown() {
        System.setOut(null);
        System.setErr(null);
    }

}
