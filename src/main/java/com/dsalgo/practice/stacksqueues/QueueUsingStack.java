package com.dsalgo.practice.stacksqueues;

public class QueueUsingStack<T> implements Queue<T> {
    private Stack<T> putStack;
    private Stack<T> popStack;
    private int maxSize;

    public QueueUsingStack(int maxSize) {
        putStack = new ArrayStack<T>(maxSize);
        popStack = new ArrayStack<T>(maxSize);
        this.maxSize = maxSize;
    }

    public void insert(T data) {
        putStack.push(data);
    }

    public T remove() {
        if (popStack.isEmpty()) {
            copyFrom(putStack, popStack);
        }
        T pop = popStack.pop();
        return pop;
    }

    private void copyFrom(Stack<T> fromStack, Stack<T> toStack) {
        while (!fromStack.isEmpty()) {
            toStack.push(fromStack.pop());
        }
    }

    public T peek() {
        if (popStack.isEmpty()) {
            copyFrom(putStack, popStack);
        }
        T peek = popStack.peek();
        return peek;
    }

    public boolean isEmpty() {
        return putStack.isEmpty() && popStack.isEmpty();
    }

    public boolean isFull() {
        return maxSize == (putStack.size() + popStack.size());
    }

    public int size() {
        return putStack.size() + popStack.size();
    }
}

class QueueUsingStackClient {
    public static void main(String[] args) {
        Queue queue = new QueueUsingStack<Integer>(5);

        System.out.println(queue.isEmpty());
        System.out.println(queue.isFull());
        System.out.println(queue.size());

        queue.insert(1);
        queue.insert(2);
        queue.insert(3);

        System.out.println(queue.isEmpty());
        System.out.println(queue.isFull());
        System.out.println(queue.size());

        System.out.println(queue.peek());

        System.out.println(queue.isEmpty());
        System.out.println(queue.isFull());
        System.out.println(queue.size());

        System.out.println(queue.remove());

        queue.insert(4);
        queue.insert(5);
        queue.insert(6);

        System.out.println(queue.isEmpty());
        System.out.println(queue.isFull());
        System.out.println(queue.size());

        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());

        System.out.println(queue.isEmpty());
        System.out.println(queue.isFull());
        System.out.println(queue.size());
    }
}
