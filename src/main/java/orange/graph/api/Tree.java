package orange.graph.api;

import orange.graph.api.Graph;

import java.util.function.Consumer;

/**
 * Created 8/26/2019
 *
 * @author sjkumar
 */

/*
* A tree is acyclic with exactly N-1 edges */
public interface Tree<T> extends DirectedAcyclicGraph<T> {
    Vertex<T> getRoot();


    //max distance from root to any leaf
    default int getHeight(){
        return 0;
    }

    void preOrderTraversal(Consumer<Vertex<T>> vertexConsumer);

    Vertex<T> getLCA(Vertex<T> first, Vertex<T> second);
}
