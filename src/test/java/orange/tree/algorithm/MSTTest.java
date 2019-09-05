package orange.tree.algorithm;

import orange.graph.SimpleWeightedGraph;
import orange.graph.api.WeightedGraph;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created 8/31/2019
 *
 * @author sjkumar
 */
public class MSTTest {
    @Test
    public void simpleTest() {
        SimpleWeightedGraph simpleGraph = new SimpleWeightedGraph(6,5);
        simpleGraph.addEdge(1,2, 1);
        simpleGraph.addEdge(1,3,12);
        simpleGraph.addEdge(3,4,4);
        simpleGraph.addEdge(3,5,6);
        simpleGraph.addEdge(4,6,5);
        simpleGraph.addEdge(2,4,4);

        Assertions.assertThat(MST.kruskalMST(simpleGraph)).isEqualTo(20);
    }
}