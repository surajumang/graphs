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
        trie.add("ashish");
//        trie.getAll("s").forEach(System.out::println);
        trie.getAll("as").forEach(System.out::println);
//        trie.getAll("sa").forEach(System.out::println);
//        assertThat()
    }
}