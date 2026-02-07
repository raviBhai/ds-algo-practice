package com.dsalgo.arrays;


import java.util.ArrayList;
import java.util.List;

/*

Given 2 sorted arrays. The arrays might have duplicate values.
Create a union of these 2 sorted arrays such that the union has unique elements in sorted order.

array_1 - 1,1,2,3,4,5
array_2 - 2,3,4,4,5,6

output - 1,2,3,4,5,6

Algorithm -

Brute force -
Take a TreeSet which will maintain unique elements as well as insertion order.
Then add all elements from both the arrays to this set.

Optimized solution -
Take 2 pointers - i and j for array_1 and array_2

Start iterating from the start.

Whichever is smaller from i and j can go to the output result list.
However, to avoid duplicates, check if the last element in the output list is not the same as the element that is about to be added.
Increment i or j whichever was smaller.

It is possible that the input arrays have different lenghts, and that i or j might reach the end.
To take care of this situation, run a while loop to add remainning elements to the output. Do this for both the arrays.

 */
public class _4_UnionOfTwoSortedArrays {

    private static List<Integer> solve(int[] array1, int[] array2) {

        List<Integer> result = new ArrayList<>();

        int i = 0, j = 0;
        while (i < array1.length && j < array2.length) {
            if (array1[i] <= array2[j]) {
                if (result.isEmpty() || result.get(result.size() - 1) != array1[i]) {
                    result.add(array1[i]);
                }
                i++;
            } else if (array1[i] > array2[j]) {
                if (result.isEmpty() || result.get(result.size() - 1) != array2[j]) {
                    result.add(array2[j]);
                }
                j++;
            }
        }

        while (i < array1.length) {
            if (result.isEmpty() || result.get(result.size() - 1) != array1[i]) {
                result.add(array1[i]);
            }
            i++;
        }

        while (j < array2.length) {
            if (result.isEmpty() || result.get(result.size() - 1) != array2[j]) {
                result.add(array2[j]);
            }
            j++;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] array_1 = {1, 1, 2, 3, 4, 5};
        int[] array_2 = {2, 3, 4, 4, 5, 6};

        System.out.println(solve(array_1, array_2));

    }
}
