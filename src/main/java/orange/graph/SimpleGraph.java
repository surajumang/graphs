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

import java.util.*;
import java.util.stream.IntStream;

/**
 * Created 8/26/2019
 *
 * @author sjkumar
 */
public class SimpleGraph implements Graph<Integer> {

    public SimpleGraph(int vertices, int edges) {
        this.vertices = new HashSet<>(vertices);
        // add integers unto (1 to vertices) to the set of vertices.
        IntStream.range(1, vertices+1).forEach(num-> this.vertices.add(new SimpleVertex( num)));
        this.edges = new HashSet<>(edges);
        this.adjacencyList = new HashMap<>();
    }
    // adds an edge from first to second only
    public void addEdge(int first, int second){
        Vertex<Integer> v = new SimpleVertex( first);
        Vertex<Integer> v2 = new SimpleVertex( second);
        Edge<Integer> edge = new SimpleEdge(v, v2);
        edges.add(edge);
        List<Vertex<Integer>> neighbour  = new ArrayList<>();
        neighbour.add(v2);

        adjacencyList.merge(v, neighbour, (n1, n2) -> {n1.addAll(n2); return n1;});
    }

    @Override
    public Set<? extends Vertex<Integer>> getVertices() {
        return vertices;
    }

    @Override
    public Set<? extends Edge<Integer>> getEdges() {
        return edges;
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

    static class SimpleVertex implements Vertex<Integer>{
        private final Integer data;
        private final Integer id;

        public SimpleVertex(Integer data, Integer id) {
            this.data = data;
            this.id = id;
        }

        public SimpleVertex(Integer id) {
            this.id = id;
            data = 0;
        }

        @Override
        public Integer getData() {
            return data;
        }

        @Override
        public Integer getId() {
            return id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof SimpleVertex)) return false;
            SimpleVertex that = (SimpleVertex) o;
            return id.equals(that.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }
    static class SimpleEdge<T> implements Edge<T>{
        private final Vertex<T> first;
        private final Vertex<T> second;

        public SimpleEdge(Vertex<T> first, Vertex<T> second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public Vertex<T> getFirst() {
            return first;
        }

        @Override
        public Vertex<T> getSecond() {
            return second;
        }
    }

    private Set<Vertex<Integer>> vertices;
    private Map<Vertex<Integer>, List<Vertex<Integer>>> adjacencyList;
    private Set<Edge<Integer>> edges;

    //perform a DFS starting with the given source
    public void performDFS(int source){
        performDFS(source, new BitSet(vertices.size()));
    }

    private void performDFS(int source, BitSet visited){
        // mark source as visited
        visited.set(source);
        System.out.print(source + ",");
        for (Vertex<Integer> neighbour: getNeighbours(new SimpleVertex(source))) {
            if (!visited.get(neighbour.getId())){
                performDFS(neighbour.getId(), visited);
            }
        }
    }

    public void performBFS(int source){
        List<Vertex<Integer> > vertices = new ArrayList<>();
        BitSet visited = new BitSet(getVertices().size());
        vertices.add(new SimpleVertex(source));

        while (!vertices.isEmpty()){
            // extract and print it if not visited
            int node = vertices.get(0).getId();
            if (!visited.get(node)){
                visited.set(node);
                System.out.print(node + ",");
                vertices.addAll(getNeighbours(new SimpleVertex(node)));
            }
            vertices.remove(0);
        }
    }



}
