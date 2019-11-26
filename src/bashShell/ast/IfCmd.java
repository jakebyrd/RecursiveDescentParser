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
        i++;
        treeAst = treeAst + command.visit(i);
        treeAst = treeAst + args.visit(i);
        treeAst = treeAst + ifBlock.visit(i);
        treeAst = treeAst + elseBlock.visit(i);
        return(Indent(i) + "IfCmd\n" + treeAst);
    }
}