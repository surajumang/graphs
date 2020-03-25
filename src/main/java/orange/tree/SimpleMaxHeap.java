package orange.tree;

import java.util.TreeSet;

/**
 * Created 8/28/2019
 *
 * @author sjkumar
 */
public class SimpleMaxHeap<T extends Comparable<T>> implements MaxHeap<T> {

    private final TreeSet<T> treeSet;

    public SimpleMaxHeap() {
        treeSet = new TreeSet<>();
    }

    @Override
    public T extractMax() {
        return treeSet.pollLast();
    }

    @Override
    public T max() {
        return treeSet.last();
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
