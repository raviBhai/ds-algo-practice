package com.dsalgo.dynamicProgramming;

public class LongestCommonSubsequence {
    private String s1;
    private String s2;
    int[][] dpTable;

    public LongestCommonSubsequence() {

    }

    public LongestCommonSubsequence(String s1, String s2) {
        this.s1 = s1;
        this.s2 = s2;
        this.dpTable = new int[s1.length() + 1][s2.length() + 1];
    }

    public void solve() {
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dpTable[i][j] = 1 + dpTable[i - 1][j - 1];
                } else {
                    dpTable[i][j] = Math.max(dpTable[i - 1][j], dpTable[i][j - 1]);
                }
            }
        }
        System.out.println("Longest common sub is - " + dpTable[s1.length()][s2.length()]);
    }

    public void printLCS() {
        for (int row = s1.length(), col = s2.length(); row >0 ;) {
            if (row > 0 && col > 0) {
                if (s1.charAt(row - 1) == s2.charAt(col - 1)) {
                    System.out.println(s1.charAt(row - 1));
                    row--;
                    col--;
                } else {
                    if (dpTable[row][col] == dpTable[row][col - 1]) {
                        col--;
                    } else {
                        if (dpTable[row][col] == dpTable[row - 1][col]) {
                            row--;
                        }
                    }
                }
            } else {
                row--;
                col--;
            }
        }
    }

    // Use this instead of above method
    public void printLCS2() {
        int row = s1.length();
        int col = s2.length();
            while (row > 0 && col > 0) {
                if (s1.charAt(row - 1) == s2.charAt(col - 1)) {
                    System.out.println(s1.charAt(row - 1));
                    row--;
                    col--;
                } else {
                    if (dpTable[row][col] == dpTable[row][col - 1]) {
                        col--;
                    } else {
                        row--;
                    }
                }
            }
    }


    public static void main(String[] args) {
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();
        String s1 = "AGGTAB";
        String s2 = "GXTXAYB";

        char[] X=s1.toCharArray();
        char[] Y=s2.toCharArray();
        int m = X.length;
        int n = Y.length;

        System.out.println("Length of LCS is" + " " + lcs.dp_lcs( X, Y, m, n ) );

        //Better way
        lcs = new LongestCommonSubsequence(s1, s2);
        lcs.solve();
        lcs.printLCS();
        System.out.println("***********");
        lcs.printLCS2();
    }

    public int dp_lcs(char[] s1, char[] s2, int m, int n) {
        int[][] L = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j ==0) {
                    L[i][j] = 0;
                } else if (s1[i-1] == s2[j-1]) {
                    L[i][j] = 1 + L[i - 1][j - 1];
                } else {
                    L[i][j] = Math.max(L[i - 1][j], L[i][j - 1]);
                }
            }
        }
        return L[m][n];
    }
}
