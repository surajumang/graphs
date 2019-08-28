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

import java.util.Set;

/**
 * Created 8/26/2019
 *
 * @author sjkumar
 */
public class WeightedDirectedGraph<T> implements Graph<T> {
    /*TODO : We should be good by simply changing the vertex and Edge implementation.
    * Rethink on including both these as objects.*/
    private WeightedGraph<T> weightedGraph;

    @Override
    public Set<? extends Vertex<T>> getVertices() {
        return null;
    }

    @Override
    public Set<? extends Edge> getEdges() {
        return null;
    }

    @Override
    public Set<? extends Vertex<T>> getNeighbours(Vertex<T> source) {
        return null;
    }
}
