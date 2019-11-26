package bashShell.ast;

public class NullCmd extends Command {
    private Command cmd = null;

    public NullCmd(){
        this.cmd = null;
    }

    public String visit(int i) {
        treeAst = treeAst + (Indent(i) + "nullCmd");
        return(Indent(i) + "nullCmd");
    }
}