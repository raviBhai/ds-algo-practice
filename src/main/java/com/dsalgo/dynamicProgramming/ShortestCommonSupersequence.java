package com.dsalgo.dynamicProgramming;

public class ShortestCommonSupersequence {
    private String s1;
    private String s2;
    int[][] dpTable;

    public ShortestCommonSupersequence(String s1, String s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    public void solve() {
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(s1, s2);
        lcs.solve();
        this.dpTable = lcs.dpTable;
        int lcsLength = dpTable[s1.length()][s2.length()];
        int scsLength = (s1.length() + s2.length()) - lcsLength;
        System.out.println("Shortest common supersequence length - " + scsLength);

    }

    public void printSCS() {
        int row = s1.length();
        int col = s2.length();
        while (row > 0 && col > 0) {
            if (s1.charAt(row - 1) == s2.charAt(col - 1)) {
                System.out.println(s1.charAt(row - 1));
                row--;
                col--;
            } else {
                if (dpTable[row][col-1] > dpTable[row-1][col]) {    // if left > above
                    System.out.println(s2.charAt(col - 1));
                    col--;
                } else {
                    System.out.println(s1.charAt(row - 1));
                    row--;
                }
            }
        }

        while (row > 0) {
            System.out.println(s1.charAt(row - 1));
            row--;
        }
        while (col > 0) {
            System.out.println(s2.charAt(col - 1));
            col--;
        }
    }

    public static void main(String[] args) {
        String s1 = "geek";
        String s2 = "eke";
        //ShortestCommonSupersequence scs = new ShortestCommonSupersequence(s1, s2);
        ShortestCommonSupersequence scs = new ShortestCommonSupersequence("brute", "groot");
        scs.solve();
        scs.printSCS();
    }
}
