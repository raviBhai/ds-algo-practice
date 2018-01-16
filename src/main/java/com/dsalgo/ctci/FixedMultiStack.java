package com.dsalgo.ctci;

import java.util.EmptyStackException;

public class FixedMultiStack {
    int numberOfStacks = 3;
    int stackCapacity;
    int[] values;
    int[] sizes;

    public FixedMultiStack(int stackSize) {
        this.stackCapacity = stackSize;
        values = new int[stackSize * numberOfStacks];
        sizes = new int[numberOfStacks];
    }

    public void push(int stackNumber, int value) {
        if (!isFull(stackNumber)) {
            sizes[stackNumber]++;
            values[indexOfTop(stackNumber)] = value;
        }
    }

    public int pop(int stackNumber) {
        if (!isEmpty(stackNumber)) {
            int top = values[indexOfTop(stackNumber)];
            sizes[stackNumber]--;
            return top;
        }
        throw new EmptyStackException();
    }

    public int peek(int stackNumber) {
        if (!isEmpty(stackNumber)) {
            return values[indexOfTop(stackNumber)];
        }
        throw new EmptyStackException();
    }

    public boolean isFull(int stackNumber) {
        return sizes[stackNumber] == stackCapacity;
    }

    public boolean isEmpty(int stackNumber) {
        return sizes[stackNumber] == 0;
    }

    public int indexOfTop(int stackNumber) {
        int offset = stackNumber * stackCapacity;
        int top = offset - 1 + sizes[stackNumber];
        return top;
    }
}

class ClientFixedMultiStack {
    public static void main(String[] args) {
        FixedMultiStack multiStack = new FixedMultiStack(5);
        multiStack.push(0,1);
        multiStack.push(0,2);
        multiStack.push(0,3);
        multiStack.push(0,4);
        multiStack.push(0,5);

        multiStack.push(1,6);
        multiStack.push(1,7);
        multiStack.push(1,8);

        multiStack.push(2,9);
        multiStack.push(2,10);

        System.out.println(multiStack.pop(0));
        System.out.println(multiStack.pop(1));
        System.out.println(multiStack.pop(2));
        System.out.println(multiStack.pop(0));
        System.out.println(multiStack.pop(1));



    }
}
