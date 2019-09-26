package bashShell;

public class Token {
    public byte kind;
    public String spelling;
    private Token currentToken = null;
    private MyScanner scanner = null;

    public final static byte FName = 0;
    public final static byte LIT = 1;
    public final static byte VAR = 2;
    public final static byte ASSIGN = 3;
    public final static byte IF = 4;
    public final static byte THEN = 5;
    public final static byte ELSE = 6;
    public final static byte FI = 7;
    public final static byte FOR = 8;
    public final static byte IN = 9;
    public final static byte DO = 10;
    public final static byte OD = 11;
    public final static byte EOL = 12;  // end of line
    public final static byte EOT = 13;  // end of the text
    public final static byte CMD = 14;
    public final static byte ARG = 15;

    private final static String[] spellings = {
            "<shell command>", "<literal>", "<variable>", "assign",
            "if", "then", "else", "fi", "for", "in", "do", "od", "<eol>",
            "<eot>", "<command>", "<argument>"
    };
    private void acceptIt() {
        currentToken = scanner.nextToken();
    }

    public static String kindString(byte kind) {
        return spellings[kind];
    }

    public Token(byte kind, String spelling){
        this.kind = kind;
        this.spelling = spelling;
        if(kind == Token.VAR){
            switch(currentToken){
                case 'a': case 'b': case 'c': case 'd': case 'e': case 'f': case 'g': case 'h':
                case 'i': case 'j': case 'k': case 'l': case 'm': case 'n': case 'o': case 'p':
                case 'q': case 'r': case 's': case 't': case 'u': case 'v': case 'w': case 'x':
                case 'y': case 'z':
                    acceptIt();
                    return Token.FName;
                case '0': case '1': case '2': case '3': case '4': case '5':
                case '6': case '7': case '8': case '9': case '_': case '.':

                default:
                    System.out.print("Script is not a command");
            }
        }
    }
}
