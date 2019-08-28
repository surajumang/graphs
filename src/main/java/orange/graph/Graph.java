/*
 * Copyright 2006-2019 (c) Care.com, Inc.
 * 1400 Main Street, Waltham, MA, 02451, U.S.A.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Care.com, Inc. ("Confidential Information").  You shall not disclose
 * such Confidential Information and shall use it only in accordance with
 * the terms of an agreement between you and CZen.
 */
package orange.graph;

import java.util.Set;

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

public interface Graph<T> {
    interface Vertex<T>{
        T getData();
        Integer getId();
    }

    interface Edge{
        Integer getFirst();
        Integer getSecond();
    }

    interface WeightedEdge<W> extends Edge {
        W getWeight();
    }
    /*Means that the edge is from first to second vertex, and not vice-versa*/
    interface DirectedEdge extends Edge{
    }

    Set<? extends Vertex<T>> getVertices();
    Set<? extends Edge> getEdges();
    /*Get all the Immediate Neighbours*/
    Set<? extends Vertex<T>> getNeighbours(Vertex<T> source);
}
