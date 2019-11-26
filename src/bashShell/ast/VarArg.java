package bashShell.ast;

public class VarArg extends SingleArg {
    private Terminal variable;

    public VarArg(Terminal variable){
        this.variable = variable;
    }

    public String visit(int i){
        i++;
        treeAst = treeAst + variable.visit(i);
        return(Indent(i) + "VarArg " + treeAst);
    }
}