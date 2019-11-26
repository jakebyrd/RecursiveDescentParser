package bashShell.ast;

public class FNameArg extends SingleArg  {
    private Terminal term;

    public FNameArg(Terminal term){
        this.term = term;
    }

    public String visit(int i){
        i++;
        treeAst += term.visit(i);
        return(Indent(i) + "FNameArg " + treeAst);
    }

}