package com.dsalgo.binarysearch;

/**
 * Given input array which has chars in sorted order, not necessarily continuous.
 * Given a char key. Of all the elements in the array which are greater than the key, find the smallest.
 * The key may or may not be present in the array.
 * Even if the key is present, do not return the key, but return the next higher element than key which is present in the array.
 *
 * In _10_FindCeilOfAnElementInSortedArray, when the key was present in the array, the key was returned.
 *
 * Solution -
 * This is similar to finding the ceil of an element.
 * If the key is found in the array, rather than returning it, search for the output in the next half.
 * To do so, when the key is found, move start to mid+1 and then continue the search.
 *
 */
public class _11_NextAlphabetElement {

    private static char solve(char[] arr, char key) {
        int start = 0, end = arr.length - 1, mid = 0;
        char result = ' ';
        while (start <= end) {
            mid = start + (end - start) / 2;
            if (arr[mid] == key) {
                start = mid + 1;
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
        char[] arr1 = {'a', 'c', 'f', 'h'};
        System.out.println(solve(arr1, 'f'));

        char[] arr2 = {'a', 'c', 'h'};
        System.out.println(solve(arr2, 'f'));
    }
}
