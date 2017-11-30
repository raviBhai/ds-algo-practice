package com.dsalgo.practice.stacksqueues;

import com.dsalgo.practice.linkedlists.single.Link;
import com.dsalgo.practice.linkedlists.LinkList;

public class StackUsingLinkList implements Stack<Integer> {

    private LinkList linkList;

    public StackUsingLinkList() {
        linkList = new LinkList();
    }

    public void push(Integer data) {
        linkList.insertFirst(data);
    }

    public Integer pop() {
        Link link = linkList.deleteFirst();
        return link.data;
    }

    public Integer peek() {
        return linkList.getFirst().data;
    }

    public boolean isEmpty() {
        return linkList.isEmpty();
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

    public void displayStack() {
        System.out.println("Displaying stack");
        linkList.displayList();
    }

    public static void main(String[] args) {
        StackUsingLinkList stack = new StackUsingLinkList();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.displayStack();

        System.out.println();

        Integer pop;
        pop = stack.pop();
        System.out.println(pop);

        stack.displayStack();
    }
}
