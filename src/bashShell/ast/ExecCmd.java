package bashShell.ast;
import bashShell.VisitCommands;
import bashShell.VisitPrintCommands;

public class ExecCmd extends Command {
    private FNameArg command;
    private Argument args;

    public ExecCmd(FNameArg command, Argument args){
        this.command = command;
        this.args = args;
    }

    public FNameArg getCommand() {
        return command;
    }

    public Argument getArgs() {
        return args;
    }

    public String accept(VisitPrintCommands visitor, int i){
        return visitor.visitExecCmd(this, i);
    }

    public Object accept(VisitCommands visitor, Object object){
        return visitor.visitExecCmd(this, object);
    }

}