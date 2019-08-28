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

/**
 * Created 8/26/2019
 *
 * @author sjkumar
 */
// this class should restrict the edges to be Directed,
    // first Type parameter describes the Type of data in the Vertex.
    // Specifying the Kind of Edges will be a better idea.
public interface DirectedGraph<T, E extends Graph.DirectedEdge> extends Graph<T> {

}
