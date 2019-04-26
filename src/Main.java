import javax.swing.*;

public class Main {
    public static void main(String args[]){
        TinyScanner tinyScanner=new TinyScanner();
        Parser p =new Parser(tinyScanner.getTokenList());
        guiFrame guiFrame =new guiFrame();
        guiPanel mainPanel =new guiPanel();
        JScrollPane jScrollPane =new JScrollPane(mainPanel);
        guiFrame.add(jScrollPane);
        guiFrame.setVisible(true);
    }
}
