package bashShell;
import java.util.*;

public class IdentificationTable {
    private int level;
    private HashMap<String, Attribute> table;

    //create new id table
    public IdentificationTable(){
        table = new HashMap<>();
    }
    //Method for entering attribute with id into table
    public void enter(String id, Attribute attribute){
        attribute.setLevel(0);
        table.add(id, attribute);
    }
    //Retrieves attribute for corresponding id
    public Attribute retrieve(String id){
        return table.get(id);
    }

    //Opens scope at start of block
    public void openScope(){
        this.level++;
    }

    //Closes scope at end of block
    public void closeScope(){
        ArrayList<String> Remove = new ArrayList<>();
        for (String s : table.keySet()) {
            if(table.get(s).getLevel() == this.level){
                Remove.add(key);
            }
        }
        for (String key : keysToRemove){
            table.remove(key);
        }
        this.level --;
    }

    public void enterScoped(String id, Attribute attribute) {
        attribute.setLevel(this.level);
        table.put(id, attribute);
    }
}