package com.dsalgo.onlinetests;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class EvaluateExpression {
}

/*
You are building an educational website and want to create a simple calculator for students to use. The calculator will only allow addition and subtraction of positive integers.

We also want to allow parentheses in our input. Given an expression string using the "+", "-", "(", and ")" operators like "5+(16-2)", write a function to parse the string and evaluate the result.

Sample output:
  evaluate("5+16-((9-6)-(4-2))") => 20
  evaluate("22+(2-4)") => 20
  evaluate("6+9-12") => 3
  evaluate("((1024))") => 1024


 */

class Solution {
    public static void main(String[] args) {
        String expression2_1 = "5+16-((9-6)-(4-2))";
        String expression2_2 = "22+(2-4)";
        String expression2_3 = "6+9-12";
        String expression2_4 = "((1024))";



        //System.out.println(evaluate(expression1));
        //System.out.println(evaluate(expression2));
        //System.out.println(evaluate(expression3));

        // System.out.println(evaluate(expression4));
        // System.out.println(evaluate(expression5));
        // System.out.println(evaluate(expression6));
        //System.out.println(evaluate("1-1"));
        String exp = getExp(expression2_2);
        System.out.println(exp);
        System.out.println(evaluate(exp));
    }

    //"5+16-((9-6)-(4-2))";
    public static String getExp(String input) {
        String exp = "";
        Stack<Character> stack = new Stack<>();

        for(int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if(ch == '(') {
                stack.push(ch);
                String finalExp = "";
                String intermediateExp = "";
                while(!stack.isEmpty()) {
                    i++;
                    if(i < input.length()) {
                        char innerCh = input.charAt(i);
                        if(innerCh == '(') {
                            stack.push(innerCh);
                            finalExp += intermediateExp;
                            intermediateExp = "";
                        } else if(innerCh == ')') {
                            System.out.println(intermediateExp);
                            if (!intermediateExp.isEmpty()) {
                                int res = evaluate(intermediateExp);
                                intermediateExp = "";
                                finalExp += res;
                            }
                            stack.pop();
                        } else {
                            intermediateExp += String.valueOf(innerCh);
                        }
                    }
                }
                exp += evaluate(finalExp);
            } else {
                exp += String.valueOf(ch);
            }


        }

        return exp;
    }


    public static int evaluate(String input) {

        if (input.equals("")) {
            return 0;
        }

        String[] havingSubs = input.split("\\+");
        List<Integer> adds = new ArrayList<>();

        for(String s1 : havingSubs) {
            String[] havingAdds = s1.split("\\-");
            int sub = Integer.parseInt(havingAdds[0]);
            for(int i = 1; i < havingAdds.length; i++) {
                sub -= Integer.parseInt(havingAdds[i]);
            }

            adds.add(sub);
        }

        int res = 0;
        for(int i : adds) {
            res += i;
        }

        return res;
    }

}