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

/**
 * Created 8/26/2019
 *
 * @author sjkumar
 */

// todo : should extend DirectedAcyclic graph.
public interface Tree<T> extends Graph<T> {
    Vertex<T> getRoot();
    int getHeight();
    void preOrderTraversal();
    Vertex<T> getLCA();
}
