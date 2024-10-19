/**
 * This class represents a database of Storages that will be stored in a hash table.
*/

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class StorageTable implements Serializable{
    private static int serialVersionUID;
    private HashMap<Integer, Storage> hashMap = new HashMap<>();

    /**
     * Manually inserts a Storage object into the table using the specified key.
     * Preconditions: 
     * storageId ≥ 0 and does not already exist in the table.
     * Storage ≠ null
     * @param storageID
     * The unique key for the Storage object.
     * @param storage
     * The Storage object to insert into the table.
     * @throws IllegalArgumentException
     * If any of the preconditions is not met.
     */
    public void putStorage(int storageID, Storage storage) throws IllegalArgumentException{
        if(storageID < 0 || storage == null){
            throw new IllegalArgumentException("storageID must be greater than 0 and storage cannot be null.");
        }
        hashMap.put(storageID, storage);
    }

    /**
     * Retrieve the Storage from the table having the indicated storageID. 
     * If the requested storageID does not exist in the StorageTable, return null.
     * @param storageID
     * Key of the Storage to retrieve from the table.
     * @return
     * A Storage object with the given key, null otherwise.
     */
    public Storage getStorage(int storageID){
        return hashMap.getOrDefault(storageID, null);
    }

    /**
     * This method removes the given key from the hash map.
     * @param storageID
     *  int
     */
    public void remove(int storageID){
        hashMap.remove(storageID);
    }

    public void printByClients(String client){
        System.out.println("Box#          Contents                       Owner");
        System.out.println("----------------------------------------------------------------");
        ArrayList<Integer> keys = new ArrayList<Integer>();
        for (Map.Entry<Integer, Storage> entry : hashMap.entrySet()) {
            Integer key = entry.getKey();
            keys.add(key);
        }
        Collections.sort(keys);  
        if(keys.size()>0){
            for(int i = 0; i < keys.size(); i++){
                Integer key = keys.get(i);
                Storage storage = hashMap.get(key);
                if(storage.getClient().equalsIgnoreCase(client)){
                    System.out.println(String.format("%13s%32s%19s", storage.getId(), storage.getContents(), storage.getClient()));
                }
            }
        }
    }

    /**
     * Prints a formatted table of the hashMap
     */
    public String toString(){
        String output = "";
        output += "Box#          Contents                       Owner\n";
        output += "----------------------------------------------------------------\n";
        ArrayList<Integer> keys = new ArrayList<Integer>();
        for (Map.Entry<Integer, Storage> entry : hashMap.entrySet()) {
            Integer key = entry.getKey();
            keys.add(key);
        }
        Collections.sort(keys);  
        if(keys.size()>0){
            for(int i = 0; i < keys.size(); i++){
                Integer key = keys.get(i);
                Storage storage = hashMap.get(key);
                output += String.format("%-13s%-32s%-19s", storage.getId(), storage.getContents(), storage.getClient()) + "\n";
            }
        }
        return output;

    }
}