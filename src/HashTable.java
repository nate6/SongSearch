import java.util.Arrays;

/**
 * Hashes strings to integers and stores them in memory.
 * 
 * @author Nate Axt <nate6>
 *         Julie Tran <juliet12>
 * @version 12.7.2017
 */
public class HashTable implements HashTableInterface {
    private Memory mem;
    private int[] handles;
    private int hashSize;
    private int tableSize;

    /**
     * Creates memory and sets sizes.
     * @param hashSize for handle array
     * @param blockSize for memory array
     */
    public HashTable(int hashSize, int blockSize) {
        mem = new Memory(blockSize);
        this.hashSize = hashSize;
        this.tableSize = blockSize;
        handles = new int[hashSize];
        Arrays.fill(handles, -1);
    }

    /**
     * Inserts string into memory by a hashing.
     * @param record string
     * @return the handle or -1 if failed insertion
     */
    @Override
    public int insert(String record) {
        int handle = hash(record);
        return insert(handle, record);
    }

    /**
     * Inserts string into memory by a hashing.
     * @param handle of string
     * @param record string
     * @return the handle or -1 if failed insertion
     */
    private int insert(int handle, String record) {
        int pos = mem.add(record);
        
        if (pos == -1) {
            return -1;
        }
        else if (pos == -2) {
            return insert(collision(handle), record);
        }
        handles[handle] = pos;
        return handle;
    }
    
    /**
     * Deletes record from memory.
     * @param record string
     * @return if deleted
     */
    @Override
    public boolean delete(String record) {
        int pos = handles[hash(record)];
        return mem.remove(pos);
    }

    /**
     * Searches for the handle of the given record.
     * @param record string
     * @return the handle or -1 if failed to find
     */
    @Override
    public int find(String record) {
        int handle = hash(record);
        while (handle < hashSize) {
            int pos = handles[handle];
            if (record.equals(mem.getString(pos))) {
                return handle;
            }
            handle = collision(handle);
        }
        return -1;
    }

    /**
     * Gets the pos in memory for the record for the given hashcode.
     * @param hashCode for string
     * @return the pos in memory that goes with this hashcode
     */
    @Override
    public String get(int hashCode) {
        int pos = handles[hashCode];
        if (!mem.isActive(pos)) {
            return null;
        }
        return mem.getString(pos);
    }

    /**
     * Gets the string record for the given hashcode.
     * @param hashCode for string
     * @return the string that goes with this hashcode
     */
    @Override
    public int getMemPos(int hashCode) {
        return handles[hashCode];
    }
    
    /**
     * Adjusts collision while inserting
     * @param handle of string
     * @return new calculated handle
     */
    private int collision(int handle) {
        return (int) Math.pow(handle, 2);
    }
    
    /**
     * Gets a hashcode for a given string.
     * @param s string
     * @return the hash for the string
     */
    private int hash(String s) {
        int intLength = s.length() / 4;
        long sum = 0;
        for (int j = 0; j < intLength; j++) {
            char[] c = s.substring(j * 4, (j * 4) + 4).toCharArray();
            long mult = 1;
            for (int k = 0; k < c.length; k++) {
                sum += c[k] * mult;
                mult *= 256;
            }
        }

        char[] c = s.substring(intLength * 4).toCharArray();
        long mult = 1;
        for (int k = 0; k < c.length; k++) {
            sum += c[k] * mult;
            mult *= 256;
        }

        return (int) (Math.abs(sum) % tableSize);
    }
}
