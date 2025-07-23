package com.dsalgo.dynamicProgramming.mcm;

/**
 * Minimum partitions required to break the input string
 * such that the resultant sub-strings are palindromes
 *
 * Eg - nitix
 * 2 partitions are needed to break it in 3 substrings - n, iti, x
 * The 3 resultant sub-strings are palindromes
 *
 * Return minimum such partitions.
 */
public class _2PalindromePartition {

    private static int solve(String s, int i, int j) {
        if (i >= j) {
            return 0;
        }

        if (isPalindrome(s, i, j)) {
            return 0;
        }

        int min = Integer.MAX_VALUE;
        for (int k = i; k <= j - 1; k++) {
            int minParitions = 1 + solve(s, i, k) + solve(s, k + 1, j);
            min = Math.min(min, minParitions);
        }
        return min;
    }

    public static boolean isPalindrome(String s, int i, int j) {
        while (i <= j) {
            if (s.charAt(i) == s.charAt(j)) {
                i++;
                j--;
            } else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "nitix";
        System.out.println(solve(s, 0, s.length() - 1));
    }
}

class MemoizationPalindromePartition {
    private static int[][] t;

    private static int solve(String s, int i, int j) {
        if (i >= j) {
            return 0;
        }

        if (_2PalindromePartition.isPalindrome(s, i, j)) {
            return 0;
        }
        if (t[i][j] != -1) {
            return t[i][j];
        }
        int min = Integer.MAX_VALUE;
        for (int k = i; k <= j - 1; k++) {
            int minParitions = 1 + solve(s, i, k) + solve(s, k + 1, j);
            min = Math.min(min, minParitions);
        }
        t[i][j] = min;
        return t[i][j];
    }

    public static void main(String[] args) {
        String s = "nitix";
        t = new int[s.length() + 1][s.length() + 1];
        init(t);
        System.out.println(solve(s, 0, s.length() - 1));
    }

    public static void init(int[][] t) {
        for (int i = 0; i < t.length; i++) {
            for (int j = 0 ; j < t[0].length; j++) {
                t[i][j] = -1;
            }
        }
    }
}

class OptimizedMemoizationPalindromePartition {
    private static int[][] t;

    private static int solve(String s, int i, int j) {
        if (i >= j) {
            return 0;
        }

        if (_2PalindromePartition.isPalindrome(s, i, j)) {
            return 0;
        }
        if (t[i][j] != -1) {
            return t[i][j];
        }
        int min = Integer.MAX_VALUE;
        for (int k = i; k <= j - 1; k++) {
            int left = t[i][k] != -1 ? t[i][k] : solve(s, i, k);
            t[i][k] = left;
            int right = t[k+1][j] != -1 ? t[k+1][j] : solve(s, k + 1, j);
            t[k + 1][j] = right;
            int minParitions = 1 + left + right;
            min = Math.min(min, minParitions);
        }
        t[i][j] = min;
        return t[i][j];
    }

    public static void main(String[] args) {
        String s = "nitix";
        t = new int[s.length() + 1][s.length() + 1];
        MemoizationPalindromePartition.init(t);
        System.out.println(solve(s, 0, s.length() - 1));
    }
}