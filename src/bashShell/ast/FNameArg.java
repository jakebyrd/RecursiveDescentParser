package bashShell.ast;

public class FNameArg extends SingleArg  {
    private Terminal term;

    public FNameArg(Terminal term){
        this.term = term;
    }

    public String visit(int i){
        return(Visitor.node(i) + "FNameArg\n" + this.term.visit(i+1));
    }

}