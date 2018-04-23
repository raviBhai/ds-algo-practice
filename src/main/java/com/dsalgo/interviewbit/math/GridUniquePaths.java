package com.dsalgo.interviewbit.math;

public class GridUniquePaths {
    public static void main(String[] args) {
        GridUniquePaths gup = new GridUniquePaths();
        System.out.println(gup.uniquePaths2(4,6));
        System.out.println(gup.uniquePaths(4,6));
    }

    public int uniquePaths2(int A, int B) {

        if (A == 1 || B == 1 || A == 0 || B == 0) {
            return 1;
        }

        int[][] res = new int[A][B];

        //initialize first col to 1
        for(int i = 0; i < A; i++) {
            res[i][0] = 1;
        }

        //initialize first row to 1
        for(int i = 0; i < B; i++) {
            res[0][i] = 1;
        }

        for (int i = 1; i < A; i++) {
            for (int j = 1; j < B; j++) {
                res[i][j] = res[i - 1][j] + res[i][j - 1];
            }
        }

        return res[A - 1][B - 1];
    }

    public int uniquePaths(int A, int B) {

        if (A == 1 || B == 1 || A == 0 || B == 0) {
            return 1;
        }
        return uniquePaths(A - 1, B) + uniquePaths(A, B - 1);
    }
}
