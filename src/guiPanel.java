import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class guiPanel extends JPanel {
    String wholeFile;
    String[] statements;
    private final int nodeSize=52;
    public guiPanel() {
        File f = new File("parser_output.txt");
        byte[] data = new byte[(int) f.length()];
        try {
            FileInputStream fis = new FileInputStream(f);
            fis.read(data);
            fis.close();
            wholeFile = new String(data, "UTF-8");
            statements =wholeFile.split("\n");
            this.setDoubleBuffered(true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawSyntaxTree(g);
    }
    public void drawSyntaxTree(Graphics g){
        drawIfStmt(100,100,g);
    }
    private void drawIfStmt (int x,int y,Graphics g){
        drawNode("IF",x,y,g);
        int node1X = x+nodeSize+nodeSize/2;
        int nodeY = y+nodeSize+nodeSize/2;
        g.drawLine(x+nodeSize/2,y+nodeSize,node1X,nodeY);
        int node2X = x-nodeSize/2;
        g.drawLine(x+nodeSize/2,y+nodeSize,node2X,nodeY);
        drawNode("IF",(int)(node1X-0.292*nodeSize/2),(int)(nodeY-0.292*nodeSize/2),g);
        drawNode("IF",(int)(node1X-0.292*nodeSize/2),(int)(nodeY-0.292*nodeSize/2),g);


    }
    private void drawNode(String label,int x,int y,Graphics g){
        int textWidth = g.getFontMetrics().stringWidth(label);
        g.setColor(Color.BLACK);
        g.drawOval(x,y,nodeSize,nodeSize);
        g.drawString(label,x+nodeSize/2-textWidth/2,y+nodeSize/2);
    }

}