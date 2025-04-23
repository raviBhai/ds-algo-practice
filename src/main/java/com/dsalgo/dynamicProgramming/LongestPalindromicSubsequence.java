package com.dsalgo.dynamicProgramming;

import com.sun.deploy.util.StringUtils;

/**
 * String s1 = agbcba
 * longest palindromic subsequence is abcba
 * output should be length - 5
 */

public class LongestPalindromicSubsequence {

    public static int lps(String s1) {
        String reverseOfS1 = new StringBuilder(s1).reverse().toString();
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(s1, reverseOfS1);
        return lcs.solve();
    }

    public static void main(String[] args) {
        System.out.println("Length of Longest palindromic subsequence is - " + lps("agbcba"));
    }

}
