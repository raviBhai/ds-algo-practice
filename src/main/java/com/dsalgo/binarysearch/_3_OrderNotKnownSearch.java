package com.dsalgo.binarysearch;

/**
 * Given input array. Find an element in the array.
 * The input array is sorted but we do not know if it is in ascending or descending order.
 *
 * Solution -
 * Figure out the array's sort order. This can be done by comparing any 2 elements from the array, for eg, compare 0th and 1st element
 * Based on the sort order, perform binary search
 */
public class _3_OrderNotKnownSearch {

    private static int search(int key, int[] array) {
        if (array[0] < array[1]) {
            return _1_BinarySearch.searchIndex(key, array);
        } else {
            return _2_BinarySearchInReverseSortedArray.searchIndex(key, array);
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(search(2, arr));

        int[] arr2 = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        System.out.println(search(2, arr2));
    }
}
