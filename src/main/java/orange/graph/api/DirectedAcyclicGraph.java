package orange.graph.api;


import java.util.*;

/**
 * Created 8/26/2019
 *
 * @author sjkumar
 */
public interface DirectedAcyclicGraph<T> extends Graph<T> {
    // a delegate/ internal graph, so that we get the existing directed graph properties and
    // we can simply focus on making sure that this doesn't have any cycles.
    // this can be achieved using Disjoint Data structures (throw some Exception at an attempt to
    // add an edge which introduces a cycle).
    default List<? extends Vertex<T>> topologicalSort(){
        return new TopologicalSort<T>().topologicalOrder(this);
    }

//    int inDegree(Vertex<T> vertex);

    class TopologicalSort<T> {
        private List<Vertex<T>> ordered = new ArrayList<>();
        private Graph<T> graph;

        public List<Vertex<T>> topologicalOrder(Graph<T> graph){
            //simply do DFS and postpone the printing of current node until all its children are done.
            //assume the graph edges to be bidirectional.
            this.graph = graph;
            BitSet visited = new BitSet(graph.getVertices().size());
            for (int i = 0; i < graph.getVertices().size(); i++) {
                if (!visited.get(i+1)){
                    graph.getVertex(i+1).ifPresent(v -> dfsLike(v, visited));
                }
            }
            Collections.reverse(ordered);
            return ordered;
        }

        private void dfsLike(Vertex<T> source, BitSet visited){
            // 1) mark source as visited
            // 2) process all the children/neighbour
            // 3) add the current node into the list.
            visited.set(source.getId());
            for (Vertex<T> vertex: graph.getNeighbours(source)) {
                if (!visited.get(vertex.getId())){
                    dfsLike(vertex, visited);
                }
            }
            ordered.add(source);
        }
    }

}
