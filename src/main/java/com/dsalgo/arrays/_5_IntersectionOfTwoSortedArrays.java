package com.dsalgo.arrays;

/*

Given 2 sorted arrays that can have duplicate elements.
Return the intersection of these two arrays.

array_1 - 1,2,2,3,3,4,5,6
array_2 - 2,3,3,5,6,6,7

There are two 3's in both the arrays, so both can be part of the output.

result - 2,3,3,5,6

Algorithm -
Have two pointers, i and j, one each at the start of both the arrays.
If i and j are equal, add to result.
If i < j, move i ahead, else move j ahead

 */

import java.util.ArrayList;
import java.util.List;

public class _5_IntersectionOfTwoSortedArrays {

    private static List<Integer> solve(int[] array1, int[] array2) {
        List<Integer> result = new ArrayList<>();
        int i = 0, j = 0;
        while (i < array1.length && j < array2.length) {
            if (array1[i] == array2[j]) {
                result.add(array1[i]);
                i++;
                j++;
            } else if (array1[i] < array2[j]) {
                i++;
            } else if (array1[i] > array2[j]) {
                j++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] array_1 = {1, 2, 2, 3, 3, 4, 5, 6};
        int[] array_2 = {2, 3, 3, 5, 6, 6, 7};

        System.out.println(solve(array_1, array_2));
    }
}
