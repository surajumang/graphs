package orange.graph;

import orange.graph.algorithms.SimpleGraph;
import org.junit.Test;


/**
 * Created 8/26/2019
 *
 * @author sjkumar
 */
public class SimpleGraphTest {

    @Test
    public void simpleDFS() {
        SimpleGraph simpleGraph = new SimpleGraph(6, 5);
//        Assertions.assertThat(simpleGraph.getVertices().)
        simpleGraph.addEdge(1,2);
        simpleGraph.addEdge(1,3);
        simpleGraph.addEdge(3,4);
        simpleGraph.addEdge(3,5);
        simpleGraph.addEdge(4,6);

        simpleGraph.performDFS(1);
        System.out.println();
        simpleGraph.performBFS(1);
    }
}