package graph;

import exception.DuplicateEdgeException;

import java.util.Set;
import java.util.stream.Collectors;

public class Graph {

    public Graph() {
        graph = new HashNodeMap();
    }


    public Graph(HashNodeMap graph) {
        this.graph = graph;
    }

    private HashNodeMap graph;

    public void addEdges(Iterable<Edge> allEdges) {
        for (Edge edge : allEdges) {
            addEdge(edge.node1, edge.node2);
            addEdge(edge.node2, edge.node1);
        }
    }

    private void addEdge(Node node1, Node node2) {
        if (!graph.containsKey(node1))
            graph.put(node1, (new NodeSet()));
        Set<Node> set = graph.get(node1);
        if (set.contains(node2))
            try {
                throw new DuplicateEdgeException("Duplicate " + node1 + " to " + node2);
            } catch (DuplicateEdgeException e) {
                e.printStackTrace();
            }
        set.add(node2);
    }

    public void addNeighbour(Node node, Node newNeighbour) {
        getGraph().get(node).add(newNeighbour);
    }

    public boolean isComplete()
    {
        int count = graph.size();
        for (Set<Node> set: graph.values())
            if (count != (set.size() + 1))
                return false;
        return true;
    }

    public Set<Node> getAdjuscent (Node node) {
        if (!graph.containsKey(node))
            throw new IllegalStateException("The graph doesnt contain " + node);
        return graph.get(node);
    }

    public Set<Node> getVerticesByParentName(String parentName) {
        return graph.keySet().stream().filter(node -> node.getWholeName().startsWith(parentName)).collect(Collectors.toSet());
    }

    public int getNumberOfVertexes() {
        return graph.size();
    }

    public int getValencyOfVertex(Node node) {
        return graph.get(node).size();
    }

    public int getValencyOfVertexByName(String name) {
        return graph.getByName(name).size();
    }

    public boolean hasEdge(String n1, String n2) {
        for (Node node: graph.keySet()) {
            if (node.getWholeName() == n1) {
                return (graph.get(node).stream().filter(n -> n.getWholeName() == n2).count() == 1);
            }
        }

        return false;
    }

    public boolean hasEdge(Node n1, Node n2) {
        return (graph.get(n1).contains(n2));
    }

    public boolean hasInNeighbourhood(Node node, Node neighbour) {
        return graph.get(node).contains(neighbour);
    }

    public boolean hasInNeighbourhood(String nodeName, String neighbourName) {
        return graph.getByName(nodeName).getByName(neighbourName) != null;
    }

    @Override
    public String toString() {
        String result = "Graph{}\n";
        for (Node nd: graph.keySet()) {
            result += nd.toString() + ":[";
            for(Node n: graph.get(nd)) {
                result += n.toString() + ",";
            }
            result += "]\n";
        }
        return result;
    }

    public HashNodeMap getGraph() {
        return graph;
    }

    public void setGraph(HashNodeMap graph) {
        this.graph = graph;
    }
}
