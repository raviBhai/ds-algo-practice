package com.dsalgo.binarysearch;

/**
 * Given input matrix which has rows and columns in sorted order.
 * Given a key, find if key is present in the matrix.
 *
 * Solution -
 * Take input array -
 * {10,20,30,40},
 * {15,25,35,45},
 * {27,29,37,48},
 * {32,33,39,50}
 *
 * Start at position of element 40. Init i and j.
 * If arr[i][j] > key, then we move left, i.e. j--
 * If arr[i][j] < key, then we move down, i.e. i++
 * If arr[i][j] == key, return.
 *
 * Keep searching till i and j have valid values.
 *
 * Return -1 if the key is not found
 *
 * Similar algo will work if we start from element 32.
 * If arr[i][j] > key, then we move up, i.e. i--
 * If arr[i][j] < key, then we move right, i.e. j++
 * If arr[i][j] == key, return.
 *
 * This will not work if we start from 10 or 50.
 * With 10, we cannot move anywhere if 10 is greater than key
 * With 50, we cannot move anywhere if 50 is less than key
 */
public class _18_SearchInRowAndColumnSortedMatrix {

    private static String solve(int[][] arr, int key) {
        int i = 0, j = arr[0].length - 1;
        while (i < arr.length && j >= 0) {
            if (arr[i][j] == key) {
                return i + "," + j + " - " + arr[i][j];
            } else if (arr[i][j] > key) {
                j--;
            } else {
                i++;
            }
        }
        return "-1";
    }

    public static void main(String[] args) {
        int[][] arr = {
                {10,20,30,40},
                {15,25,35,45},
                {27,29,37,48},
                {32,33,39,50}
        };
        System.out.println(solve(arr, 29));
    }
}
