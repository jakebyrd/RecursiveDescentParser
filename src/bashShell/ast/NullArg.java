package bashShell.ast;

public class NullArg extends Argument {
    private Argument arg = null;

    public NullArg(){
        this.arg = null;
    }

    public String visit(int i){
        return(Visitor.node(i) + "NullArg\n");
    }
}