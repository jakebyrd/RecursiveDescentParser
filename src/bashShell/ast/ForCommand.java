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

    public String visit(int i) {
        return(Visitor.node(i) + "ForCommand\n" + this.var.visit(i+1) + this.args.visit(i+1) + this.doBody.visit(i+1));
    }
}