package com.dsalgo.recursion;

import java.util.Stack;

public class SortStackByRecursion {

    public static void sort(Stack<Integer> stack) {
        if (stack.size() == 1) {
            return;
        }
        int last = stack.pop();
        sort(stack);
        insert(stack, last);
    }

    private static void insert(Stack<Integer> stack, int temp) {
        if (stack.size() == 0 || stack.peek() <= temp) {
            stack.push(temp);
            return;
        }
        int last = stack.pop();
        insert(stack, temp);
        stack.add(last);
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(5);
        stack.push(0);
        stack.push(2);
        System.out.println("before sorting - " + stack);
        sort(stack);
        System.out.println("after sorting - " + stack);
    }
}
