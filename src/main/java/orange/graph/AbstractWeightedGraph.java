package orange.graph;

import orange.graph.api.WeightedGraph;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created 8/31/2019
 *
 * @author sjkumar
 */
public abstract class AbstractWeightedGraph<T, E> extends AbstractGraph<T> implements WeightedGraph<T, E> {

    protected final Map<Vertex<T>, List<WeightedEdge<E>>> edgeMap;

    public AbstractWeightedGraph(int vertices, int edges) {
        super(vertices, edges);
        edgeMap = new HashMap<>(vertices);
    }

    @Override
    public final void addEdge(Vertex<T> first, Vertex<T> second) {
        // no-op
    }

    public abstract void addEdge(Vertex<T> first, Vertex<T> second, E weight);


    private Optional<WeightedEdge<E>> getEdge(Vertex<T> first, Vertex<T> second, int dummy) {
        for (WeightedEdge<E> edge: edgeMap.get(first)) {
            if (edge.getSecond().equals(second.getId()))
                return Optional.of(edge);
        }
        return Optional.empty();
    }
    @Override
    public Optional<WeightedEdge<E>> getEdge(Vertex<T> first, Vertex<T> second){
        Optional<WeightedEdge<E>> first1 = getEdge(first, second,1);
        Optional<WeightedEdge<E>> second1 = getEdge(first,second,2);
        return (first1.isPresent()) ? first1 : second1;
    }

    @Override
    public Set<? extends WeightedEdge<E>> getEdges() {
        return edgeMap.values()
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }
}
