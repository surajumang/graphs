package orange.dp;

/*
* Making a stack of coins equal by adding a number to each of them except one.
* https://www.hackerrank.com/challenges/equal/problem
* */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class EqualStacks {

    /*
    A recursive approach which might not be optimal is to make the lowest stack equal to the highest stack
    by marking the highest stack, this will surely make other stacks higher than the currently highest stack.
    This step would require K steps which could be calculated.
    Now since the lowest stack equals the highest stack, there is at least two stacks with the same value.
    The above step can simply be repeated until all the stacks are of equal length.
    Step 0: Check if all the elements are equal, if so return.
    Step 1: Given a list of stack lengths, find the index of the maximum and the minimum.
    Step 2: Find K such that it is the minimum number of operations which will make Min exatly equal to Max.
    Step 3:
     */

    private final List<Integer> stacks;
    private Queue<List<Integer>> previous;
    private Queue<List<Integer>> next;

    public static void main(String... args) throws  Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(reader.readLine());
        while (t > 0){
            t--;
            reader.readLine();
            new EqualStacks(Arrays.stream(reader.readLine().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList())).solve();
        }
    }

    public EqualStacks(List<Integer> stacks) {
        this.stacks = stacks;
        previous = new LinkedList<>();
        previous.add(stacks);
        next = new LinkedList<>();
    }

    int solve(){
        int val = equate(stacks);
        System.out.println(val);
        return val;
    }

    /*
    * At all times we only require Two numbers the min and the max, we don't need to update the whole array,
    * Another thing is that the Min in not changing, Max of previous operation is Min of next operation.
    * */
    static int equate(List<Integer> stacks){
//        int max = Collections.max(stacks);
        int min = Collections.min(stacks);

       int first = process(stacks, min);
       int second = process(stacks, min-1);
       int third = process(stacks, min-2);

        return Collections.min(Arrays.asList(first, second, third));
    }

    static int process(List<Integer> stacks, int target){
        // target must be greater that equal to zero.
//        if (target < 0){
//            return Integer.MAX_VALUE;
//        }
        // one loop would suffice for the operation.

        int operation = 0;
        for (int i = 0; i < stacks.size(); i++) {
            operation += getNumberOfOperations(target, stacks.get(i));
        }
        return operation;
    }

    /*
    * An approach to use BFS to find a node which is at the minimum distance from root.*/
    int bfs(){
        if (allEqual(stacks)){
            return 0;
        }
        int count = 1;
        // for each item in previous Queue, add all its children to the next queue.
        while(true){
            while (!previous.isEmpty()){
                List<Integer> current = previous.remove();
                List<List<Integer>> allChildren = getAllChildren(current);
                for (int i = 0; i < allChildren.size(); i++) {
                    if (allEqual(allChildren.get(i))){
//                        System.out.println(count);
                        return count;
                    }
                    next.add(allChildren.get(i));
                }
            }
            //now previous is empty, so reverse the process.
            previous = next;
            next = new LinkedList<>();
            count++;
        }
        // sw
    }


    static List<List<Integer>> getAllChildren(List<Integer> stacks){
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < stacks.size(); i++) {
            result.add(addToAllExcept(stacks, 1, i));
            result.add(addToAllExcept(stacks, 2, i));
            result.add(addToAllExcept(stacks, 5, i));
        }
        return result;
    }


    static List<Integer> addToAllExcept(List<Integer>stacks, int quantity, int skipIndex){
        List<Integer> newList = new ArrayList<>();
        for (int i = 0; i < stacks.size(); i++) {
            if (i == skipIndex){
                newList.add(stacks.get(i));
                continue;
            }
            newList.add(stacks.get(i) + quantity);
        }
        return newList;
    }

    static boolean allEqual(List<Integer> stacks){
        // stack of zero length should return true;
        int first = stacks.get(0);
        boolean result = true;
        for (int i = 1; i < stacks.size(); i++) {
            if (stacks.get(i) != first){
                return false;
            }
        }
        return result;
    }

    static int getNumberOfOperations(int from, final int to){
        //add the highest multiple of five you can to 'from'.
        int fives = (to - from) / 5;
        from = from + fives*5;
        int twos = (to - from) / 2;
        from = from + twos*2;
        int ones = (to-from);
        return fives + twos + ones;
    }
}
