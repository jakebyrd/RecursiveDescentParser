package bashShell;


//@uthor Jake Byrd

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
    private void parseScript() {
        while (currentToken == Token.FName
                || currentToken == Token.VAR
                || currentToken == Token.IF
                || currentToken == Token.FOR)
            parseCommand();
    }

    private void parseCommand() {
        switch (currentToken) {
            case Token.FName: {
                acceptIt();
                //parseFileName();
                while (currentToken == Token.FName
                        || currentToken == Token.LIT
                        || currentToken == Token.VAR)
                    parseArgument();
                accept(Token.EOL);
                break;
            }
            case Token.VAR: {
                acceptIt();
                accept(Token.ASSIGN);
                while (currentToken == Token.FName
                        || currentToken == Token.VAR
                        || currentToken == Token.LIT)
                    parseArgument();
                accept(Token.EOL);
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
                accept(Token.VAR);
                accept(Token.IN);
                while (currentToken == Token.FName
                        || currentToken == Token.LIT
                        || currentToken == Token.VAR)
                    parseArgument();
                accept(Token.EOL);
                accept(Token.DO);
                accept(Token.EOL);
                while (currentToken == Token.FName
                        || currentToken == Token.LIT
                        || currentToken == Token.VAR
                        || currentToken == Token.FOR)
                    parseCommand();
                accept(Token.OD);
                accept(Token.EOL);
                break;
            }
        }
    }

    private void parseArgument() {
        switch (currentToken) {
            case Token.FName: {
                parseFileName();
                break;
            }
            case Token.LIT: {
                parseLiteral();
                break;
            }
            case Token.VAR: {
                parseVariable();
                break;
            }
        }
    }

    private void parseFileName() {
        acceptIt();
    }

    private void parseLiteral() {
        acceptIt();
    }

    private void parseVariable() {
        acceptIt();
    }
}
