import java.util.ArrayList;
import java.util.List;

public class Node {
    private String data;
    private Node parent = null;
    private List<Node> children = new ArrayList<>();
    private int x, y;

    public Node(String data) {
        this.data = data;
    }

    public Node(String data, Node parent) {
        this.data = data;
        this.parent = parent;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public void addChild(Node child) {
        child.parent = this;
        this.children.add(child);
    }
    public  Node deleteChild(){
       return this.children.remove(children.size()-1);
    }

    public void addChild(String data) {
        Node newChild = new Node(data);
        this.addChild(newChild);
    }

    public void addChildren(List<Node> children) {
        for (Node child : children) {
            child.setParent(this);
        }
        this.children.addAll(children);
    }
    public boolean haveChildren(){
        if(children.size()>0)
            return true;
        return false;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
