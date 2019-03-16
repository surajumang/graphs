package orange.dp.sequences;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created 3/15/2019
 *
 * @author sjkumar
 */
public class LargestCommonSubsequenceTest {

    @Test
    public void simplest() {
        int val = LargestCommonSubsequence.solve(Arrays.asList('P','D','K'), Arrays.asList('P','K'));
        assertThat(val).isEqualTo(2);
    }

    @Test
    public void solve() {
        int val = LargestCommonSubsequence.solve(Arrays.asList('C', 'P','A','D','K'), Arrays.asList('P','K'));
        assertThat(val).isEqualTo(2);
    }

    @Test
    public void simple() {
        int val = LargestCommonSubsequence.solve(Arrays.asList('C','P','A','D','K'), Arrays.asList('C','A', 'K'));
        assertThat(val).isEqualTo(3);
    }

    @Test
    public void site() {
        int val = LargestCommonSubsequence.solve(Arrays.asList(1,2,3,4,1), Arrays.asList(3,4,1,2,1,3));
        assertThat(val).isEqualTo(3);
    }

    @Test
    public void siteAlphabet() {
        int val = LargestCommonSubsequence.solve(Arrays.asList('A','B','C','D','A'), Arrays.asList('C','D','A','B','A','C'));
        assertThat(val).isEqualTo(3);
    }
}