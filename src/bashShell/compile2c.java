package bashShell;
import java.util.Scanner;
import java.io.*;

public class compile2C {

    public static void main(String [] args) throws Exception{
        boolean outFile = false;
        boolean outScreen = false;
        String inputFName = "";

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
            File file = new File(inputFName);
            Scanner scan = new Scanner(file);
            StringBuilder input = new StringBuilder();
            while (scan.hasNextLine())
                input.append(scan.nextLine()).append(" eol ");

            Parser parse = new Parser(input.toString());

            if (outScreen) {
                System.out.println("\n" + Parser.displayAST());
            }

            if (outFile) {
                String outFName = inputFName.substring(inputFName.lastIndexOf("/") + 1,
                        inputFName.lastIndexOf(".")) + ".ast";
                writeFile(outFName, Parser.displayAST());
                System.out.println(outFName);
            }
        }
    }
    private static void writeFile(String fName, String fContent) throws IOException
    {
        FileWriter fileWrite = new FileWriter(System.getProperty("user.dir") + "/" + fName);
        PrintWriter printWrite = new PrintWriter(fileWrite);
        printWrite.print(fContent);
        printWrite.close();
    }
}
