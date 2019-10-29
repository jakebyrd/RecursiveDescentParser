package bashShell;


//@uthor Jake Byrd

import bashShell.ast.*;

public class Parser {
    private Token currentToken = null;
    private TheScanner scanner = null;
    private boolean errorHappened;
    private static Script sAST = null;

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

    public Parser(String script){
        scanner = new TheScanner(script);
        currentToken = scanner.nextToken();
        sAST = parseScript();
        if (!errorHappened){
            System.out.println("Legal Script");
        }
    }

    //---------------- Parsing Methods ---------------
    private Script parseScript() {
        while (currentToken.kind == Token.FName
                || currentToken.kind == Token.VAR
                || currentToken.kind == Token.IF
                || currentToken.kind == Token.FOR)
            parseCommand();
    }

    private Command parseSingleCommand() {
        Command cAST;
        switch (currentToken.kind) {
            case Token.FName: {
                FNameArg filenameArg = parseFileName();
                Argument arg;
                //parseFileName();
                while (currentToken.kind == Token.FName
                        || currentToken.kind == Token.LIT
                        || currentToken.kind == Token.VAR)
                    arg = parseArgument();
                accept(Token.EOL);
                cAST = new ExecCmd(filenameArg, arg);
                break;
            }
            case Token.VAR: {
                VarArg lvalue = parseVariable();
                accept(Token.ASSIGN);
                SingleArg rvalue = parseSingleArgument();
                accept(Token.EOL);
                cAST = new AssignCmd(lvalue, rvalue);
                break
            }
            case Token.IF: {
                acceptIt();
                FNameArg filenameArg = parseFileName();
                Argument args;
                while (currentToken.kind == Token.FName
                        || currentToken.kind == Token.LIT
                        || currentToken.kind == Token.VAR)
                    args = parseArgument();
                accept(Token.THEN);
                accept(Token.EOL);
                Command thenBlock;
                while (currentToken.kind == Token.FName
                        || currentToken.kind == Token.LIT
                        || currentToken.kind == Token.VAR
                        || currentToken.kind == Token.FOR)
                    thenBlock = parseCommand();
                accept(Token.ELSE);
                accept(Token.EOL);
                Command elseBlock;
                while (currentToken.kind == Token.FName
                        || currentToken.kind == Token.LIT
                        || currentToken.kind == Token.VAR
                        || currentToken.kind == Token.FOR)
                    elseBlock = parseCommand();
                accept(Token.FI);
                accept(Token.EOL);
                break;
                cAST = new IfCmd(FNameArg, args, thenBlock, elseBlock);
            }
            case Token.FOR: {
                acceptIt();
                Argument varArg = parseVariable();
                Argument arg;
                Command command;
                accept(Token.VAR);
                accept(Token.IN);
                while (currentToken.kind == Token.FName
                        || currentToken.kind == Token.LIT
                        || currentToken.kind == Token.VAR)
                    arg = parseArgument();
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
                break;
                cAST = new ForCommand(varArg, arg, command);
            }
        }
        return cAST;
    }

    private Command parseCommand(){
        Command c1 = parseSingleCommand();
        while(currentToken.kind == Token.ASSIGN){
            acceptIt();
            Command c2 = parseSingleCommand();;
            c1 = new SeqCmd(c1,c2);
        }
        return c1;
    }

    private Argument parseArgument() {
        Argument arg;
        switch (currentToken.kind) {
            case Token.FName: {
                arg = parseFileName();
                return arg;
                break;
            }
            case Token.LIT: {
                arg = parseLiteral();
                return arg;
                break;
            }
            case Token.VAR: {
                arg = parseVariable();
                return arg;
                break;
            }
        }
    }

    private Argument parseFileName() {
        Terminal t = new Terminal(currentToken.spelling);
        acceptIt();
        return new FNameArg(t);
    }

    private Argument parseLiteral() {
        Terminal t = new Terminal(currentToken.spelling);
        acceptIt();
        return new LiteralArg(t);
    }

    private Argument parseVariable() {
        Terminal t = new Terminal(currentToken.spelling);
        acceptIt();
        return new VarArg(t);
    }

    private SingleArg parseSingleArgument() {
        switch(currentToken.kind) {
            case Token.FName: {
                return parseFileName();
            }
            case Token.LIT: {
                return parseLiteral();
            }
            case Token.VAR: {
                return parseVariable();
            }
        }
        return null;
    }

    public static String displayAST(){
        return sAST.visit(0);
    }
}
