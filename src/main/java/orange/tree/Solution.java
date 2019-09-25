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


import java.io.BufferedReader;
        import java.io.InputStreamReader;
        import java.util.*;
        import java.util.concurrent.atomic.AtomicInteger;
        import java.util.function.Consumer;
        import java.util.stream.Collectors;
        import java.util.stream.IntStream;

/**
 * Created 9/3/2019
 *
 * @author sjkumar
 */
public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int q = Integer.parseInt(reader.readLine());

        int i =0;
        while(i++ < q){
            String [] firstLine = reader.readLine().split(" ");
            Integer N = Integer.parseInt(firstLine[0]);
            Integer E = Integer.parseInt(firstLine[1]);
            int Clib = Integer.parseInt(firstLine[2]);
            int Croad = Integer.parseInt(firstLine[3]);

            SimpleWeightedGraph graph = new SimpleWeightedGraph(N, E);
            int k = 0;
            while (k++ < E){
                String[] vals = reader.readLine().split(" ");
                int from = Integer.parseInt(vals[0]);
                int to = Integer.parseInt(vals[1]);
                graph.addEdge(from, to, 0);
            }
            if (Clib <= Croad){
                System.out.println((long) Clib * N);continue;
            }
            System.out.println(solve(graph, Clib, Croad));
        }
    }

    public static long solve(SimpleWeightedGraph graph, int clib, int croad){
        int N = graph.getVertices().size();
        BitSet bitSet = new BitSet(N);
        AtomicInteger sum = new AtomicInteger(0);
        long finalSum = 0;
        System.err.println("Start");
        for (int k=1; k<=N; k++){
            if (!bitSet.get(k)){ //hasn't been visited
                graph.bfs(Vertices.SimpleVertex.create(k), v -> {
                    bitSet.set(v.getId()); // mark visited
                    sum.incrementAndGet();
                });
                System.err.println("Connected component Size " + sum.get());
                finalSum +=  clib + croad * (sum.get() - 1);
                sum.set(0);
            }
        }
        System.err.println("End");
        return finalSum;
    }


    public abstract static class AbstractWeightedGraph<T, E> extends AbstractGraph<T> implements WeightedGraph<T, E> {

        protected final Map<Vertex<T>, List<WeightedEdge<E>>> edgeMap;

        public AbstractWeightedGraph(int vertices, int edges) {
            super(vertices, edges);
            edgeMap = new HashMap<>(vertices);
        }

        @Override
        public final void addEdge(Vertex<T> first, Vertex<T> second) {
            // no-op
        }

        public abstract void addEdge(Vertex<T> first, Vertex<T> second, E weight);


        private Optional<WeightedEdge<E>> getEdge(Vertex<T> first, Vertex<T> second, int dummy) {
            for (WeightedEdge<E> edge: edgeMap.get(first)) {
                if (edge.getSecond().equals(second.getId()))
                    return Optional.of(edge);
            }
            return Optional.empty();
        }
        @Override
        public Optional<WeightedEdge<E>> getEdge(Vertex<T> first, Vertex<T> second){
            Optional<WeightedEdge<E>> first1 = getEdge(first, second,1);
            Optional<WeightedEdge<E>> second1 = getEdge(first,second,2);
            return (first1.isPresent()) ? first1 : second1;
        }

        @Override
        public Set<? extends WeightedEdge<E>> getEdges() {
            return edgeMap.values()
                    .stream()
                    .flatMap(Collection::stream)
                    .collect(Collectors.toSet());
        }
    }

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

    public interface Graph<T> {
        interface Vertex<T>{
            T getData();
            Integer getId();
        }

        interface Edge{
            Integer getFirst();
            Integer getSecond();
        }

        interface WeightedEdge<W> extends Edge, Comparable<WeightedEdge<W>> {
            W getWeight();
        }
        /*Means that the edge is from first to second vertex, and not vice-versa*/
        interface DirectedEdge extends Edge{
        }

        Set<? extends Vertex<T>> getVertices();
        Set<? extends Edge> getEdges();
        void addEdge(Vertex<T> first, Vertex<T> second);
        /*Get all the Immediate Neighbours*/
        Set<? extends Vertex<T>> getNeighbours(Vertex<T> source);

        Optional<Vertex<T>> getVertex(Integer id);

        default void dfs(Vertex<T> source, Consumer<? extends Vertex<T>> vertexConsumer){

        }

        default void bfs(Vertex<T> source, Consumer<Vertex<T>> vertexConsumer){
            LinkedList<Vertex<T>> vertices = new LinkedList<>();
            vertices.add(source);
            BitSet visited = new BitSet(getVertices().size());
            while (!vertices.isEmpty()){
                Vertex<T> current = vertices.pollFirst();
                if (current!= null && !visited.get(current.getId())){
                    visited.set(current.getId());
                    vertexConsumer.accept(current);
                    vertices.addAll(getNeighbours(current));
                }
            }
        }
    }

    public abstract static class AbstractGraph<T> implements Graph<T> {

        protected Set<Vertex<T>> vertices;
        protected Set<Edge> edges;
        protected Map<Vertex<T>, List<Vertex<T>>> adjacencyList;

        public AbstractGraph(int vertices, int edges) {
            this.vertices = new HashSet<>(vertices);
            // add integers unto (1 to vertices) to the set of vertices.
            IntStream.range(1, vertices+1).forEach(num-> this.vertices.add(new Vertices.SimpleVertex<>( num)));
            this.edges = new HashSet<>(edges);
            this.adjacencyList = new HashMap<>(vertices);
        }

        @Override
        public Set<? extends Vertex<T>> getVertices() {
            return vertices;
        }

        @Override
        public Set<? extends Edge> getEdges() {
            return edges;
        }

        @Override
        public Set<? extends Vertex<T>> getNeighbours(Vertex<T> source) {
            HashSet<Vertex<T>> vertices = new HashSet<>();
            List<Vertex<T>>  cc = adjacencyList.get(source);
            if (cc == null){
                return new HashSet<>();
            }
            vertices.addAll(cc);
            return vertices;
        }

        @Override
        public Optional<Vertex<T>> getVertex(Integer id) {
            for (Vertex<T> vertex: getVertices()) {
                if (vertex.getId().equals(id))
                    return Optional.of(vertex);
            }
            return Optional.empty();
        }
    }

    public static class Edges {
        public static class SimpleEdge implements Graph.Edge {
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

        public static class SimpleDirectedEdge extends SimpleEdge implements Graph.DirectedEdge {

            public SimpleDirectedEdge(Integer first, Integer second) {
                super(first, second);
            }
        }

        public static class SimpleWeightedEdge<W extends Comparable<? super W>> implements Graph.WeightedEdge<W> {
            private final SimpleEdge simpleEdge;
            private final W weight;

            public SimpleWeightedEdge(Integer first, Integer second, W weight) {
                simpleEdge = new SimpleEdge(first, second);
                this.weight = weight;
            }

            public static <W1 extends Comparable<? super W1>> SimpleWeightedEdge<W1> create(Integer first, Integer second, W1 weight){
                return new SimpleWeightedEdge<>(first, second, weight);
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
            public W getWeight() {
                return weight;
            }

            @Override
            public int compareTo(Graph.WeightedEdge<W> o) {
                return weight.compareTo(o.getWeight());
            }
        }
    }

    public static class Vertices {
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

    public static class SimpleWeightedGraph extends AbstractWeightedGraph<Integer, Integer> {

        public SimpleWeightedGraph(int vertices, int edges) {
            super(vertices, edges);
        }

        @Override
        public void addEdge(Vertex<Integer> first, Vertex<Integer> second, Integer weight) {
            Edges.SimpleWeightedEdge<Integer> edge1 = Edges.SimpleWeightedEdge
                    .create(first.getId(), second.getId(), weight);
            edges.add(edge1);
            List<WeightedEdge<Integer>> edgeList1 = new ArrayList<>(Collections.singletonList(edge1));
            edgeMap.merge(first, edgeList1, (l1, l2) -> {l1.addAll(l2); return l1;});

            List<Vertex<Integer>> neighbour = new ArrayList<>(Collections.singletonList(second));
            List<Vertex<Integer>> neighbour2 = new ArrayList<>(Collections.singletonList(first));
            adjacencyList.merge(first, neighbour, (l1, l2) -> {l1.addAll(l2); return l1;});
            adjacencyList.merge(second, neighbour2, (l1, l2) -> {l1.addAll(l2); return l1;});
        }

        public void addEdge(int first, int second, int weight){
            addEdge(Vertices.SimpleVertex.create(first), Vertices.SimpleVertex.create(second), weight);
        }
    }

    public static class AbstractDisjointSet<T extends Comparable<? super T>> implements DisjointSet<T> {

        static class Node<T1 extends Comparable<? super T1>> implements Comparable<Node<T1>>{
            private final Integer id;
            private T1 data;
            private Node<T1> parent;

            public Node(Integer id, T1 data) {
                this.id =id;
                this.data = data;
                parent = this;
            }

//        public Node(T1 data, Node<T1> parent) {
//            this.data = data;
//            this.parent = parent;
//        }

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
                return id.equals(((Node<?>) o).id);
            }

            @Override
            public int hashCode() {
                return Objects.hash(id);
            }
        }

        private List<Node<T>> disjointSet;

        public AbstractDisjointSet(Collection<T> data) {
            // initially all the items are in their own set.
            int id = 1;
            disjointSet = new ArrayList<>();
            //adding a dummy data to help with 1 based indexing

            for (T item: data) {
                disjointSet.add(new Node<>(id++, item));
            }
        }

        @Override
        public int disjointSetSize(){
            Set<Node<T>> distinct = new HashSet<>();
            for (Node<T> node: disjointSet) {
                distinct.add(node.representative());
            }
            return distinct.size();
        }

        public int[] getMinAndMax(){
            Map<Integer, Integer> map = new HashMap<>();
            for (Node<T> node: disjointSet) {
                map.merge(node.representative().id, 1, (i1, i2)-> i1+i2);
            }
            //find the value for lowest key without considering 1s.
            Integer min = Integer.MAX_VALUE;
            Integer max = Integer.MIN_VALUE;
            for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
                if (entry.getValue() == 1) continue;
                if (entry.getValue() < min){
                    min = entry.getValue();
                }
                if (entry.getValue() > max){
                    max = entry.getValue();
                }
            }
            return new int[]{min, max};
        }

        private Node<T> find(Integer id){
            Node<T> node = disjointSet.get(id-1);
            if (node != null){
                return node;
            }
            throw new RuntimeException("Node not found in Set "+ id);
        }

        @Override
        public void union(Integer first, Integer second) {
            Node<T> node1 = find(first).representative();
            Node<T> node2 = find(second).representative();
            if (node1.compareTo(node2) == 0)
                return;
            if (node1.compareTo(node2) < 0)
                node2.setParent(node1);
            else node1.setParent(node2);
        }

        @Override
        public boolean areConnected(Integer first, Integer second) {
            return find(first).representative().compareTo(find(second).representative()) == 0;
        }
    }

    public interface DisjointSet<T extends Comparable<? super T>> {
        void union(Integer first, Integer second);

        boolean areConnected(Integer first, Integer second);

        int disjointSetSize();
    }


}
