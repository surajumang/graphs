package orange.dp.sequences;


import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created 3/14/2019
 *
 * @author sjkumar
 */
public class MaxSubarrayTest {

    private boolean useBruteForce = true;
    private List<Integer> inner = new ArrayList<>();
    private Set<List<Integer>> temp = new HashSet<>();

    @Before
    public void setUp() throws Exception {
        temp.add(inner);
    }

//    // to make sure that all possible subsets are generated.
//    @Test
//    public void simpleTest() {
//        System.out.println(MaxSubarray.allSubsequences(Arrays.asList(1,2,3,4), temp));;
//    }

    @Test
    public void simpleMaxCheck() {
        MaxSubarray maxSubarray = new MaxSubarray(Arrays.asList(2,-1,3), useBruteForce);
        assertThat(maxSubarray.getSubSequenceMax()).isEqualTo(5);
        assertThat(maxSubarray.getSubArrayMax()).isEqualTo(4);
    }

    @Test
    public void simpleMaxCheck2() {
        MaxSubarray maxSubarray = new MaxSubarray(Arrays.asList(-1,-2,-2,-3), useBruteForce);
        assertThat(maxSubarray.getSubSequenceMax()).isEqualTo(-1);
        assertThat(maxSubarray.getSubArrayMax()).isEqualTo(-1);
    }

    @Test
    public void simpleMaxCheck3() {
        MaxSubarray maxSubarray = new MaxSubarray(Arrays.asList(-1,-2,6,-2,-3,4 ), useBruteForce);
        assertThat(maxSubarray.getSubSequenceMax()).isEqualTo(10);
        assertThat(maxSubarray.getSubArrayMax()).isEqualTo(6);
    }

    @Test
    public void webSampleTest() {
        MaxSubarray maxSubarray = new MaxSubarray(Arrays.asList(2, -1, 2, 3, 4, -5), useBruteForce);
        assertThat(maxSubarray.getSubSequenceMax()).isEqualTo(11);
        assertThat(maxSubarray.getSubArrayMax()).isEqualTo(10);
    }
}