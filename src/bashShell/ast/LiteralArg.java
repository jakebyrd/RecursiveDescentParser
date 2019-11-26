package bashShell.ast;

public class LiteralArg extends SingleArg {
    private Terminal literal;

    public LiteralArg(Terminal literal){
        this.literal = literal;
    }

    public String visit(int i){
        i++;
        treeAst = treeAst + literal.visit(i);
        return(Indent(i) + "LiteralArg " + treeAst);
    }
}