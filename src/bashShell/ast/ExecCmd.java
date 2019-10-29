package bashShell.ast;

public class ExecCmd extends Command {
    private FNameArg command;
    private Argument args;

    public ExecCmd(FNameArg command, Argument args){
        this.command = command;
        this.args = args;
    }

    public String visit(int i){
        return(Visitor.node(i) + "ExecCommand\n" + this.command.visit(i+1) + this.args.visit(i+1));
    }

}