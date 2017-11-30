package com.dsalgo.ctci;

import java.util.Arrays;

public class InverseMatrix {
    public static void main(String[] args) {
        int[][] input = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };

        int[][] result = new int[input.length][input.length];
        int rows = input.length;
        int columns = input.length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result[j][rows - i - 1] = input[i][j];
            }
        }

        System.out.println("Input:");
        print2DArray(input);
        System.out.println("Result:");
        print2DArray(result);
    }

    public static void print2DArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(Arrays.toString(array[i]));
        }
    }
}
