package orange.tree;

import java.util.Iterator;
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

    @Override
    public void update(T existing, T newer) {
        Iterator<T> iterator = treeSet.iterator();
        boolean removed = false;
        while (iterator.hasNext()){
            T item = iterator.next();
            if (item.compareTo(existing) == 0){
                iterator.remove();
                removed = true; break;
            }
        }
        if (removed){
            add(newer);
        }
    }

    public void remove(T existing) {
        treeSet.removeIf(item -> item.compareTo(existing) == 0);
    }
}
