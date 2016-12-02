package tests;

import graph.Graph;
import graph.GraphConstructor;
import graph.Node;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

/**
 * Created by Vito on 27. 11. 2016.
 */
public class BasicTests {


    Graph g1;
    Graph g2;
    GraphConstructor constructor = new GraphConstructor();

    int g1Valency = 3;
    int g2Valency = 2;

    @Test
    public void testOfValency() {
        System.out.println("Testing testOfValency");
        g1 = constructor.generateCompleteGraph(g1Valency);
        g2 = constructor.generateCompleteGraph(g2Valency, "");
        constructor.substitute(g1, g2);

        Assert.assertNotNull(g2);
        Assert.assertEquals(g2.getNumberOfVertexes(), g2Valency + 1);

    }

    @Test
    public void testGetVerticesFromOneParent() {
        System.out.println("Testing testGetVerticesFromOneParent");

        String parentName = "par";
        g2 = constructor.generateCompleteGraph(g2Valency, parentName + ":");

        Assert.assertNotNull(g2);
        //System.out.print(g2.toString());
        Assert.assertEquals(g2.getVerticesByParentName(parentName).size(), g2.getNumberOfVertexes());
    }


    @Test
    public void vertexConnectivityTest() {
        System.out.println("Testing testGetVerticesFromOneParent");

        String parentName = "par";
        g2 = constructor.generateCompleteGraph(g2Valency, parentName + ":");

        Assert.assertNotNull(g2);
        //System.out.print(g2.toString());
        Assert.assertEquals(g2.getVerticesByParentName(parentName).size(), g2.getNumberOfVertexes());
    }

    @Test
    public void graphValencyTest() {
        g1 = constructor.generateCompleteGraph(g1Valency);
        Node v1 = null;

        Iterator<Node> iter = g1.getGraph().keySet().iterator();
        if (iter.hasNext()) {
            v1 = iter.next();
        }

        Assert.assertNotNull(v1);
        Assert.assertTrue(constructor.hasSameValency(v1, g1Valency, g1));
    }

    @Test
    public void sameStructureTest() {
        Graph origGraph = constructor.generateCompleteGraph(g1Valency);
        g1 = constructor.generateCompleteGraph(g1Valency);
        g2 = constructor.generateCompleteGraph(g2Valency, "");
        constructor.substitute(g1, g2);

        Assert.assertTrue(constructor.hasSameStructure(g1, g1));
    }



}
