package bashShell.ast;

public class Script extends AST {
    public Command c;

    public Script(Command c){
        this.c = c;
    }

    public String visit(int i){
        return(Visitor.node(i) + "Script\n" + this.c.visit(i+1));
    }
}