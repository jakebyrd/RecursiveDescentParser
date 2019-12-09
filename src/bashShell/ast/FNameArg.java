package bashShell.ast;
import bashShell.VisitCommands;
import bashShell.VisitPrintCommands;
import bashShell.VarType;

public class FNameArg extends SingleArg  {
    private Terminal term;

    public FNameArg(Terminal term){
        this.term = term;
    }

    public Terminal getTerm() {
        return term;
    }

    public String accept(VisitPrintCommands visitor, int i) {
        return visitor.visitFNameArg(this, i);
    }
    public Object accept(VisitCommands visitor, Object object){
        return visitor.visitFNameArg(this, object);
    }

}