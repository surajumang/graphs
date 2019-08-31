package orange.graph;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created 8/31/2019
 *
 * @author sjkumar
 */
public class SimpleWeightedGraphTest {

    @Test
    public void simpleWeightTest() {
        SimpleWeightedGraph simpleGraph = new SimpleWeightedGraph(6, 5);
        simpleGraph.addEdge(1,2, 1);
        simpleGraph.addEdge(1,3,12);
        simpleGraph.addEdge(3,4,4);
        simpleGraph.addEdge(3,5,6);
        simpleGraph.addEdge(4,6,5);

        simpleGraph.getEdges().forEach(e-> System.out.print(e.getWeight() + ","));
        System.out.println();
        simpleGraph.bfs(Vertices.SimpleVertex.create(1), (v)-> System.out.print(v.getId() +","));
    }
}