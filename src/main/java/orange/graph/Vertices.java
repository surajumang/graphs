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

import java.util.Objects;

/**
 * Created 8/27/2019
 *
 * @author sjkumar
 */
public class Vertices {
    public static class SimpleVertex<T> implements Graph.Vertex<T> {
        private final T data;
        private final Integer id;

        public SimpleVertex(T data, Integer id) {
            this.data = data;
            this.id = id;
        }

        public SimpleVertex(Integer id) {
            this.id = id;
            data = null;
        }

        public static <K> SimpleVertex<K> create(Integer id){
            return new SimpleVertex<>(id);
        }

        @Override
        public T getData() {
            return data;
        }

        @Override
        public Integer getId() {
            return id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof SimpleVertex)) return false;
            SimpleVertex<?> that = (SimpleVertex<?>) o;
            return id.equals(that.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }

}
