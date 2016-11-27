package graph;

import java.util.*;

public class GraphConstructor {

    //todo include Hamiltonian circuit (needed when graph is not regular)


    public Graph generateCompleteGraph(int valency) {
        return generateCompleteGraph(valency, "");
    }

    public Graph generateCompleteGraph(int valency, String prefix) {

        HashSet<Node> nodes = new HashSet<Node>();
        for (int i = 0; i < valency +1; i++) {
            nodes.add(new Node(prefix + i));
        }
        Map<Node, Set<Node>> g = new HashMap<Node, Set<Node>>();
        for (Node node: nodes) {
            HashSet<Node> s = new HashSet<Node>(nodes);
            s.remove(node);
            g.put(node, s);
        }

        return new Graph(g);
    }

    public void substitute(Graph graph, Graph subs) {
        Map<Node, Set<Node>>  newGraph = new  HashMap<Node, Set<Node>>();
        TreeMap<String, Node> map = new TreeMap<String, Node>();
        TreeMap<String, Node> allNodes = new TreeMap<String, Node>();
        TreeMap<String, Map<Node, Set<Node>>> localGraphs = new TreeMap<String, Map<Node, Set<Node>>>();


        for (Node origNode: graph.getGraph().keySet()) {
            map.clear();

            // create vertices for one orig. node
            for (Node newNode: subs.getGraph().keySet()) {
                Node node = new Node(newNode.getName());
                node.setPredecesor(origNode);

                map.put(node.getWholeName(), node);
            }

            Node prev = null;
            for (Node newNode: subs.getGraph().keySet()) {
                Iterator iter = subs.getGraph().get(newNode).iterator();
                HashSet<Node> neighbours = new HashSet<Node>();

                while (iter.hasNext()) {
                    Node nd = (Node) iter.next();
                    neighbours.add(map.get(origNode.getWholeName() + ":" + nd.getWholeName()));
                }

                if (newNode.getName().equals("0")) {
                    String key = origNode.getWholeName() + ":" + (map.size() - 1);
                    Node tmp = map.get(key);
                    neighbours.remove(tmp);
                }

                if (newNode.getName().equals((map.size()-1)+"")) {
                    String key = origNode.getWholeName() + ":0";
                    Node tmp = map.get(key);
                    neighbours.remove(tmp);
                }
                newGraph.put(map.get(origNode.getWholeName() + ":" + newNode.getName()),neighbours);
                prev = newNode;
            }
            localGraphs.put(origNode.getName(), new HashMap<>(newGraph));
            newGraph.clear();
            allNodes.putAll(new HashMap<>(map));
        }

        //makes hamiltonian circuit
        Node first = null;
        Node prev = null;
        Node end = null;
        int lastVertex = -1;

        for (Node origNode: graph.getGraph().keySet()) {
            if (first == null) {
                first = allNodes.get(origNode.getWholeName() + ":" + 0);
            }
            Node start = allNodes.get(origNode.getWholeName() + ":" + 0);
            if (prev != null) {
                end = allNodes.get(prev.getWholeName() + ":" + lastVertex);
                addEdge(start, end, localGraphs.get(origNode.getName()));
                addEdge(end, start, localGraphs.get(prev.getName()));
            } else {
                lastVertex = localGraphs.get(origNode.getName()).size()-1;
            }
            prev = origNode;
            end = allNodes.get(prev.getWholeName() + ":" + lastVertex);
        }

        addEdge(first, end, localGraphs.get(first.getPredecesor().getName()));
        addEdge(end, first, localGraphs.get(end.getPredecesor().getName()));

        // adding subgraphs
        newGraph.clear();
        for (String s: localGraphs.keySet()) {
            newGraph.putAll(localGraphs.get(s));
        }
        graph.setGraph(newGraph);
    }


    private void addEdge(Node curr, Node last, Map<Node, Set<Node>> local) {
        local.get(curr).add(last);
    }

    /**
     * Creates set of vertices connected to a certain vertex in substitonal graph according given its neighbourhood.
     * @param nodes
     * @param map
     * @return
     */
    private HashSet<Node> getNeighbours(Set<Node> nodes, TreeMap<String, Node> map) {
        Iterator iter = nodes.iterator();
        HashSet<Node> set = new HashSet<Node>();
        while (iter.hasNext()) {
            Node nd = (Node) iter.next();
            set.add(map.get(nd.getName()));
        }
        return set;
    }


    public static void main(String[] args) {
        GraphConstructor constructor = new GraphConstructor();
        Graph g1 = constructor.generateCompleteGraph(3);
        System.out.print(g1.toString());
        Graph g2 = constructor.generateCompleteGraph(2,"");
        constructor.substitute(g1, g2);

        System.out.print(g1.toString());
        System.out.println(g1.getNumberOfVertexes());
    }

}
