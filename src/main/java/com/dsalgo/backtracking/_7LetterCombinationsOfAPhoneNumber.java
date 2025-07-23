package com.dsalgo.backtracking;

import java.util.ArrayList;
import java.util.List;

public class _7LetterCombinationsOfAPhoneNumber {

    private static final String[] map = {
            "0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    };

    private static void solve(String input, int index, StringBuffer temp, List<String> result) {
        if (index == input.length()) {
            result.add(temp.toString());
            return;
        }
        String s = map[input.charAt(index) - '0'];
        for (int i = 0; i < s.length(); i++) {
            temp.append(s.charAt(i));
            solve(input, index + 1, temp, result);
            temp.deleteCharAt(temp.length() - 1);
        }
    }

    public static void main(String[] args) {
        List<String> result = new ArrayList<>();
        StringBuffer sb = new StringBuffer("");
        solve("234", 0, sb, result);
        System.out.println(result);
    }
}
