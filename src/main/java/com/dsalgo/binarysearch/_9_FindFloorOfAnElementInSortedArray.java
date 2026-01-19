package com.dsalgo.binarysearch;

/**
 * Given input sorted array and an element key.
 * Find the greatest number which is smaller than or equal to the input element from the array.
 * The key may or may not be present in the array.
 * If the key is present, return the key, else return the greatest number which is smaller than the key.
 *
 * Example - {1, 2, 3, 4, 8, 10, 10, 12, 19};
 * Find floor of 5
 * Elements in the array which are less than 5 are - 1,2,3,4
 * Greatest from these is 4
 * Output - 4
 *
 * Solution -
 * Use binary search.
 * Calculate mid.
 * If element at mid is less than the input element, it can be a potential result, hence store it in result.
 * If element at mid is greater than the input element, then it can never be the result.
 *
 * After storing the potential result, move right as there can be a greater element than the potential result and less than the input element
 *
 *
 */
public class _9_FindFloorOfAnElementInSortedArray {

    private static int solve(int[] arr, int key) {
        int start = 0, end = arr.length-1, mid = 0, result = -1;
        while (start <= end) {
            mid = start + (end - start) / 2;
            if (arr[mid] == key) {
                return arr[mid];
            } else if (arr[mid] < key) {
                result = arr[mid];
                start = mid + 1;
            } else if (arr[mid] > key) {
                end = mid - 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 8, 10, 10, 12, 19};
        System.out.println(solve(arr, 5));
        System.out.println(solve(arr, 4));
    }
}
