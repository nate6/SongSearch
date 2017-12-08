
public class Database {
    private HashTable artistTable;
    private HashTable songTable;
    private BST artistTree;
    private BST songTree;
    

    public Database(int hashSize, int blockSize, String file) {
        artistTable = new HashTable(hashSize, blockSize);
        songTable = new HashTable(hashSize, blockSize);
        artistTree = new BST();
        songTree = new BST();
        
        Parser parser = new Parser(file);
        parser.parse(this);
    }
    
    public int[] insert(String artist, String song) {
        int a = artistTable.insert(artist);
        int s = songTable.insert(song);
        if (a > 0 && s > 0) {
            KVPair apair = new KVPair(a, s);
            artistTree.insert(apair);
            KVPair spair = new KVPair(s, a);
            songTree.insert(spair);
        }
        int[] report = {a, s};
        return report;
    }
    
    public String[] remove(Type type, String name) {
        if (name.equals("null")) {
            return null;
        }
        String[] s = new String[3];
        if (type == Type.ARTIST) {
            s[0] = "rmsong1";
            s[1] = "rmsong2";
            s[2] = "rmsong3";
        }
        else {
            s[0] = "rmartist1";
            s[1] = "rmartist2";
            s[2] = "rmartist3";
        }
        return s;
    }
    
    public String[] print(Type type) {
        String[] s = new String[3];
        if (type == Type.ARTIST) {
            s[0] = "song1";
            s[1] = "song2";
            s[2] = "song3";
        }
        else {
            s[0] = "artist1";
            s[1] = "artist2";
            s[2] = "artist3";
        }
        return s;
    }
    
    public String[] list(Type type, String name) {
        if (name.equals("null")) {
            return null;
        }
        String[] s = new String[3];
        if (type == Type.ARTIST) {
            s[0] = "lssong1";
            s[1] = "lssong2";
            s[2] = "lssong3";
        }
        else {
            s[0] = "lsartist1";
            s[1] = "lsartist2";
            s[2] = "lsartist3";
        }
        return s;
    }
    
    public int[] delete(String artist, String song) {
        int[] i = new int[2];
        if (artist.equals("null") && song.equals("null")) {
            i[0] = -1;
            i[1] = -1;
        }
        else if (artist.equals("null")) {
            i[0] = -1;
            i[1] = 1;
        }
        else if (song.equals("null")) {
            i[0] = 1;
            i[1] = -1;
        }
        else {
            i[0] = 1;
            i[1] = 1;
        }
        return i;
    }
    
    public BST printTree() {
        
        return null;
    }

    public boolean exists(Type type, String string) {
        if (type == Type.ARTIST) {
            return artistTable.find(string) > 0;
        }
        else {
            return songTable.find(string) > 0;
        }
    }
}
