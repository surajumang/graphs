package orange.dp.sequences;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created 3/23/2019
 *
 * @author sjkumar
 */
public class AbbreviationTest {

    @Test
    public void simple() {
        Abbreviation abbreviation = new Abbreviation("ABc", "ABC");
        assertThat(abbreviation.solve()).isTrue();
    }

    @Test
    public void site() {
        Abbreviation abbreviation = new Abbreviation("daBcd", "ABC");
        assertThat(abbreviation.solve()).isTrue();
    }

    @Test
    public void single() {
        Abbreviation abbreviation = new Abbreviation("a", "A");
        assertThat(abbreviation.solve()).isTrue();
    }

    @Test
    public void notPossibleBecauseOfEarlyUpperCaps() {
        Abbreviation abbreviation = new Abbreviation("Ba", "A");
        assertThat(abbreviation.solve()).isFalse();
        abbreviation = new Abbreviation("ba", "A");
        assertThat(abbreviation.solve()).isTrue();
    }
}