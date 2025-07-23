package com.dsalgo.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * For an N-digit binary number, all its prefixes should have more 1s than 0s
 * Print all such N-digit binary numbers for given input N.
 *
 * All prefixes of 1101 -
 * 1101
 * 110
 * 11
 * 1
 *
 * For N = 3, output will be -
 * 111
 * 110
 * 101
 */
public class _11PrintNBinary {

    public static void solve(int n) {
        List<String> list = new ArrayList<>();
        solve(0, 0, n, "", list);
        System.out.println(list);
    }

    private static void solve(int ones, int zeros, int n, String op, List<String> list) {
        if (n == 0) {
            list.add(op);
            return;
        }
        if (ones > zeros) {     // can add a zero
            String op1 = op;
            op1 = op1 + "0";
            solve(ones, zeros + 1, n - 1, op1, list);
        }

        // always add a 1
        String op2 = op;
        op2 = op2 + "1";
        solve(ones + 1, zeros, n - 1, op2, list);
    }

    public static void main(String[] args) {
        solve(3);
    }
}
