package com.dsalgo.binarysearch;

public class _13_IndexOfFirstOneInBinarySortedInfiniteArray {

    private static int solve(int[] arr) {
        int start = 0, end = 1;
        int key = 1;
        while (key > arr[end]) {
            start = end;
            end = end * 2;
        }
        return firstOccurrence(arr, start, end, key);
    }

    private static int firstOccurrence(int[] arr, int start, int end, int key) {
        int mid = 0;
        int firstOccurrence = -1;
        while (start <= end) {
            mid = start + (end - start) / 2;
            if (arr[mid] == key) {
                firstOccurrence = mid;
                end = mid - 1;
            } else if (arr[mid] < key) {
                start = mid + 1;
            } else if (arr[mid] > key) {
                end = mid - 1;
            }
        }
        return firstOccurrence;
    }

    public static void main(String[] args) {
        int[] arr = {0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1};    // first 1 is at 10th index
        System.out.println(solve(arr));
    }
}
