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

import java.util.List;
import java.util.Set;

/**
 * Created 8/26/2019
 *
 * @author sjkumar
 */
public interface DirectedAcyclicGraph<T> extends Graph<T> {
    // a delegate/ internal graph, so that we get the existing directed graph properties and
    // we can simply focus on making sure that this doesn't have any cycles.

    List<? extends Vertex<T>> topologicalSort();
    int inDegree(Vertex<T> vertex);

}
