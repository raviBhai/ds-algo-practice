package com.dsalgo.arrays;

/*
Given input matrix. It has 0s and 1s.

If a cell has a 0, mark the entire row and col as 0.

Algorithm -



Traverse the matrix and get rows and cols which has zeros.
Later, traverse the matrix again to update all the elements in these rows and cols as zero.


 */

public class _16_MarkZeroInMatrix {

    private static void solve(int[][] matrix) {
        int[] rows = new int[matrix.length];
        int[] cols = new int[matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    rows[i] = 1;    // mark row when a cell is zero
                    cols[j] = 1;    // mark col when a cell is zero
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (rows[i] == 1 || cols[j] == 1) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    private static void print(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 1, 1, 1},
                {1, 0, 1, 1},
                {1, 1, 0, 1},
                {1, 0, 0, 1}
        };

        solve(matrix);
        print(matrix);

    }

}
