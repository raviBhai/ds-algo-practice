package com.dsalgo.recursion;

public class OptimalStrategyForAGame {
    private int[] coins;
    private int[][] dpTable;
    private int count;

    public OptimalStrategyForAGame(int[] coins) {
        this.coins = coins;
        this.dpTable = new int[coins.length][coins.length];
    }

    public void solve() {
        System.out.println(solve(0, coins.length - 1));
    }

    private int solve(int i, int j) {

        //if only one coin is present, return that coin
        if (i == j) {
            return coins[i];
        }

        //if two coins are present, return max of them
        if (i + 1 == j) {
            return Math.max(coins[i], coins[j]);
        }
        count++;
        return Math.max(
                coins[i] + Math.min(solve(i+2, j), solve(i+1, j-1)),
                coins[j] + Math.min(solve(i+1, j-1), solve(i, j-2))
            );
    }

    public static void main(String[] args) {
        int[] coins = {8, 15, 3, 7, 4, 5, 2, 2, 2, 2};
        OptimalStrategyForAGame game = new OptimalStrategyForAGame(coins);
        game.solve();
        System.out.println(game.count);
    }
}
