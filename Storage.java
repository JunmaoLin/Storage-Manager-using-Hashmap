/**
 * This class represents a storage that contains three private data fields: int id, String client, String contents
*/

import java.io.Serializable;
public class Storage implements Serializable {
    private static long serialVersionUID;
    private int id;
    private String client;
    private String contents;

    /**
     * This is the constructor of the class.
     * @param id
     * int
     * @param client
     * String
     * @param contents
     * String
     */
    public Storage(int id, String client, String contents){
        this.id = id;
        this.client = client;
        this.contents = contents;
    }

    /**
     * This is the getter method for id
     * @return
     *  an int
     */
    public int getId() {
        return this.id;
    }

    /**
     * This is the getter method for client
     * @return
     * a string
     */
    public String getClient() {
        return this.client;
    }

    /**
     * This is the getter method for content
     * @return
     * a string
     */
    public String getContents() { 
        return this.contents;
    }

    /**
     * This is the getter method for SerialVersionUID
     * @return
     */
    public long getSerialVersionUID() {
        return this.serialVersionUID;
    }

    /**
     * this is the setter method for id.
     * @param id
     * an int
     */
    public void setID(int id){
        this.id = id;
    }

    /**
     * This is the setter method for client.
     * @param client
     * a String
     */
    public void setClient(String client){
        this.client = client;
    }

    /**
     * This is the setter method for contents
     * @param contents
     * a String
     */
    public void setContent(String contents){
        this.contents = contents;
    }
}