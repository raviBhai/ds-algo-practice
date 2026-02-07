package com.dsalgo.arrays;


// Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

// For example,
// Given [100, 4, 200, 1, 3, 2],
// The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

/*

Approach-1 :
Sort the input array.
Then traverse the sorted array and check if the element can be part of the sequence.


Approach-2 :
https://github.com/kdn251/interviews/blob/master/company/google/LongestConsecutiveSequence.java
Use a set
 */

import java.util.Arrays;

public class _15_LongestConsecutiveSequence {

    private static int solve_withSorting(int[] array) {
        Arrays.sort(array);
        int lastSmallest = Integer.MIN_VALUE;
        int currentLength = 0;
        int maxLength = 0;
        for (int i = 0; i < array.length; i++) {
            if (lastSmallest == Integer.MIN_VALUE) {
                currentLength = 1;
                lastSmallest = array[i];
            } else if (array[i] - 1 == lastSmallest) {
                currentLength++;
                lastSmallest = array[i];
            } else if (array[i] == lastSmallest) {
                // do nothing, just skip this element
            } else if (array[i] != lastSmallest) {
                currentLength = 1;
                lastSmallest = array[i];
            }
            maxLength = Math.max(maxLength, currentLength);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        int[] array = {100, 102, 100, 101, 101, 4, 3, 2, 3, 2, 1, 1, 1, 2};
        System.out.println(solve_withSorting(array));
    }

}
