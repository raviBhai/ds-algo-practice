package com.dsalgo.recursion;

import java.util.ArrayList;
import java.util.List;

public class _7PermutationWithSpaces {

    public static void solve(String ip, String op, List<String> list) {
        if (ip.length() == 0) {
            list.add(op);
            return;
        }
        String op1 = op + "_" + ip.charAt(0);
        String op2 = op + ip.charAt(0);

        ip = ip.substring(1);

        solve(ip, op1, list);
        solve(ip, op2, list);
    }

    public static void solve(String input) {
        String output = input.substring(0, 1);
        input = input.substring(1);
        List<String> list = new ArrayList<>();
        solve(input, output, list);
        System.out.println(list);
    }

    public static void main(String[] args) {
        String input = "abc";
        solve(input);
    }
}
