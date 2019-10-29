package bashShell.ast;

public class SeqCmd extends Command {
    Command c1;
    Command c2;

    public SeqCmd(Command c1, Command c2){
        this.c1 = c1;
        this.c2 = c2;
    }

    public String visit(int i){
        return(Visitor.node(i) + "SeqCommand\n" + this.c1.visit(i+1) + this.c2.visit(i+1));
    }
}