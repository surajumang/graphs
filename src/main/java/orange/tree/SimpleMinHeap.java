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
package orange.tree;

import java.util.TreeSet;

/**
 * Created 8/28/2019
 *
 * @author sjkumar
 */
public class SimpleMinHeap<T extends Comparable<T>> implements MinHeap<T> {

    private final TreeSet<T> treeSet;

    public SimpleMinHeap() {
        treeSet = new TreeSet<>();
    }

    @Override
    public T extractMin() {
        return treeSet.pollFirst();
    }

    @Override
    public T min() {

        return treeSet.first();
    }

    @Override
    public void add(T element) {
        treeSet.add(element);
    }

    @Override
    public boolean isEmpty() {
        return treeSet.isEmpty();
    }
}
