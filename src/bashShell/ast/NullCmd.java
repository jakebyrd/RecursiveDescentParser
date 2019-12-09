package bashShell.ast;
import bashShell.VisitCommands;
import bashShell.VisitPrintCommands;

public class NullCmd extends Command {
    private Command cmd = null;

    public NullCmd(){
        this.cmd = null;
    }

    public Command getCmd() {
        return cmd;
    }

    public String accept(VisitPrintCommands visitor, int i) {
        return visitor.visitNullCmd(this, i);
    }
    public Object accept(VisitCommands visitor, Object object){
        return visitor.visitNullCmd(this, object);
    }
}