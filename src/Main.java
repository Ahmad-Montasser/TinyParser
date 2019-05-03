import java.util.Arrays;

public class Main {
    public static void main(String args[]){
        TinyScanner tinyScanner=new TinyScanner();
        Parser p =new Parser(tinyScanner.getTokenList());
        Node root = p.getRoot();
        guiFrame guiFrame=new guiFrame(root);
        guiFrame.setVisible(true);
    }
}
