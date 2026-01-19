package com.dsalgo.dynamicProgramming;

/*

Given an integer array height[] where height[i] represents the height of the i-th stair,
a frog starts from the first stair and wants to reach the last stair.
From any stair i, the frog has two options: it can either jump to the (i+1)th stair or the (i+2)th stair.
The cost of a jump is the absolute difference in height between the two stairs.
Determine the minimum total cost required for the frog to reach the last stair.

Algorithm :

The frog could have taken multiple paths to reach the last step.
As we need to find minimum cost across all the paths, we first need to find all the paths.

If we need to find all the paths, think of recursion.

Try to fit the recursion problem in below steps -
1. Think of the problem in terms of index
2. Do all stuff for that index
3. Calculate minimum across these stuffs

In recursion, we do not start from the first step, rather, we start from the last step and solve downwards.
Let's say there were 10 steps.
The answer at 10 will depend on answers at step 9 and step 8 as the frog can take either 1 or 2 step jump.

answer(10) {

    left = answer(9) + height_diff_from_9_to_10
    right = answer(8) + height_diff_from_8_to_10

    return minimum (left, right)

}


 */

public class _1_FrogJump {


    private static int solve(int n, int[] height) {

        // frog was already at starting point which is step_1, which is n=0. To reach the same step, it will take 0 cost
        if (n == 0) {
            return 0;
        }

        int left = solve(n-1, height) + Math.abs(height[n] - height[n-1]);
        int right = Integer.MAX_VALUE;

        if (n > 1) {    // if frog is at step 1, it cannot take 2 steps jump to go to -1
            right = solve(n-2, height) + Math.abs(height[n] - height[n-2]);
        }

        return Math.min(left, right);
    }

    private static int memoization(int n, int[] height, int[] dp) {

        // frog was already at starting point which is step_1, which is n=0. To reach the same step, it will take 0 cost
        if (n == 0) {
            return 0;
        }

        if (dp[n] != -1) {
            return dp[n];
        }

        int left = memoization(n-1, height, dp) + Math.abs(height[n] - height[n-1]);
        int right = Integer.MAX_VALUE;

        if (n > 1) {    // if frog is at step 1, it cannot take 2 steps jump to go to -1
            right = memoization(n-2, height, dp) + Math.abs(height[n] - height[n-2]);
        }

        dp[n] = Math.min(left, right);
        return dp[n];
    }

    private static int tabulation(int n, int[] height) {
        int[] dp = new int[n+1];
        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            int firstStep = dp[i - 1] + Math.abs(height[i] - height[i - 1]);
            int secondStep = Integer.MAX_VALUE;
            if (i > 1) {
                secondStep = dp[i - 2] + Math.abs(height[i] - height[i - 2]);
            }
            dp[i] = Math.min(firstStep, secondStep);
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int n = 5;
        int[] height = {30, 20, 50, 10, 40};

        // height array is 0-based index.
        // 0th index is height of step_1 = 30
        // 1st index is height of step_2 = 20
        // 4th index is height of step_5 = 40
        // As the height is for 0th to 4th step, number of steps are from 0 to 4. Hence, passing n-1 rather than n below.
        int answer = solve(n - 1, height);

        System.out.println(answer);


        // memoization
        int[] dp = new int[n];
        for(int i = 0; i < dp.length; i++) {
            dp[i] = -1;
        }
        answer = memoization(n-1, height, dp);
        System.out.println(answer);

        // tabulation
        answer = tabulation(n - 1, height);
        System.out.println(answer);

    }

}

/*

followup question.
Rather than taking 1 or 2 jumps, frog can take 1 to K jumps.

This is similar to the logic in this -
https://github.com/kdn251/interviews/blob/master/company/facebook/CombinationSumIV.java

 */

class KSteps {

    private static int tabulation(int n, int[] height, int k) {
        int[] dp = new int[n+1];
        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            int minSteps = Integer.MAX_VALUE;
            for (int j = 1; j <= k; j++) {
                int step = Integer.MAX_VALUE;
                if (i - j >= 0) {
                    step = dp[i - j] + Math.abs(height[i] - height[i - j]);
                    minSteps = Math.min(minSteps, step);
                }
            }
            dp[i] = minSteps;
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int n = 5;
        int[] height = {30, 20, 50, 10, 40};
        int k = 2;
        int answer = tabulation(n - 1, height, k);
        System.out.println(answer);
    }

}
