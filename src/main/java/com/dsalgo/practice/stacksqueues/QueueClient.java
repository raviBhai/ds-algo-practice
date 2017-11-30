package com.dsalgo.practice.stacksqueues;

public class QueueClient {
    public static void main(String[] args) {
        ArrayQueue<String> queue = new ArrayQueue<String>(5);

        System.out.println("Empty - " + queue.isEmpty());
        System.out.println("Full - " + queue.isFull());

        queue.insert("one");
        queue.insert("two");
        queue.insert("three");
        queue.insert("four");
        queue.insert("five");

        System.out.println("Empty - " + queue.isEmpty());
        System.out.println("Full - " + queue.isFull());
        System.out.println("Size - " + queue.size());

        System.out.println(queue.remove());
        System.out.println(queue.remove());

        System.out.println("Size - " + queue.size());

        queue.insert("six");
        queue.insert("seven");

        System.out.println("Size - " + queue.size());

        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());

    }
}
