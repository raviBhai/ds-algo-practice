package com.dsalgo.ctci;

import java.util.Stack;

public class SortStack {
    private Stack<Integer> inputStack;
    private Stack<Integer> bufferStack;
    private int temp;

    public SortStack() {
        inputStack = new Stack<Integer>();
        bufferStack = new Stack<Integer>();
    }

    public void push(int data) {
        inputStack.push(data);
    }

    public void sortAscending() {
        while (!inputStack.isEmpty()) {
            temp = inputStack.pop();
            while (!bufferStack.isEmpty() && bufferStack.peek() > temp) {
                inputStack.push(bufferStack.pop());
            }
            bufferStack.push(temp);
        }

        //populate input stack in ascending order
        while (!bufferStack.isEmpty()) {
            inputStack.push(bufferStack.pop());
        }

        //print input stack in ascending order
        while (!inputStack.isEmpty()) {
            System.out.println(inputStack.pop());
        }
    }

    public void sortDescending() {
        while (!inputStack.isEmpty()) {
            temp = inputStack.pop();
            while (!bufferStack.isEmpty() && bufferStack.peek() < temp) {
                inputStack.push(bufferStack.pop());
            }
            bufferStack.push(temp);
        }

        //populate input stack in descending order
        while (!bufferStack.isEmpty()) {
            inputStack.push(bufferStack.pop());
        }

        //print input stack in descending order
        while (!inputStack.isEmpty()) {
            System.out.println(inputStack.pop());
        }
    }
}

class ClientSortStack {
    public static void main(String[] args) {
        SortStack stack = new SortStack();
        stack.push(1);
        stack.push(2);
        stack.push(5);
        stack.push(4);
        stack.push(3);

        stack.sortAscending();
        stack.sortDescending();
    }
}
