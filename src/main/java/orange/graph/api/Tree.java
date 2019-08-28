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

import orange.graph.api.Graph;

import java.util.function.Consumer;

/**
 * Created 8/26/2019
 *
 * @author sjkumar
 */

// todo : should extend DirectedAcyclic graph.
/*
* A tree is acyclic with*/
public interface Tree<T> extends DirectedAcyclicGraph<T> {
    Vertex<T> getRoot();


    //max distance from root to any leaf
    default int getHeight(){
        return 0;
    }

    void preOrderTraversal(Consumer<Vertex<T>> vertexConsumer);

    Vertex<T> getLCA(Vertex<T> first, Vertex<T> second);
}
