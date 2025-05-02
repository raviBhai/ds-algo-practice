package com.dsalgo.recursion;

import java.util.ArrayList;
import java.util.List;

public class BalancedParentheses {

    public static void solve(int n) {
        List<String> list = new ArrayList<>();
        solve(n, n, "",list);
        System.out.println(list);
    }

    private static void solve(int open, int close, String op, List<String> list) {
        if (open == 0 && close == 0) {
            list.add(op);
            return;
        } else if (open == 0 && close > 0) {
            String op1 = op + ")";
            solve(open, close - 1, op1, list);
        } else if (open == close) {
            String op1 = op + "(";
            solve(open - 1, close, op1, list);
        } else { // open != 0 && close != 0 && open < close && open != close
            String op1 = op + "(";
            String op2 = op + ")";
            solve(open - 1, close, op1, list);
            solve(open, close-1, op2, list);
        }
    }

    public static void main(String[] args) {
        solve(3);
    }
}
