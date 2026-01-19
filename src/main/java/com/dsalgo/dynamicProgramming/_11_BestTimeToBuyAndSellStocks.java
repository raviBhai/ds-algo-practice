package com.dsalgo.dynamicProgramming;

/*

// Say you have an array for which the ith element is the price of a given stock on day i.

// If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock),
design an algorithm to find the maximum profit.

// Example 1:
// Input: [7, 1, 5, 3, 6, 4]
// Output: 5
 */
public class _11_BestTimeToBuyAndSellStocks {

    private static int solve(int[] stockPrices) {
        int mini = stockPrices[0];
        int profit = 0;
        for (int i = 1; i < stockPrices.length; i++) {
            profit = Math.max(profit, stockPrices[i] - mini);
            mini = Math.min(mini, stockPrices[i]);
        }
        return profit;
    }

    public static void main(String[] args) {
        int[] stockPrices = {7, 1, 5, 3, 6, 4};
        System.out.println(solve(stockPrices));
    }
}
