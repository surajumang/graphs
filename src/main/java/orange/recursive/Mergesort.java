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
public class Mergesort {

    public static <T extends Comparable<T>> List<T> mergeSort(List<T> input){
        if (input.size() <= 1){
            return input;
        }
        int half = input.size()/2;
        List<T> left = mergeSort(input.subList(0, half));
        List<T> right = mergeSort(input.subList(half, input.size()));

        return merge(left, right, new ArrayList<>());
    }

    /*
    * Merges tow sorted list a,b into one list and returns a list having size equal to sum(a,b)*/
    public static <T extends Comparable<T>> List<T> merge(List<T> first, List<T> second, List<T> result){
        if (first.size() == 0 && second.size() == 0){
            return result;
        }
        if (first.size() > 0 && second.size() > 0){
            // if first is smaller than second
            if (first.get(0).compareTo(second.get(0)) <= 0){
                result.add(first.get(0));
                first = first.subList(1, first.size());
            }else {
                result.add(second.get(0));
                second = second.subList(1, second.size());
            }
            return merge(first, second, result);
        }
        if (first.size() == 0){
            result.add(second.get(0));
            second = second.subList(1, second.size());
        } else{
            result.add(first.get(0));
            first = first.subList(1, first.size());
        }
        return merge(first, second, result);
    }
}
