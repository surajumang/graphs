/*
 * Copyright 2006-2018 (c) Care.com, Inc.
 * 1400 Main Street, Waltham, MA, 02451, U.S.A.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Care.com, Inc. ("Confidential Information").  You shall not disclose
 * such Confidential Information and shall use it only in accordance with
 * the terms of an agreement between you and CZen.
 */
package orange.dp.sequences;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created 3/23/2019
 *
 * @author sjkumar
 */
public class Abbreviation {
    private String first;   //S
    private String second;  //T
    private Boolean[][]  cache;
    private Map<Pair<String, String>, Boolean> cach = new HashMap<>();

    public Abbreviation(String first, String second) {
        this.first = first;
        this.second = second;
        cache = new Boolean[first.length()][second.length()];
    }

    public static void main(String... args)throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(reader.readLine());
        while (T > 0){
            T--;
            String f = reader.readLine();
            String s = reader.readLine();
            System.out.println(new Abbreviation(f,s).solve() ? "YES" : "NO");
        }
    }

    public boolean solve(){
//        return solveSub(first.length()-1, second.length()-1);
        return solveSub(first, second);
    }

    // index into the instance Strings first and second.
    public boolean solveSub(int s, int t){

        if (readCache(s,t) != null){
            return readCache(s,t);
        }
        //base cases
        if (s<0 && t<0){ //both indices out of bound
            return true;
        }
        // only one of the index goes out of bound
        if (s<0){
            return false;
        }

        if (first.substring(0, s+1).equals(second.substring(0, t+1))){  //already equal
            return true;
        }

        //recursion
        if (readGracefully(s, first) == (readGracefully(t, second))){  //both capital and equal
            return solveSub(s-1, t-1);
        }
        if (Character.isUpperCase(first.charAt(s))){    //can't proceed
            return false;
        }
        // check if [s] can be changed to [t].
        boolean value = false;
        Character current = Character.toUpperCase(readGracefully(s, first));
        if (current.compareTo(readGracefully(t, second)) == 0){
            value = solveSub(s-1, t-1);
        }
        value = value || solveSub(s-1, t);
        addToCache(s,t, value);
        return value;
    }

    private char readGracefully(int index, String str){
        if (index <0 || index >= str.length()){
            return ' ';
        }
        return str.charAt(index);
    }

    private Boolean readCache(int s, int t){
        if (s<0 || t< 0 || s>=cache.length || t>= cache[s].length){
            return null;
        }
        return cache[s][t];
    }
    private void addToCache(int s, int t, Boolean b){
        if (s<0 || t< 0 || s>=cache.length || t>= cache[s].length){
            return;
        }
        cache[s][t] = b;
    }

    public boolean solveSub(String s, String t){
        Boolean b = cach.get(new Pair<>(s,t));
        if (b != null){
            return b;
        }
        //base case
        if (s.equals(t)){
            return true;
        }
        if (s.length() < t.length()){
            return false;
        }
        //recursive cases
        //if first char is equal
        if (readGracefully(getLastReadable(s), s) == readGracefully(getLastReadable(t), t)){
            return solveSub(s.substring(0,getLastReadable(s)), t.substring(0, getLastReadable(t)));
        }
        //first char is capital
        if (Character.isUpperCase(readGracefully(getLastReadable(s), s))){
            return false;
        }
        boolean value = false;
        Character current = Character.toUpperCase(readGracefully(getLastReadable(s), s));
        if (current.compareTo(readGracefully(getLastReadable(t), t)) == 0){
            value = solveSub(s.substring(0, getLastReadable(s)), t.substring(0, getLastReadable(t)));
        }
        value = value || solveSub(s.substring(0, getLastReadable(s)), t);
        cach.put(new Pair<>(s, t), value);
        return value;
    }

    private int getLastReadable(String s){
        return s.length()-1;
    }

    private static class Pair<T, S> {
        T t;
        S s;

        public Pair(T t, S s) {
            this.t = t;
            this.s = s;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair<?, ?> pair = (Pair<?, ?>) o;
            return t.equals(pair.t) &&
                    s.equals(pair.s);
        }

        @Override
        public int hashCode() {
            return Objects.hash(t, s);
        }
    }
}
