package orange.tree;

public interface DisjointSet<T extends Comparable<? super T>> {
    void union(Integer first, Integer second);

    boolean areConnected(Integer first, Integer second);

    int disjointSetSize();
}
