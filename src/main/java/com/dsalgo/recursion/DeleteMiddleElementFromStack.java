package com.dsalgo.recursion;


import java.util.Stack;

public class DeleteMiddleElementFromStack {

    public static void solve(Stack<Integer> stack, int k) {
        if (k == 1) {
            stack.pop();
            return;
        }
        int last = stack.pop();
        solve(stack, k - 1);
        stack.push(last);
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        System.out.println("Before deleting - " + stack);
        int middleElementIndex = (stack.size() / 2) + 1;
        solve(stack, middleElementIndex);
        System.out.println("after deleting - " + stack);
    }
}
