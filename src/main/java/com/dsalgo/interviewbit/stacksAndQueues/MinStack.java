package com.dsalgo.interviewbit.stacksAndQueues;

import java.util.Stack;

public class MinStack {
    Stack<Integer> stack = new Stack<>();
    Stack<Integer> minStack = new Stack<>();

    public void push(int x) {
        if (minStack.isEmpty()) {
            minStack.push(x);
        } else {
            if (x <= getMin()) {
                minStack.push(x);
            }
        }
        stack.push(x);
    }

    public void pop() {
        if (!stack.isEmpty()) {
            int top = stack.pop();
            if (top == getMin()) {
                minStack.pop();
            }
        }
    }

    public int top() {
        if (!stack.isEmpty()) {
            return stack.peek();
        }
        return -1;
    }

    public int getMin() {
        if (minStack.isEmpty()) {
            return -1;
        }
        return minStack.peek();
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();

        System.out.println(minStack.getMin());
        minStack.push(10);
        System.out.println(minStack.getMin());

        minStack.push(20);
        System.out.println(minStack.getMin());

        minStack.push(5);
        System.out.println(minStack.getMin());

        minStack.push(3);
        System.out.println(minStack.getMin());

        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
    }
}
