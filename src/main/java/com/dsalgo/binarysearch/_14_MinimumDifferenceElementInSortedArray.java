package com.dsalgo.binarysearch;

/**
 * Given a sorted array and a key.
 * Find an element from the array such that the difference between the element from the array and the key is minimum.
 *
 * Example -
 * array - {1, 3, 8, 10, 15}
 * key - 12
 * Return 10
 *
 * array - {1, 3, 8, 10, 15}
 * key - 13
 * Return 15
 *
 * array - {1, 3, 8, 10, 12, 15}
 * key - 12
 * Return 12
 *
 *
 * Solution -
 * We can use floor and ceil to solve this.
 * However, this can be solved without ceil and floor as well.
 *
 * In binary search, we return an element if it is found.
 * And, we return -1 when the element is not found in the sorted array.
 *
 * When the element is not found, the while loop breaks because start would have become greater than end.
 * At this time, start and end have crossed each other.
 * And, they are pointing to the elements which are immediately smaller and greater than the element to be found.
 *
 * For eg,
 * array - 1, 3, 8, 10, 15
 * index - 0, 1, 2,  3,  4
 * initialize start = 0, end = 4, and search for key = 12
 * The while loop in binary search will end when start and end cross each other.
 * At this time, start will be 4 and end will be 3.
 * That is, start is pointing to 15 and end to 10.
 * This will always happen when the element is not found.
 *
 * We can then calculate the minimum difference with start and end to get the output.
 */
public class _14_MinimumDifferenceElementInSortedArray {

    public static int solve(int key, int[] arr) {
        int start = 0, end = arr.length - 1, mid = 0;
        while (start <= end) {
            mid = start + (end - start) / 2;
            if (arr[mid] == key) {
                return arr[mid];
            } else if (arr[mid] < key) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        // the flow reaches here as the key is not found in the array
        int immediatelyGreater = arr[start];
        int immediatelySmaller = arr[end];

        if (Math.abs(immediatelyGreater - key) < Math.abs(immediatelySmaller - key)) {
            return immediatelyGreater;
        } else {
            return immediatelySmaller;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 8, 10, 15};
        System.out.println(solve(12, arr));
        System.out.println(solve(13, arr));
        System.out.println(solve(10, arr));
    }
}
