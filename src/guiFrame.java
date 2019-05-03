import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class guiFrame extends JFrame {
    private int panelWidth  = Toolkit.getDefaultToolkit().getScreenSize().width/2;
    private int panelHeight = Toolkit.getDefaultToolkit().getScreenSize().height/2;
    private guiPanel guiPanel ;
    private JScrollPane jScrollPane ;
    public guiFrame(Node root) throws HeadlessException {
        this.setTitle("Syntax Tree");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiPanel =new guiPanel(root);
        this.setMinimumSize(new Dimension(panelWidth,panelHeight));
        guiPanel.setPreferredSize(new Dimension(panelWidth,panelHeight));
        jScrollPane =new JScrollPane(guiPanel);
        this.add(jScrollPane);
        }


}

