package graph;

import exception.DuplicateEdgeException;

import java.util.*;

public class Graph {

    private Map<Node, Set<Node>> graph;


    public Graph() {
        graph = new HashMap<Node, Set<Node>>();
    }

    public Graph(Map<Node, Set<Node>> graph) {
        this.graph = graph;
    }

    List<Node> nodes;
    List<Edge> edges;

    public void addEdges(Iterable<Edge> allEdges) {
        for (Edge edge : allEdges) {
            addEdge(edge.node1, edge.node2);
            addEdge(edge.node2, edge.node1);
        }
    }

    private void addEdge(Node node1, Node node2) {
        if (!graph.containsKey(node1))
            graph.put(node1, (new HashSet<Node>()));
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

    public int getNumberOfVertexes() {
        return graph.size();
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

    public Map<Node, Set<Node>> getGraph() {
        return graph;
    }

    public void setGraph(Map<Node, Set<Node>> graph) {
        this.graph = graph;
    }
}
