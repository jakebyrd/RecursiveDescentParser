package bashShell;
import java.util.ArrayList;
import java.util.Scanner;


public class TheScanner {

    private int nextToken;
    private ArrayList<Byte> tokens = null;
    public final String varDeclare = "[A-Za-z][A-Za-z0-9_.]*";
    public final String litDeclare = "-?[A-Za-z0-9]*|[0-9]";
    private String currentToken = null;
    private TheScanner scanner = null;

    public TheScanner(Path source){
        Scanner sent = new Scanner(source);
        tokens = new ArrayList<>();
        while(sent.hasNext()) {
            String temp = sent.next();
            switch (temp){
                case "cat": case "ls": case "pwd": case "touch": case "cp": case "mv":
                case "rm": case "chmod": case "man": case "ps": case "bg":
                case "mkdir": case "test": case "cd":
                    tokens.add(Token.FName);
                    break;
                case "=":
                    tokens.add(Token.ASSIGN);
                    break;
                case "if":
                    tokens.add(Token.IF);
                    break;
                case "then":
                    tokens.add(Token.THEN);
                    break;
                case "else":
                    tokens.add(Token.ELSE);
                    break;
                case "fi":
                    tokens.add(Token.FI);
                    break;
                case "for":
                    tokens.add(Token.FOR);
                    break;
                case "in":
                    tokens.add(Token.IN);
                    break;
                case "do":
                    tokens.add(Token.DO);
                    break;
                case "od":
                    tokens.add(Token.OD);
                    break;
                case "eol":
                    tokens.add(Token.EOL);
                    break;
                default:
                    if(temp.matches(varDeclare)){
                        tokens.add(Token.VAR);
                    }
                    else if(temp.matches(litDeclare)){
                        tokens.add(Token.LIT);
                    }
                    else{
                        throw new RuntimeException("Invalid Token: " + temp);
                    }
            }
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
            return Token.EOL;
    }

    public boolean hasTokens(){
        return nextToken == tokens.size();
    }

    public static void main(String [] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Bash Shell file, making sure the file ends with .sh");
        String script = in.nextLine();
        Parser s = new Parser(script);
    }

    //Tests from class.
    //Parser s = new Parser("touch myNewFile mkdir newStuff mv myNewFile newStuff cd newStuff chmod 557");
    //Parser s = new Parser("if test -e apples then eol else eol touch apples eol fi eol mkdir
    // basket eol for file in fruit eol do eol mv apples basket eol od eol");
}
