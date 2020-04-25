package com.dsalgo.dynamicProgramming;

public class RodCutting {
    private int[][] dpTable;
    private int[] prices;
    private int lengthOfRod;

    public RodCutting(int lengthOfRod, int[] prices) {
        this.lengthOfRod = lengthOfRod;
        this.prices = prices;
        this.dpTable = new int[prices.length+1][lengthOfRod + 1];
    }

    /*public void solve2() {

        for (int i = 1; i < prices.length; i++) {
            for (int j = 1; j <= lengthOfRod; j++) {
                if (i <= j) {
                    dpTable[i][j] = Math.max(dpTable[i - 1][j], prices[i] + dpTable[i][j - i]);
                } else {
                    dpTable[i][j] = dpTable[i - 1][j];
                }
            }
        }
    }*/

    public void solve() {

        for (int i = 1; i <= prices.length; i++) {
            for (int j = 1; j <= lengthOfRod; j++) {
                int notTaking = dpTable[i - 1][j];
                int taking = 0;
                if (i <= j) {
                    taking = prices[i-1] + dpTable[i][j - i];
                }
                dpTable[i][j] = Math.max(taking, notTaking);
            }
        }
    }

    public void showResult() {
        System.out.println("Max profit is " + dpTable[prices.length - 1][lengthOfRod]);

        for (int n = prices.length, w = lengthOfRod; n > 0;) {
            if (dpTable[n][w] != 0 && dpTable[n - 1][w] != dpTable[n][w]) {
                System.out.println("make cut at " + n + "m");
                w = w - n;
            } else {
                n--;
            }
        }
    }

    public static void main(String[] args) {
        int lengthOfRod = 5;
        int[] prices = {0, 2, 5, 7, 3};
        //prices -> 1m price = 0, 2m price = 2, 3m price = 5, 4m price = 7, 5m price = 3,

        //int lengthOfRod = 8;
        //int[] prices = {0, 3, 5, 8, 9, 10, 17, 17, 20};

        RodCutting rodCutting = new RodCutting(lengthOfRod, prices);
        rodCutting.solve();
        rodCutting.showResult();
    }
}
