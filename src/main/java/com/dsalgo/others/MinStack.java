package com.dsalgo.others;

import java.util.Stack;

public class MinStack {

    Stack<Integer> stack = new Stack<>();
    Stack<Integer> minStack = new Stack<>();

    public void push(int data) throws Exception {
        if (minStack.isEmpty()) {
            minStack.push(data);
        } else {
            if (data <= top()) {
                minStack.push(data);
            }
        }
        stack.push(data);
    }

    public int pop() throws Exception {
        if (!stack.isEmpty()) {
            Integer popped = stack.pop();
            if (popped == top()) {
                minStack.pop();
            }
            return popped;
        }
        throw new Exception("stack is empty");
    }

    public int top() throws Exception {
        if (!minStack.isEmpty()) {
            return minStack.peek();
        }
        throw new Exception("stack is empty");
    }
}
