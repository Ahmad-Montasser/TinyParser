import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class guiFrame extends JFrame {
    private int panelWidth  = Toolkit.getDefaultToolkit().getScreenSize().width/2;
    private int panelHeight = Toolkit.getDefaultToolkit().getScreenSize().height/2;
    public guiFrame() throws HeadlessException {
        this.setTitle("Syntax Tree");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(panelWidth,panelHeight));
        }


}

