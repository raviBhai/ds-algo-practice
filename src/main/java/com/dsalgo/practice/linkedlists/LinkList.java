package com.dsalgo.practice.linkedlists;

import com.dsalgo.practice.linkedlists.single.Link;

public class LinkList {
    private Link first;

    public LinkList() {
        first = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public Link find(int key) {
        Link current = first;
        while (current != null) {
            if (current.data == key) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public Link delete(int key) {
        Link current = first;
        Link previous = null;
        while (current != null) {
            if (current.data == key) {
                if (current == first) {
                    first = first.next;
                } else {
                    previous.next = current.next;
                }
                return current;
            }
            previous = current;
            current = current.next;
        }
        return null;
    }

    public void insertFirst(int data) {
        Link newLink = new Link(data);
        newLink.next = first;
        first = newLink;
    }

    public Link deleteFirst() {
        Link temp = first;
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

    public void setFirst(Link first) {
        this.first = first;
    }

    public static void main(String[] args) {
        LinkList list = new LinkList();
        System.out.println("List empty : " + list.isEmpty());

        list.insertFirst(1);
        list.insertFirst(2);
        list.insertFirst(3);
        list.insertFirst(4);

        list.displayList();

        Link deleted = list.deleteFirst();
        System.out.println();
        System.out.print("Deleted below link : ");
        deleted.displayLink();
        System.out.println();
        list.displayList();

        list.insertFirst(5);
        list.insertFirst(6);
        list.insertFirst(7);
        System.out.println();
        list.displayList();

        System.out.println();

        Link foundLink = null;
        foundLink = list.find(5);
        if (foundLink == null) {
            System.out.println("Link not found");
        } else {
            System.out.print("Found : ");
            foundLink.displayLink();
        }

        System.out.println();

        foundLink = list.find(8);
        if (foundLink == null) {
            System.out.println("Link not found");
        } else {
            System.out.println();
            System.out.print("Found : ");
            foundLink.displayLink();
        }

        Link deletedLink = null;
        deletedLink = list.delete(5);
        if (deletedLink == null) {
            System.out.println();
            System.out.println("Link not found");
        } else {
            System.out.print("After deleting : ");
            deletedLink.displayLink();
            System.out.println();
            list.displayList();
        }

        System.out.println();

        deletedLink = list.delete(7);
        if (deletedLink == null) {
            System.out.println("Link not found");
        } else {
            System.out.print("After deleting : ");
            deletedLink.displayLink();
            System.out.println();
            list.displayList();
        }

        System.out.println();

        deletedLink = list.delete(1);
        if (deletedLink == null) {
            System.out.println("Link not found");
        } else {
            System.out.print("After deleting : ");
            deletedLink.displayLink();
            System.out.println();
            list.displayList();
        }
    }
}
