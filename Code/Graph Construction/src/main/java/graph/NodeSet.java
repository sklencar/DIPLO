package graph;

import java.util.HashSet;

/**
 * Created by Vito on 27. 11. 2016.
 */
public class NodeSet extends HashSet<Node> {

    public NodeSet() {
        super();
    }

    public NodeSet(NodeSet set) {
        super(set);
    }

    public Node getByName(String name) {
        for (Node node : this) {
            if (node.getWholeName().equals(name)) return node;
        }
        return null;
    }
}
