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
package orange.recursive;

import java.util.ArrayList;
import java.util.List;

/**
 * Created 4/15/2019
 *
 * @author sjkumar
 */
public final class Quicksort {
    /*
    * Let the pivot be the first element of the List.
    * After each iteration, all the element less than pivot will be on the left of the pivot and all elements which
    * are greater than equals to pivot will be on the right of the pivot.
    *
    * Split the original array into two part, left and right and repeat the algorithm on them.
    *
    * Base case will be when there is only one item in the List.*/
    public static <T extends Comparable<T>> List<T> quicksort(final List<T> input){
        if (input.size() <= 1){
            return input;
        }
        int k = placePivot(input);
        List<T> left = quicksort(new ArrayList<>(input.subList(0, k)));
        List<T> right = quicksort(new ArrayList<>(input.subList(k+1, input.size())));
        left.add(input.get(k));
        left.addAll(right);
        // check if input is same as left.
        return left;
    }

    /*
    * Mutate the passed in list or return a new List such that the pivot element(last element) is in its proper position.
    * Returns the position the pivot was placed into.*/
    public static <T extends Comparable<T>> int placePivot(final List<T> input){
        if (input.size() == 0){
            throw new IllegalArgumentException("Size can't be zero");
        }
        T pivotElement = input.get(input.size()-1);
        int index = -1;
        for (int i = 0; i < input.size()-1; i++) {
            // if current element is less than pivot, then swap it to index.
            if (input.get(i).compareTo(pivotElement) <= 0){
                index++;
                T temp = input.get(index);
                input.set(index, input.get(i));
                input.set(i, temp);
            }
        }
        //value of index denotes the appropriate poosition for pivot. swap the last element with pivot.
        index++;
        T temp = input.get(index);
        input.set(index, pivotElement);
        input.set(input.size()-1, temp);
        return index;
    }
}
