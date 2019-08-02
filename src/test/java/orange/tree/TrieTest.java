package orange.tree;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created 4/2/2019
 *
 * @author sjkumar
 */
public class TrieTest {

    @Test
    public void simpleAddition() {
        Trie trie = new Trie();
        trie.add("suraj");
        trie.add("arun");
        trie.add("sujeet");
        trie.add("sagar");
        trie.add("ashish").add("swastik").add("krishna").add("ayush").add("aayush").add("pranav").add("deepankar");
        trie.add("guru").add("gurudev").add("sayak").add("alita").add("anu").add("ajay").add("priya");

        assertThat(trie.getAll("s")).containsExactlyInAnyOrder("suraj", "sujeet", "sagar", "swastik","sayak");
        assertThat(trie.getAll("as")).containsExactlyInAnyOrder("ashish");
        assertThat(trie.getAll("sa")).containsExactlyInAnyOrder("sagar", "sayak");
        assertThat(trie.getAll("a")).containsExactlyInAnyOrder("ayush", "arun", "ashish", "alita", "anu", "ajay", "aayush");
        assertThat(trie.getAll("pr")).containsExactlyInAnyOrder("priya", "pranav");
        assertThat(trie.getAll("guru")).containsExactlyInAnyOrder("gurudev", "guru");
        assertThat(trie.getAll("deep")).containsExactlyInAnyOrder("deepankar");
        assertThat(trie.getAll("xyz")).containsExactly("");
//        assertThat()
    }
}