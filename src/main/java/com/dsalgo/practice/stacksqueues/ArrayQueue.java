package com.dsalgo.practice.stacksqueues;

public class ArrayQueue<T> implements Queue<T> {
    private int front;
    private int rear;
    private int maxSize;
    private int nElems;
    private T[] array;

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        this.front = 0;
        this.rear = -1;
        this.nElems = 0;
        this.array = (T[])new Object[maxSize];
    }

    public void insert(T data) {
        if (rear == maxSize - 1) {
            rear = -1;
        }
        array[++rear] = data;
        nElems++;
    }

    public T remove() {
        T data = array[front++];
        nElems--;
        if (front == maxSize) {
            front = 0;
        }
        return data;
    }

    public T peek() {
        return array[front];
    }

    public boolean isEmpty() {
        return nElems == 0;
    }

    public boolean isFull() {
        return nElems == maxSize;
    }

    public int size() {
        return nElems;
    }
}
