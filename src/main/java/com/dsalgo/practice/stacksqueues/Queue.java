package com.dsalgo.practice.stacksqueues;

public interface Queue<T> {
    public void insert(T data);
    public T remove();
    public T peek();
    public boolean isEmpty();
    public boolean isFull();
    public int size();
}
