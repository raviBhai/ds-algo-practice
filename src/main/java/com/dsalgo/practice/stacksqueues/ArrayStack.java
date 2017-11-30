package com.dsalgo.practice.stacksqueues;

import java.lang.reflect.Array;

public class ArrayStack<T> implements Stack<T>{
    private int maxSize;
    private T[] array;
    private int top;

    public ArrayStack(int size) {
        maxSize = size;
        array = (T[]) new Object [size];
        top = -1;
    }

    public void push(T data) {
        array[++top] = data;
    }

    public T pop() {
        return array[top--];
    }

    public T peek() {
        return array[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return (top == maxSize - 1);
    }

    public int size() {
        return maxSize - (maxSize - (top + 1));
    }
}
