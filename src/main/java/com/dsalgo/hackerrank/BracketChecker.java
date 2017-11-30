package com.dsalgo.hackerrank;

import java.util.Scanner;
import java.util.Stack;


public class BracketChecker {
    public static boolean isBalanced(String expression) {
        Stack<Character> characterStack = new Stack<Character>();
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            switch (ch) {
                case '{':
                case '[':
                case '(':
                    characterStack.push(ch);
                    break;
                case '}':
                case ']':
                case ')':
                    if (!characterStack.isEmpty()) {
                        Character pop = characterStack.pop();
                        if (pop == null) {
                            return false;
                        }
                        if ((ch == '}' && pop != '{')
                                || (ch == ']' && pop != '[')
                                || (ch == ')' && pop != '(')) {
                            return false;
                        }
                    } else {
                        return false;
                    }

                    break;
            }
        }
        if (!characterStack.isEmpty()) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
       /* Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int a0 = 0; a0 < t; a0++) {
            String expression = in.next();
            System.out.println( (isBalanced(expression)) ? "YES" : "NO" );
        }*/
        System.out.println( (isBalanced("{[(])}")) ? "YES" : "NO" );
    }
}
