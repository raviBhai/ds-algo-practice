package com.dsalgo.dynamicProgramming;


/*

Given m * n grid.
Start cell - 0,0
End cell - m-1,n-1
Move only  - down, right
Count the number of ways you can reach from start to end cell.

Solution :
As we need to find all paths, we can use recursion.

Step_1 : Express problem in index
solve(i,j) - gives me number of ways to reach i,j from 0,0

Step_2 : Do all stuff
number of ways to reach i,j = (number of ways to reach i, j-1) + (number of ways to reach i-1, j)

Step_3 : return the sum


As this is counting the number of ways, it is similar to _15_PrintSubsequences._PrintAllSubsequences_Input_Output._CountAllSubsequencesWithSumAsK_Pick_NonPick_

In recursion, we start from top, and the moment we reach base case, it is one of the path.
Base case for this problem is 0,0
Hence, when both i and j reach (0,0), return 1

Other base cases:
Let's say the function is called with solve(0,1).
Internally, it will make two recursive calls - solve(0,0) and solve(-1,1)
So, i or j, can be less than 0. In this case, number of paths will be 0

solve(i, j) {

    if (i == 0 && j == 0) {
        return 1
    }

    if (i < 0 || j < 0) {
        return 0
    }

    int left = solve(i, j-1);
    int up = solve(i-1, j);

    return left + up;

}

 */
public class _5_CountPathsInGrid {

    private static int solve(int i, int j) {
        if (i == 0 && j == 0) {
            return 1;
        }

        if (i < 0 || j < 0) {
            return 0;
        }

        int left = solve(i, j-1);
        int up = solve(i-1, j);

        return left + up;
    }

    private static int memoization(int i, int j, int[][] dp) {
        if (i == 0 && j == 0) {
            return 1;
        }

        if (i < 0 || j < 0) {
            return 0;
        }

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int left = solve(i, j-1);
        int up = solve(i-1, j);

        dp[i][j] = left + up;
        return dp[i][j];
    }

    private static int tabulation(int m, int n) {

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                } else {

                    int left = 0, up = 0;

                    if (j > 0) left = dp[i][j - 1];
                    if (i > 0) up = dp[i - 1][j];

                    dp[i][j] = left + up;
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        int m = 3;
        int n = 3;

        // recursive
        System.out.println(solve(m-1,n-1));

        // memoization
        int[][] dp = new int[m][n];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }
        System.out.println(memoization(m-1, n-1, dp));

        // tabulation
        System.out.println(tabulation(m-1, n-1));
    }
}

/*

Given input matrix, and any of its cell can be -1 which is an obstacle.
Do not count paths going through the obstacle.
Count the allowed paths.

Use similar approach as the above question

New base condition : if ( i >= 0 && j >= 0 && matrix[i][j] == -1 )

As in recursion, we start from top and go down.
Top is the last cell and down is the first cell.
While going towards the first cell, if we encounter a blocked cell, we return 0 as there is no path ahead.

Hence, this will be a new base condition.
This new base condition will come above the existing base condition of "if (i == 0 && j == 0)"
because 0,0 cell could have been a blocked cell as well, and we should return 0 from it if it were blocked cell.

 */

class _5_CountPathsInGrid_WithObstacles {

    private static int solve(int i, int j, int[][] matrix) {

        if (i >= 0 && j >= 0 && matrix[i][j] == -1) {
            return 0;
        }

        if (i == 0 && j == 0) {
            return 1;
        }

        if (i < 0 || j < 0) {
            return 0;
        }

        int left = solve(i, j-1, matrix);
        int up = solve(i-1, j, matrix);

        return left + up;
    }

    private static int memoization(int i, int j, int[][] dp, int[][] matrix) {

        if (i >= 0 && j >= 0 && matrix[i][j] == -1) {
            return 0;
        }

        if (i == 0 && j == 0) {
            return 1;
        }

        if (i < 0 || j < 0) {
            return 0;
        }

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int left = solve(i, j-1, matrix);
        int up = solve(i-1, j, matrix);

        dp[i][j] = left + up;
        return dp[i][j];
    }


    private static int tabulation(int m, int n, int[][] matrix) {

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {

                if (i >= 0 && j >= 0 && matrix[i][j] == -1) {   // i >= 0 && j >= 0 is not required as i and j moves from 0 to end
                    dp[i][j] = 0;
                } else if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                } else {

                    int left = 0, up = 0;

                    if (j > 0) left = dp[i][j - 1];
                    if (i > 0) up = dp[i - 1][j];

                    dp[i][j] = left + up;
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {0,0,0},
                {0,-1,0},
                {0,0,0}
        };

        int m = 3;
        int n = 3;

        // recursive
        System.out.println(solve(m-1,n-1, matrix));

        // memoization
        int[][] dp = new int[m][n];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }
        System.out.println(memoization(m-1, n-1, dp, matrix));

        // tabulation
        System.out.println(tabulation(m-1, n-1, matrix));
    }

}
