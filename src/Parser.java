import java.util.Scanner;

public class Parser {
    private Scanner sc;

    public Parser(String file) {
        sc = new Scanner(file);
    }
    
    public void parse(Database db) {
        while (sc.hasNext()) {
            String line = sc.next();
            String[] words = line.split(" ");
            if (words[0].equals("insert")) {
                insert(db, words);
            }
            else if (words[0].equals("remove")) {
                remove(db, words);
            }
            else if (words[0].equals("print")) {
                print(db, words);
            }
            else if (words[0].equals("list")) {
                list(db, words);
            }
            else if (words[0].equals("delete")) {
                delete(db, words);
            }
        }
    }

    private void insert(Database db, String[] words) {
        String[] names = words[1].split("<SEP>");
        int[] handles = db.insert(names[0], names[1]);
        
        if (handles[0] > 0) {
            System.out.println("|" + names[1] + "| is added to the " +
                    "Artist database");
        }
        else {
            System.out.println("|" + names[0] + "| duplicates a record " + 
                    "already in the Artist database.");
        }
        
        if (handles[1] > 0) {
            System.out.println("|" + names[1] + "| is added to the " +
                    "Song database");
        }
        else { 
            System.out.println("|" + names[0] + "| duplicates a record " + 
                    "already in the Song database.");
        }
        
        if (handles[0] < 0 && handles[1] < 0) {
            System.out.println("The KVPair (|" + names[0] + "|,|" + names[1] + 
                    "|),(" + handles[0] + "," + handles[1] + ") " + 
                    "duplicates a record already in the tree.");
            
            System.out.println("The KVPair (|" + names[1] + "|,|" + names[0] + 
                    "|),(" + handles[1] + "," + handles[0] + ") " + 
                    "duplicates a record already in the tree.");
        }
        else {
            System.out.println("The KVPair (|" + names[0] + "|,|" + names[1] + 
                    "|),(" + handles[0] + "," + handles[1] + ") " + 
                    "is added to the tree.");
            
            System.out.println("The KVPair (|" + names[1] + "|,|" + names[0] + 
                    "|),(" + handles[1] + "," + handles[0] + ") " + 
                    "is added to the tree.");
        }
        
        //TODO print if expanded?
    }

    private void remove(Database db, String[] words) {
        String[] removed = null;
        Type type = null;
        
        if (words[1].equals("artist")) {
            removed = db.remove(Type.ARTIST, words[2]);
            type = Type.ARTIST;
        }
        else if (words[1].equals("song")) {
            removed = db.remove(Type.SONG, words[2]);
            type = Type.SONG;
        }
        
        if (removed == null) {
            if (type == Type.ARTIST) {
                System.out.println("|" + words[2] + "| does not exist in " + 
                        "the artist database");
            }
            else if (type == Type.SONG) {
                System.out.println("|" + words[2] + "| does not exist in " + 
                        "the song database");
            }
        }
        else {
            for (int i = 0; i < removed.length; i++) {
                if (type == Type.ARTIST) {
                    System.out.println("The KVPair (|" + words[2] + "|,|" + 
                            removed[i] + "|) is deleted from the tree.");
                    System.out.println("The KVPair (|" + removed[i] + "|,|" + 
                            words[2] + "|) is deleted from the tree.");
                }
                else if (type == Type.SONG) {
                    System.out.println("The KVPair (|" + removed[i] + "|,|" + 
                            words[2] + "|) is deleted from the tree.");
                    System.out.println("The KVPair (|" + words[2] + "|,|" + 
                            removed[i] + "|) is deleted from the tree.");
                }
            }
            
            if (type == Type.ARTIST) {
                System.out.println("|" + words[2] + "| is deleted from the " + 
                        "Artist database.");
                for (int i = 0; i < removed.length; i++) {
                    if (!db.exists(Type.SONG, removed[i])) {
                        System.out.println("|" + removed[i] + "| is " + 
                                "deleted from the Song database.");
                    }
                }
            }
            else if (type == Type.SONG) {
                for (int i = 0; i < removed.length; i++) {
                    if (!db.exists(Type.ARTIST, removed[i])) {
                        System.out.println("|" + removed[i] + "| is " + 
                                "deleted from the Artist database.");
                    }
                }
                System.out.println("|" + words[2] + "| is deleted from the " + 
                        "Song database.");
            }
        }
        
    }

    private void print(Database db, String[] words) {
        if (words[1].equals("artist")) {
            String[] artists = db.print(Type.ARTIST);
            if (artists != null) { 
                for (int i = 0; i < artists.length; i++) {
                    System.out.println("|" + artists[i] + "|");
                }
                System.out.println("total artists: " + artists.length);
            }
            else {
                System.out.println("total artists: " + 0);
            }
        }
        else if (words[1].equals("song")) {
            String[] songs = db.print(Type.SONG);
            if (songs != null) { 
                for (int i = 0; i < songs.length; i++) {
                    System.out.println("|" + songs[i] + "|");
                }
                System.out.println("total songs: " + songs.length);
            }
            else {
                System.out.println("total songs: " + 0);
            }
        }
        else if (words[1].equals("tree")) {
            //TODO use BST iterator
        }
        
        //TODO Printing tree or Printing BST?
    }

    private void list(Database db, String[] words) {
        String[] listings = null;
        if (words[1].equals("artist")) {
            listings = db.list(Type.ARTIST, words[2]);
        }
        else if (words[1].equals("song")) {
            listings = db.list(Type.SONG, words[2]);
        }
        
        for (int i = 0; i < listings.length; i++) {
            System.out.println("|" + listings[i] + "|");
        }
    }

    private void delete(Database db, String[] words) {
        String[] names = words[1].split("<SEP>");
        int[] deleted = db.delete(names[0], names[1]);
        
        if (deleted[0] > 0 && deleted[1] > 0) {
            System.out.println("The KVPair (|" + names[0] + "|,|" + names[1] + 
                    "|) is deleted from the tree.");
            System.out.println("The KVPair (|" + names[1] + "|,|" + names[0] + 
                    "|) is deleted from the tree.");
            
            if (deleted[0] == 1) {
                System.out.println("|" + deleted[0] + "| is deleted from " + 
                        "the artist database.");
            }
            if (deleted[1] == 1) {
                System.out.println("|" + deleted[1] + "| is deleted from " + 
                        "the song database.");
            }
        }
        else if (deleted[0] == -1 && deleted[1] == -1) {
            System.out.println("The KVPair (|" + names[0] + "|,|" + names[1] + 
                    "|) was not found in the database.");
            System.out.println("The KVPair (|" + names[1] + "|,|" + names[0] + 
                    "|) was not found in the database.");
        }
        else if (deleted[0] == -1) {
            System.out.println("|" + deleted[0] + "| does not exist in " + 
                    "the artist database.");
        }
        else if (deleted[1] == -1) {
            System.out.println("|" + deleted[1] + "| does not exist in " + 
                    "the song database.");
        }
    }
    
}
