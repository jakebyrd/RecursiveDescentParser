package bashShell.ast;
import bashShell.VisitCommands;
import bashShell.VisitPrintCommands;
import bashShell.VarType;

public class AssignCmd extends Command {
    private VarArg lValue;
    private SingleArg rValue;
    public Type type;

    public AssignCmd(VarArg lValue, SingleArg rValue){
        this.lValue = lValue;
        this.rValue = rValue;
    }

    public VarArg getlValue() {
        return lValue;
    }

    public SingleArg getrValue() {
        return rValue;
    }

    public String accept(VisitPrintCommands visitor, int i) {
        return visitor.visitAssignCmd(this, i);
    }

    public Object accept(VisitPrintCommands visitor, Object object){
        return visitor.visitAssignCmd(this, object);
    }
}