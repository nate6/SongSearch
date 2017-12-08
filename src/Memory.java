
/**
 * Memory byte array that holds records.
 * 
 * @author Nate Axt <nate6>
 *         Julie Tran <juliet12>
 * @version 12.7.2017
 */
public class Memory {
    private byte[] table;
    private int tableSize;
    private int size;
    private final int maxRecordLength = 65536;

    /**
     * Creates array of bytes.
     * @param blockSize for array
     */
    public Memory(int blockSize) {
        this.tableSize = blockSize;
        table = new byte[blockSize];
        size = 0;
    }
    
    /**
     * Adds record to bytes array.
     * @param record string
     * @return position added in array or -1 if failed
     */
    public int add(String record) {
        int start = size;
        byte[] recordBytes = record.getBytes();
        
        if (recordBytes.length > maxRecordLength) {
            return -1;
        }
        if (table[size] == 0 && table[size + 1] > 0) {
            return -2;
        }
        
        if (size >= tableSize / 2) {
            reallocate();
        }
        
        // Sets it active
        table[size] = (byte) 1;
        size++;
        
        // Sets the length
        if (recordBytes.length > 256) {
            int b1 = (recordBytes.length) & 0xff;
            int b2 = (recordBytes.length >> 8) & 0xff;
            table[size] = (byte) b1;
            size++;
            table[size] = (byte) b2;
            size++;
        }
        else {
            int b = (recordBytes.length >> 0) & 0xff;
            table[size] = (byte) b;
            size += 2;
        }
        
        // Inserts string as bytes
        for (int i = 0; i < recordBytes.length; i++) {
            table[size] = recordBytes[i];
            size++;
        }
        
        return start;
    }

    /**
     * Remove record by marking deleted.
     * @param pos of record
     * @return if deleted
     */
    public boolean remove(int pos) {
        if(pos > size || pos < 0) {
            return false;
        }
        if ((table[pos] & 0xFF) == 1) {
            table[pos] = (byte) 0;
            return true;
        }
        return false;
    }
    
    /**
     * Checks if a record is active.
     * @param pos of record
     * @return if active
     */
    public boolean isActive(int pos) {
        if(pos > size || pos < 0) {
            return false;
        }
        return (table[pos] & 0xFF) == 1;
    }
    
    /**
     * Gets the string in the record.
     * @param pos of record
     * @return the string
     */
    public String getString(int pos) {
        if(pos > size || pos < 0) {
            return null;
        }
        if ((table[pos] & 0xFF) == 1) {
            int len = (table[pos + 1]) & 0xff;
            len += (table[pos + 2] << 8) & 0xff;
            pos += 3;
            byte[] bytes = new byte[len];
            for (int i = 0; i < len; i++) {
                bytes[i] = table[pos + i];
            }
            return new String(bytes);
        }
        return null;
    }
    
    /**
     * Reallocates bytes array.
     */
    public void reallocate() {
        tableSize = tableSize * 2;
        byte[] tTemp = table;
        
        table = new byte[tableSize];
        for (int i = 0; i < tTemp.length; i++) {
            table[i] = tTemp[i];
        }
    }
}
