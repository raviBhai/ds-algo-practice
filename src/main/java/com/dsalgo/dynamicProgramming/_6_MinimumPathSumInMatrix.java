package com.dsalgo.dynamicProgramming;

/*

Given 2D grid of size m*n.
Starting from 0,0 you can move right or down to reach the end cell which is the bottom right cell.
There can be multiple paths from start to end.
Each path has a sum.
Return the minimum sum across all the paths.

Solution:
1. As this requires all paths, use recursion.
2. solve(i,j) will be the function which says that it gives the minimum path from 0,0 to i,j

Base condition:
When i,j both are (0,0) it  could mean that the function was called the first time using 0,0
Hence the minimum sum will be value at matrix[0][0]

When calling recursion, we will do i,j-1 and i-1,j
Here, either i or j can go negative.
So we add another base condition i<0 || j<0
It means that from the end cell, we reached a cell which is outside the matrix.
We would not want to take this path when we reached outside the matrix.
Let's say we were at cell 2,0 from where we can go either up(1,0) or left(2,-1)
and then get the minimum(matrix[2][0] + matrix[1][0] , matrix[2][0] + matrix[2][-1])
We would never want matrix[2][0] + matrix[2][-1] to be in the path, hence we would make matrix[2][-1] return the Integer.MAX_VALUE

The moment we reach outside the matrix, we would not want that path.
To ignore that path, let it return the Integer.MAX_VALUE so that this path is ignored while getting minimum across all paths

 */

public class _6_MinimumPathSumInMatrix {

    private static final int INF = 100_000_000;

    private static int solve(int i, int j, int[][] matrix) {
        if (i == 0 && j == 0) {
            return matrix[i][j];
        }

        if (i < 0 || j < 0) {
            return INF;
        }

        int left = matrix[i][j] + solve(i, j - 1, matrix);
        int up = matrix[i][j] + solve(i - 1, j, matrix);
        return Math.min(left, up);
    }

    private static int memoization(int i, int j, int[][] matrix, int[][] dp) {
        if (i == 0 && j == 0) {
            return matrix[i][j];
        }

        if (i < 0 || j < 0) {
            return INF;
        }

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int left = matrix[i][j] + solve(i, j - 1, matrix);
        int up = matrix[i][j] + solve(i - 1, j, matrix);
        dp[i][j] = Math.min(left, up);
        return dp[i][j];
    }

    private static int tabulation(int m, int n, int[][] matrix) {
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = matrix[i][j];
                } else {
                    int left = 0, up = 0;
                    if (j > 0) {
                        left = matrix[i][j] + dp[i][j - 1];
                    } else {
                        left = INF;
                    }
                    if (i > 0) {
                        up = matrix[i][j] + dp[i-1][j];
                    } else {
                        up = INF;
                    }
                    dp[i][j] = Math.min(left, up);
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1,2,3},
                {4,5,6}
        };

        int m = 2;
        int n = 3;

        // recursion
        System.out.println(solve(m - 1, n - 1, matrix));

        // memoization
        int[][] dp = new int[m][n];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }
        System.out.println(memoization(m - 1, n - 1, matrix, dp));

        // tabulation
        System.out.println(tabulation(m-1, n-1, matrix));
    }
}
