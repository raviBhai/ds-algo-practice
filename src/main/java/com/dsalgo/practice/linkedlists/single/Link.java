package com.dsalgo.practice.linkedlists.single;

public class Link {
    public int data;
    public Link next;

    public Link(int data) {
        this.data = data;
    }

    public void displayLink() {
        System.out.print("{ " + data + " }");
    }

    public void displayChain() {
        System.out.print("{ " + data + " }");
        if (next != null) {
            next.displayChain();
        }
    }
}
