package com.dsalgo.practice.stacksqueues;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BracketChecker {
    private String input;

    public BracketChecker(String input) {
        this.input = input;
    }

    public void check() {
        int length = input.length();
        ArrayStack<Character> stack = new ArrayStack<Character>(length);
        for (int i = 0; i < length; i++) {
            char ch = input.charAt(i);
            switch (ch) {
                case '{':
                case '[':
                case '(':
                    stack.push(ch);
                    break;

                case '}':
                case ']':
                case ')':
                    if (!stack.isEmpty()) {
                        char poppedChar = stack.pop();
                        if ((poppedChar == '{' && ch == '}') || (poppedChar == '[' && ch == ']') || (poppedChar == '(' && ch == ')')) {

                        } else {
                            System.out.println("Invalid");
                        }
                    } else {
                        System.out.println("Invalid");
                    }
                    break;
                default:
                    break;
            }
        }
        if (!stack.isEmpty()) {
            System.out.println("Invalid");
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word = br.readLine();
        BracketChecker bracketChecker = new BracketChecker(word);
        bracketChecker.check();
    }
}
