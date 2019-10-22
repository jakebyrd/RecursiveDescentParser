package bashShell;


//@uthor Jake Byrd

import bashShell.ast.*;

public class Parser {
    private Byte currentToken = null;
    private TheScanner scanner = null;
    private boolean errorHappened;

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
        if (currentToken == expectedKind)
            currentToken = scanner.nextToken();
        else
            writeError("Expected:  " + Token.kindString(expectedKind) +
                    "Found :" + Token.kindString(currentToken));
    }

    /**
     * Accept the current token by setting currentToken
     * to the next token in the input stream.
     */
    private void acceptIt() {
        currentToken = scanner.nextToken();
    }

    private void writeError(String error) {
        errorHappened = true;
        System.out.println(error);
    }

    public Parser(String script){
        scanner = new TheScanner(script);
        currentToken = scanner.nextToken();
        parseScript();
        if (!errorHappened){
            System.out.println("Legal Script");
        }
    }

    //---------------- Parsing Methods ---------------
    private Script parseScript() {
        while (currentToken == Token.FName
                || currentToken == Token.VAR
                || currentToken == Token.IF
                || currentToken == Token.FOR)
            return new Script(parseCommand());
    }

    private Command parseSingleCommand() {
        Command cAST;
        switch (currentToken) {
            case Token.FName: {
                Argument FNameArg = parseFileName();
                acceptIt();
                //parseFileName();
                Argument args;
                while (currentToken == Token.FName
                        || currentToken == Token.LIT
                        || currentToken == Token.VAR)
                    args = parseArgument();
                accept(Token.EOL);
                cAST = new ExecCmd(FNameArg, args);
                break;
            }
            case Token.VAR: {
                Command command;
                Argument lvalue = parseVariable();
                acceptIt();
                accept(Token.ASSIGN);
                Argument rvalue;
                while (currentToken == Token.FName
                        || currentToken == Token.VAR
                        || currentToken == Token.LIT)
                    rvalue = parseArgument();
                accept(Token.EOL);
                cAST = new AssignCmd(lvalue, rvalue);
                break;
            }
            case Token.IF: {
                acceptIt();
                parseFileName();
                while (currentToken == Token.FName
                        || currentToken == Token.LIT
                        || currentToken == Token.VAR)
                    parseArgument();
                accept(Token.THEN);
                accept(Token.EOL);
                while (currentToken == Token.FName
                        || currentToken == Token.LIT
                        || currentToken == Token.VAR
                        || currentToken == Token.FOR)
                    parseCommand();
                accept(Token.ELSE);
                accept(Token.EOL);
                while (currentToken == Token.FName
                        || currentToken == Token.LIT
                        || currentToken == Token.VAR
                        || currentToken == Token.FOR)
                    parseCommand();
                accept(Token.FI);
                accept(Token.EOL);
                break;
            }
            case Token.FOR: {
                acceptIt();
                Argument varArg = parseVariable();
                Argument arg;
                Command command;
                accept(Token.VAR);
                accept(Token.IN);
                while (currentToken == Token.FName
                        || currentToken == Token.LIT
                        || currentToken == Token.VAR)
                    arg = parseArgument();
                accept(Token.EOL);
                accept(Token.DO);
                accept(Token.EOL);
                while (currentToken == Token.FName
                        || currentToken == Token.LIT
                        || currentToken == Token.VAR
                        || currentToken == Token.FOR)
                    command = parseCommand();
                accept(Token.OD);
                accept(Token.EOL);
                cAST = new ForCommand(varArg, arg, command);
                break;
            }
        }
    }

    private Command parseCommand(){
        Command c1 = parseSingleCommand();
        while(currentToken == Token.ASSIGN){
            acceptIt();
            Command c2 = parseSingleCommand();;
            c1 = new SeqCmd(c1,c2);
        }
        return c1;
    }

    private Argument parseArgument() {
        Argument arg;
        switch (currentToken) {
            case Token.FName: {
                arg = new FNameArg();
                parseFileName();
                return arg;
                break;
            }
            case Token.LIT: {
                arg = new LiteralArg();
                parseLiteral();
                return arg;
                break;
            }
            case Token.VAR: {
                arg = new VarArg();
                parseVariable();
                return arg;
                break;
            }
        }
    }

    private Argument parseFileName() {
        term = new Terminal()
        acceptIt();
        return new FNameArg(term);
    }

    private Argument parseLiteral() {
        term = new Terminal()
        acceptIt();
        return new LiteralArg(term);
    }

    private Argument parseVariable() {
        term = new Terminal();
        acceptIt();
        return new VarArg(term);
    }



    public static void main(String [] args){

    }
}
