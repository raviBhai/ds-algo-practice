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

    public int solve() {
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
        return dpTable[s1.length()][s2.length()];
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
                    if (dpTable[row][col] == dpTable[row][col - 1]) { // if current == left
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


class LcsRecursion {
    public static int lcs(String s1, String s2, int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }
        if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
            return 1 + lcs(s1, s2, m - 1, n - 1);
        } else {
            return Math.max(lcs(s1, s2, m, n - 1), lcs(s1, s2, m-1, n));
        }
    }

    public static void main(String[] args) {
        String s1 = "AGGTAB";
        String s2 = "GXTXAYB";
        System.out.println(lcs(s1, s2, s1.length(), s2.length()));
    }
}

class LcsMemoization {
    int[][] t;
    String s1;
    String s2;

    public LcsMemoization(String s1, String s2) {
        this.s1 = s1;
        this.s2 = s2;
        t = new int[s1.length() + 1][s2.length() + 1];
        init(t);
    }

    public int lcs(int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }
        if (t[m][n] != -1) {
            return t[m][n];
        }
        if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
            t[m][n] = 1 + lcs(m - 1, n - 1);
        } else {
            t[m][n] = Math.max(lcs(m, n - 1), lcs(m-1, n));
        }
        return t[m][n];
    }

    private void init(int[][] t) {
        for (int i = 0; i < t.length; i++) {
            for (int j = 0 ; j < t[0].length; j++) {
                t[i][j] = -1;
            }
        }
    }

    public static void main(String[] args) {
        String s1 = "AGGTAB";
        String s2 = "GXTXAYB";
        LcsMemoization lcs = new LcsMemoization(s1, s2);
        System.out.println(lcs.lcs(s1.length(), s2.length()));
    }

}

/**
 * Input S1 = "AABEBCDD"
 * Longest repeating subsequence is ABD as it comes 2 times
 * Oupput is length of ABD = 3
 */

class LongestRepeatingSubsequence {
    private String s1;
    private String s2;
    int[][] dpTable;

    public LongestRepeatingSubsequence() {

    }

    public LongestRepeatingSubsequence(String s) {
        this.s1 = s;
        this.s2 = s;
        this.dpTable = new int[s1.length() + 1][s2.length() + 1];
    }

    public int solve() {
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1) && (i != j)) {
                    dpTable[i][j] = 1 + dpTable[i - 1][j - 1];
                } else {
                    dpTable[i][j] = Math.max(dpTable[i - 1][j], dpTable[i][j - 1]);
                }
            }
        }
        System.out.println("Longest repeating sub is - " + dpTable[s1.length()][s2.length()]);
        return dpTable[s1.length()][s2.length()];
    }

    public static void main(String[] args) {
        LongestRepeatingSubsequence lrs = new LongestRepeatingSubsequence("AABEBCDD");
        lrs.solve();
    }
}


class SequencePatternMatching {

    /**
     * Given 2 strings
     * S1 = AXB
     * S2 = AYXCDB
     * Return true if S1 is a subsequence of S2, else return false
     *
     * Solution-1:
     * If S1 is a sub-sequence of S2, it would mean that S1 is same as LCS of S1 and S2
     * Hence, get the LCS(S1, S2) and compare with S1 and return result.
     *
     * Solution-2:
     * It is not required to calculate the LCS string.
     * Only calculating the length of LCS(S1, S2) is enough.
     * If S1.length < S2.length, then length of LCS(S1, S2) will be between 0 and S1.length
     * Hence if length of LCS(S1, S2) is same as S1, it would mean that LCS and S1 is same.
     * Hence, we can return result.
     *
     */

}