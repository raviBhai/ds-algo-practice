package com.dsalgo.greedy;

/*
Given a string which has open and close brackets. It can have a "*"
"*" can be replaced with open, close or empty string

figure out if the input string can be a valid parenthesis.

Input - (*()
If we replace * with ")", it is a string with valid parenthesis

Input - (*(
Possible strings by replacing * are - (((, ((, ()(
None are valid.

Algorithm -
Let's say there was no "*"
If we need to check whether a string without a "*" has valid parenthesis, we can keep a counter.
Increment the counter on "(" and decrement on ")"
At the end if the counter value is 0, it is valid parenthesis.
Example - ()()
current char - 0 index - ( - counter=1
current char - 1 index - ) - counter=0
current char - 2 index - ( - counter=1
current char - 3 index - ) - counter=0
As counter=0, it is valid parenthesis.

Edge case - )(
current char - 0 index - ) - counter= -1
current char - 1 index - ( - counter=0
counter=0 at the end, but it is not valid.
We can say that if counter becomes negative anytime, then it is not valid.

Coming back to the string with "*", as "*" can be replaced with 3 different chars, there are multiple
paths, hence we can solve each with recursion

 */

public class _8_ValidParenthesis {

    /*

    this has time complexity as 3^N
    we can memoize it with index,counter which vary from 0 to N
    time complexity of memoization will be N^2

     */
    private static boolean recursion(String s, int index, int counter) {
        if (counter < 0) {
            return false;
        } else if (index == s.length()) {
            if (counter == 0) {
                return true;
            } else {
                return false;
            }
        } else if (s.charAt(index) == '(') {
            return recursion(s, index + 1, counter + 1);
        } else if (s.charAt(index) == ')') {
            return recursion(s, index + 1, counter - 1);
        } else if (s.charAt(index) == '*') {
            return
                    recursion(s, index + 1, counter + 1) ||
                            recursion(s, index + 1, counter - 1) ||
                            recursion(s, index + 1, counter) ;
        }

        return false;
    }

    public static void main(String[] args) {
        String s = "(*()";
        System.out.println(recursion(s, 0, 0));

        System.out.println(recursion("(*(", 0, 0));
    }
}
