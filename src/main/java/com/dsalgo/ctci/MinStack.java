package com.dsalgo.ctci;

import java.util.Stack;

public class MinStack extends Stack<Integer> {
    private Stack<Integer> minData = new Stack<Integer>();

    public void push(int input) {
        if (minData.isEmpty()) {
            minData.push(input);
        } else {
            if (input <= min()) {
                minData.push(input);
            }
        }
        super.push(input);
    }

    public Integer pop() {
        int top = super.pop();
        if (top == min()) {
            minData.pop();
        }
        return top;
    }

    public int min() {
        if (minData.isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return minData.peek();
    }
}
