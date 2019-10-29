package bashShell.ast;

public class IfCmd extends Command{
    private FNameArg command;
    private Argument args;
    private Command thenBlock;
    private Command elseBlock;

    public IfCmd(FNameArg command, Argument args, Command thenBlock, Command elseBlock){
        this.command = command;
        this.args = args;
        this.thenBlock = thenBlock;
        this.elseBlock = elseBlock;
    }

    public String visit(int i){
        return(Visitor.node(i) + "IfCommand\n" + this.command.visit(i+1) + this.args.visit(i+1) + this.thenBlock.visit(i+1) + this.elseBlock.visit(i+1));
    }
}