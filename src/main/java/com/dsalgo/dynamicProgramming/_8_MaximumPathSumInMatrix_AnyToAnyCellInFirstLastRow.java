package com.dsalgo.dynamicProgramming;


/*
Given a 2D matrix.
You can start from any cell in first row and reach any cell in the last row.
From any cell, you can move in 3 ways -  down, diagonal down-right, diagonal down-left
There can be multiple such paths.
Return the maximum sum path.

Solution:

As we need to find all paths, use recursion.
Here, both the start cell and end cell are variable.

In _6_MinimumPathSumInMatrix, both start and end cells were fixed.


As the end is variable and the start is also variable here, we can call recursion from every cell
in the bottom row and let it reach any cell in the top row.
Then across all these recursive calls, return the max sum

Base condition:
There are always 2 base conditions -
Either we reach the destination or we reach out of bound.
1. Destination in this case is any cell in the first row.
2. Out of bound -
        Given directions to move : down, diagonal down-right, diagonal down-left
        Hence, with recursion, we move : up, up-left, up-right
        In all these 3 directions, check if we can get out of bound.
        up - we can reach row 0 and will hit the base case, so we will NOT get out of bound with up direction
        up-left - column can become -ve, hence return Integer.MIN_VALUE
        up-right - column can become greater than col length, hence return Integer.MIN_VALUE

While writing the base cases, write the out of bound base case before the destination base case.
Else, you will get an OutOfBoundException if you write out-of-bound case after the destination case.


Time complexity -
Recursion:
In the case when we can move from a cell to another cell in right or down directions, we can move in 2 directions from a cell.
We consider that there are m*n cells, and in each cell we can move in 2 directions.
Hence, TC of that case is 2 ^ (m * n)
Here, when we move horizontally, we have a chance to cover all the cells in the matrix, hence we take m*n

However, in this case, we cannot move horizontally, we can move only vertically.
Hence, we cannot move to all the cells in the matrix. Rather, we can move across the number of rows.
Let n be the number of rows, and from each cell, we can move in 3 directions.
So, TC will be 3 ^ n

Memoization:
TC will reduce from 3^n to m*n
Why - in memoization, it will try to evaluate for every cell, but it will evaluate only once.
In recursion, due to overlapping sub-problems, it evaluates more than once some cells.
As number of cells is m*n, TC is also m*n

 */
public class _8_MaximumPathSumInMatrix_AnyToAnyCellInFirstLastRow {

    private static final int INF_NEGATIVE = -100_000_000;

    private static int recursion(int[][] matrix) {
        int max = Integer.MIN_VALUE;
        for (int j = 0; j < matrix[0].length; j++) {
            max = Math.max(max, recursionHelper(matrix.length - 1, j, matrix));
        }
        return max;
    }

    private static int recursionHelper(int i, int j, int[][] matrix) {

        if (j < 0 || j >= matrix[0].length) {
            return INF_NEGATIVE;
        }

        if (i == 0) {
            return matrix[0][j];
        }


        int up = matrix[i][j] + recursionHelper(i - 1, j, matrix);
        int upLeft = matrix[i][j] + recursionHelper(i - 1, j - 1, matrix);
        int upRight = matrix[i][j] + recursionHelper(i - 1, j + 1, matrix);
        return Math.max(up, Math.max(upLeft, upRight));
    }

    /*
    We use memoization so that we do not solve overlapping sub-problems.
    When we start with one of the cell in the last row, for eg(row,0) and move up, we might not get overlapping sub-problems.
    However, when we start with the next cell in the last row, that is (row+1, 0), and move up, we will get the sub-problems that we would have already solved when we started from (row,0)
    Hence, we use the same memoized table dp for all the starting cells.
     */
    private static int memoization(int[][] matrix) {
        int[][] dp = new int[matrix.length][matrix[0].length];
        for (int row = 0; row < dp.length; row++) {
            for (int col = 0; col < dp[0].length; col++) {
                dp[row][col] = -1;
            }
        }

        int max = Integer.MIN_VALUE;
        for (int j = 0; j < matrix[0].length; j++) {
            max = Math.max(max, memoizationHelper(matrix.length - 1, j, matrix, dp));
        }
        return max;
    }

    private static int memoizationHelper(int i, int j, int[][] matrix, int[][] dp) {

        if (j < 0 || j >= matrix[0].length) {
            return INF_NEGATIVE;
        }

        if (i == 0) {
            return matrix[0][j];
        }


        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int up = matrix[i][j] + memoizationHelper(i - 1, j, matrix, dp);
        int upLeft = matrix[i][j] + memoizationHelper(i - 1, j - 1, matrix, dp);
        int upRight = matrix[i][j] + memoizationHelper(i - 1, j + 1, matrix, dp);
        dp[i][j] = Math.max(up, Math.max(upLeft, upRight));
        return dp[i][j];
    }

    /*
    After figuring out the for loops, keep the other code similar to that in recursion code.
    That is, keep the code similar for getting up, upLeft, upRight

    As in recursive solution, to get the answer, we got the max across all recursive calls made from the last row,
    here in tabulation solution as well, we need to get the max of all the entries in the last row and return that as an answer

     */
    private static int tabulation(int[][] matrix) {

        int[][] dp = new int[matrix.length][matrix[0].length];

        // first row in dp table should have same data as first row in matrix
        for (int j = 0; j < matrix[0].length; j++) {
            dp[0][j] = matrix[0][j];
        }

        // build the for loops -
        // as dp is bottom up, we solved for the bottom which was the base case, now we move next level from bottom, that is, next row
        for (int i = 1; i < matrix.length; i++) {

            //for every i, the variable j will move from 0 to matrix[0].length
            for(int j = 0; j < matrix[0].length; j++) {

                // copy up, upLeft, upRight from recursive solution and replace recursive call with dp
                int up = matrix[i][j] + dp[i - 1][j];
                int upLeft = INF_NEGATIVE;
                if (j > 0) {
                    upLeft = matrix[i][j] + dp[i - 1][j - 1];
                }

                int upRight = INF_NEGATIVE;
                if (j + 1 < matrix[0].length) {
                    upRight = matrix[i][j] + dp[i - 1][j + 1];
                }
                dp[i][j] = Math.max(up, Math.max(upLeft, upRight));
            }
        }

        // return the max from the last row
        int max = Integer.MIN_VALUE;
        for (int j = 0; j < matrix[0].length; j++) {
            max = Math.max(max, dp[matrix.length-1][j]);
        }
        return max;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 10, 4},
                {100, 3, 2, 1},
                {1, 1, 20, 2},
                {1, 2, 2, 1}
        };
        // max path sum is - 2, 100, 1, 2 = 105

        // recursion
        System.out.println(recursion(matrix));


        // memoization
        System.out.println(memoization(matrix));


        // tabulation
        System.out.println(tabulation(matrix));
    }
}
