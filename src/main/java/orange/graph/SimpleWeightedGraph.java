package orange.graph;

import orange.graph.Edges.SimpleWeightedEdge;
import orange.graph.Vertices.SimpleVertex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created 8/31/2019
 *
 * @author sjkumar
 */
public class SimpleWeightedGraph extends AbstractWeightedGraph<Integer, Integer> {

    public SimpleWeightedGraph(int vertices, int edges) {
        super(vertices, edges);
    }

    @Override
    public void addEdge(Vertex<Integer> first, Vertex<Integer> second, Integer weight) {
        SimpleWeightedEdge<Integer> edge = SimpleWeightedEdge
                .create(first.getId(), second.getId(), weight);
        edges.add(edge);
        List<WeightedEdge<Integer>> edgeList = new ArrayList<>(Collections.singletonList(edge));
        edgeMap.merge(first, edgeList, (l1, l2) -> {l1.addAll(l2); return l1;});
        List<Vertex<Integer>> neighbour = new ArrayList<>(Collections.singletonList(second));

        adjacencyList.merge(first, neighbour, (l1, l2) -> {l1.addAll(l2); return l1;});
    }

    public void addEdge(int first, int second, int weight){
        addEdge(SimpleVertex.create(first), SimpleVertex.create(second), weight);
    }
}
