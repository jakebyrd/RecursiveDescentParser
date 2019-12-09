package bashShell.ast;
import bashShell.VisitCommands;
import bashShell.VisitPrintCommands;
import bashShell.VarType;

public class Terminal extends AST {
    public String spelling;

    public Terminal(String spelling){
        this.spelling = spelling;
    }

    public String getSpelling() {
        return spelling;
    }

    public String accept(VisitPrintCommands visitor, int i) {
        return visitor.visitTerminal(this, i);
    }
    public Object accept(VisitCommands visitor, Object object){
        return visitor.visitTerminal(this, object);
    }
}