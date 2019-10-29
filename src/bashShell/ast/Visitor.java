package bashShell.ast;

public class Visitor {
    public static String node(int length){
        length = length*3;
        StringBuilder output = new StringBuilder(length);
        for (int i = 0; i<length; i++){
            output.append(" ");
        }
        return output.toString();
    }
}
