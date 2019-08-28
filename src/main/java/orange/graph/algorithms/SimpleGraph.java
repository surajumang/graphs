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
package orange.graph.algorithms;

import orange.graph.AbstractGraph;
import orange.graph.Edges;
import orange.graph.Vertices;
import orange.graph.api.DirectedAcyclicGraph;

import java.util.*;

/**
 * Created 8/26/2019
 *
 * @author sjkumar
 */
public class SimpleGraph extends AbstractGraph<Integer> implements DirectedAcyclicGraph<Integer> {

    private Map<Vertex<Integer>, List<Vertex<Integer>>> adjacencyList;

    public SimpleGraph(int vertices, int edges) {
        super(vertices, edges);
        this.adjacencyList = new HashMap<>();
    }
    // adds an edge from first to second only {making it a directed edge}
    public void addEdge(int first, int second){
        addEdge(new Vertices.SimpleVertex<>(first), new Vertices.SimpleVertex<>(second));
    }

    @Override
    public void addEdge(Vertex<Integer> first, Vertex<Integer> second){
        Edge edge = new Edges.SimpleEdge(first.getId(), second.getId());
        edges.add(edge);
        List<Vertex<Integer>> neighbour  = new ArrayList<>();
        neighbour.add(second);

        adjacencyList.merge(first, neighbour, (n1, n2) -> {n1.addAll(n2); return n1;});
    }

    @Override
    public Set<? extends Vertex<Integer>> getNeighbours(Vertex<Integer> source) {
        HashSet<Vertex<Integer>> vertices = new HashSet<>();
        List<Vertex<Integer>>  cc = adjacencyList.get(source);
        if (cc == null){
            return new HashSet<>();
        }
        vertices.addAll(cc);
        return vertices;
    }

    //perform a DFS starting with the given source
    public void performDFS(int source){
        performDFS(source, new BitSet(vertices.size()));
    }

    private void performDFS(int source, BitSet visited){
        // mark source as visited
        visited.set(source);
        System.out.print(source + ",");
        for (Vertex<Integer> neighbour: getNeighbours(new Vertices.SimpleVertex<>(source))) {
            if (!visited.get(neighbour.getId())){
                performDFS(neighbour.getId(), visited);
            }
        }
    }

    public void performBFS(int source){
        List<Vertex<Integer> > vertices = new ArrayList<>();
        BitSet visited = new BitSet(getVertices().size());
        vertices.add(new Vertices.SimpleVertex<>(source));

        while (!vertices.isEmpty()){
            // extract and print it if not visited
            int node = vertices.get(0).getId();
            if (!visited.get(node)){
                visited.set(node);
                System.out.print(node + ",");
                vertices.addAll(getNeighbours(new Vertices.SimpleVertex<>(node)));
            }
            vertices.remove(0);
        }
    }



}
