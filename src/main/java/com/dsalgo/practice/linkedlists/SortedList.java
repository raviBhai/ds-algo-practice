package com.dsalgo.practice.linkedlists;

import com.dsalgo.practice.linkedlists.single.Link;

public class SortedList {
    private Link first;

    public boolean isEmpty() {
        return first == null;
    }

    public void insert(int data) {
        Link newLink = new Link(data);
        if (isEmpty()) {
            first = newLink;
            return;
        }
        Link current = first;
        Link previous = null;
        while (current != null && data > current.data) {
            previous = current;
            current = current.next;
        }
        if (previous == null) {
            newLink.next = first;
            first = newLink;
        } else {
            previous.next = newLink;
            newLink.next = current;
        }
    }

    public Link remove() {
        Link temp = first;
        first = first.next;
        return temp;
    }
}
