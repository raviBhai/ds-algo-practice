package com.dsalgo.dynamicProgramming;

public class SubsetSum {
    private int sum;
    private int[] S;
    private boolean[][] dpTable;

    public SubsetSum(int sum, int[] S) {
        this.sum = sum;
        this.S = S;
        this.dpTable = new boolean[S.length + 1][sum + 1];
    }

    public void solve() {

        //first row is false expect from element at 0,0

        //first column is true

        for (int i = 0; i <= S.length; i++) {
            dpTable[i][0] = true;
        }

        for (int rowIndex = 1; rowIndex <= S.length; rowIndex++) {
            for (int columnIndex = 1; columnIndex <= sum; columnIndex++) {

                if (S[rowIndex - 1] > columnIndex) {
                    dpTable[rowIndex][columnIndex] = dpTable[rowIndex - 1][columnIndex];
                } else {
                    if (dpTable[rowIndex - 1][columnIndex] == true) {
                        dpTable[rowIndex][columnIndex] = dpTable[rowIndex - 1][columnIndex];
                    } else {
                        dpTable[rowIndex][columnIndex] = dpTable[rowIndex - 1][columnIndex - S[rowIndex - 1]];
                    }
                }

            }
        }

        System.out.println("Solution: " + dpTable[S.length][sum]);
    }

    public void showResult() {
        int rowIndex  = S.length;
        int colIndex = sum;

        while (rowIndex > 0 || colIndex > 0) {
            if (dpTable[rowIndex][colIndex] == dpTable[rowIndex - 1][colIndex]) {
                rowIndex = rowIndex - 1;
            } else {
                System.out.println("We take: " + S[rowIndex - 1]);
                colIndex = colIndex - S[rowIndex - 1];
                rowIndex = rowIndex - 1;
            }
        }
    }

    public static void main(String[] args) {
        int[] S = {5, 2, 3, 1};
        int sum = 9;

        SubsetSum subsetSum = new SubsetSum(sum, S);
        subsetSum.solve();
        subsetSum.showResult();
    }
}
