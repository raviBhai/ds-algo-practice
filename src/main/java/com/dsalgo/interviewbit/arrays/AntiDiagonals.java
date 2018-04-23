package com.dsalgo.interviewbit.arrays;

import java.util.Arrays;

public class AntiDiagonals {
    public static void main(String[] args) {
        int[][] B = {
                {1,2,3,4,5},
                {6,7,8,9,10},
                {11,12,13,14,15},
                {16,17,18,19,20},
                {21,22,23,24,25}
        };

        int[][] C = {
                {1,2,3,4},
                {6,7,8,9},
                {11,12,13,14},
                {16,17,18,19}

        };

        int[][] A = diagonal(C);

        for (int i=0; i<A.length; i++) {
            System.out.println(Arrays.toString(A[i]));
        }

    }

    public static int[][] diagonal(int[][] A) {
        int[] firstRow = A[0];
        int count = 0;
        int row, col;
        int[][] res = new int[firstRow.length * 2 - 1][];
        for (int i = 0; i< firstRow.length-1; i++) {
            row = 0;
            col = i;
            int[] B = new int[i + 1];
            for (int j=0; j<B.length; j++) {
                B[j] = A[row + j][col - j];
            }
            res[count] = B;
            count++;
        }

        for (int i=0; i<firstRow.length; i++) {
            row = i;
            col = firstRow.length - 1;
            int[] B = new int[firstRow.length - i];
            for (int j=0; j<B.length; j++) {
                B[j] = A[row + j][col - j];
            }
            res[count] = B;
            count++;
        }
        return res;
    }
}
