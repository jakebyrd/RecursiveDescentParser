package bashShell;


//@uthor Jake Byrd

import bashShell.ast.*;

public class Parser {
    private Token currentToken = null;
    private TheScanner TheScanner = null;
    private boolean errorHappened;
    private static Script sAST = null;
    private static Script dAST = null;

    //------------- Utility Methods -------------

    /**
     * Accept a specified token if it matches the
     * current Token.  Acceptance entails setting
     * currentToken to the next token in the input
     * stream.
     *
     * @param expectedKind The expected type of token.
     */
    private void accept(byte expectedKind) {
        if (currentToken.kind == expectedKind)
            currentToken = TheScanner.nextToken();
        else
            writeError("Expected:  " + Token.kindString(expectedKind) +
                    "Found :" + Token.kindString(currentToken.kind));
    }

    /**
     * Accept the current token by setting currentToken
     * to the next token in the input stream.
     */
    private void acceptIt() {
        currentToken = TheScanner.nextToken();
    }

    private void writeError(String error) {
        errorHappened = true;
        System.out.println(error);
    }
    //Parser Constructor
    public Parser(String script){
        TheScanner = new TheScanner(script);
        currentToken = TheScanner.nextToken();
        sAST = parseScript();
        if (!errorHappened){
            System.out.println("Legal Script");
        }
    }

    //---------------- Parsing Methods ---------------
    //parses Script
    private Script parseScript() {
        while (currentToken.kind == Token.FName
                || currentToken.kind == Token.VAR
                || currentToken.kind == Token.IF
                || currentToken.kind == Token.FOR)
            parseCommand();
        return null;
    }
    //parses Command
    private Command parseCommand() {
        Command cAST = null;
        switch (currentToken.kind) {
            case Token.FName: {
                FNameArg filenameArg;
                filenameArg = parseFileName();
                Argument arg = null;
                while (currentToken.kind == Token.FName
                        || currentToken.kind == Token.LIT
                        || currentToken.kind == Token.VAR)
                    arg = parseArgument();
                accept(Token.EOL);
                cAST = new ExecCmd(filenameArg, arg);
                break;
            }
            case Token.VAR: {
                VarArg lvalue;
                lvalue = parseVariable();
                accept(Token.ASSIGN);
                SingleArg rvalue;
                rvalue = parseSingleArgument();
                accept(Token.EOL);
                cAST = new AssignCmd(lvalue, rvalue);
                break;
            }
            case Token.IF: {
                acceptIt();
                FNameArg filenameArg;
                filenameArg = parseFileName();
                Argument args = null;
                if (currentToken.kind == Token.FName
                        || currentToken.kind == Token.LIT
                        || currentToken.kind == Token.VAR)
                    args = parseArgument();
                else{
                    args = new NullArg();
                }
                accept(Token.THEN);
                accept(Token.EOL);
                Command ifBlock = null;
                if (currentToken.kind == Token.FName
                        || currentToken.kind == Token.LIT
                        || currentToken.kind == Token.VAR
                        || currentToken.kind == Token.FOR)
                    ifBlock = parseCommand();
                else{
                    ifBlock = new NullCmd();
                }
                accept(Token.ELSE);
                accept(Token.EOL);
                Command elseBlock = null;
                if (currentToken.kind == Token.FName
                        || currentToken.kind == Token.LIT
                        || currentToken.kind == Token.VAR
                        || currentToken.kind == Token.FOR)
                    elseBlock = parseCommand();
                else{
                    elseBlock = new NullCmd();
                }
                accept(Token.FI);
                accept(Token.EOL);
                cAST = new IfCmd(filenameArg, args, ifBlock, elseBlock);
                break;
            }
            case Token.FOR: {
                acceptIt();
                VarArg varArg;
                varArg = parseVariable();
                Argument args = null;
                Command command = null;
                accept(Token.IN);
                while (currentToken.kind == Token.FName
                        || currentToken.kind == Token.LIT
                        || currentToken.kind == Token.VAR)
                    args = parseArgument();
                accept(Token.EOL);
                accept(Token.DO);
                accept(Token.EOL);
                while (currentToken.kind == Token.FName
                        || currentToken.kind == Token.LIT
                        || currentToken.kind == Token.VAR
                        || currentToken.kind == Token.FOR)
                    command = parseCommand();
                accept(Token.OD);
                accept(Token.EOL);
                cAST = new ForCommand(varArg, args, command);
                break;
            }
            default: {
                System.err.println("Error in parseCommand");
            }
        }
        if(currentToken.kind != Token.EOT &&
                currentToken.kind != Token.ELSE &&
                currentToken.kind != Token.FI &&
                currentToken.kind != Token.OD){
            Command command;
            command = parseCommand();
            return new SeqCmd(cAST, command);
        }
        return cAST;
    }
    //Parse Argument
    private Argument parseArgument() {
        Argument arg = null;
        switch (currentToken.kind) {
            case Token.FName: {
                arg = parseFileName();
                break;
            }
            case Token.LIT: {
                arg = parseLiteral();
                break;
            }
            case Token.VAR: {
                arg = parseVariable();
                break;
            }
        }
        if(currentToken.kind != Token.EOL && currentToken.kind != Token.THEN){
            Argument arg2;
            arg2 = parseArgument();
            return new SeqArg(arg, arg2);
        }
        return arg;
    }
    //parses filename
    private FNameArg parseFileName() {
        Terminal t = new Terminal(currentToken.spelling);
        acceptIt();
        return new FNameArg(t);
    }
    //parses literal
    private LiteralArg parseLiteral() {
        Terminal t = new Terminal(currentToken.spelling);
        acceptIt();
        return new LiteralArg(t);
    }
    //single variables
    private VarArg parseVariable() {
        Terminal t = new Terminal(currentToken.spelling);
        acceptIt();
        return new VarArg(t);
    }
    //parses single arguments
    private SingleArg parseSingleArgument() {
        SingleArg arg1;
        switch(currentToken.kind) {
            case Token.FName: {
                arg1 = parseFileName();
                return arg1;
            }
            case Token.LIT: {
                arg1 = parseLiteral();
                return arg1;
            }
            case Token.VAR: {
                arg1 = parseVariable();
                return arg1;
            }
        }
        return null;
    }

    public static void decorateAst(){
        DecorateVisitAst visitor = new DecorateVisitAst();
        visitor.visitScript(sAST, null);
    }

    public static printAst(){
        VisitPrintCommands visitor = new VisitPrintCommands();
        return visitor.visitScript(sAST, 0);
    }
}
