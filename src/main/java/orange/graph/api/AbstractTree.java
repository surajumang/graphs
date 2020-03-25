package orange.graph.api;

/**
 * Created 8/28/2019
 *
 * @author sjkumar
 */
public abstract class AbstractTree<T> extends AbstractDirectedAcyclicGraph<T> {
    public AbstractTree(int vertices, boolean cycleCheck) {
        super(vertices, vertices-1, cycleCheck);
    }

    // cycle check is turned off by default.
    public AbstractTree(int vertices){
        this(vertices, false);
    }
}
