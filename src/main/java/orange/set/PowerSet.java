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
    public static <T> Set<List<T>> allSubsequences(final List<T> value, Set<List<T>> set){
        if (value.size() == 1 ){
            Set<List<T>> temp = new HashSet<>(set);
            temp.add(Collections.singletonList(value.get(0)));
            // append this element to the end of every list in the set.
            for (List<T> s: set) {
                List<T> k = new ArrayList<>(s);
                k.add(value.get(0));
                temp.add(k);
            }
            return new HashSet<>(temp);
        }
        // put every element of the list except the first one.
        final List<T> exceptFirst = getSubList(value, 1);
        Set<List<T>> left = allSubsequences(exceptFirst, getCopy(set));

        Set<List<T>> temp = new HashSet<>(); //make this loop go at least once.
        temp.add(Collections.singletonList(value.get(0)));
        for (List<T> s: set) {
            List<T> k = new ArrayList<>(s);
            k.add(value.get(0));
            temp.add(k);
        }
        Set<List<T>> right = allSubsequences(exceptFirst, temp);

        left.addAll(right);
        return left;
    }

    private static <T> Set<List<T>> getCopy(Set<List<T>> prev){
        return new HashSet<>(prev);
    }

    private static<T> List<T> getSubList(List<T> prev, int start){
        List<T> val = new ArrayList<>();
        for (int i = start; i < prev.size(); i++) {
            val.add(prev.get(i));
        }
        return val;
    }
}
