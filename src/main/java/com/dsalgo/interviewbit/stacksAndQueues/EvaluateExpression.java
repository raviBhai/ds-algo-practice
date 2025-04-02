package com.dsalgo.interviewbit.stacksAndQueues;

import java.util.Stack;

public class EvaluateExpression {

    public static void main(String[] args) {

        //String s = "2+(13-4/2*4)";
        //String s = "5+16-((9-6)-(4-2))";
        //String s = "22+(2-4)";
        //String s = "6+9-12";
        //String s = "((1024))";
        String s = "123+45";
        System.out.println(evaluate(s));
        System.out.println(evaluate2("2+3+4+5"));

    }

    public static int evaluate2(String exp) {
        String[] arr1 = exp.split("\\+");
        int add = 0;
        for(String s1 : arr1) {
            String[] arr2 = s1.split("\\*");
            int multiplier = Integer.parseInt(arr2[0]);
            for (int i = 1; i < arr2.length; i++) {
                multiplier *= Integer.parseInt(arr2[i]);
            }
            add += multiplier;
        }
        return add;
    }

    public static int evaluate(String exp) {
        int num = 0;
        Stack<Integer> integerStack = new Stack<>();
        Stack<Character> charStack = new Stack<>();
        char ch1;
        for (int i = 0; i < exp.length(); i++) {
            if (exp.charAt(i) >= '0' && exp.charAt(i) <= '9') {
                for (; i < exp.length(); i++) {
                    if (exp.charAt(i) >= '0' && exp.charAt(i) <= '9') {
                        num = num * 10 + (exp.charAt(i) - '0');
                    } else {
                        i--;
                        break;
                    }
                }
                integerStack.push(num);
                num = 0;
            } else {
                ch1 = exp.charAt(i);
                switch (ch1) {
                    case '(':
                        charStack.push(exp.charAt(i));
                        break;
                    case ')':
                        handleCloseParenthesis(integerStack, charStack);
                        break;
                    case '+':
                    case '-':
                        balance(integerStack, charStack, 1);
                        charStack.push(ch1);
                        break;
                    case '*':
                    case '/':
                        balance(integerStack, charStack, 2);
                        charStack.push(ch1);
                        break;

                }
            }
        }

        handleResult(integerStack, charStack);
        return integerStack.pop();
    }

    private static void balance(Stack<Integer> integerStack, Stack<Character> charStack, int prec1) {
        char ch2;
        int prec2 = 0;
        while (!charStack.isEmpty()) {
            ch2 = charStack.pop();
            if (ch2 == '(') {
                charStack.push(ch2);
                break;
            }
            if (ch2 == '*' || ch2 == '/') {
                prec2 = 2;
            } else if (ch2 == '+' || ch2 == '-') {
                prec2 = 1;
            }
            if (prec1 > prec2) {
                charStack.push(ch2);
                break;
            } else {
                solveIntStack(integerStack, ch2);
            }
        }
    }

    private static void solveIntStack(Stack<Integer> integerStack, char ch2) {
        int n2 = integerStack.pop();
        int n1 = integerStack.pop();
        switch (ch2) {
            case '+':
                integerStack.push(n1 + n2);
                break;
            case '-':
                integerStack.push(n1 - n2);
                break;
            case '*':
                integerStack.push(n1 * n2);
                break;
            case '/':
                integerStack.push(n1 / n2);
                break;
        }
    }

    private static void handleResult(Stack<Integer> integerStack, Stack<Character> charStack) {
        char ch2;
        while (!charStack.isEmpty()) {
            ch2 = charStack.pop();
            if (ch2 == '+' || ch2 == '-' || ch2 == '*' || ch2 == '/') {
                solveIntStack(integerStack, ch2);
            }
        }
    }

    private static void handleCloseParenthesis(Stack<Integer> integerStack, Stack<Character> charStack) {
        char ch2;
        while (!charStack.isEmpty()) {
            ch2 = charStack.pop();
            if (ch2 == '+' || ch2 == '-' || ch2 == '*' || ch2 == '/') {
                solveIntStack(integerStack, ch2);
            } else if (ch2 == '('){
                break;
            }
        }
    }
}
