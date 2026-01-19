package com.dsalgo.binarysearch;

public class _7_FindAnElementInRotatedSortedArray {

    public static int solve(int[] arr, int key) {
        int pivotIndex = _6_NumberOfTimesASortedArrayIsRotated.solve(arr);
        int result = -1;
        result = binarySearch(key, arr, 0, pivotIndex - 1);
        if (result == -1) {
            result = binarySearch(key, arr, pivotIndex, arr.length - 1);
        }
        return result;
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
        int[] arr = {6, 7, 8, 1, 2, 3, 4, 5};
        System.out.println(solve(arr, 8));
        System.out.println(solve(arr, 3));
        System.out.println(solve(arr, 10));
    }
}
