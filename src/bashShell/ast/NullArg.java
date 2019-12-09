package bashShell.ast;
import bashShell.VisitCommands;
import bashShell.VisitPrintCommands;
import bashShell.VarType;

public class NullArg extends Argument {
    private Argument arg = null;

    public NullArg(){
        this.arg = null;
    }

    public Argument getArg() {
        return arg;
    }

    public String accept(VisitPrintCommands visitor, int i) {
        return visitor.visitNullArg(this, i);
    }
    public Object accept(VisitCommands visitor, Object object){
        return visitor.visitNullArg(this, object);
    }
}