package com.dsalgo.dynamicProgramming.mcm;


import java.util.HashMap;
import java.util.Map;

public class _4ScrambledString {

    private static boolean solve(String a, String b) {
        if (a.equals(b)) {
            return true;
        }

        // below if clause never passes. The recursive function solve(a,b) is always called with some non-empty a and b
        if (a.length() == 0 || b.length() == 0) {
            System.out.println();
        }

        // a and b will always have same length. IF a and b are not equal and their length is 1, it means, this is a base case
        if (a.length() <= 1) {
            return false;
        }

        boolean ans = false;
        int n = a.length();
        for (int i = 1; i <= n - 1; i++) {

            boolean condition1 = solve(a.substring(0, i), b.substring(n - i, n))
                    && solve(a.substring(i, n), b.substring(0, n-i));

            boolean condition2 = solve(a.substring(0, i), b.substring(0, i))
                    && solve(a.substring(i, n), b.substring(i, n));

            if (condition1 || condition2) {
                ans = true;
                break;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String a = "great";
        String b = "grate";
        if (a.length() != b.length()) {
            System.out.println(false);
        }
        else if (a.length() == 0 && b.length() == 0) {
            System.out.println(true);
        }
        else {
            System.out.println(solve(a, b));
            System.out.println(solve("great", "ategr"));
            System.out.println(solve("great", "ategb"));
        }
    }
}

class MemoizedScrambledString {

    private static Map<String, Boolean> map = new HashMap<>();

    private static boolean solve(String a, String b) {
        if (a.equals(b)) {
            return true;
        }
        if (a.length() <= 1) {
            return false;
        }
        String key = a + "_" + b;
        if (map.containsKey(key)) {
            return map.get(key);    // map can have either TRUE or FALSE
        }

        boolean ans = false;
        int n = a.length();
        for (int i = 1; i <= n - 1; i++) {

            boolean condition1 = solve(a.substring(0, i), b.substring(n - i, n))
                    && solve(a.substring(i, n), b.substring(0, n-i));

            boolean condition2 = solve(a.substring(0, i), b.substring(0, i))
                    && solve(a.substring(i, n), b.substring(i, n));

            if (condition1 || condition2) {
                ans = true;
                break;
            }
        }
        map.put(key, ans);
        return ans;
    }

    public static void main(String[] args) {
        String a = "great";
        String b = "grate";
        if (a.length() != b.length()) {
            System.out.println(false);
        }
        else if (a.length() == 0 && b.length() == 0) {
            System.out.println(true);
        }
        else {
            System.out.println(solve(a, b));
            System.out.println(solve("great", "ategr"));
            System.out.println(solve("great", "ategb"));
        }
    }
}