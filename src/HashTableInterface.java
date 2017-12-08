
public interface HashTableInterface {

    public int insert(String record);
    
    public boolean delete(String record);
    
    public int find(String record);
    
    public String get(int hashCode);
}
