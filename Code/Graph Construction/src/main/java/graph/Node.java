package graph;

/**
 * Created by Vito on 28. 4. 2016.
 */
public class Node {

    private static final String DELIMITER = ":";

    private String name;
    private Node parent;

    public Node(String name) {
        this.name = name;
    }
    public Node(int i) {
        this.name = i+"";
    }

    public void concatToName(String name) {
        this.name += ","+name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public String getWholeName() {
        return toString();
    }

    @Override
    public String toString() {
        return ((parent != null) ? parent.toString() + DELIMITER : "") + getName();
    }
}
