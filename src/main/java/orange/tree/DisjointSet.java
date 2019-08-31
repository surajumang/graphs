package orange.tree;

public interface DisjointSet<T extends Comparable<? super T>> {
    void union(T first, T second);

    boolean areConnected(T first, T second);
}
