package com.dsalgo.recursion;

import java.util.Stack;

public class ReverseStack {

    public static void solve(Stack<Integer> stack) {
        if (stack.size() == 1) {
            return;
        }
        int top = stack.pop();
        solve(stack);
        insertAtFront(stack, top);
    }

    private static void insertAtFront(Stack<Integer> stack, int element) {
        if (stack.size() == 0) {
            stack.push(element);
            return;
        }
        int top = stack.pop();
        insertAtFront(stack, element);
        stack.push(top);
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        System.out.println("Before - " + stack);
        solve(stack);
        System.out.println("after - " + stack);
    }
}
