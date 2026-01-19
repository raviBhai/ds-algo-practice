package com.dsalgo.dynamicProgramming;

/*

Given input array.
There can be multiple sub-sequences.
Constraint - A sub-sequence cannot have adjacent elements.
Return the maximum sum across all sub-sequences.

Example -
Input array - 2,1,4,9

Possible sub-sequences :
2,4 : sum - 6
2,9 : sum - 11
1,9 : sum - 10

Return 11 as output.

Solution:

As we need to find all sub-sequences, it is similar to finding all the paths.
To find all paths, we should use Recursion.

Recursion has 3 steps :
1. Explain the problem in index
2. Do all stuffs for that index
3. Pick the best - in this case, pick maximum across all stuffs

Step-1 :  Explain the problem in index
This is same as IBH - Hypothesis.
Function name solve(index)
It would mean that this function gives me the output, that is, maximum sum of non-adjacent elements, from 0 to index.

Step-2 : Do all stuffs for that index
At an index, do all the stuffs.
In this problem, when we are at an index, the stuff that we can do is either pick that element or do not pick that element.
If we pick that element, then we call recursion for next to next
If we do not pick that element, then we call recursion for the next element

Base case :
when index = 0
As it is recursion, we would have started from the top, that it from index.
If we reached index=0, that means, we would have not reached index=1 as we pick non-adjacent elements.
That means, we need to pick the value at index=0

If the solve(index) function is called with index=1, it would internally call solve(0) and solve(-1)
When solve(-1), then, return 0

 */
public class _2_MaximumSumOfNonAdjacentElements  {

    private static int solve(int[] array, int index) {

        if (index == 0) {
            return array[0];
        }

        if (index < 0) {
            return 0;
        }

        int pick = array[index] + solve(array, index - 2);
        int notPick = solve(array, index - 1);

        return Math.max(pick, notPick);

    }

    private static int memoization(int[] array, int index, int[] dp) {

        if (index == 0) {
            return array[0];
        }

        if (index < 0) {
            return 0;
        }

        if (dp[index] != -1) {
            return dp[index];
        }

        int pick = array[index] + memoization(array, index - 2, dp);
        int notPick = memoization(array, index - 1, dp);
        dp[index] = Math.max(pick, notPick);
        return dp[index];

    }

    public static int tabulation(int[] array) {
        int[] dp = new int[array.length];
        dp[0] = array[0];

        for (int i = 1; i < array.length; i++) {

            int take = 0;
            if (i - 2 >= 0) {
                take = array[i] + dp[i - 2];
            } else {
                take = array[i];
            }

            int nonTake = dp[i - 1];

            dp[i] = Math.max(take, nonTake);

        }

        return dp[array.length - 1];
    }

    public static void main(String[] args) {
        int[] array = {2, 1, 4, 9};
        System.out.println(solve(array, array.length - 1));

        // memoization
        int index = array.length - 1;
        int[] dp = new int[index + 1];
        for(int i = 0; i < dp.length; i++) {
            dp[i] = -1;
        }
        System.out.println(memoization(array, index, dp));

        // tabulation
        System.out.println(tabulation(array));
    }

}
