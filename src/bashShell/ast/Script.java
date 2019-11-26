package bashShell.ast;

public class Script extends AST {
    public Command c;

    public Script(Command c){
        this.c = c;
    }

    public String visit(int i){
        treeAst += Indent(i) + "Script\n";
        i++;
        treeAst = treeAst + c.visit(i);
        return(treeAst);
    }
}