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

import java.util.Optional;
import java.util.Set;

/**
 * Created 8/31/2019
 *
 * @author sjkumar
 */
/*Need to make sure that this graph uses Weighted edges*/
public interface WeightedGraph<T, E> extends Graph<T> {
    /*
    * Give the WeightedEdge between First and Second*/

    Set<? extends WeightedEdge<E>> getEdges();

    /*
    * A very expensive operation and should be overridden to have a constant time function
    * based on the internal Data Structure used.*/

    default Optional<WeightedEdge<E>> getEdge(Vertex<T> first, Vertex<T> second){
        for (WeightedEdge<E> edge : getEdges()) {
            if ((edge.getFirst().equals(first.getId()) && edge.getSecond().equals(second.getId())) ||
                    (edge.getFirst().equals(second.getId()) && edge.getSecond().equals(first.getId()))){
                // found the proper edge.
                return Optional.of(edge);
            }
        }
        return Optional.empty();
    }
}
