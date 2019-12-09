package bashShell.ast;
import bashShell.VisitCommands;
import bashShell.VisitPrintCommands;

public class ForCommand extends Command {
    private VarArg var;
    private Argument args;
    private Command doBody;

    public ForCommand(VarArg var, Argument args, Command doBody){
        this.var = var;
        this.args = args;
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