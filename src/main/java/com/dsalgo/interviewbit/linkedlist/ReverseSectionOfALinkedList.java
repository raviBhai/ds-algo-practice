package com.dsalgo.interviewbit.linkedlist;

public class ReverseSectionOfALinkedList {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        //ListNode l3 = new ListNode(3);
       /* ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        ListNode l6 = new ListNode(6);
        ListNode l7 = new ListNode(7);
        ListNode l8 = new ListNode(8);
        ListNode l9 = new ListNode(9);*/


        l1.next = l2;
        //l2.next = l3;
       /* l3.next = l4;
        l4.next = l5;
        l5.next = l6;
        l6.next = l7;
        l7.next = l8;
        l8.next = l9;*/

        System.out.println("before reverse:");
        l1.display();
        ReverseSectionOfALinkedList rv = new ReverseSectionOfALinkedList();
        ListNode listNode = rv.reverseBetween(l1, 1, 2);
        System.out.println();
        System.out.println("after reverse:");
        listNode.display();

    }

    public ListNode reverseBetween(ListNode A, int B, int C) {
        if (A.next == null) {
            return A;
        }
        ListNode prev = null;
        ListNode first = A;
        ListNode current = A;

        ListNode next = null;
        ListNode justBeforeStart = null;
        int counter = 1;

        while (counter != B) {
            justBeforeStart = current;
            current = current.next;
            counter++;
        }

        ListNode start = current;

        while (counter != C+1) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
            counter++;
        }

        start.next = next;
        if (justBeforeStart != null) {
            justBeforeStart.next = prev;
        }

        if (start == first) {
            first = prev;
        }

        return first;
    }
}
