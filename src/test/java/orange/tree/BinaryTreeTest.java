package orange.tree;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


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
        int[] values = new int[]{3,2,8,1,6,0};
        binaryTree.insertAll(3,2,8,1,6,0);
//        assertThat(binaryTree.fillInorder())
//                .containsSequence(3,2,8,1,6,0);
        assertThat(binaryTree.find(2)).isTrue();
        assertThat(binaryTree.find(11)).isFalse();
    }

    @Test
    public void preOrderTest() {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        binaryTree.insertAll(Arrays.asList(3,2,8,1));
        binaryTree.preOrderTraversal();
        assertThat(binaryTree.fillInPreOrder())
                .containsExactly(3,2,1,8);
    }

}