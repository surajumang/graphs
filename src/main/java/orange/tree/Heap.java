package orange.tree;

import java.util.Collection;

public interface Heap<T> {
    default void build(Collection<T> elements){
        for (T e: elements) {
            add(e);
        }
    }

    void add(T element);

    boolean isEmpty();
}
