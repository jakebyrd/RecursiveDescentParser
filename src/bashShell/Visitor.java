package bashShell;
import bashShell.ast.*;

//Visitor methods
public interface Visitor {
    public Object visitAssignCmd(AssignCmd assignCmd, Object object);
    public Object visitExecCmd(ExecCmd execCmd, Object object);
    public Object visitFNameArg(FNameArg fNameArg, Object object);
    public Object visitForCommand(ForCommand forCommand, Object object);
    public Object visitIfCmd(IfCmd ifCmd, Object object);
    public Object visitLiteralArg(LiteralArg literalArg, Object object);
    public Object visitNullArg(NullArg nullArg, Object object);
    public Object visitNullCmd(NullCmd nullCmd, Object object);
    public Object visitScript(Script script, Object object);
    public Object visitSeqCmd(SeqCmd seqCmd, Object object);
    public Object visitTerminal(Terminal terminal, Object object);
    public Object visitVarArg(VarArg varArg, Object object);
}