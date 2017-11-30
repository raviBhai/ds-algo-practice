package com.dsalgo.ctci;

public class OneAway {
    public static final int MAX_CHARS = 256;
    public static void main(String[] args) {
        System.out.println(isOneAway("ravi", "aavi"));
    }

    private static boolean isOneAway(String s1, String s2) {

        if (s1.length() == s2.length()) {
            return oneEditReplace(s1, s2);
        } else if (s1.length() + 1 == s2.length()) {
                return oneEditInsert(s1, s2);
        } else if (s1.length() - 1 == s2.length()) {
            return oneEditInsert(s2, s1);
        }

        return false;
    }

    // s1.length < s2.length
    private static boolean oneEditInsert(String s1, String s2) {
        int i = 0, j = 0;

        while (i < s1.length() && j < s2.length()) {
            if (s1.charAt(i) == s2.charAt(j)) {
                i++;
                j++;
            } else {
                if (i != j) {
                    return false;
                }
                j++;
            }
        }
        return true;
    }

    private static boolean oneEditReplace(String s1, String s2) {
        int i;
        boolean oneChangeFound = false;
        for (i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (oneChangeFound) {
                    return false;
                }
                oneChangeFound = true;
            }
        }
        return true;
    }
}
