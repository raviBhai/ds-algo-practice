package com.dsalgo.interviewbit.stacksAndQueues;

import java.util.Stack;

public class RedundantBraces {
    public static void main(String[] args) {
        RedundantBraces rb = new RedundantBraces();
        System.out.println(rb.braces("(a + (a + b))"));
    }

    public int braces(String A) {
        int redundant = 0;
        Stack<Character> stack = new Stack<>();
        char ch;
        char popped;
        boolean isOperatorEncountered = false;

        outer:
        for (int i = 0; i < A.length(); i++) {
            ch = A.charAt(i);
            if (ch == '(' || ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                stack.push(ch);
            } else if (ch == ')') {
                while (!stack.isEmpty()) {
                    popped = stack.pop();
                    if (popped == '+' || popped == '-' || popped == '*' || popped == '/') {
                        isOperatorEncountered = true;
                    } else if (popped == '('){
                        if (!isOperatorEncountered) {
                            redundant = 1;
                            break outer;
                        }
                        break;
                    }
                }
                isOperatorEncountered = false;
            }
        }

        return redundant;
    }
}
