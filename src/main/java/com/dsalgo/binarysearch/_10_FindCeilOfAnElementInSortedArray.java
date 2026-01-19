package com.dsalgo.binarysearch;

public class _10_FindCeilOfAnElementInSortedArray {

    private static int solve(int[] arr, int key) {
        int start = 0, end = arr.length-1, mid = 0, result = -1;
        while (start <= end) {
            mid = start + (end - start) / 2;
            if (arr[mid] == key) {
                return arr[mid];
            } else if (arr[mid] < key) {
                start = mid + 1;
            } else if (arr[mid] > key) {
                result = arr[mid];
                end = mid - 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 8, 10, 10, 12, 19};
        System.out.println(solve(arr, 5));
        System.out.println(solve(arr, 9));
        System.out.println(solve(arr, 10));
    }
}
