package bashShell.ast;

public class SeqArg extends Argument {
    private Argument arg1;
    private Argument arg2;

    public SeqArg(Argument arg1, Argument arg2) {
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    public String visit(int i){
        i++;
        treeAst = treeAst + arg1.visit(i);
        treeAst = treeAst + arg2.visit(i);
        return(Indent(i) + "SeqArg\n" + treeAst);
    }
}
