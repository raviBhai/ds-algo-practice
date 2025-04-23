package com.dsalgo.dynamicProgramming;

public class MinimumDeletionToConvertToPalindromicString {

    public static int solve(String s1) {
        int lpsLength = LongestPalindromicSubsequence.lps(s1);
        return s1.length() - lpsLength;
    }

    public static void main(String[] args) {
        System.out.println("Minimum Deletion To Convert To Palindromic Subsequence - " + solve("agbcba"));
    }
}

class MinimumInsertionToConvertToPalindromicString {
    /**
     * Input s1 = "agbcba"
     * If a "g" is added just before last "a", then the string becomes a palindrome.
     * Hence, minimum insertion is 1.
     *
     * This minimum count is the same as minimum deletion required to convert string to palindrome.
     * In that case, one "g" had to be deleted.
     *
     * Hence, minimum-insertion is same as minimum-deletion to convert string to a palindrome.
     */
}