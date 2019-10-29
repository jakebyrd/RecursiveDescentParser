package bashShell.ast;

public class NullCmd extends Command {
    private Command cmd = null;

    public NullCmd(){
        this.cmd = null;
    }

    public String visit(int i){
        return(Visitor.node(i) + "NullCommand\n");
    }
}