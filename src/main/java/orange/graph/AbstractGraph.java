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

import orange.graph.api.Graph;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.IntStream;

/**
 * Created 8/27/2019
 *
 * @author sjkumar
 */
public abstract class AbstractGraph<T> implements Graph<T> {

    protected Set<Vertex<T>> vertices;
    protected Set<Edge> edges;
    protected Map<Vertex<T>, List<Vertex<T>>> adjacencyList;

    public AbstractGraph(int vertices, int edges) {
        this.vertices = new HashSet<>(vertices);
        // add integers unto (1 to vertices) to the set of vertices.
        IntStream.range(1, vertices+1).forEach(num-> this.vertices.add(new Vertices.SimpleVertex<>( num)));
        this.edges = new HashSet<>(edges);
        this.adjacencyList = new HashMap<>(vertices);
    }

    @Override
    public Set<? extends Vertex<T>> getVertices() {
        return vertices;
    }

    @Override
    public Set<? extends Edge> getEdges() {
        return edges;
    }

    @Override
    public Set<? extends Vertex<T>> getNeighbours(Vertex<T> source) {
        HashSet<Vertex<T>> vertices = new HashSet<>();
        List<Vertex<T>>  cc = adjacencyList.get(source);
        if (cc == null){
            return new HashSet<>();
        }
        vertices.addAll(cc);
        return vertices;
    }

    @Override
    public Optional<Vertex<T>> getVertex(Integer id) {
        for (Vertex<T> vertex: getVertices()) {
            if (vertex.getId().equals(id))
                return Optional.of(vertex);
        }
        return Optional.empty();
    }
}
