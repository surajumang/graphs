package orange.tree;
// https://blog.anudeep2011.com/persistent-segment-trees-explained-with-spoj-problems/

/*Think about removing the Comparable Restriction.
* Take example of TreeMap
* Either a comparator needs to be provider or the default ordering specified by Comparable
* will be used.*/

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;

public class SegmentTree<TYPE extends Comparable<TYPE>> {

    /*A Segment tree can be put inside an Array without needing any pointers.
    * This will be used to support Range Minimum or Maximum Query.
    * Effort will be to provide a way to plug in a generic Querying method rather than using the default
    * Comparable method.
    * It is worthwhile to note here is that once the Tree is made it's structure can;t be changed.*/
    private final List<TYPE> tree;
    private final List<TYPE> array;

    /*Mapper needs to handle null values*/
    private BinaryOperator<TYPE> mapper;
    public final BinaryOperator<TYPE> summationOperator = (first, second) -> first;

//    public SegmentTree(List<TYPE> array) {
//        this(array, null);
//    }

    public SegmentTree(List<TYPE> array, BinaryOperator<TYPE> mapper) {
        this.array = array;
        tree = new ArrayList<>(2*(array.size()+2));
        for (int i = 0; i < 2*(array.size()+2); i++) {
            tree.add(array.get(0));
        }
        this.mapper = mapper;
        buildTree(1, 0, array.size() -1);
    }

    /*Create and point update are identical*/
    private void buildTree(int index, int start, int end){
        //base case, creating a leaf node.
        if (start == end ){
            tree.set(index, array.get(start));
            return;
        }
        int mid = (start+end)/2;
        //build left and right subtree.
        buildTree(2*index, start, mid);
        buildTree(2*index+1, mid+1, end);
        tree.set(index, mapper.apply(tree.get(2*index), tree.get(2*index+1)));

    }

    public TYPE queryRange(int left, int right){
        return query(1, 0, array.size()-1, left, right);
    }

    private TYPE query(int index, int currentLeft, int currentRight, int left, int right){
        //Query is completely outside the current range.
        if (right < currentLeft || left > currentRight){
            return null;
        }
        if (left <= currentLeft && currentRight<= right){
            return tree.get(index);
        }
        int mid = (currentLeft + currentRight) / 2;
        TYPE leftHalf = query(2*index, currentLeft, mid, left, right);
        TYPE rightHalf = query(2*index+1, mid+1, currentRight, left, right);

        return mapper.apply(leftHalf, rightHalf);
    }

    /*Planning to integrate lazy update policy as well.*/
}
