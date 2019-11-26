package bashShell.ast;

public class ExecCmd extends Command {
    private FNameArg command;
    private Argument args;

    public ExecCmd(FNameArg command, Argument args){
        this.command = command;
        this.args = args;
    }
    //visit method
    public String visit(int i){
        i++;
        treeAst = treeAst + command.visit(i);
        treeAst = treeAst + args.visit(i);
        return(Indent(i) + "ExecCmd\n" + treeAst);
    }

}