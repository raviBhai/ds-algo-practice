package com.dsalgo.interviewbit.arrays;

import java.util.Arrays;

public class FillSpiralWithNumbersTillSquare {
    public static void main(String[] args) {
        int N = 0;
        int[][] A = generateMatrix(N);

        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(A[i]));
        }
    }

    public static int[][] generateMatrix(int A) {
        if (A == 0) {
            int[][] res = new int[0][0];
            return res;
        }

        int[][] res = new int[A][A];
        int rowStart = 0, colStart = 0;
        int rowLength = res.length - 1, colLength = res[0].length - 1;
        int count = 0;

        while (!(rowStart > rowLength || colStart > colLength)) {

            for (int i = rowStart; i <= colLength; i++) {
                res[rowStart][i] = ++count;
            }

            for (int i = rowStart+1; i <= rowLength; i++) {
                res[i][colLength] = ++count;
            }

            if (rowStart != rowLength) {
                for (int i = colLength-1; i >= colStart; i--) {
                    res[rowLength][i] = ++count;
                }
            }

            if (colStart != colLength) {
                for (int i = rowLength-1; i > rowStart; i--) {
                    res[i][colStart] = ++count;
                }
            }

            rowStart++;
            colStart++;
            rowLength--;
            colLength--;
        }
        return res;
    }
}
