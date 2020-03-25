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
