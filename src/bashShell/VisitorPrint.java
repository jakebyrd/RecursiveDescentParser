package bashShell;
import bashShell.ast.*;

//Visit methods that are strings for printing to file or console.
public interface PrintVisitor {
    public String visitAssignCmd(AssignCmd assignCmd, int i);
    public String visitExecCmd(ExecCmd execCmd, int i);
    public String visitFNameArg(FNameArg fNameArg, int i);
    public String visitForCommand(ForCommand forCommand, int i);
    public String visitIfCmd(IfCmd ifCmd, int i);
    public String visitLiteralArg(LiteralArg literalArg, int i);
    public String visitNullArg(NullArg nullArg, int i);
    public String visitNullCmd(NullCmd nullCmd, int i);
    public String visitScript(Script script, int i);
    public String visitSeqCmd(SeqCmd seqCmd, int i);
    public String visitTerminal(Terminal terminal, int i);
    public String visitVarArg(VarArg varArg, int i);
}