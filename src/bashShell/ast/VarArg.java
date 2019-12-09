package bashShell.ast;
import bashShell.VisitCommands;
import bashShell.VisitPrintCommands;
import bashShell.VarType;

public class VarArg extends SingleArg {
    private Terminal variable;

    public VarArg(Terminal variable){
        this.variable = variable;
    }

    public Terminal getTerm() {
        return variable;
    }

    public String accept(VisitPrintCommands visitor, int i) {
        return visitor.visitVarArg(this, i);
    }
    public Object accept(VisitCommands visitor, Object object){
        return visitor.visitVarArg(this, object);
    }
}