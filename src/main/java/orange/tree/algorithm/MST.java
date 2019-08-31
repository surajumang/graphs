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
package orange.tree.algorithm;

import orange.graph.api.Graph;
import orange.tree.MinHeap;
import orange.tree.SimpleMinHeap;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

/**
 * Created 8/25/2019
 *
 * @author sjkumar
 */
public class MST {
    public static <T> void primMST(Graph<T> graph){
        /*
        * Step 1: Initialize the vertex of this graph
        * Step 2: For each edge not in the existing set*/
        // only the edges are supposed to be weighted.

        final int graph_size = graph.getVertices().size();
        MinHeap<Integer> minHeap = new SimpleMinHeap<>();
        minHeap.build(intialize(graph_size));

        BitSet mst = new BitSet(graph_size);

        List<Graph.Vertex<T>> tree = new ArrayList<>(graph_size);

        while (!minHeap.isEmpty()){
            Integer current = minHeap.extractMin();
            // add to list
            Graph.Vertex<T> currentVertex = graph.getVertex(current);
            tree.add(currentVertex);
            // for each neighbour of current change their keys in the Heap.
            for (Graph.Vertex<T> neigh: graph.getNeighbours(currentVertex)) {

            }
        }

    }

    private static List<Integer> intialize(int n){
        // the index represent vertexId and the value is the Key.
        List<Integer> vertices  = new ArrayList<>(n);
        vertices.add(0);
        for (int i =1; i < n; i++){
            vertices.add(Integer.MAX_VALUE);
        }
        return vertices;
    }
}
