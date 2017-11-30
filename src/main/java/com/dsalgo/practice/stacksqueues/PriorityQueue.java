package com.dsalgo.practice.stacksqueues;

public class PriorityQueue<T extends Comparable<T>> implements Queue<T> {
    private Object[] array;
    private int nElems;
    private int maxSize;

    public PriorityQueue(int maxSize) {
        this.array = new Object[maxSize];
        this.nElems = 0;
        this.maxSize = maxSize;
    }

    public void insert(T data) {
        if (nElems == 0) {
            array[nElems++] = data;
        } else {
            int j;
            for (j = nElems - 1; j >=0 ; j--) {
                if (data.compareTo((T)array[j]) > 0) {
                    array[j + 1] = array[j];
                } else {
                    break;
                }
            }
            array[j + 1] = data;
            nElems++;
        }
    }

    public T remove() {
        return (T)array[--nElems];
    }

    public T peek() {
        return (T)array[nElems - 1];
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
