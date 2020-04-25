package com.dsalgo.interviewbit.stacksAndQueues;

import java.util.Stack;

public class RedundantBraces {
    public static void main(String[] args) {
        RedundantBraces rb = new RedundantBraces();
        System.out.println(rb.braces("(a + (a + b))"));
        System.out.println(rb.braces("(a + ((a + b)))"));
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


    public int braces2(String A) {
        int result = 1;
        char ch, top;
        boolean isOprEnc = false;
        Stack<Character> stack = new Stack<>();
        outer:
        for (int i = 0; i < A.length(); i++) {
            ch = A.charAt(i);
            if (ch == '(' || ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                stack.push(ch);
            } else if (ch == ')') {
                while (!stack.isEmpty()) {
                    top = stack.pop();
                    if (top == '+' || top == '-' || top == '*' || top == '/') {
                        isOprEnc = true;
                    } else {
                        if (top == '(') {
                            if (!isOprEnc) {
                                result = 0;
                                break outer;
                            }
                        }
                        break;
                    }
                }
                isOprEnc = false;
            }
        }

        return result;
    }
}
