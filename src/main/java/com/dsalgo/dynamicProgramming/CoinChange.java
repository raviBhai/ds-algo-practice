package com.dsalgo.dynamicProgramming;

public class CoinChange {

    public int naiveCoinChange(int total, int[] coins, int index) {

        if (total < 0) return 0;
        if (total == 0) return 1;

        if (coins.length == index) return 0;

        return naiveCoinChange(total - coins[index], coins, index) + naiveCoinChange(total, coins, index + 1);
    }

    // https://leetcode.com/problems/combination-sum/solutions/16502/a-general-approach-to-backtracking-questions-in-java-subsets-permutations-combination-sum-palindrome-partitioning/
    // Combination Sum : https://leetcode.com/problems/combination-sum/
    public void dpCoinChange(int total, int[] coins) {
        int[][] dpTable = new int[coins.length + 1][total + 1];

        for (int i = 0; i <= coins.length; i++)
            dpTable[i][0] = 1;

        for (int i = 1; i <= total; i++)
            dpTable[0][i] = 0;

        for (int i = 1; i <= coins.length; i++) {
            for (int j = 1; j <= total; j++) {

                if (coins[i-1] <= j) {
                    dpTable[i][j] = dpTable[i - 1][j] + dpTable[i][j - coins[i - 1]];
                } else {
                    dpTable[i][j] = dpTable[i - 1][j];
                }
            }
        }

        System.out.println("Solution: " + dpTable[coins.length][total]);
        printdpTable(dpTable);
    }

    private void printdpTable(int[][] dpTable) {
        for (int i = 0; i < dpTable.length; i++) {
            for (int j = 0; j < dpTable[0].length; j++) {
                System.out.print(dpTable[i][j] + " - ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        CoinChange coinChange = new CoinChange();

        //int[] coins = {1,2,3};
        //int total = 4;

        //int[] coins = {1,3,5};
        //int total = 7;

        int[] coins = {2, 3, 5, 6, 8, 10};
        int total = 10;


        System.out.println(coinChange.naiveCoinChange(total, coins, 0));
        coinChange.dpCoinChange(total, coins);

    }
}
