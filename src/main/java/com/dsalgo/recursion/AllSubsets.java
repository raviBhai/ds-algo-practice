package com.dsalgo.recursion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AllSubsets {

    public static void solve(String ip, String op) {
        if (ip.length() == 0) {
            System.out.println(op);
            return;
        }
        String op1 = op;
        String op2 = op;

        op2 = op2 + ip.charAt(0);
        ip = ip.substring(1);

        solve(ip, op1);
        solve(ip, op2);
    }

    public static void solve(String ip, String op, List<String> list) {
        if (ip.length() == 0) {
            list.add(op);
            return;
        }
        String op1 = op;
        String op2 = op;

        op2 = op2 + ip.charAt(0);
        ip = ip.substring(1);

        solve(ip, op1, list);
        solve(ip, op2, list);
    }

    public static void main(String[] args) {
        String s1 = "abc";
        List<String> output = new ArrayList<>();
        solve(s1, "", output);
        System.out.println(output);
    }
}

/**
 * Input string = "aab"
 * All subsets will be - ["", "a", "a", "b", "ab", "ab", "aa", "aab"]
 * Avoid duplicates from the above subsets to get unique subsets
 * Output will be - ["", "a", "b", "ab", "aa", "aab"]
 */
class AllUniqueSubsets {
    public static void solve(String ip, String op, Set<String> set) {
        if (ip.length() == 0) {
            if (!set.contains(op)) {
                set.add(op);
            }
            return;
        }
        String op1 = op;
        String op2 = op;

        op2 = op2 + ip.charAt(0);
        ip = ip.substring(1);

        solve(ip, op1, set);
        solve(ip, op2, set);
    }

    public static void main(String[] args) {
        String s1 = "aab";
        Set<String> output = new HashSet<>();
        solve(s1, "", output);
        System.out.println(output);
    }
}