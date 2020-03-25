package orange.graph.api;

/**
 * Created 8/26/2019
 *
 * @author sjkumar
 */
// this class should restrict the edges to be Directed,
    // first Type parameter describes the Type of data in the Vertex.
    // Specifying the Kind of Edges will be a better idea.
public interface DirectedGraph<T, E extends Graph.DirectedEdge> extends Graph<T> {

}
