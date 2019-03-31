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


import com.sun.org.apache.xpath.internal.operations.Mod;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created 3/16/2019
 *
 * @author sjkumar
 */
public class SamAndSubstring {

    private String number;
    private int[] cache;

    public SamAndSubstring(String number) {
        this.number = number;
        cache = new int[number.length()];
    }

    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String value = reader.readLine();
        new SamAndSubstring(value).solve();
    }

    public long solve(){
//        if (number.length() > 10){
//            throw new RuntimeException("sdjfn");
//        }
        return topDown();
    }

    public int bottomUp(){
        // set the base case.
        cache[0] = 0;
        for (int i = 1; i < number.length(); i++) {
            //character being processed is (i-1).
            int current = Integer.parseInt(number.substring(i-1, i));
            // do modular addition below.
            cache[i] = cache[i-1] + (int)Math.pow(2, i-1) * current + Integer.parseInt(number.substring(0, i));
        }
        return cache[number.length()-1];
    }
    /* You are given a number as a string, let's say f(num ) returns the sum of all the sub-strings
    *   then for any general number "i" we have the following recurrence
    *   ##Base case : f("") = 0;
    *   value = value present at last index in I.
    *   pos = size(i) -1;
    *   f(i) -> f(i-1) + pow(2, pos)* value
    *
    *   Dry run for f("123") -> f("1,2") + 4 * 4  = 21
    *               f("12")  -> f("1") + 2 * 2 = 5
    *               f("1")  = 1 //base
    *               */
    // simple brute force approach which is O(N square)
    public long topDown(){
        Modulo sum = Modulo.createModulo(0);
        Modulo multiplier = Modulo.createModulo(1);
        for (int i = number.length()-1, j = 1; i >= 0; i--, j++) {
            Modulo temp = Modulo.createModulo(Integer.parseInt(number.substring(i, i+1)))
                                .multiply(i + 1)
                                .multiply(multiplier.get());
            multiplier.multiply(10).add(1);

            sum.add(temp.get());
        }
        System.out.println(sum.get());
        return sum.get();
    }

    private static class Modulo{
        private static final int MOD = 1000_000_007;
        private long current = 0;

        private Modulo(long current) {
            current = current % MOD;
            this.current = current;
        }

        public static Modulo createModulo(int current){
            return new Modulo(current);
        }
        public Modulo add(long number){
            number = number % MOD;
            current = (current + number) % MOD;
            return this;
        }
        public Modulo multiply(long number){
            number = number % MOD;
            current = (current * number) % MOD;
            return this;
        }

        public long get(){
            return current;
        }
    }
}
