package com.dsalgo.dynamicProgramming;

import java.util.HashMap;
import java.util.Map;

/*

Input: [7, 1, 5, 3, 6, 4]
You can buy and sell multiple times, and keep accumulating the profit.
Return the max profit.

Condition - If you already bought, you need to sell before making the next buy.

Algorithm -
There are multiple ways in which we can buy/sell.
As there are multiple ways, we can use recursion.

Step-1 : Express in terms of index
We can have an index which represents the index of the array.
However, to perform any operation on this index, we need to know whether we are allowed to perform any operation on that index.
The operation that we can perform is buy/sell.
    If we can buy on the index, we have 2 choices - we may buy, or, we may not buy
    If we cannot buy on the index, we have 2 choices - we may sell, or, we may not sell
Hence, at each index, we need to know whether we can buy or not.
Hence, we can pass another variable with index to maintain this state - canBuy

Step-2 : Do all stuff on this index
At an index, the operation that we can perform is buy/sell.
    If we can buy on the index, we have 2 choices -
        we may buy      : if we buy, current day price will be subtracted from the profit
        we may not buy  : if we do not buy, zero will be subtracted from the profit
    If we cannot buy on the index, we have 2 choices -
        we may sell     :  if we sell, current day price will be added to the profit
        we may not sell :  if we do not sell, zero will be added to the profit

Step-3 : Return max profit

Base condition :
While trying to make a decision on every index, if I reach the index outside the array, I cannot do any sell even if I bought earlier.
hence I cannot make any profit if I reach outside the array. Hence, return 0

 */
public class _12_BuySellStocksMultipleTimes {

    // canBuy - 1, cannotBuy - 0
    private static int solve(int[] array, int index, int canBuy) {
        if (index == array.length) {
            return 0;
        }
        int profit = 0;

        // if I canBuy, I may or may not buy
        if (canBuy == 1) {
            profit = Math.max(
                    -array[index] + solve(array, index+1, 0),   // buy on current day, hence cannotBuy on index+1 day
                    0 + solve(array, index+1, 1)                // do not buy on current day, hence canBuy on index+! day
            );
        } else {
            // if I cannotBuy, I may or may not sell
            profit = Math.max(
                    array[index] + solve(array, index+1, 1),// sell on current day, hence canBuy on index+1 day
                    0 + solve(array, index+1, 0)// do not sell on current day, hence cannotBuy on index+1 day
            );
        }

        return profit;
    }

    /*
    To convert recursion to memoization, check the changing params.
    Changing params are - index, canBuy
     */
    private static int memoize(int[] array, int index, int canBuy, int[][] dp) {
        if (index == array.length) {
            return 0;
        }
        int profit = 0;

        if (dp[index][canBuy] != -1) {
            return dp[index][canBuy];
        }

        // if I canBuy, I may or may not buy
        if (canBuy == 1) {
            profit = Math.max(
                    -array[index] + memoize(array, index+1, 0, dp),   // buy on current day, hence cannotBuy on index+1 day
                    0 + memoize(array, index+1, 1, dp)                // do not buy on current day, hence canBuy on index+! day
            );
        } else {
            // if I cannotBuy, I may or may not sell
            profit = Math.max(
                    array[index] + memoize(array, index+1, 1, dp),// sell on current day, hence canBuy on index+1 day
                    0 + memoize(array, index+1, 0, dp)// do not sell on current day, hence cannotBuy on index+1 day
            );
        }

        dp[index][canBuy] = profit;
        return dp[index][canBuy];
    }


    public static void main(String[] args) {
        int[] array = {7, 1, 5, 3, 6, 4};
        // answer - Buy:1, Sell:5, profit:4 ||| Buy:3, Sell: 6, profit: 3 ||| totalProfit = 4+3 = 7

        // recursion
        System.out.println(solve(array, 0, 1));

        // memoization
        int[][] dp = new int[array.length][2];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }
        System.out.println(memoize(array, 0, 1, dp));
    }

}

/*
Follow up from previous question.
One transaction - buy + sell
Max allowed transactions - K

Solution :
Pass another argument called cap to the recursive function.
cap reduces when a transaction is completed.
A transaction completes when a sell happens.
Hence, reduce the cap value in the recursive call of sell when the sell actually happens

Add another base case when cap reaches 0, then no profit can be made.
It means that all the transactions are exhausted, and I am not allowed to make further transactions, hence no profit

 */
