package bashShell.ast;

public class Terminal extends AST {
    public String spelling;

    public Terminal(String spelling){
        this.spelling = spelling;
    }

    public String visit(int i){
        treeAst = treeAst + spelling;
        return(spelling + "\n");
    }
}