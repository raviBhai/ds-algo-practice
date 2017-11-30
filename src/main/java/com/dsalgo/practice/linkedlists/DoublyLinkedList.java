package com.dsalgo.practice.linkedlists;


import com.dsalgo.practice.linkedlists.doubly.Link;

public class DoublyLinkedList {
    private Link first;
    private Link last;

    public void insertFirst(int data) {
        Link newLink = new Link(data);
        if (isEmpty()) {
            last = newLink;
        } else {
            first.previous = newLink;
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
            newLink.previous = last;
        }
        last = newLink;
    }

    public void insertAfter(int key, int data) {
        Link newLink = new Link(data);
        Link current = first;
        while (current != null && current.data != key) {
            current = current.next;
        }
        if (current.next == null) {
            current.next = newLink;
            newLink.previous = current;
            last = newLink;
        } else {
            newLink.next = current.next;
            newLink.previous = current;
            current.next.previous = newLink;
            current.next = newLink;
        }
    }

    public Link deleteFirst() {
        Link temp = first;
        if (first.next == null) {
            last = null;
        } else {
            first.next.previous = null;
        }
        first = first.next;
        return temp;
    }

    public Link deleteLast() {
        Link temp = last;
        if (last.previous == null) {
            first = null;
        } else {
            last.previous.next = null;
        }
        last = last.previous;
        return temp;
    }

    public Link deleteKey(int key) {
        Link previous = null;
        Link current = first;
        Link temp = null;
        while (current != null && current.data != key) {
            previous = current;
            current = current.next;
        }
        if (previous == null) { //if first node
            temp = current;
            first = first.next;
            first.previous = null;
        } else if (current.next == null) { //if last node
            temp = current;
            last = last.previous;
            last.next = null;
        } else {
            temp = current;
            previous.next = current.next;
            current.next.previous = previous;
        }

        return temp;
    }

    public void displayList() {
        System.out.print("List (first -> last)");
        Link current = first;
        while (current != null) {
            current.displayLink();
            current = current.next;
        }
        System.out.println("");
    }

    public boolean isEmpty() {
        return first == null;
    }

    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();
        list.insertFirst(1);
        list.insertLast(2);
        list.insertLast(3);
        list.insertLast(5);
        list.insertLast(6);
        list.insertLast(7);
        list.insertAfter(3, 4);
        list.displayList();

        list.deleteFirst();
        list.deleteLast();

        list.deleteKey(2);
        list.deleteKey(6);
        list.deleteKey(4);

        list.displayList();
    }
}