class MaxTransactionsAllowedIsK {


    // canBuy - 1, cannotBuy - 0
    private static int solve(int[] array, int index, int canBuy, int cap) {

        if (cap == 0) {
            return 0;
        }

        if (index == array.length) {
            return 0;
        }
        int profit = 0;

        // if I canBuy, I may or may not buy
        if (canBuy == 1) {
            profit = Math.max(
                    -array[index] + solve(array, index+1, 0, cap),   // buy on current day, hence cannotBuy on index+1 day
                    0 + solve(array, index+1, 1, cap)                // do not buy on current day, hence canBuy on index+! day
            );
        } else {
            // if I cannotBuy, I may or may not sell
            profit = Math.max(
                    array[index] + solve(array, index+1, 1, cap-1),// sell on current day, hence canBuy on index+1 day
                    0 + solve(array, index+1, 0, cap)// do not sell on current day, hence cannotBuy on index+1 day
            );
        }

        return profit;
    }

    private static int memoize(int[] array, int index, int canBuy, int cap, Map<String, Integer> map) {

        if (cap == 0) {
            return 0;
        }

        if (index == array.length) {
            return 0;
        }

        String key = index + "_" + canBuy + "_" + cap;
        if (map.containsKey(key)) {
            return map.get(key);
        }

        int profit = 0;

        // if I canBuy, I may or may not buy
        if (canBuy == 1) {
            profit = Math.max(
                    -array[index] + memoize(array, index+1, 0, cap, map),   // buy on current day, hence cannotBuy on index+1 day
                    0 + memoize(array, index+1, 1, cap, map)                // do not buy on current day, hence canBuy on index+! day
            );
        } else {
            // if I cannotBuy, I may or may not sell
            profit = Math.max(
                    array[index] + memoize(array, index+1, 1, cap-1, map),// sell on current day, hence canBuy on index+1 day
                    0 + memoize(array, index+1, 0, cap, map)// do not sell on current day, hence cannotBuy on index+1 day
            );
        }

        map.put(key, profit);
        return map.get(key);
    }

    public static void main(String[] args) {
        int[] array = {7, 1, 5, 3, 6, 4};
        // answer - Buy:1, Sell:5, profit:4 ||| Buy:3, Sell: 6, profit: 3 ||| totalProfit = 4+3 = 7

        // recursion
        int cap = 2;
        System.out.println(solve(array, 0, 1, cap));

        // memoization
        Map<String, Integer> map = new HashMap<>();
        System.out.println(memoize(array, 0, 1, cap,  map));
    }
}

/*

This is variation of _12_BuySellStocksMultipleTimes
In _12_BuySellStocksMultipleTimes, we can buy/sell unlimited number of times.
If we sell on day "i", we can buy on day "i+1"

In this problem, we can buy/sell unlimited number of times as well, however, we cannot buy immediately after selling.
If we sell on day "i", we cannot buy on day "i+1". We can buy only from "i+2"

Solution :
In _12_BuySellStocksMultipleTimes, in the else block where we cannot buy (meaning we may or may not sell),
if we sell, then we should call the next recursive call from the "i+2" day rather than "i+1" day.


 */
class BuySellWithCooldown {
    // canBuy - 1, cannotBuy - 0
    private static int solve(int[] array, int index, int canBuy) {
        if (index >= array.length) {
            return 0;
        }
        int profit = 0;

        // if I canBuy, I may or may not buy
        if (canBuy == 1) {
            profit = Math.max(
                    -array[index] + solve(array, index+1, 0),   // buy on current day, hence cannotBuy on index+1 day
                    0 + solve(array, index+1, 1)                // do not buy on current day, hence canBuy on index+! day
            );
        } else {
            // if I cannotBuy, I may or may not sell
            profit = Math.max(
                    array[index] + solve(array, index+2, 1),// sell on current day, hence canBuy on index+2 day
                    0 + solve(array, index+1, 0)// do not sell on current day, hence cannotBuy on index+1 day
            );
        }

        return profit;
    }

