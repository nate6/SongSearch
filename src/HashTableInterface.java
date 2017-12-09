
/**
 * Hashes strings to integers and stores them in memory.
 * 
 * @author Nate Axt <nate6>
 *         Julie Tran <juliet12>
 * @version 12.7.2017
 */
public interface HashTableInterface {

    /**
     * Inserts string into memory by a hashing.
     * @param record string
     * @return the handle or -1 if failed insertion
     */
    public int insert(String record);
    
    /**
     * Deletes record from memory.
     * @param record string
     * @return if deleted
     */
    public boolean delete(String record);

    /**
     * Searches for the handle of the given record.
     * @param record string
     * @return the handle or -1 if failed to find
     */
    public int find(String record);

    /**
     * Gets the string record for the given hashcode.
     * @param hashCode for string
     * @return the string that goes with this hashcode
     */
    public String get(int hashCode);

    /**
     * Gets the string record for the given hashcode.
     * @param hashCode for string
     * @return the string that goes with this hashcode
     */
    public int getMemPos(int hashCode);
}
