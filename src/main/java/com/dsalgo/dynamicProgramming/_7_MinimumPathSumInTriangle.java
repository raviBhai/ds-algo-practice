package com.dsalgo.dynamicProgramming;


/*

Given a triangle. Start from the top cell and you can move down or down-right.
End cell is any cell in the botton row.
Return the minimum sum path when traversing

1
1 2
1 2 3
1 2 3 4

Possible paths -
1,1,1,1,
1,1,1,2
1,1,2,2
1,1,2,3
and so on

Here, as we have to find across all paths, we can use recursion.

How this is different from previous recursion?
In previous matrix recursion problems, we had to find paths from top-left to bottom-right.
Both start and end were fixed.
We took solve(m-1, n-1) that is, we called solve() with the bottom-right i,j and then we recursed up till top-left which was our base case.

In this problem, start is fixed but end is variable, as end can be all the cells in the last row in the triangle.
As end can be multiple cells, we will need to call recursion from multiple end cells and try to reach top, and then check the minimum result for all these recursion calls.
Rather than this, we can start the recursion call from top-left, that is, start cell, and then recurse down.
The base case can be any cell in the bottom row.

Is this recursion also top-down?
Previous problems recursions were from solve(end) to solve(start).
This problem recursions will be from solve(start) to solve(end).
Still, this problem recursion is also top-down. Why?
Because, recursion is always top-down. We always start from a position till we reach the base case, and then we return the result back after the recursive call gets over.
Even in this problem, we can draw a recursion tree that starts from solve(start) and will have leaf nodes as solve(end), and then, when the recursion calls return, we finally get the answer for solve(start)

Base condition:
The moment we reach the last row, we return that value.
Think that if the solve() function was called the first time using any of the last row's cell, we would have to return that cell's value.
As we move down or down-right, we can never go out of the triangle, hence, no other base case.

 */


/*

Finding the minimum path sum in a triangle introduces a shift in how we think about recursion.
Here is a restructured explanation of the problem and the logic behind its solution.

---

## Problem Overview: The Minimum Triangle Path

Given a triangle, you must find the minimum sum path starting from the top cell and moving down to any cell in the bottom row.
At each cell, you are limited to two moves:

1. **Down:**
2. **Down-Right:**

---

## How This Differs From Matrix Problems

In standard matrix problems (like Top-Left to Bottom-Right), both the **start** and **end** points are fixed.
Usually, we start the recursion at the destination  and work backward to .

In this triangle problem, the **start is fixed** (top cell), but the **end is variable** (any cell in the bottom row).

* **The Problem:** If we started from the bottom like before,
we would have to trigger multiple recursion calls—one for every single cell in the last row—and then compare them.

* **The Solution:** It is more efficient to start the recursion at the **top**  and move downward.
The recursion naturally branches out to explore all possible paths to the bottom.

---

## Understanding "Top-Down" Recursion

You might wonder: "If I'm moving from the top of the triangle to the bottom, is this still top-down recursion?"

**Yes.** In the context of Dynamic Programming and Recursion, "Top-Down" doesn't refer to the physical direction of the movement (up or down the triangle).
Instead, it refers to the **functional approach**:

1. You start with the "big" problem (the total path from the start).
2. You break it into smaller sub-problems (the paths from the next step).
3. You solve until you hit a **Base Case** and then "bubble" the results back up the recursion tree.

Regardless of whether you move from index  or , the process of starting at the initial call and waiting for leaf nodes to return values is always a top-down recursive process.

---

## Logic and Base Conditions

Since we are moving from the top down, our base case occurs when we arrive at the destination row.

### 1. The Base Case

When the current row index  equals the last row index (), we have reached the end of a path. We simply return the value of that cell:

```java
if (i == n - 1) {
    return triangle[i][j];
}

```

### 2. The Recursive Step

At any cell , the minimum path sum is the value of the current cell plus the minimum of the two possible paths below it:

```java
int down = solve(i + 1, j, triangle);
int downRight = solve(i + 1, j + 1, triangle);

return triangle[i][j] + Math.min(down, downRight);

```

### 3. Why No Out-of-Bounds Check?

Unlike a square matrix, if you only move "Down" or "Down-Right" within a triangle structure, you are mathematically guaranteed to stay within the triangle's boundaries until you hit the bottom row. Therefore, you only need one base case: **reaching the last row.**

---


 */
