package com.dsalgo.arrays;

/*

Given input sorted array that can have duplicate entries.
Have the unique elements in sorted order at the start of the array.
Do not create new array.
Remaining part of the array can have anything.
Return the size of the array which has the unique sorted elements.
 */

public class _1_RemoveDuplicatesFromSortedArray {

    private static int solve(int[] input) {
        int i = 0;
        for (int j = 1; j < input.length; j++) {
            if (input[j] != input[i]) {
                i++;
                input[i] = input[j];
            }
        }
        return i+1;
    }

    public static void main(String[] args) {
        int[] input = {1,1,2,2,2,3,3,3,3,3};
        System.out.println(solve(input));
    }

}
