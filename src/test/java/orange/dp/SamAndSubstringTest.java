package orange.dp;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created 3/16/2019
 *
 * @author sjkumar
 */
public class SamAndSubstringTest {

    @Test
    public void topDown() {
        SamAndSubstring s = new SamAndSubstring("16");
        assertThat(s.solve()).isEqualTo(23);
        s = new SamAndSubstring("123");
        assertThat(s.solve()).isEqualTo(164);
        s = new SamAndSubstring("1234");
        assertThat(s.solve()).isEqualTo(1670);
        s = new SamAndSubstring("42");
        assertThat(s.solve()).isEqualTo(48);

    }

    @Test
    public void simple() {
        SamAndSubstring s = new SamAndSubstring("456");
        assertThat(s.solve()).isEqualTo(572);
        s = new SamAndSubstring("4155");
        assertThat(s.solve()).isEqualTo(4851);
        s = new SamAndSubstring("7345");
        assertThat(s.solve()).isEqualTo(8595);
    }

    @Test
    public void hidden() {
        SamAndSubstring s = new SamAndSubstring("972698438521");
        assertThat(s.solve()).isEqualTo(445677619);
    }
}