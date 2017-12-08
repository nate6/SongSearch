
public class SongSearch {

    public static void main(String[] args) {
        int hashSize = Integer.parseInt(args[0]);
        int blockSize = Integer.parseInt(args[1]);
        new Database(hashSize, blockSize, args[2]);
    }
}
