package com.dsalgo.practice.stacksqueues;

public class StackUsingQueue<T> implements Stack<T> {
    private Queue<T> firstQueue;
    private Queue<T> secondQueue;
    private Queue<T> refQueue;
    private int maxSize;
    private int top;

    public int push = 0;
    public int pop = 0;

    public StackUsingQueue(int maxSize) {
        this.maxSize = maxSize;
        this.top = -1;
        firstQueue = new ArrayQueue<T>(maxSize);
        secondQueue = new ArrayQueue<T>(maxSize);
        refQueue = firstQueue;
    }

    public void push(T data) {
        push++;
        refQueue.insert(data);
    }

    public T pop() {
        T last;
        if (refQueue == firstQueue) {
            //copy from first to second
            last = copyAllButLastAndReturnLast(firstQueue, secondQueue);
            refQueue = secondQueue;
        } else {    //refQueue == secondQueue
            //copy from second to first
            last = copyAllButLastAndReturnLast(secondQueue, firstQueue);
            refQueue = firstQueue;
        }
        return last;
    }

    private T copyAllButLastAndReturnLast(Queue<T> fromQueue, Queue<T> toQueue) {
        T data;
        while (true) {
            if (fromQueue.size() == 1) {
                pop++;
                data = fromQueue.remove();
                break;
            } else {
                push++;
                pop++;
                toQueue.insert(fromQueue.remove());
            }
        }
        return data;
    }

    public T peek() {
        T top;
        if (refQueue == firstQueue) {
            //copy from first to second
            top = copyAllButLastAndReturnLast(firstQueue, secondQueue);
            secondQueue.insert(top);
            refQueue = secondQueue;
        } else {    //refQueue == secondQueue
            //copy from second to first
            top = copyAllButLastAndReturnLast(secondQueue, firstQueue);
            firstQueue.insert(top);
            refQueue = firstQueue;
        }
        return top;
    }

    public boolean isEmpty() {
        return refQueue.isEmpty();
    }

    public boolean isFull() {
        return refQueue.isFull();
    }

    public int size() {
        return refQueue.size();
    }
}

class StackUsingQueueClient {
    public static void main(String[] args) {
        StackUsingQueue stack = new StackUsingQueue<Integer>(5);

        stack.push(5);
        stack.push(6);
        stack.pop();

        stack.push(3);
        stack.push(4);
        stack.pop();

        System.out.println(stack.push);
        System.out.println(stack.pop);


/*        System.out.println(stack.isEmpty());
        System.out.println(stack.isFull());

        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println(stack.isEmpty());
        System.out.println(stack.isFull());
        System.out.println(stack.size());

        stack.push(4);
        stack.push(5);

        System.out.println(stack.pop());
        System.out.println(stack.pop());

        System.out.println(stack.peek());

        System.out.println(stack.size());

        stack.push(6);
        stack.push(7);

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());*/
    }
}
