package com.dsalgo.interviewbit.linkedlist;

public class MergeSortedLists {
    public static void main(String[] args) {

        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(3);
        ListNode a3 = new ListNode(5);
        ListNode a4 = new ListNode(7);
        ListNode a5 = new ListNode(19);
        a1.next = a2;
        a2.next = a3;
        a3.next = a4;
        a4.next = a5;

        //ListNode b1 = new ListNode(2);
        ListNode b2 = new ListNode(14);
        ListNode b3 = new ListNode(16);
        //b1.next = b2;
        b2.next = b3;

        MergeSortedLists mergeSortedLists = new MergeSortedLists();
        ListNode mergedListNode = mergeSortedLists.mergeTwoLists(a1, b2);
        mergedListNode.display();
    }

    public ListNode mergeTwoLists(ListNode A, ListNode B) {
        ListNode currentA = A;
        ListNode currentB = B;

        while (currentA != null && currentB != null) {
            ListNode currentBprev = null;
            ListNode currentAprev = null;
            while (currentA != null && currentB != null && currentA.val >= currentB.val) {
                currentBprev = currentB;
                currentB = currentB.next;
            }
            if (currentBprev != null) {
                currentBprev.next = currentA;
            }

            while (currentA != null && currentB != null && currentA.val < currentB.val) {
                currentAprev = currentA;
                currentA = currentA.next;
            }
            if (currentAprev != null) {
                currentAprev.next = currentB;
            }
        }

        if (A.val < B.val) {
            return A;
        } else {
            return B;
        }
    }
}
