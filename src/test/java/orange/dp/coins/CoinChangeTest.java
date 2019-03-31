package orange.dp.coins;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created 3/16/2019
 *
 * @author sjkumar
 */
public class CoinChangeTest {

    @Test
    public void simple() {
        CoinChange c = new CoinChange(4, Arrays.asList(1,2,3));
        assertThat(c.execute()).isEqualTo(4);
    }

    @Test
    public void siteTest() {
        CoinChange c = new CoinChange(10, Arrays.asList(2,5,3,6));
        assertThat(c.execute()).isEqualTo(5);
    }

    @Test
    public void wrongTest() {
        CoinChange c = new CoinChange(245, Arrays.stream("16 30 9 17 40 13 42 5 25 49 7 23 1 44 4 11 33 12 27 2 38 24 28 32 14 50".split(" ")).map(Integer::valueOf).collect(Collectors.toList()));
        assertThat(c.execute()).isEqualTo(64027917156L);
    }
}