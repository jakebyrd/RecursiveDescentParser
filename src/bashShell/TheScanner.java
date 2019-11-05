package bashShell;
import java.util.ArrayList;
import java.util.Scanner;


public class TheScanner {

    private int nextToken;
    private ArrayList<Token> tokens = null;
    public final String varDeclare = "[A-Za-z][A-Za-z0-9_.]*";
    public final String litDeclare = "-?[A-Za-z0-9]*|[0-9]";
    private TheScanner scanner = null;

    public TheScanner(String script){
        Scanner sent = new Scanner(script);
        tokens = new ArrayList<Token>();
        while (sent.hasNext()){
            String temp = sent.next();
            switch (temp){
                case "cat": case "ls": case "pwd": case "touch": case "cp": case "mv":
                case "rm": case "chmod": case "man": case "ps": case "bg":
                case "mkdir": case "test": case "cd":
                    tokens.add (new Token(Token.FName, temp));
                    break;
                case "=":
                    tokens.add (new Token(Token.ASSIGN, temp));
                    break;
                case "if":
                    tokens.add (new Token(Token.IF, temp));
                    break;
                case "then":
                    tokens.add (new Token(Token.THEN, temp));
                    break;
                case "else":
                    tokens.add (new Token(Token.ELSE, temp));
                    break;
                case "fi":
                    tokens.add (new Token(Token.FI, temp));
                    break;
                case "for":
                    tokens.add (new Token(Token.FOR, temp));
                    break;
                case "in":
                    tokens.add (new Token(Token.IN, temp));
                    break;
                case "do":
                    tokens.add (new Token(Token.DO, temp));
                    break;
                case "od":
                    tokens.add (new Token(Token.OD, temp));
                    break;
                case "eol":
                    tokens.add (new Token(Token.EOL, temp));
                    break;
                default:
                    if(temp.matches(varDeclare)){
                        tokens.add (new Token(Token.VAR, temp));
                    }
                    else if(temp.matches(litDeclare)){
                        tokens.add (new Token(Token.LIT, temp));
                    }
                    else{
                        throw new RuntimeException("Invalid Token: " + temp);
                    }
            }
        }
        for (Token token: tokens){
            System.out.print(token.spelling + " ");
        }
        System.out.println(tokens);
        nextToken = 0;
    }

    public Token nextToken() {
        if (nextToken < tokens.size()) {
            nextToken++;
            return tokens.get(nextToken-1);
        }
        else
            return new Token(Token.EOL,"eot");
    }

    public boolean hasTokens(){
        return nextToken == tokens.size();
    }

    // public static void main(String [] args) {
    //     Scanner in = new Scanner(System.in);
    //     System.out.println("Enter Bash Shell file, making sure the file ends with .sh");
    //     String source = in.nextLine();
    //     Parser s = new Parser(source);
    // }

    //Tests from class.
    //Parser s = new Parser("touch myNewFile mkdir newStuff mv myNewFile newStuff cd newStuff chmod 557");
    //Parser s = new Parser("if test -e apples then eol else eol touch apples eol fi eol mkdir
    // basket eol for file in fruit eol do eol mv apples basket eol od eol");
}
