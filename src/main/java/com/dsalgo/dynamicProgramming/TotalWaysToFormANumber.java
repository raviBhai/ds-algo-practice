package com.dsalgo.dynamicProgramming;

public class TotalWaysToFormANumber {

    static int[] dp = new int[100];

    public static void main(String[] args) {
        System.out.println(state(7));
    }

    static int state(int n) {
        for (int i = 0; i < dp.length; i++) {
            dp[i] = -1;
        }
        return state_helper(n);
    }

    static int state_helper(int n) {
        if (n < 0) {
            return 0;
        }

        if (dp[n] == -1) {
            if (n == 0) {
                dp[n] = 1;
            } else {
                dp[n] = state_helper(n - 1) + state_helper(n - 3) + state_helper(n - 5);
            }
        }

        return dp[n];
    }
}
