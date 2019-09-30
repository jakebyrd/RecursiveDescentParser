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

    public TheScanner(String script){
        Scanner sent = new Scanner(script);
        tokens = new ArrayList<>();
        while(sent.hasNext()) {
            String temp = sent.next();
            switch (temp){
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
                case "cat": case "ls": case "pwd": case "touch": case "cp": case "mv":
                case "rm": case "chmod": case "man": case "ps": case "bg":
                case "mkdir": case "test": case "cd":
                    tokens.add(Token.FName);
                case "=":
                    tokens.add(Token.ASSIGN);
                case "if":
                    tokens.add(Token.IF);
                case "then":
                    tokens.add(Token.THEN);
                case "else":
                    tokens.add(Token.ELSE);
                case "fi":
                    tokens.add(Token.FI);
                case "for":
                    tokens.add(Token.FOR);
                case "in":
                    tokens.add(Token.IN);
                case "do":
                    tokens.add(Token.DO);
                case "od":
                    tokens.add(Token.OD);
                case "eol":
                    tokens.add(Token.EOL);
            }
        }
        System.out.println(tokens);
        nextToken = 0;
    }

    public Byte nextToken() {
        if (nextToken < tokens.size()) {
            nextToken++;
            return tokens.get(nextToken-1);
        }
        else
            return null;
    }

    public boolean hasTokens(){
        return nextToken == tokens.size();
    }

    public static void main(String [] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter script while remembering to keep spaces and having eol at the end");
        String script = in.nextLine();
        Parser s = new Parser(script);
    }

    //Tests from class.
    //Parser s = new Parser("touch myNewFile mkdir newStuff mv myNewFile newStuff cd newStuff chmod 557");
    //Parser s = new Parser("if test -e apples then eol else eol touch apples eol fi eol mkdir
    // basket eol for file in fruit eol do eol mv apples basket eol od eol");
}
