package com.dsalgo.ctci;

import java.util.Arrays;

public class RotateMatrix {
    public static void main(String[] args) {
        int[][] input = {
                {1,2,3,4,5},
                {6,7,8,9,10},
                {11,12,13,14,15},
                {16,17,18,19,20},
                {21,22,23,24,25}
        };

        //rotateWithExtraSpace(input);
        rotateWithoutExtraSpace(input);

    }

    private static void rotateWithoutExtraSpace(int[][] input) {
        int layer;
        int length = input[0].length;
        System.out.println("Input:");
        print2DArray(input);
        for (layer = 0; layer < length / 2; layer++) {
            rotateLayer(layer, length, input);
        }
        System.out.println("Output:");
        print2DArray(input);
    }

    private static void rotateLayer(int layer, int length, int[][] input) {
        int startIndex = layer;
        int endIndex = length - layer - 1;
        int temp;

        for (int i = startIndex; i < endIndex; i++) {
            int offset = i - startIndex;
            //temp = top left
            temp = input[startIndex][i];
            //top left = bottom left
            input[startIndex][i] = input[endIndex - offset][startIndex];
            //bottom left = bottom right
            input[endIndex - offset][startIndex] = input[endIndex][endIndex - offset];
            //bottom right = top right
            input[endIndex][endIndex - offset] = input[i][endIndex];
            //top right = temp
            input[i][endIndex] = temp;
        }
    }

    private static void rotateWithExtraSpace(int[][] input) {
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
