package bashShell.ast;
import bashShell.VisitCommands;
import bashShell.VisitPrintCommands;

public abstract class AST {
    public abstract String accept(VisitPrintCommands visitor, int i);
    public abstract Object accept(VisitCommands visitor, Object object);
}