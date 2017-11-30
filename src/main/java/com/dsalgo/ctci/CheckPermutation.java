package com.dsalgo.ctci;

import java.util.Arrays;

public class CheckPermutation {
    public static void main(String[] args) {
        System.out.println(isPermutation1("ravi", "avir"));
        System.out.println(isPermutation1("ravi", "abcd"));

        System.out.println(isPermutation2("ravi", "avir"));
        System.out.println(isPermutation2("ravi", "abcd"));
    }

    private static boolean isPermutation1(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        return sort(s1).equals(sort(s2));
    }

    private static String sort(String s) {
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }

    private static boolean isPermutation2(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        int[] chars = new int[256];
        int ch;
        for (int i = 0; i < s1.length(); i++) {
            ch = s1.charAt(i);
            chars[ch]++;
        }
        for (int i = 0; i < s2.length(); i++) {
            ch = s2.charAt(i);
            chars[ch]--;
            if (chars[ch] < 0) {
                return false;
            }
        }
        return true;
    }
}
