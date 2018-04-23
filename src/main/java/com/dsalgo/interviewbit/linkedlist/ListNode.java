package com.dsalgo.interviewbit.linkedlist;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {

    }

    public ListNode(int x) {
        val = x;
        next = null;
    }

    public void display() {
        System.out.print("{" + val + "}");
        if (next != null) {
            next.display();
        }
    }

    public void displayNode() {
        System.out.print("{" + val + "}");
    }
}
