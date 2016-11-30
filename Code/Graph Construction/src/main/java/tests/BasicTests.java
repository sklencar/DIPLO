package tests;

import graph.Graph;
import graph.GraphConstructor;
import org.junit.Assert;
import org.junit.Test;

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
    public void test() {
        System.out.println("Testing testGetVerticesFromOneParent");

        String parentName = "par";
        g2 = constructor.generateCompleteGraph(g2Valency, parentName + ":");

        Assert.assertNotNull(g2);
        //System.out.print(g2.toString());
        Assert.assertEquals(g2.getVerticesByParentName(parentName).size(), g2.getNumberOfVertexes());
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
}
