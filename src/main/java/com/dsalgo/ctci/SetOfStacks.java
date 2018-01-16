package com.dsalgo.ctci;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

public class SetOfStacks {
    private int threshold;
    List<Stack<Integer>> stackList = new ArrayList<Stack<Integer>>();

    public SetOfStacks(int threshold) {
        this.threshold = threshold;
    }

    public void push(int data) {
        if (stackList.isEmpty()) {
            Stack<Integer> stack = new Stack<Integer>();
            stackList.add(stack);
        }
        Stack<Integer> stack = stackList.get(stackList.size() - 1);
        if (stack.size() == threshold) {
            Stack<Integer> newStack = new Stack<Integer>();
            newStack.push(data);
            stackList.add(newStack);
        } else {
            stack.push(data);
        }
    }

    public int pop() {
        if (!stackList.isEmpty()) {
            Stack<Integer> stack = stackList.get(stackList.size() - 1);
            int pop = stack.pop();
            if (stack.size() == 0) {
                stackList.remove(stack);
            }
            return pop;
        }
        throw new EmptyStackException();
    }

    public int popAt(int index) {
        Stack<Integer> stack = stackList.get(index);
        int pop = stack.pop();
        if (stack.size() == 0) {
            stackList.remove(stack);
        }
        return pop;
    }
}

class ClientSetOfStacks {
    public static void main(String[] args) {
        SetOfStacks setOfStacks = new SetOfStacks(3);

        setOfStacks.push(1);
        setOfStacks.push(2);
        setOfStacks.push(3);
        setOfStacks.push(4);
        setOfStacks.push(5);
        setOfStacks.push(6);
        setOfStacks.push(7);
        setOfStacks.push(8);

        System.out.println(setOfStacks.pop());
        System.out.println(setOfStacks.pop());
        System.out.println(setOfStacks.pop());
        System.out.println(setOfStacks.pop());

        setOfStacks.push(41);
        setOfStacks.push(42);
        setOfStacks.push(43);

        System.out.println(setOfStacks.pop());
    }
}