    /*
    To convert recursion to memoization, check the changing params.
    Changing params are - index, canBuy
     */
    private static int memoize(int[] array, int index, int canBuy, int[][] dp) {
        if (index >= array.length) {
            return 0;
        }
        int profit = 0;

        if (dp[index][canBuy] != -1) {
            return dp[index][canBuy];
        }

        // if I canBuy, I may or may not buy
        if (canBuy == 1) {
            profit = Math.max(
                    -array[index] + memoize(array, index+1, 0, dp),   // buy on current day, hence cannotBuy on index+1 day
                    0 + memoize(array, index+1, 1, dp)                // do not buy on current day, hence canBuy on index+! day
            );
        } else {
            // if I cannotBuy, I may or may not sell
            profit = Math.max(
                    array[index] + memoize(array, index+2, 1, dp),// sell on current day, hence canBuy on index+2 day
                    0 + memoize(array, index+1, 0, dp)// do not sell on current day, hence cannotBuy on index+1 day
            );
        }

        dp[index][canBuy] = profit;
        return dp[index][canBuy];
    }


    public static void main(String[] args) {
        int[] array = {4,9,0,4,10};
        // answer - Buy:4, Sell:9, profit:5 ||| Buy:4, Sell: 10, profit: 6 ||| totalProfit = 5+6 = 11

        // recursion
        System.out.println(solve(array, 0, 1));

        // memoization
        int[][] dp = new int[array.length][2];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }
        System.out.println(memoize(array, 0, 1, dp));
    }
}

/*

With one transaction (buy + sell), a fee will be deducted.
Buy/sell can be performed unlimited number of times.
Return max profit.

Solution :

Approach-1 :
Transaction completes on sell. Hence, while selling, deduct fees from the profit.

Approach-2 :
If you want to pay during the buy itself rather than during the sell, then deduct the fees during the buy, but not during the sell.

 */
class BuySellUnlimitedTimesWithTransactionFees {


    private static int solve_duringSell(int[] array, int index, int canBuy, int fees) {
        if (index >= array.length) {
            return 0;
        }
        int profit = 0;

        // if I canBuy, I may or may not buy
        if (canBuy == 1) {
            profit = Math.max(
                    -array[index] + solve_duringSell(array, index+1, 0, fees),   // buy on current day, hence cannotBuy on index+1 day
                    0 + solve_duringSell(array, index+1, 1, fees)                // do not buy on current day, hence canBuy on index+! day
            );
        } else {
            // if I cannotBuy, I may or may not sell
            profit = Math.max(
                    array[index] - fees + solve_duringSell(array, index+1, 1, fees),// sell on current day, hence canBuy on index+2 day
                    0 + solve_duringSell(array, index+1, 0, fees)// do not sell on current day, hence cannotBuy on index+1 day
            );
        }

        return profit;
    }

    private static int solve_duringBuy(int[] array, int index, int canBuy, int fees) {
        if (index >= array.length) {
            return 0;
        }
        int profit = 0;

        // if I canBuy, I may or may not buy
        if (canBuy == 1) {
            profit = Math.max(
                    -array[index] - fees + solve_duringBuy(array, index+1, 0, fees),   // buy on current day, hence cannotBuy on index+1 day
                    0 + solve_duringBuy(array, index+1, 1, fees)                // do not buy on current day, hence canBuy on index+! day
            );
        } else {
            // if I cannotBuy, I may or may not sell
            profit = Math.max(
                    array[index] + solve_duringBuy(array, index+1, 1, fees),// sell on current day, hence canBuy on index+2 day
                    0 + solve_duringBuy(array, index+1, 0, fees)// do not sell on current day, hence cannotBuy on index+1 day
            );
        }

        return profit;
    }

    public static void main(String[] args) {
        int[] array = {1, 3, 2, 8, 4, 9};
        // answer - Buy:1, Sell:8, profit: 7 - 2 = 5 ||| Buy:4, Sell: 9, profit: 9 - 4 - 2 = 3 ||| totalProfit = 5+3 = 8

        // recursion
        int fees = 2;
        System.out.println(solve_duringSell(array, 0, 1, fees));
        System.out.println(solve_duringBuy(array, 0, 1, fees));
    }
}