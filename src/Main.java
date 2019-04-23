import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
    public static int state=0;
    public static void main(String args[]){
        TinyScanner tinyScanner=new TinyScanner();
        tinyScanner.getTokens();
        try {
            PrintStream fOut = new PrintStream("parser_output.txt");
            System.setOut(fOut);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Parser p =new Parser(tinyScanner.tokenList);

        }

}
