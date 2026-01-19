package com.dsalgo.binarysearch;


/**
 * Given input sorted array and an element.
 * Find the count of element in the sorted array.
 *
 * Solution -
 * We can get first and last occurrences of the element from the sorted array.
 * These will be indexes.
 * Then, to get the count, do lastOccurrence - firstOccurrence + 1
 */
public class _5_CountOfElementInSortedArray {
    private static int solve(int key, int[] arr) {
        int first = _4_FirstAndLastOccurrenceOfAnElement.firstOccurrence(key, arr);
        int last = _4_FirstAndLastOccurrenceOfAnElement.lastOccurrence(key, arr);
        return last - first + 1;
    }

    public static void main(String[] args) {
        int[] arr = {2, 4, 10, 10, 10, 18, 20};
        System.out.println(solve(10, arr));
    }
}
