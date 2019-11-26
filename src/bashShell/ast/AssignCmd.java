package bashShell.ast;

public class AssignCmd extends Command {
    private VarArg lValue;
    private SingleArg rValue;

    public AssignCmd(VarArg lValue, SingleArg rValue){
        this.lValue = lValue;
        this.rValue = rValue;
    }
    //visit method
    public String visit(int i){
        i++;
        treeAst = treeAst + lValue.visit(i);
        treeAst = treeAst + rValue.visit(i);
        return(Indent(i) + "AssignCmd\n" + treeAst);
    }
}