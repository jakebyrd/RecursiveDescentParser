package bashShell;

public class Attribute {

    private Type type;
    private int level;
    //Sets attribute.
    public Attribute(Type type) {
        this.type = type;
    }
    //Gets type. Either string, numeric, exec, or null.
    public Type getType() {
        return this.type;
    }
    //Sets level for id table.
    public void setLevel(int level) {
        this.level = level;
    }
    //Gets level of current scope.
    public int getLevel() {
        return this.level;
    }
}