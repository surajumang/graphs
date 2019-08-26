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
public class WeightedGraph<T> implements Graph<T> {
    static class SimpleWeightedEdge implements WeightedEdge<Integer, Integer>{
        private final SimpleGraph.SimpleEdge<Integer> simpleEdge;
        private final Integer weight;

        public SimpleWeightedEdge(Vertex<Integer> first, Vertex<Integer> second, Integer weight) {
            simpleEdge = new SimpleGraph.SimpleEdge<>(first, second);
            this.weight = weight;
        }

        @Override
        public Vertex<Integer> getFirst() {
            return simpleEdge.getFirst();
        }

        @Override
        public Vertex<Integer> getSecond() {
            return simpleEdge.getSecond();
        }

        @Override
        public Integer getWeight() {
            return weight;
        }
    }
    @Override
    public Set<? extends Vertex<T>> getVertices() {
        return null;
    }

    @Override
    public Set<? extends Edge<T>> getEdges() {
        return null;
    }

    @Override
    public Set<? extends Vertex<T>> getNeighbours(Vertex<T> source) {
        return null;
    }
}
