package com.dsalgo.stack;

import java.util.Stack;

public class _8MinStackIn_O_1_Space {

    private Stack<Integer> stack;
    private int minElement = -1;

    public _8MinStackIn_O_1_Space() {
        this.stack = new Stack<>();
    }

    public void push(int x) {
        if (stack.isEmpty()) {
            stack.push(x);
            minElement = x;
        } else if (stack.peek() >= minElement) {
            stack.push(x);
        } else if (stack.peek() < minElement) {
            stack.push(2 * x - minElement);
            minElement = x;
        }
    }

    public int pop() {
        if (stack.isEmpty()) {
            return -1;
        } else if (stack.peek() >= minElement) {
            return stack.pop();
        } else { // if (stack.peek() < minElement) {
            int temp = minElement;
            minElement = 2 * minElement - stack.peek();
            stack.pop();
            return temp;
        }
    }

    public int top () {
        if (stack.isEmpty()) {
            return -1;
        } else if (stack.peek() >= minElement) {
            return stack.peek();
        } else { // if (stack.peek() < minElement) {
            return minElement;
        }
    }

    public int min() {
        if (stack.isEmpty()) {
            return -1;
        }
        return minElement;
    }
}
