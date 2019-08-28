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

/**
 * Created 8/27/2019
 *
 * @author sjkumar
 */
public class Edges {
    static class SimpleEdge implements Graph.Edge {
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

    static class SimpleDirectedEdge extends SimpleEdge implements Graph.DirectedEdge {

        public SimpleDirectedEdge(Integer first, Integer second) {
            super(first, second);
        }
    }

    static class SimpleWeightedEdge implements Graph.WeightedEdge<Integer> {
        private final SimpleEdge simpleEdge;
        private final Integer weight;

        public SimpleWeightedEdge(Integer first, Integer second, Integer weight) {
            simpleEdge = new SimpleEdge(first, second);
            this.weight = weight;
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
        public Integer getWeight() {
            return weight;
        }
    }
}
