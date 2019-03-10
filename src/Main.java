import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static int state=0;
    public static void main(String args[]){
        TinyScanner tinyScanner=new TinyScanner();
        Token t = tinyScanner.getNextToken();
        System.out.println(t);
        t= tinyScanner.getNextToken();
        System.out.println(t);
        t= tinyScanner.getNextToken();
        System.out.println(t);
    }

}
