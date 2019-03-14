/*
 * Copyright 2006-2010 (c) Care.com, Inc.
 * 1400 Main Street, Waltham, MA, 02451, U.S.A.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Care.com, Inc. ("Confidential Information").  You shall not disclose
 * such Confidential Information and shall use it only in accordance with
 * the terms of an agreement between you and CZen.
 */
package orange.dp.sequences;

import org.omg.CORBA.PRIVATE_MEMBER;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created 3/14/2019
 *
 * @author sjkumar
 */
public class MaxSubarray {
    /*
    * Problem description
    * Given an Array, find the max sum Subarray and the max sum sub-sequence*/

    private List<Integer> input;
    private boolean useBruteForce;

    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int test = Integer.valueOf(reader.readLine());
        while (test > 0){
            test--;
            String  n = reader.readLine();
            new MaxSubarray(Arrays.stream(reader.readLine().split(" ")).map(Integer::valueOf).collect(Collectors.toList()), false)
                    .printBoth();

        }
    }

    public MaxSubarray(List<Integer> input, boolean useBruteForce) {
        this.input = input;
        this.useBruteForce = useBruteForce;
    }

    public void printBoth(){
        System.out.println(getSubArrayMax() + " " + getSubSequenceMax());
    }

    public Integer getSubSequenceMax(){
        int max = Collections.max(input);
        //if there are no positive numbers then return the max thus obtained
        if(input.stream().filter(num-> num>0).count() <= 0){
            return max;
        }
        //
        int sum = 0;
        for (int i = 0; i < input.size(); i++) {
            if (input.get(i) > 0)
                sum += input.get(i);
        }
        return sum;
    }

    public Integer getSubArrayMax(){
        int[] SUM = new int[input.size()];
        SUM[0] = input.get(0);
        for (int i = 1; i < input.size(); i++) {
            int potential = SUM[i-1] + input.get(i);
            //test if adding this to the previous sum will be beneficial
            SUM[i] = (potential > input.get(i)) ? potential : input.get(i);
        }
        return Arrays.stream(SUM).max().getAsInt();
    }

    /* ***************************BRUTE-FORCE WAY*//*
    private static Integer bruteForce(final List<Integer> input){
        return 0;
    }

    *//* ***************************DP WAY*//*
    private static Integer optimized(final List<Integer> input){
        return 0;
    }


    // this generates all possible sub-sequence for the passed list.
    public static Set<List<Integer>> allSubsequences(final List<Integer> value, Set<List<Integer>> set){
        if (value.size() == 1 ){
            Set<List<Integer>> temp = new HashSet<>(set);
            temp.add(Collections.singletonList(value.get(0)));
            // append this element to the end of every list in the set.
            for (List<Integer> s: set) {
                List<Integer> k = new ArrayList<>(s);
                k.add(value.get(0));
                temp.add(k);
            }
            return new HashSet<>(temp);
        }
        // put every element of the list except the first one.
        final List<Integer> exceptFirst = getSubList(value, 1);
        Set<List<Integer>> left = allSubsequences(exceptFirst, getCopy(set));

        Set<List<Integer>> temp = new HashSet<>(); //make this loop go at least once.
        temp.add(Collections.singletonList(value.get(0)));
        for (List<Integer> s: set) {
            List<Integer> k = new ArrayList<>(s);
            k.add(value.get(0));
            temp.add(k);
        }
        Set<List<Integer>> right = allSubsequences(exceptFirst, temp);

        left.addAll(right);
        return left;
    }
    private static Set<List<Integer>> getCopy(Set<List<Integer>> prev){
        return new HashSet<>(prev);
    }

    private static List<Integer> getSubList(List<Integer> prev, int start){
        List<Integer> val = new ArrayList<>();
        for (int i = start; i < prev.size(); i++) {
            val.add(prev.get(i));
        }
        return val;
    }*/
}
