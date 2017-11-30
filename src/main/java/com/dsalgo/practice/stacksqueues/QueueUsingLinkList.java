package com.dsalgo.practice.stacksqueues;

import com.dsalgo.practice.linkedlists.FirstLastList;
import com.dsalgo.practice.linkedlists.LinkList;

public class QueueUsingLinkList implements Queue<Integer> {

    private FirstLastList firstLastList;

    public QueueUsingLinkList() {
        this.firstLastList = new FirstLastList();
    }

    public void insert(Integer data) {
        firstLastList.insertLast(data);
    }

    public Integer remove() {
        return firstLastList.deleteFirst().data;
    }

    public Integer peek() {
        return firstLastList.getFirst().data;
    }

    public boolean isEmpty() {
        return firstLastList.isEmpty();
    }

    /**
     * This method does not work
     * @return
     */
    public boolean isFull() {
        return false;
    }

    /**
     * This method does not work
     * @return
     */
    public int size() {
        return 0;
    }

    public void displayQueue() {
        System.out.println("Displaying queue");
        firstLastList.displayList();
    }

    public static void main(String[] args) {
        QueueUsingLinkList queue = new QueueUsingLinkList();
        queue.insert(1);
        queue.insert(2);
        queue.insert(3);
        queue.insert(4);

        queue.displayQueue();

        System.out.println();
        Integer removed;
        removed = queue.remove();
        System.out.println(removed);
        removed = queue.remove();
        System.out.println(removed);

        queue.displayQueue();
    }
}
