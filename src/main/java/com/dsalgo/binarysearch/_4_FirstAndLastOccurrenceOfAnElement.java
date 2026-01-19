package com.dsalgo.binarysearch;

public class _4_FirstAndLastOccurrenceOfAnElement {

    public static int firstOccurrence(int key, int[] arr) {
        int start = 0, end = arr.length - 1, mid = 0;
        int firstOccurrence = -1;
        while (start <= end) {
            mid = start + (end - start) / 2;
            if (arr[mid] == key) {
                firstOccurrence = mid;
                end = mid - 1;
            } else if (arr[mid] < key) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return firstOccurrence;
    }

    public static int lastOccurrence(int key, int[] arr) {
        int start = 0, end = arr.length - 1, mid = 0;
        int lastOccurrence = -1;
        while (start <= end) {
            mid = start + (end - start) / 2;
            if (arr[mid] == key) {
                lastOccurrence = mid;
                start = mid + 1;
            } else if (arr[mid] < key) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return lastOccurrence;
    }

    public static void main(String[] args) {
        int[] arr = {2, 4, 10, 10, 10, 18, 20};
        System.out.println("first - " + firstOccurrence(10, arr));
        System.out.println("last - " + lastOccurrence(10, arr));
    }
}
