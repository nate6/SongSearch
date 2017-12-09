
/**
 * Starts the song search by arg inputs.
 * 
 * @author Nate Axt <nate6>
 *         Julie Tran <juliet12>
 * @version 12.7.2017
 */
public class SongSearch {

    /**
     * main method.
     * @param args {hash size, block size, file}
     */
    public static void main(String[] args) {
        int hashSize = Integer.parseInt(args[0]);
        int blockSize = Integer.parseInt(args[1]);
        new Database(hashSize, blockSize, args[2]);
    }
}
