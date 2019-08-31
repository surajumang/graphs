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

import orange.graph.Vertices;
import orange.graph.api.Graph;
import orange.graph.api.WeightedGraph;
import orange.tree.AbstractDisjointSet;
import orange.tree.DisjointSet;
import orange.tree.MinHeap;
import orange.tree.SimpleMinHeap;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created 8/25/2019
 *
 * @author sjkumar
 */
public class MST {
    public static <T> void kruskalMST(WeightedGraph<T, Integer> graph){
        /*
        * Step 1: Initialize the vertex of this graph
        * Step 2: For each edge not in the existing set*/
        // only the edges are supposed to be weighted.

        final int graph_size = graph.getVertices().size();
        List<Graph.WeightedEdge<Integer>> edges = new ArrayList<>(graph.getEdges());
        Collections.sort(edges);
        List<Integer> vertices = graph.getVertices()
                .stream()
                .map(Graph.Vertex::getId)
                .collect(Collectors.toList());
        AbstractDisjointSet<Integer> disjointSet = new AbstractDisjointSet<>(vertices);

        for (Graph.WeightedEdge<Integer> wedge: edges) {
            disjointSet.disjointSetSize();
            if (!disjointSet.areConnected(wedge.getFirst(), wedge.getSecond())){
                disjointSet.union(wedge.getFirst(), wedge.getSecond());
                System.out.println(wedge.getFirst() + "->" + wedge.getSecond() + "-->" +wedge.getWeight());
            }else {
                System.out.print("Skipped ");
                System.out.println(wedge.getFirst() + "->" + wedge.getSecond() + "-->" +wedge.getWeight());
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
