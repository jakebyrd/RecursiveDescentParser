package bashShell.ast;
import bashShell.VisitCommands;
import bashShell.VisitPrintCommands;

public class Script extends AST {
    public Command c;

    public Script(Command c){
        this.c = c;
    }

    public Command getC() {
        return c;
    }

    public String accept(VisitPrintCommands visitor, int i) {
        return visitor.visitScript(this, i);
    }
    public Object accept(VisitCommands visitor, Object object){
        return visitor.visitScript(this, object);
    }
}