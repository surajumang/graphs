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
package orange.set;

import java.util.*;

/**
 * Created 3/14/2019
 *
 * @author sjkumar
 */
public class PowerSet {

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
    }
}
