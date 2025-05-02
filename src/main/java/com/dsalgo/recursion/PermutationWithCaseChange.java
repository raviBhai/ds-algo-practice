package com.dsalgo.recursion;

import java.util.ArrayList;
import java.util.List;

public class PermutationWithCaseChange {

    public static void solve(String s) {
        List<String> list = new ArrayList<>();
        solve(s, "", list);
        System.out.println(list);
    }

    private static void solve(String ip, String op, List<String> list) {
        if (ip.length() == 0) {
            list.add(op);
            return;
        }
        String op1 = op;
        String op2 = op;

        op1 = op1 + ip.charAt(0);
        op2 = op2 + Character.toUpperCase(ip.charAt(0));

        ip = ip.substring(1);

        solve(ip, op1, list);
        solve(ip, op2, list);
    }

    public static void main(String[] args) {
        solve("ab");
    }
}
