package bashShell.ast;
import bashShell.VisitCommands;
import bashShell.VisitPrintCommands;
import bashShell.VarType;

public class LiteralArg extends SingleArg {
    private Terminal literal;

    public LiteralArg(Terminal literal){
        this.literal = literal;
    }

    public Terminal getTerm() {
        return literal;
    }

    public String accept(VisitPrintCommands visitor, int i) {
        return visitor.visitLiteralArg(this, i);
    }
    public Object accept(VisitCommands visitor, Object object){
        return visitor.visitLiteralArg(this, object);
    }
}