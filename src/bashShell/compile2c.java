package bashShell;
import bashShell.Parser;
import java.util.Scanner;
import java.io.*;

public class compile2C {

    public static void main(String [] args) throws Exception{
        boolean outFile = false;
        boolean outScreen = false;
        String inputFName = null;
        String treeAst;
        //check for -d or -p for input
        for(String arg: args) {
            if (arg.equals("-d")){
                outScreen = true;
            }
            else if (arg.equals("-p")){
                outFile = true;
            }
            else{
                inputFName = arg;
            }
        }
        if (inputFName.equals("")){
            System.err.println("Must print to screen or file");
        }
        else {
            //scans input from file
            String input = new Scanner(new File(inputFName)).useDelimiter("\\Z").next();
            //parses file input
            Parser parse = new Parser(input);
            //parses tree
            treeAst = parse.parse();
            //outputs to console
            if (outScreen) {
                System.out.println(treeAst);
            }
            //outputs ast to file
            if (outFile) {
                File file = new File(inputFName + ".ast");
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(treeAst);
                fileWriter.close;
            }
        }
    }
}
