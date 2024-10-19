import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 * This class will allow the user to interact with the storage database by listing the storage boxes occupied, 
 * allowing the user to add or remove storage boxes, searching for a box by id, and listing all the boxes for a user.
*/

public class StorageManager {
    private static StorageTable storageTable;
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws IOException{
        try {
            FileInputStream file = new FileInputStream("storage.obj");
            ObjectInputStream inStream = new ObjectInputStream(file);
            StorageTable storage = (StorageTable) inStream.readObject();
            inStream.close();
            storageTable = storage;
        } catch (Exception e) {
            storageTable = new StorageTable();
            storageTable.putStorage(1, new Storage(1, "2006 Dell Workstations", "SBU CS Department"));
            storageTable.putStorage(4, new Storage(4, "Unreturned Exams", "214 TAs"));
            storageTable.putStorage(11, new Storage(11, "Dashed Hopes and Dreams", "SBU CS Department"));
            storageTable.putStorage(12, new Storage(12, "Spare Null Pointer Exceptions", "214 TAs"));
        }
        System.out.println("Hello, and welcome to Rocky Stream Storage Manager");
        printMenu();
        System.out.print("Please select an option: ");
        String userInput = scan.nextLine().trim().toUpperCase();
        while(!userInput.equals("Q") && !userInput.equals("X")){
            switch (userInput){
                case "P":
                    System.out.println();
                    System.out.println(storageTable);
                    break;
                case "A":
                    System.out.print("Please enter id: ");
                    int id = scan.nextInt();
                    scan.nextLine();
                    System.out.print("Please enter client: ");
                    String client = scan.nextLine();
                    System.out.print("Please Enter Contents: ");
                    String contents = scan.nextLine();
                    Storage storage = new Storage(id, client, contents);
                    try{
                        storageTable.putStorage(id, storage);
                    }catch(IllegalArgumentException e){
                        System.out.println("Error: ID cannot be less than 0.");
                        break;
                    }
                    System.out.println("Storage " + id + " set!");
                    break;
                case "R":
                    System.out.print("zPlease enter ID: ");
                    int userId = scan.nextInt();
                    scan.nextLine();
                    storageTable.remove(userId);
                    System.out.println("Box " + userId + " is now removed.");
                    break;
                case "C":
                    System.out.print("Please enter the name of the client: ");
                    String clientName = scan.nextLine();
                    storageTable.printByClients(clientName);
                    break;
                case "F":
                    System.out.print("Please enter ID: ");
                    int iD = scan.nextInt();
                    Storage currentStorage = storageTable.getStorage(iD);
                    System.out.println("Box " + currentStorage.getId());
                    System.out.println("Contents: " + currentStorage.getContents());
                    System.out.println("Owner: " + currentStorage.getClient());
                    break;
                default:
                    System.out.println("Invalid input. Try again.");
                    break;
            }
            printMenu();
            System.out.print("Please select an option: ");
            userInput = scan.nextLine().trim().toUpperCase();
        }
        if(userInput.equals("Q")){
            FileOutputStream file = new FileOutputStream("storage.obj");
            ObjectOutputStream outStream = new ObjectOutputStream(file);
            outStream.writeObject(storageTable);
            outStream.close();
            System.out.println("Storage Manager is quitting, current storage is saved for next session.");
        }
        else if(userInput.equals("X")){
            storageTable = new StorageTable();
            FileOutputStream file = new FileOutputStream("storage.obj");
            ObjectOutputStream outStream = new ObjectOutputStream(file);
            outStream.writeObject(storageTable);
            outStream.close();
            System.out.println("Storage Manager is quitting, all data is being erased.");
        }
    }

    public static void printMenu(){
        System.out.println("");
        System.out.println("P - Print all storage boxes");
        System.out.println("A - Insert into storage box");
        System.out.println("R - Remove contents from a storage box");
        System.out.println("C - Select all boxes owned by a particular client");
        System.out.println("F - Find a box by ID and display its owner and contents");
        System.out.println("Q - Quit and save workspace");
        System.out.println("X - Quit and delete workspace");
        System.out.println("");
    }
}
