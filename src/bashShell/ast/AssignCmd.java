package bashShell.ast;

public class AssignCmd extends Command {
    private VarArg lValue;
    private SingleArg rValue;

    public AssignCmd(VarArg lValue, SingleArg rValue){
        this.lValue = lValue;
        this.rValue = rValue;
    }
    public String visit(int i){
        return(Visitor.node(i) + "AssignCommand\n" + this.lValue.visit(i+1) + this.rValue.visit(i+1));
    }
}