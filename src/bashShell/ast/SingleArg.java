package bashShell.ast;
import bashShell.VisitCommands;
import bashShell.VisitPrintCommands;
import bashShell.VarType;

/**
 * Note:  As per the grammar, SingleArg is a subset of
 * Argument.  Instead of creating additional subtypes, this
 * restriction will be enforced by SingleArgs constructor.
 */
public abstract class SingleArg extends Argument {
    public Type type;
    abstract public Terminal getTerm();
}