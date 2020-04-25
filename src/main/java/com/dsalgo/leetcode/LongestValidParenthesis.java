package com.dsalgo.leetcode;

import java.util.List;
import java.util.Stack;

public class LongestValidParenthesis {
    public int longestValidParentheses(String s) {

        if (s == null || s.isEmpty()) {
            return 0;
        }

        Stack<Character> stack = new Stack<>();
        char ch;
        int count = 0;
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i);
            switch (ch) {
                case '(':
                    stack.push(ch);
                    break;
                case ')':
                    if (!stack.isEmpty()) {
                        stack.pop();
                        count += 2;
                        result = Math.max(result, count);
                    } else {
                        count = 0;
                    }
                    break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        LongestValidParenthesis longestValidParenthesis = new LongestValidParenthesis();
        System.out.println(longestValidParenthesis.longestValidParentheses("()(()"));
    }
}
