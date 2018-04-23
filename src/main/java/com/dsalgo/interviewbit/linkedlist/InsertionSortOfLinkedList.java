package com.dsalgo.interviewbit.linkedlist;

public class InsertionSortOfLinkedList {
    public static void main(String[] args) {
        InsertionSortOfLinkedList sort = new InsertionSortOfLinkedList();
        ListNode a1 = new ListNode(10);
        ListNode a2 = new ListNode(2);
        ListNode a3 = new ListNode(3);
        ListNode a4 = new ListNode(4);
        ListNode a5 = new ListNode(5);
        a1.next = a2;
        a2.next = a3;
        a3.next = a4;
        a4.next = a5;
        System.out.println("Before sorting");
        a1.display();
        System.out.println();
        ListNode sortedListNode = sort.insertionSortList(a1);
        System.out.println("After sorting");
        sortedListNode.display();
    }

    public ListNode insertionSortList(ListNode A) {
        ListNode head = A;
        ListNode temp = A.next;
        ListNode tempPrev = A;
        ListNode prev = null;
        ListNode current = A;


        while (temp != null) {
            while (current != null && current != temp) {
                if (current.val > temp.val) {
                    if (prev != null) {
                        tempPrev.next = temp.next;
                        prev.next = temp;
                        temp.next = current;
                    } else {
                        tempPrev.next = temp.next;
                        temp.next = current;
                        head = temp;
                    }
                    temp = tempPrev;
                    break;
                }
                prev = current;
                current = current.next;
            }
            prev = null;
            current = head;
            tempPrev = temp;
            temp = temp.next;
        }
        return head;
    }
}
