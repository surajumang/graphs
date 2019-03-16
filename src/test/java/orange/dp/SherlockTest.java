package orange.dp;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created 3/15/2019
 *
 * @author sjkumar
 */
public class SherlockTest {

    @Test
    public void simple() {
        Sherlock sherlock = new Sherlock(Arrays.asList(5,8,2));
        assertThat(sherlock.execute()).isEqualTo(14);
    }

    @Test
    public void shorter() {
        Sherlock sherlock = new Sherlock(Arrays.asList(5,3));
        assertThat(sherlock.execute()).isEqualTo(4);
    }

    @Test
    public void simple2() {
        Sherlock sherlock = new Sherlock(Arrays.asList(5,5,5,5));
        assertThat(sherlock.execute()).isEqualTo(12);
    }

    @Test
    public void simple2s() {
        Sherlock sherlock = new Sherlock(Arrays.asList(6,4,6));
        assertThat(sherlock.execute()).isEqualTo(10);
    }

    @Test
    public void simple3() {
        Sherlock sherlock = new Sherlock(Arrays.asList(2,4,4,8,9,2));
        assertThat(sherlock.execute()).isEqualTo(23);
    }

    @Test
    public void siteTest() {
        Sherlock sherlock = new Sherlock(Arrays.asList(10,1,10,1,10));
        assertThat(sherlock.execute()).isEqualTo(36);
    }
}