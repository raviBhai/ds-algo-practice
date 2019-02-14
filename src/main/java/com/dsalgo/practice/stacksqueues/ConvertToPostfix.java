package com.dsalgo.practice.stacksqueues;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ConvertToPostfix {
    private Stack<Character> stack;
    private String input;
    private String output;

    public ConvertToPostfix(String input) {
        this.stack = new ArrayStack<Character>(input.length());
        this.input = input;
        this.output = "";
    }

    public String doPostfix() {
        for (int i = 0; i< input.length(); i++) {
            char ch = input.charAt(i);
            switch (ch) {
                case '(':
                    stack.push(ch);
                    break;
                case ')':
                    handleClosedBracket(ch);
                    break;
                case '+':
                case '-':
                    output = output + ",";
                    handleOperator(ch, 1);
                    break;
                case '*':
                case '/':
                    output = output + ",";
                    handleOperator(ch, 2);
                    break;
                default:
                    output = output + ch;
                    break;
            }
        }

        while (!stack.isEmpty()) {
            output = output + ",";
            output = output + stack.pop();
        }
        return output;
    }

    private void handleOperator(char opThis, int prec1) {
        char opTop;
        while (!stack.isEmpty()) {
            opTop = stack.pop();
            if (opTop == '(') {
                stack.push(opTop);
                break;
            } else {
                int prec2;
                if (opTop == '+' || opTop == '-') {
                    prec2 = 1;
                } else {
                    prec2 = 2;
                }
                if (prec1 > prec2) {
                    stack.push(opTop);
                    break;
                } else {
                    output = output + ",";
                    output = output + opTop;
                }
            }
        }
        stack.push(opThis);
    }

    private void handleClosedBracket(char ch) {
        char popped;
        while (!stack.isEmpty()) {
            popped = stack.pop();
            if (popped != '(') {
                output = output + ",";
                output = output + popped;
            } else {
                break;
            }
        }
    }
}

class PostfixConverterClient {
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String expression = bufferedReader.readLine();
        ConvertToPostfix convertToPostfix = new ConvertToPostfix(expression);
        System.out.println(convertToPostfix.doPostfix());
    }
}
