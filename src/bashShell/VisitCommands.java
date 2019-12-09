package bashShell;
import bashShell.ast.*;
import java.util.*;

public class VisitCommands implements Visitor {

    private IdentificationTable idTable;

    public DecorateVisitAst(){
        this.idTable = new IdentificationTable();
    }

    public Object visitAssignCmd(AssignCmd assignCmd, Object object) {
        Type rValueType = (Type) assignCmd.getrValue().accept(this, null);
        assignCmd.getlValue().type = rValueType;
        assignCmd.type = rValueType;
        Attribute attribute = new Attribute(assignCmd.type);
        idTable.enter(assignCmd.getlValue().getTerm().getSpelling(), attribute);
        return null;
    }

    public Object visitExecCmd(ExecCmd execCmd, Object object) {
        Type commandType = (Type) execCmd.getCommand().accept(this, object);
        if (!commandType.equals(Type.executable)){
            System.err.println("Type is not executable");
        }
        execCmd.getArgs().accept(this, object);
        return null;
    }

    public Object visitForCommand(ForCommand forCommand, Object object) {
        forCommand.getVar().accept(this, object);
        idTable.openScope();
        Attribute attribute = new Attribute(forCommand.getVar().type);
        idTable.enterScoped(forCommand.getVar().getTerm().getSpelling(), attribute);
        forCommand.getArg().accept(this, object);
        forCommand.getDoBody().accept(this, object);
        idTable.closeScope();
        return null;
    }

    public Object visitIfCmd(IfCmd ifCmd, Object object) {
        ifCmd.getCommand().accept(this, object);
        ifCmd.getArgs().accept(this, object);
        ifCmd.getIfBlock().accept(this, object);
        ifCmd.getElseBlock().accept(this, object);
        return null;
    }

    public Object visitTerminal(Terminal terminal, Object object) {

    }

    public Object visitScript(Script script, Object object) {
        script.getC().accept(this, object);
        return null;
    }

    public Object visitSeqCmd(SeqCmd seqCmd, Object object) {
        seqCmd.getC1().accept(this, object);
        seqCmd.getC2().accept(this, object);
        return null;
    }

    public Object visitSeqArg(SeqArg arg, Object object){
        arg.getArg1().accept(this, object);
        arg.getArg2().accept(this, object);
        return null;
    }

    public Object visitFNameArg(FNameArg fNameArg, Object object) {
        Type vType = (Type) fNameArg.getTerm().accept(this, object);
        fNameArg.type = vType;
        return fNameArg.type;
    }

    public Object visitLiteralArg(LiteralArg literalArg, Object object) {
        Type argType = (Type) literalArg.getTerm().accept(this, object);
        literalArg.type = argType;
        return argType;
    }

    public Object visitVarArg(VarArg varArg, Object object) {
        Type vType = (Type) varArg.getTerm().accept(this, object);
        varArg.type = vType;
        return varArg.type;
    }

    public Object visitNullArg(NullArg nullArg, Object object) {
        return Type.nulltype;
    }

    public Object visitNullCmd(NullCmd nullCmd, Object object) {
        return null;
    }
}