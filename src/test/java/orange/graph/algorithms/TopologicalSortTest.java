package orange.graph.algorithms;

import orange.graph.api.DirectedAcyclicGraph;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.StringJoiner;

import static org.junit.Assert.*;

/**
 * Created 8/28/2019
 *
 * @author sjkumar
 */
public class TopologicalSortTest {
    @Test
    public void simpleTest() {
        SimpleGraph simpleGraph = new SimpleGraph(6, 5);
//        Assertions.assertThat(simpleGraph.getVertices().)
        simpleGraph.addEdge(1,2);
        simpleGraph.addEdge(1,3);
        simpleGraph.addEdge(3,4);
        simpleGraph.addEdge(3,5);
        simpleGraph.addEdge(4,6);

        StringJoiner stringJoiner = new StringJoiner(",");
        DirectedAcyclicGraph<Integer> dag = simpleGraph;
        dag.topologicalSort().forEach(v -> stringJoiner.add(String.valueOf(v.getId())));
        Assertions.assertThat(stringJoiner.toString()).isEqualTo("1,3,5,4,6,2");
    }
}