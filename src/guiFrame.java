import javax.swing.*;
import java.awt.*;

public class guiFrame extends JFrame {
    private int panelWidth = Toolkit.getDefaultToolkit().getScreenSize().width/2;
    private int panelHeight = Toolkit.getDefaultToolkit().getScreenSize().height/2;
    private guiPanel mainPanel =new guiPanel();
    public guiFrame() throws HeadlessException {
        this.setTitle("Syntax Tree");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(panelWidth,panelHeight));
        this.add(mainPanel);
    }

    private class guiPanel extends JPanel{
        public guiPanel() {

        }
        @Override
        protected void paintComponent(Graphics g){
         super.paintComponent(g);

         g.drawOval(20,20,20,20);

        }

    }
}

