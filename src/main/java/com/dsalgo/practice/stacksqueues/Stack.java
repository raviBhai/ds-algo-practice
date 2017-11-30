package com.dsalgo.practice.stacksqueues;

public interface Stack<T> {
    public void push(T data);
    public T pop();
    public T peek();
    public boolean isEmpty();
    public boolean isFull();
    public int size();
}
