package bashShell.ast;

public class ForCommand extends Command {
    private VarArg var;
    private Argument args;
    private Command doBody;

    public ForCommand(VarArg var, Argument args, Command doBody){
        this.var = var;
        this.args = args;
        this.doBody = doBody;
    }

    public String visit(int i){
        i++;
        treeAst = treeAst + var.visit(i);
        treeAst = treeAst + args.visit(i);
        treeAst = treeAst + doBody.visit(i);
        return(Indent(i) + "ForCmd\n" + treeAst);
    }
}