package bashShell;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;


public class MyScanner {

    private int nextToken;

    private ArrayList<String> tokens = null;

    public MyScanner(String line) {
        Scanner sent = new Scanner(line);
        tokens = new ArrayList<>();
        while(sent.hasNext()){
            String temp = sent.next();
            if(temp == null) {
                tokens.add("EOT");
            }
            else
                tokens.add(temp);
        }
        nextToken = 0;
    }

    public Token nextToken() {
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
  //  public static void main(String [] args) {
  //      MyScanner ts = new MyScanner();
  //  }
}
