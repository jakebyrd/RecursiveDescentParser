package bashShell.ast;

public class SeqCmd extends Command {
    Command c1;
    Command c2;

    public SeqCmd(Command c1, Command c2){
        this.c1 = c1;
        this.c2 = c2;
    }

    public String visit(int i){
        i++;
        treeAst = treeAst + c1.visit(i);
        treeAst = treeAst + c2.visit(i);
        return(Indent(i) + "SeqCmd\n" + treeAst);
    }
}