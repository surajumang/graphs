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
 * Created 8/28/2019
 *
 * @author sjkumar
 */
public abstract class AbstractTree<T> extends AbstractDirectedAcyclicGraph<T> {
    public AbstractTree(int vertices, boolean cycleCheck) {
        super(vertices, vertices-1, cycleCheck);
    }

    // cycle check is turned off by default.
    public AbstractTree(int vertices){
        this(vertices, false);
    }
}
