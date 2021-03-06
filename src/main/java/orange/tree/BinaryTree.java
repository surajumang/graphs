package orange.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/*
* Operations supported by the BinaryTree
* 1 ) Insertion in O(log N)
* 2 ) Inorder, pre-order, post-order traversal
* 3 ) Retrieval, or checking the existence of a value.
* 4 ) */
public class BinaryTree<TYPE extends Comparable<TYPE>> {

    private static class Node<T extends Comparable<T>> implements Comparable<Node<T>>{

        Node(T data) {
            this.data = data;
            left = null;
            right = null;
        }

        private T data;
        private Node<T> left;
        private Node<T> right;

        @Override
        public int compareTo(Node<T> o) {
            return this.data.compareTo(o.data);
        }
    }

    private Node<TYPE> root;

    public BinaryTree() {
        root = null;
    }

    @SafeVarargs
    public final void insertAll(TYPE... items){
        insertAll(Arrays.asList(items));
    }

    public void insertAll(List<TYPE> list){
        list.forEach(this::insert);
    }

    /*
    * Simple traversal, will only print the values using the toString method.*/
    public void inorderTraversal(){
        inorderTraversal(System.out::println);
    }
    /*Traversal by invoking the consumer on each of the Element.
    * Example of an inorder traversal.
    *             1
    *          2     3
    *        4  5  6  7
    *  4 5 2 1 6 7 3
    *  left root right*/
    public void inorderTraversal(Consumer<TYPE> consumer){
        inorderTraversal(root, consumer);
    }

    public List<TYPE> fillInorder(){
        final List<TYPE> in = new ArrayList<>();
        Consumer<TYPE> addingInListConsumer = (in::add);
        inorderTraversal(addingInListConsumer);
        return in;
    }

    private void inorderTraversal(Node<TYPE> node, Consumer<TYPE> consumer){
        if (node == null){
            return;
        }
        inorderTraversal(node.left, consumer);
        consumer.accept(node.data);
        inorderTraversal(node.right, consumer);
    }

    public void preOrderTraversal(){
        preOrderTraversal(System.out::println);
    }

    public List<TYPE> fillInPreOrder(){
        final List<TYPE> in = new ArrayList<>();
        Consumer<TYPE> addingInListConsumer = (in::add);
        preOrderTraversal(addingInListConsumer);
        return in;
    }

    private void preOrderTraversal(Consumer<TYPE> consumer) {
        preOrderTraversal(root, consumer);
    }

    public void preOrderTraversal(final Node<TYPE> subRoot, Consumer<TYPE> consumer){
        //root -> left -> right
        if (subRoot == null){
            return;
        }
        consumer.accept(subRoot.data);
        preOrderTraversal(subRoot.left, consumer);
        preOrderTraversal(subRoot.right, consumer);
    }

    /*Start with root of the Tree and find a proper place for the element*/
    public BinaryTree insert(TYPE element){
        /*First entry in the tree*/
        root = insert(root, element);
        return this;
    }

    /*
    * Recursively visit the left or the right child depending on the element and data at root.*/
    private Node<TYPE> insert(Node<TYPE> root, TYPE element){
        if (root == null){
            root = new Node<>(element);
            return root;
        }
        TYPE currentValue = root.data;
        if (element.compareTo(currentValue) <= 0){ //go to left.
            root.left = insert(root.left, element);
        }else {
            root.right = insert(root.right, element);
        }
        return root;
    }

    public boolean find(TYPE element){
        return findNode(root, element) != null;
    }

    private Node<TYPE> findNode(Node<TYPE> root, TYPE element){
        if (root == null){
            return null;
        }
        if (root.data.compareTo(element) == 0){
            return root;
        }
        if (element.compareTo(root.data) <= 0){
            return findNode(root.left, element);
        }
        return findNode(root.right, element);
    }

}
