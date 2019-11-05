package bashShell.ast;

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

    public String visit(int i){
        return(Visitor.node(i) + "IfCommand\n" + this.command.visit(i+1) + this.args.visit(i+1) + this.ifBlock.visit(i+1) + this.elseBlock.visit(i+1));
    }
}