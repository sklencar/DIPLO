package graph;

/**
 * Created by Vito on 28. 4. 2016.
 */
public class Node {

    private static final String DELIMITER = ":";

    private String name;
    private Node predecesor;

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

    public Node getPredecesor() {
        return predecesor;
    }

    public void setPredecesor(Node predecesor) {
        this.predecesor = predecesor;
    }

    public String getWholeName() {
        return toString();
    }

    @Override
    public String toString() {
        return ((predecesor != null) ? predecesor.toString() + DELIMITER : "") + getName();
    }
}
