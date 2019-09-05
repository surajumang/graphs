package orange.tree;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created 8/31/2019
 *
 * @author sjkumar
 */
public class AbstractDisjointSetTest {

    @Test
    public void simpleUnion() {
        AbstractDisjointSet<Integer> disjointSet =
                new AbstractDisjointSet<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
        disjointSet.union(1,2);
        disjointSet.union(2,3);
        disjointSet.union(5,2);
        disjointSet.union(6,7);
        disjointSet.union(8,6);
        disjointSet.union(9,4);

        Assertions.assertThat(disjointSet.areConnected(2,5)).isTrue();
        Assertions.assertThat(disjointSet.areConnected(1,3)).isTrue();
        Assertions.assertThat(disjointSet.areConnected(6,7)).isTrue();
        Assertions.assertThat(disjointSet.areConnected(2,4)).isFalse();
        Assertions.assertThat(disjointSet.areConnected(6,4)).isFalse();
        Assertions.assertThat(disjointSet.areConnected(6,9)).isFalse();
        Assertions.assertThat(disjointSet.areConnected(10,9)).isFalse();
        Assertions.assertThat(disjointSet.areConnected(1,9)).isFalse();
        Assertions.assertThat(disjointSet.disjointSetSize()).isEqualTo(4);

        Assertions.assertThat(disjointSet.getMinAndMax()).containsSequence(2,4);
    }
}