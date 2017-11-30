package com.dsalgo.ctci;

import java.util.HashMap;
import java.util.Map;

import com.dsalgo.practice.linkedlists.LinkList;
import com.dsalgo.practice.linkedlists.single.Link;

public class LinkedListOperations {
    public static void main(String[] args) {

        //removeDuplicatesFromLinkedList();

        //returnKthToTheLastFromLinkedList();

        deleteMiddleNode();
    }

    private static void deleteMiddleNode() {
        LinkList list = new LinkList();
        System.out.println("List empty : " + list.isEmpty());

        list.insertFirst(2);
        list.insertFirst(1);
        list.insertFirst(11);
        list.insertFirst(10);
        list.insertFirst(1);
        list.insertFirst(15);
        list.insertFirst(7);
        list.insertFirst(10);

        list.displayList();

        deleteMiddleNode(list.getFirst().next.next.next);

        System.out.println();
        list.displayList();

    }

    private static void deleteMiddleNode(Link link) {
        if (link == null || link.next == null) {
            return;
        }
        Link next = link.next;
        link.data = next.data;
        link.next = next.next;
    }

    private static void returnKthToTheLastFromLinkedList() {
        LinkList list = new LinkList();
        System.out.println("List empty : " + list.isEmpty());

        list.insertFirst(2);
        list.insertFirst(1);
        list.insertFirst(11);
        list.insertFirst(10);
        list.insertFirst(1);
        list.insertFirst(15);
        list.insertFirst(7);
        list.insertFirst(10);

        list.displayList();

        System.out.println("\nKth element:");
        returnKthToTheLastUsingRecursion(list.getFirst(), 3);

        System.out.println("\nKth element:");
        returnKthToTheLastWihtoutUsingRecursion(list, 3);
    }

    private static int returnKthToTheLastUsingRecursion(Link link, int k) {
        if (link == null) {
            return 0;
        }
        int index = returnKthToTheLastUsingRecursion(link.next, k) + 1;
        if (index == k) {
            link.displayLink();
        }
        return index;
    }

    private static void returnKthToTheLastWihtoutUsingRecursion(LinkList list, int k) {
        Link current = list.getFirst();
        Link runner = list.getFirst();
        for (int i = 1; i < k; i++) {
            runner = runner.next;
        }

        while (runner.next != null) {
            current = current.next;
            runner = runner.next;
        }
        current.displayLink();
    }

    private static void removeDuplicatesFromLinkedList() {
        LinkList list = new LinkList();
        System.out.println("List empty : " + list.isEmpty());

        list.insertFirst(2);
        list.insertFirst(1);
        list.insertFirst(11);
        list.insertFirst(10);
        list.insertFirst(1);
        list.insertFirst(15);
        list.insertFirst(7);
        list.insertFirst(10);

        list.displayList();

        removeDups(list);

        System.out.println();
        System.out.println("Duplicate entries removed : ");
        list.displayList();
    }

    private static void removeDups(LinkList list) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        Link current = list.getFirst();
        Link previous = null;
        while (true) {
            if (map.get(current.data) == null) {
                map.put(current.data, current.data);
                previous = current;
                current = current.next;
            } else {
                previous.next = current.next;
                current = current.next;
            }
            if (current == null) {
                break;
            }
        }
    }

    private static void removeDups2(LinkList list) {
        Link current = list.getFirst();
        Link runner = null;
        while (true) {
            runner = current;
            while (true) {
                if (runner.next != null && runner.next.data == current.data) {
                    runner.next = runner.next.next;
                }
                runner = runner.next;
                if (runner == null) {
                    break;
                }
            }
            current = current.next;
            if (current == null) {
                break;
            }
        }
    }
}
