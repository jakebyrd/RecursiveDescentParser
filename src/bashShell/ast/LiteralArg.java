package bashShell.ast;

public class LiteralArg extends SingleArg {
    private Terminal literal;

    public LiteralArg(Terminal literal){
        this.literal = literal;
    }

    public String visit(int i){
        return(Visitor.node(i) + "LiteralArg" + this.literal.visit(i+1));
    }
}