public class _7_MinimumPathSumInTriangle {

    private static int solve(int i, int j, int[][] triangle) {
        if (i == triangle.length - 1) {
            return triangle[i][j];
        }
        int down = triangle[i][j] + solve(i + 1, j, triangle);
        int downright = triangle[i][j] + solve(i + 1, j + 1, triangle);
        return Math.min(down, downright);
    }

    private static int memoization(int i, int j, int[][] triangle, int[][] dp) {
        if (i == triangle.length - 1) {
            return triangle[i][j];
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        int down = triangle[i][j] + memoization(i + 1, j, triangle, dp);
        int downright = triangle[i][j] + memoization(i + 1, j + 1, triangle, dp);
        dp[i][j] = Math.min(down, downright);
        return dp[i][j];
    }

    /*
    Tabulation is always opposite of recursion.
    In previous problems, recursion was from solve(end) to solve(start), hence we did tabulation from start to end.
    While doing so, we use nested for loops with (i=0 to m) and (j=0 to n)

    In this problem, as recursion is from solve(start) to solve(end), we will do the reverse in tabulation.
    We will have base case populated first and then the inner nested loops will also move in the opposite direction.

    Let i be row and j be col.
    As this is triangle,
    when i is 0, j will be 0
    when i is 1, j will be 0,1
    when i is 2, j will be 0,1,2
    We see that j moves from 0 to i. This is the range we will use for j in nested loop, however, in reverse order.

    Base case will be populating the last row of the dp first.

     */

    /*
    When transitioning from recursion to Tabulation, the fundamental rule is that we must solve the subproblems in the opposite direction of the recursion.
    Here is a rephrased breakdown of your DP approach for the triangle problem:

    1. Reversing the Flow
    Tabulation is built from the bottom up. Since your recursion starts at the top ($0,0$) and works its way down to the base case (the last row), your tabulation must do the exact opposite:
        Recursion: Starts at the top -> Ends at the bottom row.
        Tabulation: Starts at the bottom row -> Builds up to the top.

    2. Setting the Base Case
    In tabulation, we populate the DP table with known values first.
    For this problem, the known values are the cells in the last row of the triangle.
    Because there are no more rows below them, their minimum path sum is simply their own value.

    3. Nested Loop Logic
    To fill the rest of the table, we move upward from the second-to-last row toward the peak.

    Row Logic (i):
        We start from the last row index (N-1) and move toward 0.
    Column Logic (j):
        In a triangle, the number of columns in any row is equal to the row index (j ranges from 0 to i).
        Your nested loop will reflect this structure.

     */
    private static int tabulation(int m, int n, int[][] triangle) {

        int[][] dp = new int[m+1][n+1]; // we could have initialized this to the size of the triangle as well
        for (int j = 0; j <= n; j++) {
            dp[m][j] = triangle[m][j];
        }

        // start i from one row above the last row and move up
        for (int i = m - 1; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                int down = triangle[i][j] + dp[i + 1][j];
                int downright = triangle[i][j] + dp[i + 1][j + 1];
                dp[i][j] = Math.min(down, downright);
            }
        }

        return dp[0][0];
    }

    public static void main(String[] args) {
        int[][] triangle = {
                {1},
                {2,1},
                {4,2,3},
        };
        System.out.println(solve(0, 0, triangle));

        // memoization
        int m = 3, n = 3;
        int[][] dp = new int[m][n];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }
        System.out.println(memoization(0,0, triangle, dp));

        // tabulation
        System.out.println(tabulation(2,2,triangle));
    }
}
