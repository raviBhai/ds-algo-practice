package com.dsalgo.interviewbit.arrays;

public class SpiralOrderMatrix {
    static int[][] A = null;
    public static void main(String[] args) {

    //    get(0, 0, 4, 4);

        int[][] A = {
                {1,2,3,4},
                {6,7,8,9},
                {11,12,13,14},
                {16,17,18,19}

        };

        int[][] B = {
                {1,2,3,4,5},
                {6,7,8,9,10},
                {11,12,13,14,15},
                {16,17,18,19,20},
                {21,22,23,24,25}
        };

        int[][] C = {
                {1,2,3,4,5},
                {6,7,8,9,10},
                {11,12,13,14,15}
        };

        int[][] D = {
                {3,4,5},
                {8,9,10},
                {13,14,15},
                {18,19,20},
                {23,24,25}
        };
        spiral(A);
        System.out.println("**********");
        spiral(B);
        System.out.println("**********");
        spiral(C);
        System.out.println("**********");
        spiral(D);
    }

    public static void spiral(int[][] A) {
        int rowStart = 0, colStart = 0;
        int rowLength = A.length - 1, colLength = A[0].length - 1;

        while (!(rowStart > rowLength || colStart > colLength)) {

            for (int i = colStart; i <= colLength; i++) {
                System.out.println(A[rowStart][i]);
            }

            for (int i = rowStart+1; i <= rowLength; i++) {
                System.out.println(A[i][colLength]);
            }

            if (rowStart != rowLength) {
                for (int i = colLength-1; i >= colStart; i--) {
                    System.out.println(A[rowLength][i]);
                }
            }

            if (colStart != colLength) {
                for (int i = rowLength-1; i > rowStart; i--) {
                    System.out.println(A[i][colStart]);
                }
            }

            rowStart++;
            colStart++;
            rowLength--;
            colLength--;
        }

    }

}
