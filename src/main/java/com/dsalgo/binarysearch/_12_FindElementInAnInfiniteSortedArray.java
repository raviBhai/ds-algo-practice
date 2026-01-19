package com.dsalgo.binarysearch;

/**
 * Given input array and a key to be searched in the array.
 * The input array is of infinite length, but it is sorted.
 *
 * Solution -
 * As it is sorted, we will use binary search.
 * For binary search, we need start and end index.
 * Start can be 0, but we need to figure out what will be the value of end index.
 * As the array length is infinite, we cannot have an end index which is towards the end of the array.
 * So, to begin with, take start = 0 and end = 1
 *
 * 1. Take start = 0 and end = 1
 * 2. Check if key is greater than arr[end]
 * 3. To apply binary search, the key has to be in between start and end, hence slowly, using a strategy, we will move start and end forward.
 * 4. If key > arr[end], then move start to end position, and move end to end*2
 * 5. Stop when the key is no longer greater than arr[end].
 * 6. At this time, the key would be in between start and end
 */
public class _12_FindElementInAnInfiniteSortedArray {

    private static int solve(int[] arr, int key) {
        int start = 0, end = 1;
        while (key > arr[end]) {
            start = end;
            end = end * 2;
        }
        return binarySearch(key, arr, start, end);
    }

    private static int binarySearch(int key, int[] arr, int start, int end) {
        int mid = -1;
        while (start <= end) {
            mid = start + (end - start) / 2;
            if (arr[mid] == key) {
                return mid;
            } else if (arr[mid] < key) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(solve(arr, 8));
    }
}
