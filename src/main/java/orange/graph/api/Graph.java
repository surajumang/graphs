package orange.graph.api;

import java.util.*;
import java.util.function.Consumer;

/**
 * Created 8/26/2019
 *
 * @author sjkumar
 */

/*
* Abstract representation of a Graph, This should all it's abject to be used in place of a Tree
* and a DAG.
* Basic Requirements from a graph is that we may add/remove edges between vertices,
* The edges could be weighted or unweighted, directed or Undirected. (Separate base classes should take care)
* While querying We will need size of vertices and edges.
* Whether there is an edge between two given vertices*/

/*TODO: 1) Dikstra's SSSP algorithm
*       2) Bipartite matching using Max flow
*       3) Max Flow min cut algorithm
*       4) Change the Max and Min Heap classes to use PriorityQueue
*       5) Implement AcyclicGraph such that it detects if adding an edge will create a cycle.
*       6) All pair shortest path algorithm(Bellman-ford)
*       7) Generate a random graph which can be parsed to create a Graph in Memory.*/

public interface Graph<T> {
    interface Vertex<T>{
        T getData();
        Integer getId();
    }

    interface Edge{
        Integer getFirst();
        Integer getSecond();
    }

    interface WeightedEdge<W> extends Edge, Comparable<WeightedEdge<W>> {
        W getWeight();
    }
    /*Means that the edge is from first to second vertex, and not vice-versa*/
    interface DirectedEdge extends Edge{
    }

    Set<? extends Vertex<T>> getVertices();
    Set<? extends Edge> getEdges();
    void addEdge(Vertex<T> first, Vertex<T> second);
    /*Get all the Immediate Neighbours*/
    Set<? extends Vertex<T>> getNeighbours(Vertex<T> source);

    Optional<Vertex<T>> getVertex(Integer id);

    default void dfs(Vertex<T> source, Consumer<? extends Vertex<T>> vertexConsumer){

    }

    default void bfs(Vertex<T> source, Consumer<Vertex<T>> vertexConsumer){
        LinkedList<Vertex<T>> vertices = new LinkedList<>();
        vertices.add(source);
        BitSet visited = new BitSet(getVertices().size());
        while (!vertices.isEmpty()){
            Vertex<T> current = vertices.pollFirst();
            if (current!= null && !visited.get(current.getId())){
                visited.set(current.getId());
                vertexConsumer.accept(current);
                vertices.addAll(getNeighbours(current));
            }
        }
    }
}
