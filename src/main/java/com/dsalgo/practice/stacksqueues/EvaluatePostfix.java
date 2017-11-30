package com.dsalgo.practice.stacksqueues;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class EvaluatePostfix {
    private Stack<Integer> stack;
    private String input;
    private Integer result;

    public EvaluatePostfix(String input) {
        this.input = input;
        this.stack = new ArrayStack<Integer>(input.length());
    }

    public int evaluate() {
        char ch;
        int num1, num2;
        for (int i = 0; i < input.length(); i++) {
            ch = input.charAt(i);
            if (ch >= '0' && ch <= '9') {
                stack.push(ch - '0');
            } else {
                num2 = stack.pop();
                num1 = stack.pop();

                switch (ch) {
                    case '+':
                        result = num1 + num2;
                        break;
                    case '-':
                        result = num1 - num2;
                        break;
                    case '*':
                        result = num1 * num2;
                        break;
                    case '/':
                        result = num1 / num2;
                        break;
                    default:
                        result = 0;
                        break;
                }
                stack.push(result);
            }
        }
        result = stack.pop();
        return result;
    }
}

class EvaluatePostfixClient {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String expression = br.readLine();
        ConvertToPostfix convertToPostfix = new ConvertToPostfix(expression);
        String postfixExpression = convertToPostfix.doPostfix();
        EvaluatePostfix evaluatePostfix = new EvaluatePostfix(postfixExpression);
        System.out.println(evaluatePostfix.evaluate());
    }
}
