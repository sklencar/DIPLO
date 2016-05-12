/*
package graph;

import java.util.*;

public class CopyGraphConstructor {


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

    public Graph replaceGraphBy(Graph graph, Graph repl) {
        Map<Node, Set<Node>> newGraph = new HashMap<Node, Set<Node>>();
        for (Node n: graph.getGraph().keySet()) {
            for (Node tn : repl.getGraph().keySet()) {
                newGraph.put(new Node(n.getName()+","+tn.getName()), new HashSet(repl.getGraph().get(tn)));
            }
            graph.getGraph().remove(n);
        }

        return graph;
    }

    public void substitute(Graph graph, Graph subs) {
        Map<Node, Set<Node>>  newGraph = new  HashMap<Node, Set<Node>>();
        Map<Node, Set<Node>>  mapper = new  HashMap<Node, Set<Node>>();

        for (Node origNode: graph.getGraph().keySet()) {
            Set innerNeighbours = new HashSet<Node>();
            Set m = new HashSet<Node>();

            for (Node newNode: subs.getGraph().keySet()) {
                //Node node = new Node(origNode.getName() + DELIMITER + newNode.getName());
                Node node = new Node(newNode.getName());
                node.setParent(origNode);

                m.add(node);
                innerNeighbours.add(node);
            }
            mapper.put(origNode,m);

            Iterator iter = innerNeighbours.iterator();
            while (iter.hasNext()) {
                HashSet<Node> set = new HashSet<Node>(innerNeighbours);
                Node nd = (Node) iter.next();
                set.remove(nd);
                newGraph.put(nd,set);
            }
        }
        //renewConnections();
        graph.setGraph(newGraph);
    }

    private void renewConnections(Map<Node, Set<Node>>  newGraph, Graph  graph, Map<Node, Set<Node>> mapper) {
        for (Node origNode: mapper.keySet()) {
            Object[] adjuscent = graph.getAdjuscent(origNode).toArray();
            for (int i = 0; i < adjuscent.length; i++) {


            }
        }


    }

    private  Map<Node, Set<Node>> substituteVertexForGraph(Graph graph, Node node, Graph subs) {
        Object[] adjuscent = graph.getAdjuscent(node).toArray();
        Map<Node, Set<Node>>  newOnes = new  HashMap<Node, Set<Node>>();
        int i = 0;

        for (Node n : subs.getGraph().keySet()) {
            Set tmp = new HashSet();
            for (Node nn: subs.getGraph().get(n)) {
                tmp.add(new Node(nn.getName()));
            }
            newOnes.put(new Node(node.getName()), tmp);
            newOnes.get(n).add((Node)adjuscent[i++]);
        }
        return newOnes;
    }


    public static void main(String[] args) {
        CopyGraphConstructor constructor = new CopyGraphConstructor();
        Graph g1 = constructor.generateCompleteGraph(4);
        System.out.print(g1.toString());
        Graph g2 = constructor.generateCompleteGraph(3,"t");
        constructor.substitute(g1, g2);

        System.out.print(g1.toString());
        System.out.println(g1.getNumberOfVertexes());
    }



}
*/
