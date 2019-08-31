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

/**
 * Created 8/27/2019
 *
 * @author sjkumar
 */
public class Edges {
    public static class SimpleEdge implements Graph.Edge {
        private final Integer first;
        private final Integer second;

        public SimpleEdge(Integer first, Integer second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public Integer getFirst() {
            return first;
        }

        @Override
        public Integer getSecond() {
            return second;
        }
    }

    public static class SimpleDirectedEdge extends SimpleEdge implements Graph.DirectedEdge {

        public SimpleDirectedEdge(Integer first, Integer second) {
            super(first, second);
        }
    }

    public static class SimpleWeightedEdge<W extends Comparable<? super W>> implements Graph.WeightedEdge<W> {
        private final SimpleEdge simpleEdge;
        private final W weight;

        public SimpleWeightedEdge(Integer first, Integer second, W weight) {
            simpleEdge = new SimpleEdge(first, second);
            this.weight = weight;
        }

        public static <W1 extends Comparable<? super W1>> SimpleWeightedEdge<W1> create(Integer first, Integer second, W1 weight){
            return new SimpleWeightedEdge<>(first, second, weight);
        }

        @Override
        public Integer getFirst() {
            return simpleEdge.getFirst();
        }

        @Override
        public Integer getSecond() {
            return simpleEdge.getSecond();
        }

        @Override
        public W getWeight() {
            return weight;
        }

        @Override
        public int compareTo(Graph.WeightedEdge<W> o) {
            return weight.compareTo(o.getWeight());
        }
    }
}
