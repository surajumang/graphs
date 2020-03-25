package orange.tree;

/**
 * Created 8/28/2019
 *
 * @author sjkumar
 */
public interface MinHeap<T> extends Heap<T> {
    T extractMin();
    T min();
}
