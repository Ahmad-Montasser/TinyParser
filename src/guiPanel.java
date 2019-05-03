import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class
guiPanel extends JPanel {

    String wholeFile;
    String[] statements;
    private final int nodeSize = 70;
    private int currentNodeX;
    private int currentNodeY;
    private Node root;

    public guiPanel(Node root) {
        this.root = root;
        root.setY(50);
        root.setX(400);
        File f = new File("parser_output.txt");
        currentNodeX = Toolkit.getDefaultToolkit().getScreenSize().width / 4 - nodeSize / 2 - 10;
        currentNodeY = this.getY() + nodeSize;
        byte[] data = new byte[(int) f.length()];
        try {
            FileInputStream fis = new FileInputStream(f);
            fis.read(data);
            fis.close();
            wholeFile = new String(data, "UTF-8");
            statements = wholeFile.split("\n");
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

    public void drawSyntaxTree(Graphics g) {
        getChildren(root, g);
    }

    private void getChildren(Node node, Graphics g) {
        if (node == null) return;
        List<Node> children;
        children = node.getChildren();
        int i = 0;
        if (node == root) {
            node.getChildren().get(0).setY(node.getY());
            node.getChildren().get(0).setX(node.getX());
    }        for (Node n : children) {
            List<Node> currentChildren = n.getChildren();
            switch (n.getData()) {
                case "exp":
                    break;
                case "readStmt":
                    drawOneChildStmt(n.getData(), currentChildren.get(0).getData(), g);
                    break;
                case "writeStmt":
                    if (currentChildren.get(0).getData().equals("exp"))
                        drawOneChildStmt(n.getData(),
                                currentChildren.get(0).getChildren().get(0).getData(), g);
                    else
                        drawOneChildStmt(n.getData(), currentChildren.get(0).getData(), g);
                    break;
                case "assignStmt":
                    drawTwoChildStmt(n, currentChildren.get(0)
                            , currentChildren.get(1).getChildren().get(0), g);
                    break;
                case "repeatStmt":
                    drawTwoChildStmt(n, currentChildren.get(0)
                            , currentChildren.get(currentChildren.size() - 1).getChildren().get(0)
                            , g);
                    break;
                case "ifStmt":
                    if (currentChildren.get(currentChildren.size() - 1).getData().equals("else"))
                        drawIfStmt(currentChildren.get(0).getChildren().get(0).getData()
                                , currentChildren.get(1).getData(), "else", true, g);
                    else
                        drawIfStmt(currentChildren.get(0).getChildren().get(0).getData()
                                , currentChildren.get(1).getData(), "", false, g);

                    break;
                case"+-":
                    drawTwoChildStmt(n,n.getChildren().get(0)
                            ,n.getChildren().get(1),g);
                case "else":

                    break;

            }
            getChildren(n, g);
//            if (n.getChildren().size() > i) {
//                Node currentNode = n.getChildren().get(i++);
//                getChildren(currentNode, g);
//            } else
//                return;
        }
    }

    //For write
    private void drawOneChildStmt(String stmt, String node1, Graphics g) {
        drawNode(stmt, currentNodeX, currentNodeY, g);
        int nodeY = currentNodeY + nodeSize * 2;
        g.drawLine(currentNodeX + nodeSize / 2, currentNodeY + nodeSize, currentNodeX + nodeSize / 2, nodeY);
        drawNode(node1, currentNodeX, nodeY, g);
    }

    //For +,-,*,/,repeat,assign
    private void drawTwoChildStmt(Node parent, Node node1, Node node2, Graphics g) {
        currentNodeX=parent.getX();
        currentNodeY=parent.getY();
        int nodeY = currentNodeY + nodeSize * 2;
        drawNode(parent.getData(), currentNodeX, currentNodeY, g);
        int node1X = currentNodeX + nodeSize +nodeSize/2 ;
        node1.setX(node1X);
        g.drawLine(currentNodeX + nodeSize / 2,
                currentNodeY + nodeSize, node1X-nodeSize/2, nodeY);
        int node2X =  currentNodeX - nodeSize / 2- nodeSize ;
        node2.setX(node2X);
        node1.setY(nodeY);
        node2.setY(nodeY);
        g.drawLine(currentNodeX + nodeSize / 2,
                currentNodeY + nodeSize, node2X+nodeSize/2, nodeY);
        drawNode(node2.getData(), node1X ,
                nodeY, g);
        drawNode(node1.getData(), node2X,
                nodeY , g);
    }


    //For If Statement
    private void drawIfStmt(String node1, String node2, String node3, boolean elseFound, Graphics g) {
        drawNode("IF-Stmt", this.currentNodeX, currentNodeY, g);
        if (elseFound) {
            int nodeY = currentNodeY + nodeSize * 2;
            //Left Node
            int node1X = this.currentNodeX - nodeSize - nodeSize / 2;
            g.drawLine(this.currentNodeX + nodeSize / 2, currentNodeY + nodeSize, node1X, nodeY);
            drawNode(node1, node1X - nodeSize / 2, nodeY, g);
            //Middle Node
            g.drawLine(this.currentNodeX + nodeSize / 2, currentNodeY + nodeSize, this.currentNodeX + nodeSize / 2, nodeY);
            drawNode(node2, this.currentNodeX, currentNodeY + nodeSize * 2, g);
            //Right Node
            int node3X = this.currentNodeX + nodeSize * 2 + nodeSize / 2;
            g.drawLine(this.currentNodeX + nodeSize / 2, currentNodeY + nodeSize, node3X, nodeY);
            drawNode(node3, node3X - nodeSize / 2, nodeY, g);
        } else {
            drawTwoChildStmt(new Node(""), new Node(node1), new Node(node2), g);
        }
    }

    //For each Individual Node
    private void drawNode(String label, int x, int y, Graphics g) {
        int textWidth = g.getFontMetrics().stringWidth(label);
        g.setColor(Color.BLACK);
        g.drawOval(x, y, nodeSize, nodeSize);
        g.drawString(label, x + nodeSize / 2 - textWidth / 2, y + nodeSize / 2 + nodeSize / 12);
    }

}