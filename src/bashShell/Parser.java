package bashShell;

import java.util.Scanner;

//@uthor Jake Byrd

public class Parser {
    private Token currentToken = null;
    private MyScanner scanner = null;
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
        if (currentToken.kind == expectedKind)
            currentToken = scanner.nextToken();
        else
            writeError("Expected:  " + Token.kindString(expectedKind) +
                       "Found :" + Token.kindString(currentToken.kind));
    }

    /**
     * Accept the current token by setting currentToken
     * to the next token in the input stream.
     */
    private void acceptIt() {
        currentToken = scanner.nextToken();
    }

    private void writeError(String s) {
        errorHappened = true;
        System.out.printf("Syntax Error: %s.\n", s);
    }

    public Parser(String script){
        scanner = new MyScanner(script);
        currentToken = scanner.nextToken();
        errorHappened = false;
    }

    //---------------- Parsing Methods ---------------
    private void parseScript() {
         while (currentToken.kind == Token.FName
                || currentToken.kind == Token.VAR
                || currentToken.kind == Token.IF
                 || currentToken.kind == Token.FOR)
             parseCommand();
    }

    private void parseCommand() {
        switch (currentToken.kind) {
            case Token.FName: {
                acceptIt();
                //parseFileName();
                while (currentToken.kind == Token.FName
                      || currentToken.kind == Token.LIT
                      || currentToken.kind == Token.VAR)
                    parseArgument();
                accept(Token.EOL);
            }
            case Token.VAR: {
                acceptIt();
                accept(Token.ASSIGN);
                parseArgument();
                accept(Token.EOL);
            }
            case Token.IF: {
                acceptIt();
                while (currentToken.kind == Token.FName
                  || currentToken.kind == Token.LIT
                  || currentToken.kind == Token.VAR)
                    parseArgument();
                    accept(Token.THEN);
                    accept(Token.EOL);
                while (currentToken.kind == Token.FName
                    || currentToken.kind == Token.LIT
                    || currentToken.kind == Token.VAR)
                    parseArgument();
                    accept(Token.ELSE);
                    accept(Token.EOL);
                while (currentToken.kind == Token.FName
                        || currentToken.kind == Token.LIT
                        || currentToken.kind == Token.VAR)
                    parseArgument();
                accept(Token.FI);
                accept(Token.EOL);
            }
            case Token.FOR: {
                acceptIt();
                accept(Token.VAR);
                accept(Token.IN);
                while (currentToken.kind == Token.FName
                        || currentToken.kind == Token.LIT
                        || currentToken.kind == Token.VAR)
                    parseArgument();
                accept(Token.EOL);
                accept(Token.DO);
                accept(Token.EOL);
                while (currentToken.kind == Token.FName
                        || currentToken.kind == Token.LIT
                        || currentToken.kind == Token.VAR)
                    parseArgument();
                accept(Token.OD);
                accept(Token.EOL);
            }
        }
    }

    private void parseArgument() {
        switch (currentToken.kind) {
            case Token.FName: {
                parseFileName();
            }
            case Token.LIT: {
                parseLiteral();
            }
            case Token.VAR: {
                parseVariable();
            }
        }
    }

    private void parseFileName() {
        if(currentToken.kind == Token.FName){
            acceptIt();
        }
    }

    private void parseLiteral() {
        if(currentToken.kind == Token.LIT){
            acceptIt();
        }
    }

    private void parseVariable() {
        if(currentToken.kind == Token.VAR){
            acceptIt();
        }
    }
    public void parse() {
        parseScript();
        if(currentToken.equals("EOL") && !errorHappened) {
            System.out.println("The command is in the UNIX shell grammar!");
        }
        else
            writeError("Malformed command.");
    }

    public static void main(String [] args) {
        System.out.print("Enter a shell command ==>  ");
        Scanner in = new Scanner(System.in);
        String script = in.nextLine();

        Parser prse = new Parser(script);
        prse.parse();
    }
}
