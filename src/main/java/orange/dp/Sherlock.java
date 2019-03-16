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
package orange.dp;

import orange.dp.sequences.MaxSubarray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created 3/14/2019
 *
 * @author sjkumar
 */
// sherlock and Array creation.
public class Sherlock {
    /* using only the extremes for each of the blocks.
    * let f(i, d) represent the max value we can get with i numbers and the ith number being d
    *
    * Original problem would be solved by max(f(N, 1), f(N, A[i]))*/
    private List<Integer> input;
    private int length;
    int[][] cache;

    public Sherlock(List<Integer> input) {
        this.input = input;
        length = input.size();
        cache = new int[length][101];
    }

    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int test = Integer.valueOf(reader.readLine());
        while (test > 0){
            test--;
            String  n = reader.readLine();
            new Sherlock(Arrays.stream(reader.readLine().split(" ")).map(Integer::valueOf).collect(Collectors.toList()))
                    .execute();

        }
    }

    int execute(){
//        int val = max(solve(length-1, 1), solve(length-1, input.get(length-1)));
        bottomUp();
        int val = max(cache[length-1][1], cache[length-1][input.get(length-1)]);
        System.out.println(val);
        return val;
    }

    /* *************TOP-DOWN Approach with caching*************************/

    public int solve(int i, int d) {

        //exist in cache
        if (readGracefully(i, d) != 0){
            return  cache[i][d];
        }
        //base case
        if (i == 0){
            return 0;
        }

        int first = solve(i-1, 1) + abs(1,d);
        int second = solve(i-1, input.get(i-1)) + abs(input.get(i-1), d);
        int maxVal = max(first, second);
        cache[i][d] = maxVal;

        return maxVal;
    }

    private int readGracefully(int i, int j){
        if (i<0 || i> input.size() || j<0 || j> 101){
            return 0;
        }
        return cache[i][j];
    }

    private static int max(int a, int b){
        return (a > b) ? a : b;
    }

    private static int abs(int a, int b){
        return (a > b) ? a-b : b-a;
    }

    /* *************BOTTOM-UP Approach *************************/
    //fill the table starting from the beginning
    public void bottomUp(){
        //base cases
        cache[1][1] = 0;
        cache[1][input.get(0)] = 0;

        for (int i = 1; i < input.size(); i++) {
            final int prev = input.get(i-1);
            final int curr = input.get(i);
            cache[i][1] = max(cache[i-1][1], cache[i-1][prev] + abs(prev, 1));
            cache[i][curr] = max(cache[i-1][1] + abs(curr, 1), cache[i-1][prev] + abs(curr, prev));
        }
    }

}
