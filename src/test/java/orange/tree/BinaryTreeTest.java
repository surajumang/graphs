package orange.tree;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


/**
 * Created 3/31/2019
 *
 * @author sjkumar
 */
public class BinaryTreeTest {

    @Test
    public void inorderTraversal() {
    }

    @Test
    public void inorderTraversal1() {
    }

    @Test
    @SuppressWarnings("unchecked")
    public void insert() {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        binaryTree.insert(3)
                .insert(2)
                .insert(8)
                .insert(1);
        Assertions.assertThat(binaryTree.fillInorder())
                .containsSequence(1,2,3,8);
    }
}