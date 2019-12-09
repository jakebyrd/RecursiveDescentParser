package bashShell.ast;
import bashShell.VisitCommands;
import bashShell.VisitPrintCommands;

public class IfCmd extends Command{
    private FNameArg command;
    private Argument args;
    private Command ifBlock;
    private Command elseBlock;

    public IfCmd(FNameArg command, Argument args, Command ifBlock, Command elseBlock){
        this.command = command;
        this.args = args;
        this.ifBlock = ifBlock;
        this.elseBlock = elseBlock;
    }

    public Command getElseBlock() {
        return elseBlock;
    }

    public Command getIfBlock() {
        return ifBlock;
    }

    public Argument getArgs() {
        return args;
    }

    public FNameArg getCommand() {
        return command;
    }

    public String accept(VisitPrintCommands visitor, int i) {
        return visitor.visitIfCmd(this, i);
    }
    public Object accept(VisitCommands visitor, Object object){
        return visitor.visitIfCmd(this, object);
    }
}