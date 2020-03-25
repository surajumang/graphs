package orange.graph.api;

import orange.graph.AbstractGraph;

import java.util.Set;

/**
 * Created 8/28/2019
 *
 * @author sjkumar
 */
public abstract class AbstractDirectedAcyclicGraph<T> extends AbstractGraph<T> implements DirectedAcyclicGraph<T> {

    private boolean cycleCheck;

    public AbstractDirectedAcyclicGraph(int vertices, int edges, boolean cycleCheck){
        super(vertices, edges);
        this.cycleCheck = cycleCheck;
    }

    public AbstractDirectedAcyclicGraph(int vertices, int edges) {
        this(vertices, edges, true);
    }

    /* this will add a directed edge from first to second*/
    @Override
    public void addEdge(Vertex<T> first, Vertex<T> second) {
        //todo
    }

    @Override
    public Set<? extends Vertex<T>> getNeighbours(Vertex<T> source) {
        return null;
    }
}
