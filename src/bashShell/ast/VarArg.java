package bashShell.ast;

public class VarArg extends SingleArg {
    private Terminal variable;

    public VarArg(Terminal variable){
        this.variable = variable;
    }

    public String visit(int i){
        return(Visitor.node(i) + "VarArg" + this.variable.visit(i+1));
    }
}