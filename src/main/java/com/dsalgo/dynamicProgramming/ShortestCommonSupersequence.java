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
        System.out.println("Shortes common supersequence length - " + scsLength);
    }

    public static void main(String[] args) {
        String s1 = "geek";
        String s2 = "eke";
        ShortestCommonSupersequence scs = new ShortestCommonSupersequence(s1, s2);
        scs.solve();
    }
}
