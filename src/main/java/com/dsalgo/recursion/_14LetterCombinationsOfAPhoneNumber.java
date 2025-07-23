package com.dsalgo.recursion;

import java.util.ArrayList;
import java.util.List;

public class _14LetterCombinationsOfAPhoneNumber {

    private static final String[] map = {
            "0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    };

    private static void solve(String input, String output, List<String> list) {
        if (input.length() == 0) {
            list.add(output);
            return;
        }
        int digit = input.charAt(0) - '0';
        String s = map[digit];
        input = input.substring(1);

        for (int i = 0; i < s.length(); i++) {
            String op1 = output + s.charAt(i);
            solve(input, op1, list);
        }
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        solve("234", "", list);
        System.out.println(list);
    }
}
