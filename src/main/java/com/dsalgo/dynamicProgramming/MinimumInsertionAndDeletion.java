package com.dsalgo.dynamicProgramming;

/**
 * Given Strings s1 and s2.
 * Get minimum insertion and deletion to convert s1 to s2.
 * Do not count replace.
 * If insertion, deletion, replace - all 3 counted, then it becomes MinimumEditDistance problem
 * S1 = heap
 * S2 = pea
 * to convert s1 to s2 -
 *      insert "p" at the start of s1
 *      remove h from s1
 *      remove p from s1
 *      Ans - Insertion - 1, Deletion - 2
 */
public class MinimumInsertionAndDeletion {

    public static void minimumInsertionAndDeletion(String s1, String s2) {
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(s1, s2);
        int lcsLength = lcs.solve();
        System.out.println("Deletion - " + (s1.length() - lcsLength));
        System.out.println("Insertion - " + (s2.length() - lcsLength));
    }

    public static void main(String[] args) {
        String s1 = "heap";
        String s2 = "pea";
        MinimumInsertionAndDeletion.minimumInsertionAndDeletion(s1, s2);
    }
}
