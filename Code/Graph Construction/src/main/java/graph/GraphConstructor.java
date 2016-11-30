package graph;

import exception.NullNodeException;

import java.util.*;

public class GraphConstructor {

    //todo include Hamiltonian circuit (needed when graph is not regular)


    public Graph generateCompleteGraph(int valency) {
        return generateCompleteGraph(valency, "");
    }

    public Graph generateCompleteGraph(int valency, String prefix) {

        NodeSet nodes = new NodeSet();
        for (int i = 0; i < valency + 1; i++) {
            nodes.add(new Node(prefix + i));
        }
        HashNodeMap g = new HashNodeMap();
        for (Node node : nodes) {
            NodeSet s = new NodeSet(nodes);
            s.remove(node);
            g.put(node, s);
        }

        return new Graph(g);
    }

    public void substitute(Graph graph, Graph subs) {
        HashNodeMap newGraph = new HashNodeMap();
        TreeMap<String, Node> map = new TreeMap<String, Node>();
        TreeMap<String, Node> allNodes = new TreeMap<String, Node>();
        TreeMap<String, HashNodeMap> localGraphs = new TreeMap<String, HashNodeMap>();


        for (Node origNode : graph.getGraph().keySet()) {
            map.clear();

            // create vertices for one orig. node
            for (Node newNode : subs.getGraph().keySet()) {
                Node node = new Node(newNode.getName());
                node.setPredecesor(origNode);

                map.put(node.getWholeName(), node);
            }

            Node prev = null;
            for (Node newNode : subs.getGraph().keySet()) {
                Iterator iter = subs.getGraph().get(newNode).iterator();
                NodeSet neighbours = new NodeSet();

                while (iter.hasNext()) {
                    Node nd = (Node) iter.next();
                    neighbours.add(map.get(origNode.getWholeName() + ":" + nd.getWholeName()));
                }

                if (newNode.getName().equals("0")) {
                    String key = origNode.getWholeName() + ":" + (map.size() - 1);
                    Node tmp = map.get(key);
                    neighbours.remove(tmp);
                }

                if (newNode.getName().equals((map.size() - 1) + "")) {
                    String key = origNode.getWholeName() + ":0";
                    Node tmp = map.get(key);
                    neighbours.remove(tmp);
                }
                newGraph.put(map.get(origNode.getWholeName() + ":" + newNode.getName()), neighbours);
                prev = newNode;
            }
            localGraphs.put(origNode.getName(), new HashNodeMap(newGraph)); // TODO must create new one??
            newGraph.clear();
            allNodes.putAll(new HashMap<>(map));
        }

        //makes hamiltonian circuit
        Node first = null;
        Node prev = null;
        Node end = null;
        int lastVertex = -1;

        for (Node origNode : graph.getGraph().keySet()) {
            if (first == null) {
                first = allNodes.get(origNode.getWholeName() + ":" + 0);
            }
            Node start = allNodes.get(origNode.getWholeName() + ":" + 0);
            if (prev != null) {
                end = allNodes.get(prev.getWholeName() + ":" + lastVertex);
                addEdge(start, end, localGraphs.get(origNode.getName()));
                addEdge(end, start, localGraphs.get(prev.getName()));
            } else {
                lastVertex = localGraphs.get(origNode.getName()).size() - 1;
            }
            prev = origNode;
            end = allNodes.get(prev.getWholeName() + ":" + lastVertex);
        }

        addEdge(first, end, localGraphs.get(first.getPredecesor().getName()));
        addEdge(end, first, localGraphs.get(end.getPredecesor().getName()));

        // adding subgraphs
        newGraph.clear();
        for (String s : localGraphs.keySet()) {
            newGraph.putAll(localGraphs.get(s));
        }

        graph.setGraph(newGraph);
    }


   /* private HashMap<Node, Set<Node>> connectDanglingVertices(HashMap<Node, Set<Node>> map, Integer valency) {

        map

    }*/

    private void addEdge(Node curr, Node last, HashNodeMap local) {
        local.get(curr).add(last);
    }

    /**
     * Creates set of vertices connected to a certain vertex in substitonal graph according given its neighbourhood.
     *
     * @param nodes
     * @param map
     * @return
     */
    private NodeSet getNeighbours(Set<Node> nodes, TreeMap<String, Node> map) {
        Iterator iter = nodes.iterator();
        NodeSet set = new NodeSet();
        while (iter.hasNext()) {
            Node nd = (Node) iter.next();
            set.add(map.get(nd.getName()));
        }
        return set;
    }

    public boolean hasSameStructure(Graph origGraph, Graph newGraph) {
        for (Node origNode: origGraph.getGraph().keySet()) {

        }
        return true;
    }


    /*
    *
    * */


    /*
    * Method counts number of edges to create a new graph according to original graph.
    * Number of edges equals to valency of orig. graph.
    * Should be used after completing Hamiltonian circuit.
    * NOTE: Only for complete graphs!
    * */
    private int getNumberOfFutureEdges(Node newNode, Graph origGraph) throws NullNodeException {
        Node predecesor = newNode.getPredecesor();

        if (predecesor != null) {
            return origGraph.getValencyOfVertexByName(predecesor.getWholeName());
        }
        throw new NullNodeException("Parent of " + newNode.getWholeName() + "doest exist!");
    }



    private boolean vertexConnectivityTest(Node node, List<Node> origNeighbours, Graph newGraph) {

        //Set<Node> newVertices = newGraph.getVerticesByParentName(node.getName()).stream().map(n -> newGraph.)

        for (Node neighbour: origNeighbours) {
            //newGraph.
        }

        return false;
    }

    public static void main(String[] args) {
        GraphConstructor constructor = new GraphConstructor();
        Graph g1 = constructor.generateCompleteGraph(3);
        System.out.print(g1.toString());
        Graph g2 = constructor.generateCompleteGraph(2, "");
        constructor.substitute(g1, g2);

        System.out.print(g1.toString());
        System.out.println(g1.getNumberOfVertexes());
    }

}
