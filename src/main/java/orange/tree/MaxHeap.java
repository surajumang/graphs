package orange.tree;

/**
 * Created 8/28/2019
 *
 * @author sjkumar
 */
public interface MaxHeap<T> extends Heap<T> {
    T extractMax();
    T max();
}
