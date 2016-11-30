package graph;

import java.util.HashMap;

/**
 * Created by Vito on 27. 11. 2016.
 */
public class HashNodeMap extends HashMap<Node, NodeSet> {

    public HashNodeMap() {
        super();
    }

    public HashNodeMap(HashNodeMap map) {
        super(map);
    }

    public NodeSet getByName(String name) {
        for (Node node: this.keySet()) {
            if (node.getWholeName().equals(name)) return this.get(node);
        }
        return null;
    }

    public Node getKeyByName(String name) {
        for (Node node: this.keySet()) {
            if (node.getWholeName().equals(name)) return node;
        }
        return null;
    }

}
