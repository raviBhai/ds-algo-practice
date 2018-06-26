package com.dsalgo.dynamicProgramming;

public class OptimalStrategyForAGame {
    private int[] coins;
    private int[][] dpTable;

    public OptimalStrategyForAGame(int[] coins) {
        this.coins = coins;
        this.dpTable = new int[coins.length][coins.length];
        initializeDpTable();
    }

    private void initializeDpTable() {
        for (int i = 0; i < dpTable.length; i++) {
            for (int j = 0; j < dpTable[0].length; j++) {
                dpTable[i][j] = -1;
            }
        }
    }

    public void solve() {
        System.out.println(solve_dp(0, coins.length - 1));
    }

    private int solve_dp(int i, int j) {
        if (dpTable[i][j] == -1) {
            if (i == j) {
                dpTable[i][j] = coins[i];
            } else if (i + 1 == j) {
                dpTable[i][j] = Math.max(coins[i], coins[j]);
            } else {
                dpTable[i][j] = Math.max(
                        coins[i] + Math.min(solve_dp(i+2, j), solve_dp(i+1, j-1)),
                        coins[j] + Math.min(solve_dp(i+1, j-1), solve_dp(i, j-2))
                );
            }
        }
        return dpTable[i][j];
    }

    public static void main(String[] args) {
        int[] coins = {8, 15, 3, 7};
        OptimalStrategyForAGame game = new OptimalStrategyForAGame(coins);
        game.solve();
    }
}
