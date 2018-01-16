package com.dsalgo.ctci;

import java.util.EmptyStackException;
import java.util.Stack;

public class MinStack extends Stack<Integer> {
    private Stack<Integer> minData = new Stack<Integer>();

    public void push(int input) {
        if (input <= min()) {
            minData.push(input);
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
            return Integer.MAX_VALUE;
        }
        return minData.peek();
    }
}
