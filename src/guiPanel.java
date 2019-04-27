import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class guiPanel extends JPanel {

    String wholeFile;
    String[] statements;
    private final int nodeSize=52;
    private int currentNodeX;
    private int currentNodeY;


    public guiPanel() {
        File f = new File("parser_output.txt");
        currentNodeX=Toolkit.getDefaultToolkit().getScreenSize().width/4-nodeSize/2-10;
        currentNodeY=this.getY()+nodeSize;
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
        drawIfStmt("","","",true,g);
    }

    //For assign,write
    private void drawOneChildStmt (String stmt,String node1,Graphics g){
        drawNode(stmt,currentNodeX,currentNodeY,g);
        int nodeY = currentNodeY+nodeSize*2;
        g.drawLine(currentNodeX+nodeSize/2,currentNodeY+nodeSize,currentNodeX+nodeSize/2,nodeY);
        drawNode(node1,currentNodeX,nodeY,g);

    }

    //For +,-,*,/,repeat
    private void drawTwoChildStmt (String opType,String node1,String node2,Graphics g){
        drawNode(opType,currentNodeX,currentNodeY,g);
        int node1X = currentNodeX+nodeSize+nodeSize/2;
        int nodeY = currentNodeY+nodeSize*2;
        g.drawLine(currentNodeX+nodeSize/2,currentNodeY+nodeSize,node1X,nodeY);
        int node2X = currentNodeX-nodeSize/2;
        g.drawLine(currentNodeX+nodeSize/2,currentNodeY+nodeSize,node2X,nodeY);
        drawNode(node2,(int)(node1X-0.292*nodeSize/2),(int)(nodeY-0.292*nodeSize/2),g);
        drawNode(node1,(int)(node2X+0.292*nodeSize/2)-nodeSize,(int)(nodeY-0.292*nodeSize/2),g);

    }


    //For If Statement
    private void drawIfStmt (String node1,String node2,String node3,boolean elseFound,Graphics g){
        drawNode("IF-Stmt", this.currentNodeX, currentNodeY, g);
        if(elseFound) {
            int nodeY = currentNodeY + nodeSize *2;
            //Left Node
            int node1X = this.currentNodeX - nodeSize - nodeSize / 2;
            g.drawLine(this.currentNodeX + nodeSize / 2, currentNodeY + nodeSize, node1X, nodeY);
            drawNode(node1, node1X  - nodeSize/2, nodeY, g);
            //Middle Node
            g.drawLine(this.currentNodeX +nodeSize/2,currentNodeY+nodeSize, this.currentNodeX +nodeSize/2,nodeY);
            drawNode(node2, this.currentNodeX,currentNodeY+nodeSize*2,g);
            //Right Node
            int node3X =  this.currentNodeX + nodeSize*2+nodeSize/2;
            g.drawLine(this.currentNodeX + nodeSize / 2, currentNodeY + nodeSize, node3X, nodeY);
            drawNode(node3, node3X  - nodeSize/2, nodeY , g);
        }
        else{
            int node1X = this.currentNodeX + nodeSize + nodeSize / 2;
            int nodeY = currentNodeY + nodeSize + nodeSize / 2;
            g.drawLine(this.currentNodeX + nodeSize / 2, currentNodeY + nodeSize, node1X, nodeY);
            int node2X = this.currentNodeX - nodeSize / 2;
            g.drawLine(this.currentNodeX + nodeSize / 2, currentNodeY + nodeSize, node2X, nodeY);
            drawNode(node1, (int) (node1X - 0.292 * nodeSize / 2), (int) (nodeY - 0.292 * nodeSize / 2), g);
            drawNode(node2, (int) (node2X + 0.292 * nodeSize / 2) - nodeSize, (int) (nodeY - 0.292 * nodeSize / 2), g);
        }
    }

    //For each Individual Node
    private void drawNode(String label,int x,int y,Graphics g){
        int textWidth = g.getFontMetrics().stringWidth(label);
        g.setColor(Color.BLACK);
        g.drawOval(x,y,nodeSize,nodeSize);
        g.drawString(label,x+nodeSize/2-textWidth/2,y+nodeSize/2+nodeSize/12);
    }

}