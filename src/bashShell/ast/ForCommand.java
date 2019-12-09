package bashShell.ast;
import bashShell.VisitCommands;
import bashShell.VisitPrintCommands;

public class ForCommand extends Command {
    private VarArg var;
    private Argument arg;
    private Command doBody;

    public ForCommand(VarArg var, Argument arg, Command doBody){
        this.var = var;
        this.arg = arg;
        this.doBody = doBody;
    }

    public Argument getArg() {
        return arg;
    }

    public VarArg getVar() {
        return var;
    }

    public Command getDoBody() {
        return doBody;
    }

    public String accept(VisitPrintCommands visitor, int i) {
        return visitor.visitForCommand(this, i);
    }
    public Object accept(VisitCommands visitor, Object object){
        return visitor.visitForCommand(this, object);
    }
}