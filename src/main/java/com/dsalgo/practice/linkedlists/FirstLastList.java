package com.dsalgo.practice.linkedlists;

import com.dsalgo.practice.linkedlists.single.Link;

public class FirstLastList {
    private Link first;
    private Link last;

    public FirstLastList() {
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void insertFirst(int data) {
        Link newLink = new Link(data);
        if (isEmpty()) {
            last = newLink;
        } else {
            newLink.next = first;
        }
        first = newLink;
    }

    public void insertLast(int data) {
        Link newLink = new Link(data);
        if (isEmpty()) {
            first = newLink;
        } else {
            last.next = newLink;
        }
        last = newLink;
    }

    public Link deleteFirst() {
        Link temp = first;
        if (first.next == null) {   //if only one element
            last = null;
        }
        first = first.next;
        return temp;
    }

    public void displayList() {
        System.out.print("Link list (first -> last) ");
        Link current = first;
        while (current != null) {
            current.displayLink();
            current = current.next;
        }
    }

    public Link getFirst() {
        return first;
    }

    public static void main(String[] args) {
        FirstLastList list = new FirstLastList();
        list.insertFirst(1);
        list.insertFirst(2);
        list.insertFirst(3);
        list.insertLast(4);
        list.insertLast(5);
        list.insertLast(6);

        list.displayList();

        System.out.println();

        Link deletedLink = null;
        deletedLink = list.deleteFirst();
        deletedLink.displayLink();

        System.out.println();
        list.displayList();

        list.insertFirst(7);
        list.insertLast(8);

        System.out.println();
        list.displayList();

        list = new FirstLastList();
        list.insertLast(10);
        list.insertLast(11);
        list.insertLast(12);

        System.out.println();
        list.displayList();
        list.deleteFirst();
        list.deleteFirst();

        System.out.println();
        list.displayList();

        list.deleteFirst();
        System.out.println();
        list.displayList();

    }
}
