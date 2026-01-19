package com.dsalgo.binarysearch;

/**
 * Given input array which is nearly sorted.
 * nearly sorted means that an element that was supposed to be at ith index is at i-1 or i+1 index.
 *
 * Solution -
 * Use binary search.
 * In binary search, we calculate mid and compare it with the element to be searched.
 * As the array is nearly sorted, the element at mid can be at mid, mid-1 or mid+1
 * Hence, while comparing with mid, compare with mid-1 and mid+1 as well.
 *
 * If no match found, then we search for the element in the left or the right half.
 * To search in left half, we will move end. As we already checked that element is not present at mid-1,
 * hence, end can be moved to mid-2.
 *
 * Similarly, start can be moved to mid+2
 */
public class _8_SearchInNearlySortedArray {

    public static int searchIndex(int key, int[] arr) {
        int start = 0, end = arr.length - 1, mid = 0;
        while (start <= end) {
            mid = (start + end) / 2;
            if (arr[mid] == key) {
                return mid;
            } else if (mid-1 >= start && arr[mid - 1] == key) {
                return mid - 1;
            } else if (mid+1 <= end && arr[mid + 1] == key) {
                return mid + 1;
            } else if (arr[mid] < key) {
                start = mid + 2;
            } else {
                end = mid - 2;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {5, 10, 30, 20, 40};
        System.out.println(searchIndex(5, arr));
        System.out.println(searchIndex(10, arr));
        System.out.println(searchIndex(30, arr));
        System.out.println(searchIndex(20, arr));
        System.out.println(searchIndex(40, arr));
    }
}
