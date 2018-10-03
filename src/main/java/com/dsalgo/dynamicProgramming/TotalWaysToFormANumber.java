package com.dsalgo.dynamicProgramming;

public class TotalWaysToFormANumber {

    static int[] dp = new int[100];

    /*
    This is different from the CoinChange problem because in this problem, the ordering matters
    but in coin change problem, the order of the coins don't matter.

    For example:
    To get a total of 4 with 1,2,3 coins, we get {1,1,1,1},{1,1,2},{2,2},{1,3} i.e. 4 ways.
    Here, the order within each solution does not matter. 1,1,2 and 1,2,1 will be same.

     */


    /*

    This problem is same as climbing n stairs in 1,3,5 steps
     */


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
