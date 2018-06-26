package com.dsalgo.dynamicProgramming;

public class MinimumEditDistance {
    private String s1;
    private String s2;
    private int[][] dpTable;

    public MinimumEditDistance(String s1, String s2) {
        this.s1 = s1;
        this.s2 = s2;
        this.dpTable = new int[s1.length() + 1][s2.length() + 1];
    }

    public void solve() {
        for (int i = 0; i <= s1.length(); i++) {
            dpTable[i][0] = i;
        }

        for (int i = 0; i <= s2.length(); i++) {
            dpTable[0][i] = i;
        }

        for (int row = 1; row <= s1.length(); row++) {
            for (int col = 1; col <= s2.length(); col++) {
                if (s1.charAt(row - 1) == s2.charAt(col - 1)) {
                    dpTable[row][col] = dpTable[row - 1][col - 1];
                } else {
                    dpTable[row][col] = 1 + Math.min(dpTable[row - 1][col], Math.min(dpTable[row-1][col-1], dpTable[row][col - 1]));
                }
            }
        }

        System.out.println("Minimum edit distance is - " + dpTable[s1.length()][s2.length()]);
    }

    public static void main(String[] args) {
        String s1 = "sunday";
        String s2 = "saturday";
        MinimumEditDistance minimumEditDistance = new MinimumEditDistance(s1, s2);
        minimumEditDistance.solve();
    }
}
