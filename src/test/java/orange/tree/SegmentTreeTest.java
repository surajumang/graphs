package orange.tree;

import orange.tree.algorithm.SegmentTree;
import org.junit.Test;

import java.util.Arrays;
import java.util.function.BinaryOperator;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created 3/31/2019
 *
 * @author sjkumar
 */
public class SegmentTreeTest {

    private BinaryOperator<Integer> summingOperator = (first, second) -> {
        if (first==null && second == null){
            return 0;
        }
        if (first == null){
            return second;
        }
        if (second == null){
            return first;
        }

        return first+second;
    };
    private BinaryOperator<Integer> integerMinComparator = (first, second) -> {
        if (first == null && second == null)
            return Integer.MAX_VALUE;
        if (first == null){
            return second;
        }
        if (second ==null){
            return first;
        }
        return (first < second) ? first : second;
    };
    private BinaryOperator<Integer> integerMaxComparator = (first, second) -> {
        if (first == null && second == null)
            return Integer.MIN_VALUE;
        if (first == null){
            return second;
        }
        if (second ==null){
            return first;
        }
        return (first > second) ? first : second;
    };

    @Test
    public void queryRange() {                                          // 0,1,2,3, 4, 5
        SegmentTree<Integer> segmentTree = new SegmentTree<>(Arrays.asList(3,4,1,89,45,78), integerMinComparator);
        assertThat(segmentTree.queryRange(0,5)).isEqualTo(1);
        assertThat(segmentTree.queryRange(3,5)).isEqualTo(45);
        assertThat(segmentTree.queryRange(0,1)).isEqualTo(3);
        assertThat(segmentTree.queryRange(1,4)).isEqualTo(1);

    }

    @Test
    public void summingTest() {
        SegmentTree<Integer> segmentTree = new SegmentTree<>(Arrays.asList(3,4,1,89,45,78), summingOperator);
        assertThat(segmentTree.queryRange(0,5)).isEqualTo(8+45+78+89);
        assertThat(segmentTree.queryRange(3,5)).isEqualTo(45+78+89);
        assertThat(segmentTree.queryRange(0,1)).isEqualTo(7);
        assertThat(segmentTree.queryRange(1,4)).isEqualTo(5+45+89);
    }

    @Test
    public void maxRangeTest() {
        SegmentTree<Integer> segmentTree = new SegmentTree<>(Arrays.asList(3,4,1,89,45,78), integerMaxComparator);
        assertThat(segmentTree.queryRange(0,5)).isEqualTo(89);
        assertThat(segmentTree.queryRange(3,5)).isEqualTo(89);
        assertThat(segmentTree.queryRange(0,1)).isEqualTo(4);
        assertThat(segmentTree.queryRange(1,4)).isEqualTo(89);
    }
}