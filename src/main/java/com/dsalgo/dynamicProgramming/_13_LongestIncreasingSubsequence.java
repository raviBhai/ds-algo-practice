package com.dsalgo.dynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*

Given an array with integers. Return the max length of the sub-sequence which has elements in increasing order.

LongestIncreasingSubsequence - LIS

Solution :
There can be multiple sub-sequences, out of which we need to return the best.
As there can be multiple sub-sequences, we can use recursion.

Step-1: express in index
We can have an array index.
At the current index, we can pick the element only if the current element is greater than the last element that was added to the sub-sequence.
It means, along with current index, we need the index of the last element that was added to the sub-sequence.
So, if the function is solve(index, prev_index), it means return the LIS starting from index till array length when the last element added to the sub-sequence was at prev_index

Step-2 : Do all stuff
As this is sub-sequence problem, we can either pick or not-pick the element at an index.

The first time when we call this function, the index will be 0 and prev_index will be -1
Hence, for picking the element, we can say that if the prev_index is -1, then pick it

 */
public class _13_LongestIncreasingSubsequence {

    private static int solve(int[] array, int index, int prev_index) {


        if (index == array.length) {
            return 0;
        }

        int notPick = solve(array, index + 1, prev_index);
        int pick = 0;
        if (prev_index == -1 || array[index] > array[prev_index]) {
            pick = 1 + solve(array, index + 1, index);
        }
        return Math.max(notPick, pick);
    }


    /*
    changing params are index and prev_index.
    index - ranges from 0 to n-1, hence dp[][] array can be like this - dp[n][]

    prev_index can be -1, and we cannot have -1 as an array index.
    prev_index - ranges from -1 to n-1
    We can right shift the indexes, such that, whatever we intend to store at -1, we will store that at 0,
    -1 -> 0
    0 -> 1
    ...
    ...
    n-1 -> n

    As n is the max value for  prev_index, the dp[][] array needs to be of n+1 size
    Hence, the dp[][] will be like this - dp[][n+1]

    While checking if the dp[][] table already has the value, we will not do dp[index][prev_index]
    Rather, we will do dp[index][prev_index+1] as we said earlier that value of -1 will be at 0, and so on.

     */
    private static int memoization(int[] array, int index, int prev_index, int[][] dp) {


        if (index == array.length) {
            return 0;
        }

        if (dp[index][prev_index+1] != -1) {
            return dp[index][prev_index+1];
        }

        int notPick = memoization(array, index + 1, prev_index, dp);
        int pick = 0;
        if (prev_index == -1 || array[index] > array[prev_index]) {
            pick = 1 + memoization(array, index + 1, index, dp);
        }
        dp[index][prev_index+1] = Math.max(notPick, pick);
        return dp[index][prev_index+1];
    }

    // time complexity is still O(N^2)
    private static void tabulation(int[] array) {
        int[] dp = new int[array.length];
        Arrays.fill(dp, 1);

        int[] hash = new int[array.length];
        int maxLIS = 1;
        int lastIndexOfLIS = -1;
        for (int i = 0; i < array.length; i++) {
            hash[i] = i;
            for (int prev = 0; prev < i; prev++) {
                if (array[i] > array[prev] && dp[i] < 1 + dp[prev]) {
                    dp[i] = 1 + dp[prev];
                    hash[i] = prev;
                }
            }
            if (maxLIS < dp[i]) {
                maxLIS = dp[i];
                lastIndexOfLIS = i;
            }
        }

        List<Integer> lisSequence = new ArrayList<>();
        lisSequence.add(array[lastIndexOfLIS]);
        while (hash[lastIndexOfLIS] != lastIndexOfLIS) {
            lastIndexOfLIS = hash[lastIndexOfLIS];
            lisSequence.add(array[lastIndexOfLIS]);
        }
        Collections.reverse(lisSequence);

        System.out.println("Max LIS lenght is - " + maxLIS);
        System.out.println("LIS Sequence is - " + lisSequence);
    }



    public static void main(String[] args) {
        int[] array = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(solve(array, 0, -1));

        // memoization - dp[][] array to be of lenght - n * n+1
        int[][] dp = new int[array.length][array.length + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }
        System.out.println(memoization(array, 0, -1, dp));

        // tabulation
        tabulation(array);
    }
}
