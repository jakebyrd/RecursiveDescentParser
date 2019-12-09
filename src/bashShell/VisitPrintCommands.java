package bashShell;
import bashShell.ast.*;
import java.util.*;

public class VisitPrintCommands implements VisitorPrint{

    private static String print(int length){
        length = length*2;
        StringBuilder outputBuffer = new StringBuilder(length);
        for (int i = 0; i < length; i++){
            outputBuffer.append(" ");
        }
        return outputBuffer.toString();
    }

    public String visitAssignCmd(AssignCmd assignCmd, int i){
        return(print(i) + "AssignCmd\n" + visitVarArg(assignCmd.getlValue(), i+1) + visitArgument(assignCmd.getrValue(), i+1));
    }

    public String visitExecCmd(ExecCmd execCmd, int i){
        return(print(i) + "ExecCmd\n" + visitArgument(execCmd.getCommand(), i+1) + visitArgument(execCmd.getArgs(), i+1));
    }

    public String visitForCommand(ForCommand forCommand, int i){
        return(print(i) + "ForCommand\n" + visitVarArg(forCommand.getVar(), i+1) + visitArgument(forCommand.getArg(), i+1) + visitCommand(forCommand.getDoBody(), i+1));
    }

    public String visitIfCmd(IfCmd ifCmd, int i){
        return(s(i) + "IfCmd\n" + visitArgument(ifCmd.getCommand(), i+1) + visitArgument(ifCmd.getArgs(), i+1) + visitCommand(ifCmd.getIfBlock(), i+1) + visitCommand(ifCmd.getElseBlock(), i+1));
    }

    public String visitTerminal(Terminal terminal, int i){
        return(print(i) + "Terminal (" + terminal.getSpelling() + ")\n");
    }

    public String visitScript(Script script, int i){
        return(print(i) + "Script\n" + visitCommand(script.getC(), i+1));
    }

    public String visitSeqArg(SeqArg seqArg, int i){
        return(print(i) + "SeqArg\n" + visitArgument(seqArg.getArg1(), i+1) + visitArgument(seqArg.getArg2(), i+1));
    }

    public String visitSeqCmd(SeqCmd seqCmd, int i){
        return(print(i) + "SeqCmd\n" + visitCommand(seqCmd.getC1(), i+1) + visitCommand(seqCmd.getC2(), i+1));
    }

    public String visitFNameArg(FNameArg fNameArg, int i){
        return(print(i) + "FNameArg\n" + visitTerminal(fNameArg.getTerm(), i+1));
    }

    public String visitLiteralArg(LiteralArg literalArg, int i){
        return(print(i) + "LiteralArg\n" + visitTerminal(literalArg.getTerm(), i+1));
    }

    public String visitVarArg(VarArg varArg, int i){
        return(print(i) + "VarArg\n" + visitTerminal(varArg.getTerm(), i+1));
    }

    public String visitCommand(Command command, int i){
        return command.accept(this, i);
    }

    public String visitArgument(Argument argument, int i){
        return argument.accept(this, i);
    }

    public String visitNullArg(NullArg nullArg, int i){
        return(print(i) + "NullArg\n");
    }

    public String visitNullCmd(NullCmd nullCmd, int i){
        return(print(i) + "NullCmd\n");
    }

}