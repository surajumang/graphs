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

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created 8/31/2019
 *
 * @author sjkumar
 */
public class AbstractDisjointSet<T extends Comparable<? super T>> implements DisjointSet<T> {

    static class Node<T1 extends Comparable<? super T1>> implements Comparable<Node<T1>>{
        private T1 data;
        private Node<T1> parent;

        public Node(T1 data) {
            this.data = data;
            parent = this;
        }

        public Node(T1 data, Node<T1> parent) {
            this.data = data;
            this.parent = parent;
        }

        private static <T2 extends Comparable<? super T2>> Node<T2> representative(Node<T2> other){
            if (other.parent == other){
                return other;
            }
            return representative(other.parent);
        }

        private Node<T1> representative(){
            return representative(this);
        }

        private void setParent(Node<T1> other){
            this.parent = other;
        }

        @Override
        public int compareTo(Node<T1> o) {
            return data.compareTo(o.data);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;
            Node<?> node = (Node<?>) o;
            return data.equals(node.data);
        }

        @Override
        public int hashCode() {
            return Objects.hash(data);
        }
    }

    private Collection<T> data;
    private Set<Node<T>> disjointSet;

    public AbstractDisjointSet(Collection<T> data) {
        this.data = data;
        // initially all the items are in their own set.
        disjointSet = data.stream()
                .map(Node::new)
                .collect(Collectors.toSet());
    }

    @Override
    public int disjointSetSize(){
        Set<Node<T>> distinct = new HashSet<>();
        for (Node<T> node: disjointSet) {
            distinct.add(node.representative());
        }
        distinct.forEach(a-> System.out.print(a.data + ", "));
        System.out.println();
        return distinct.size();
    }

    private Node<T> find(T element){
        Node<T> node = new Node<>(element);
        for (Node<T> node1: disjointSet) {
            if (node.compareTo(node1) == 0)
                return node1;
        }
        throw new RuntimeException("Node not found in Set "+ element);
    }

    @Override
    public void union(T first, T second) {
        Node<T> node1 = find(first).representative();
        Node<T> node2 = find(second).representative();
        if (node1.compareTo(node2) == 0)
            return;
        if (node1.compareTo(node2) < 0)
            node2.setParent(node1);
        else node1.setParent(node2);
    }

    @Override
    public boolean areConnected(T first, T second) {
        return find(first).representative().compareTo(find(second).representative()) == 0;
    }
}
