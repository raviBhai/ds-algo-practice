package com.dsalgo.binarysearch;

public class _1_BinarySearch {

    public static int searchIndex(int key, int[] arr) {
        int start = 0, end = arr.length - 1, mid = 0;
        while (start <= end) {
            mid = (start + end) / 2;
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
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(searchIndex(2, arr));
    }
}
/**
 *
 * In the above solution, mid is calculated as (start + end) / 2
 * This can lead to overflow, wherein the result is bigger than the range of the int.
 * for eg, let's say that int can store upto only 5 integers.
 * Now, while doing binary search, we move start and end, and during one of the operation,
 * if start and end both equals 4, mid will be calculated as (4 + 4) / 2
 * Here, the numerator will become 8, which is greater than the max range of 5.
 * This will lead to invalid result being returned.
 *
 * To avoid this, calculate mid as (start + (end-start)/2 )
 * In this case, when start and end both are 4, mid will be calculated as (4 + (4-4)/2) = (4 + 0/2) = 4
 * Here, nowhere in the calculation, we got a number which is greater than the range of int.
 *
 */