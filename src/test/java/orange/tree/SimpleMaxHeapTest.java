package orange.tree;

import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

/**
 * Created 8/28/2019
 *
 * @author sjkumar
 */
public class SimpleMaxHeapTest {
    static class Node implements Comparable<Node>{
        private int value;

        public Node(int value) {
            this.value = value;
        }

        public static Node create(int value){
            return new Node(value);
        }

        public static List<Node> createRandomRange(int size){
            List<Node> nodes = IntStream.range(1, size).mapToObj(Node::new).collect(Collectors.toList());
            Collections.shuffle(nodes);
            return nodes;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(value, o.value);
        }

        @Override
        public String toString() {
            return value + " ";
        }
    }

    @Test
    public void simpleTest() {
        List<Node> nodes = Node.createRandomRange(10);
        nodes.forEach(System.out::print);
        System.out.println();
        MaxHeap<Node> maxHeap = new SimpleMaxHeap<>();
        MinHeap<Node> minHeap = new SimpleMinHeap<>();
        minHeap.build(nodes);
        maxHeap.build(nodes);
        minHeap.update(Node.create(4), Node.create(44));
        while (!maxHeap.isEmpty()){
            System.out.print(maxHeap.extractMax());
        }
        System.out.println();
        while (!minHeap.isEmpty()){
            System.out.print(minHeap.extractMin());
        }

    }
}