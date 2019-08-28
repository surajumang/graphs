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
package orange.graph.api;

import orange.graph.AbstractGraph;

import java.util.Set;

/**
 * Created 8/28/2019
 *
 * @author sjkumar
 */
public class AbstractDirectedAcyclicGraph<T> extends AbstractGraph<T> implements DirectedAcyclicGraph<T> {

    public AbstractDirectedAcyclicGraph(int vertices, int edges) {
        super(vertices, edges);
    }

    /* this will add a directed edge from first to second*/
    @Override
    public void addEdge(Vertex<T> first, Vertex<T> second) {

    }

    @Override
    public Set<? extends Vertex<T>> getNeighbours(Vertex<T> source) {
        return null;
    }
}
