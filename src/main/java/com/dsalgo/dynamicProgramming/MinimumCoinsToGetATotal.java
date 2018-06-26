package com.dsalgo.dynamicProgramming;

public class MinimumCoinsToGetATotal {
    private int MAX = Integer.MAX_VALUE;
    private int total;
    private int[] coins;
    private int[][] dpTable;

    public MinimumCoinsToGetATotal(int total, int[] coins) {
        this.total = total;
        this.coins = coins;
        this.dpTable = new int[coins.length + 1][total + 1];
    }

    public void solve() {
        for (int i = 0; i <= coins.length; i++) {
            dpTable[i][0] = 0;
        }

        for (int i = 1; i<= total; i++) {
            dpTable[0][i] = MAX;
        }

        for (int row = 1; row <= coins.length; row++) {
            for (int col = 1; col <= total; col++) {
                if (coins[row - 1] <= col) {
                    int prev = dpTable[row - 1][col];
                    int curr = dpTable[row][col - coins[row - 1]];
                    if (prev == MAX && curr == MAX) {

                        dpTable[row][col] = MAX;

                    } else if (prev == MAX){

                        dpTable[row][col] = 1 + curr;

                    } else if (curr == MAX) {

                        dpTable[row][col] = prev;

                    } else {
                        dpTable[row][col] = Math.min(prev, 1 + curr);
                    }
                } else {
                    dpTable[row][col] = dpTable[row - 1][col];
                }
            }
        }

        System.out.println("Minimum number of coins - " + dpTable[coins.length][total]);
    }

    public static void main(String[] args) {
        //int[] coins = {9, 6, 5, 1};
        //int total = 11;

        int[] coins = {25, 10, 5};
        int total = 30;

        MinimumCoinsToGetATotal minimumCoinsToGetATotal = new MinimumCoinsToGetATotal(total, coins);
        minimumCoinsToGetATotal.solve();
    }
}
