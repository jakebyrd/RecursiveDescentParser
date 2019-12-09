package bashShell.ast;
import bashShell.VisitCommands;
import bashShell.VisitPrintCommands;

public class ExecCmd extends Command {
    private FNameArg command;
    private Argument arg;

    public ExecCmd(FNameArg command, Argument arg){
        this.command = command;
        this.arg = arg;
    }

    public FNameArg getCommand() {
        return command;
    }

    public Argument getArg() {
        return arg;
    }

    public String accept(VisitPrintCommands visitor, int i){
        return visitor.visitExecCmd(this, i);
    }

    public Object accept(VisitCommands visitor, Object object){
        return visitor.visitExecCmd(this, object);
    }

}