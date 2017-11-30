package com.dsalgo.ctci;

import java.util.Arrays;

public class ZeroMatrix {
    public static void main(String[] args) {
        int[][] matrix = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };
        System.out.println("Input Matrix");
        print2DArray(matrix);

/*
        System.out.println("Convert to zero for 2,2 ");
        convertToZeroMatrix(matrix, 2, 2);
        print2DArray(matrix);
*/

        System.out.println("Convert to zero for 1,3 ");
        convertToZeroMatrix(matrix, 1, 2);
        print2DArray(matrix);

    }

    private static void convertToZeroMatrix(int[][] matrix, int row, int column) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        int k;

        //convert row to zero
        for (k = 0; k < columns; k++) {
            matrix[row][k] = 0;
        }

        k = 0;
        //convert column to zero
        for (k = 0; k < rows; k++) {
            matrix[k][column] = 0;
        }
    }

    public static void print2DArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(Arrays.toString(array[i]));
        }
    }
}
