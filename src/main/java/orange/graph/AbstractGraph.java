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
 * Created 8/27/2019
 *
 * @author sjkumar
 */
public abstract class AbstractGraph<T> implements Graph<T> {

    protected Set<Vertex<T>> vertices;
    protected Set<Edge> edges;

    public AbstractGraph(int vertices, int edges) {
        this.vertices = new HashSet<>(vertices);
        // add integers unto (1 to vertices) to the set of vertices.
        IntStream.range(1, vertices+1).forEach(num-> this.vertices.add(new Vertices.SimpleVertex<>( num)));
        this.edges = new HashSet<>(edges);
    }
    // adds an edge from first to second only
    public abstract void addEdge(int first, int second);

    @Override
    public Set<? extends Vertex<T>> getVertices() {
        return vertices;
    }

    @Override
    public Set<? extends Edge> getEdges() {
        return edges;
    }
}
