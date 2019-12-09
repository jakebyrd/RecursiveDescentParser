package bashShell.ast;
import bashShell.VisitCommands;
import bashShell.VisitPrintCommands;

public class SeqCmd extends Command {
    Command c1;
    Command c2;

    public SeqCmd(Command c1, Command c2){
        this.c1 = c1;
        this.c2 = c2;
    }

    public Command getC1() {
        return c1;
    }

    public Command getC2() {
        return c2;
    }

    public String accept(VisitPrintCommands visitor, int i) {
        return visitor.visitSeqCmd(this, i);
    }
    public Object accept(VisitCommands visitor, Object object){
        return visitor.visitSeqCmd(this, object);
    }
}