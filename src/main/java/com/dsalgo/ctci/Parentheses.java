package com.dsalgo.ctci;

import java.util.HashSet;
import java.util.Set;

public class Parentheses {
    public static void main(String[] args) {
        System.out.println(getParentheses(3));
    }

    public static Set<String> getParentheses(int n) {
        Set<String> set = new HashSet<>();
        if (n == 0) {
            set.add("");
        } else {
            Set<String> previous = getParentheses(n - 1);
            for (String s : previous) {
                set.add("()" + s);
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == '(') {
                        String left = s.substring(0, i + 1);
                        String right = s.substring(i + 1, s.length());
                        String op = left + "()" + right;
                        set.add(op);
                    }
                }
            }
        }
        return set;
    }
}
