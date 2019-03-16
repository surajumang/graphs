package orange.set;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;

/**
 * Created 3/15/2019
 *
 * @author sjkumar
 */
public class PowerSetTest {

    @Test
    public void allSubsequences() {
        PowerSet.allSubsequences(Arrays.asList(1,2,3), new HashSet<>()).forEach(System.out::print);
    }

    @Test
    public void strings() {
        PowerSet.allSubsequences(Arrays.asList('a', 'b'), new HashSet<>()).forEach(System.out::print);
    }
}