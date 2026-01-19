package com.dsalgo.dynamicProgramming;


import java.util.HashMap;
import java.util.Map;

/*
Given m*n grid. In each cell, some chocolates are kept.
Alice starts from top-left cell and Bob starts from top-right cell.
They move only down, down-left, down-right
While moving, they collect the chocolates in that cell.
If both are in the same cell, then only one of them can collect the chocolates in that cell.
They stop moving if they reach any of the cell in the last row.
Figure out the maximum chocolates both can collect.


Solution :
There are 2 starting positions given and multiple end positions.
The starting positions are fixed, even if there are 2 starting positions.
However, the end positions are variable.
Hence, we use recursion and invoke it the first time with the start position and have end position in the base case.

Steps -

1. Express in terms of index -
    As we have Alice and Bob, we can index as (i1, j1) for Alice and (i2, j2) for Bob.
    However, as any move is always in the downward direction, the row will always remain same for Alice and Bob.
    Hence, we can say that "i" is the common row index and j1 and j2 are the column index for Alice and Bob.

    Base cases -
    There can be 2 base cases in recursion
    1. Out of bound
    2. Destination
    Always write out of bound case before the Destination

    Out of bound -
    "i" will not go out of bound, but j1,j2 can go outside the matrix.

    Destination -
    If "i" reaches the last row, it is the destination.
    However, both Alice and Bob can reach the same cell or different cell.
    If same cell, then only one of them can pick the chocolates.

2. Do all stuff -
    Let's say Alice is at (i,j1) and Bob is at (i,j2)
    From this cell, they pick the chocolates and then try to move to other cells.
    Alice can move in 3 directions and Bob can move in 3 directions. So, there are 9 possible ways.
        When Alice moves to down-left (i+1, j1-1),
                Bob can move to down-left (i+1, j2-1)
                Bob can move to down (i+1, j2)
                Bob can move to down-right (i+1, j2+1)

        When Alice moves to down (i+1, j1),
                Bob can move to down-left (i+1, j2-1)
                Bob can move to down (i+1, j2)
                Bob can move to down-right (i+1, j2+1)

        When Alice moves to down-right (i+1, j1+1),
                Bob can move to down-left (i+1, j2-1)
                Bob can move to down (i+1, j2)
                Bob can move to down-right (i+1, j2+1)

    We can express these 9 conditions in 2 for loops -
        When Alice moves to -1, Bob can move to -1,0,1
        When Alice moves to 0, Bob can move to -1,0,1
        When Alice moves to 1, Bob can move to -1,0,1


Time complexity -
Recursion -
We can move in 3 directions but only downwards. Let n be the number of rows.
Alice - 3^n
Bob - 3^n
For every move of Alice, Bob has 3 options
Hence, TC will be 3^n * 3^n

Memoization -
For previous problems, there were only i and j, hence total states were i*j
In this problem, we have i, j1, j2. Hence, total states will be i*j1*j2
Hence TC will be m*n*n

 */
public class _9_MaximumChocolatesWhen2AreMovingInAGrid {

    private static final int INF_NEGATIVE = -100_000_000;

    private static int solve(int i, int j1, int j2, int[][] matrix) {
        if (j1 < 0 || j1 >= matrix[0].length || j2 < 0 || j2 >= matrix[0].length) {
            return INF_NEGATIVE;
        }
        if (i == matrix.length - 1) {
            if (j1 == j2) {     // if both reach the same cell
                return matrix[i][j1];
            } else {    // else if both reach different cells
                return matrix[i][j1] + matrix[i][j2];
            }
        }

        int maxI = Integer.MIN_VALUE;
        //Do all stuff
        for (int x = -1; x <= 1; x++) {         // alice moves
            for (int y = -1; y <= 1; y++) {     // bob moves
                if (j1 == j2) {     // both are currently in the same cell, hence only one of them will pick the chocolates
                    maxI = Math.max(maxI, matrix[i][j1] + solve(i + 1, j1 + x, j2 + y, matrix));
                } else {
                    maxI = Math.max(maxI, matrix[i][j1] + matrix[i][j2] + solve(i + 1, j1 + x, j2 + y, matrix));
                }
            }
        }

        return maxI;

    }

    private static int memoize(int i, int j1, int j2, int[][] matrix, Map<String, Integer> map) {
        if (j1 < 0 || j1 >= matrix[0].length || j2 < 0 || j2 >= matrix[0].length) {
            return INF_NEGATIVE;
        }
        if (i == matrix.length - 1) {
            if (j1 == j2) {     // if both reach the same cell
                return matrix[i][j1];
            } else {    // else if both reach different cells
                return matrix[i][j1] + matrix[i][j2];
            }
        }

        String key = i + "_" + j1 + "_" + j2;
        if (map.containsKey(key)) {
            return map.get(key);
        }

        int maxI = Integer.MIN_VALUE;

        //Do all stuff
        for (int x = -1; x <= 1; x++) {         // alice moves
            for (int y = -1; y <= 1; y++) {     // bob moves
                if (j1 == j2) {     // both are currently in the same cell, hence only one of them will pick the chocolates
                    maxI = Math.max(maxI, matrix[i][j1] + solve(i + 1, j1 + x, j2 + y, matrix));
                } else {
                    maxI = Math.max(maxI, matrix[i][j1] + matrix[i][j2] + solve(i + 1, j1 + x, j2 + y, matrix));
                }
            }
        }

        map.put(key, maxI);
        return map.get(key);
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {2, 3, 1, 2},
                {3, 4, 2, 2},
                {5, 6, 3, 5}
        };

        // recursion
        // alice start - i, j1 - 0,0
        // bob start - i, j2 - 0, matrix[0].length - 1
        System.out.println(solve(0, 0, matrix[0].length - 1, matrix));

        //memoization
        Map<String, Integer> map = new HashMap<>();
        System.out.println(memoize(0, 0, matrix[0].length - 1, matrix, map));
    }
}
