package com.dsalgo.interviewbit.linkedlist;

/**
 * Check linked list is palindrome in constant time and space.
 * To have constant space, reverse second half of the linked list.
 */
public class PalindromeList {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(3);
        ListNode l5 = new ListNode(21);
        ListNode l6 = new ListNode(1);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;

        PalindromeList pl = new PalindromeList();
        System.out.println(pl.lPalin(l1));
    }

    public int lPalin(ListNode first) {

        if (first == null) {
            return 0;
        } else if (first.next == null) {
            return 1;
        } else if (first.next.next == null) {
            if (first.val == first.next.val) {
                return 1;
            } else {
                return 0;
            }
        } else if (first.next.next.next == null) {
            if (first.val == first.next.next.val) {
                return 1;
            } else {
                return 0;
            }
        }


        int palindrome = 1;
        ListNode slow = first;
        ListNode fast = first;
        ListNode last = null;
        ListNode nodeBeforeReversedList = slow;
        while (fast != null && fast.next != null) {
            if (fast.next.next == null) {
                last = fast.next;
            } else if (fast.next.next != null) {
                last = fast.next.next;
            }

            nodeBeforeReversedList = slow;
            slow = slow.next;
            fast = fast.next.next;

        }
        if (fast != null) {
            nodeBeforeReversedList = slow;
            slow = slow.next;
        }

        //reverse list starting from slow pointer
        ListNode previous = null;
        ListNode current = slow;
        ListNode next = null;
        while (current != null) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        nodeBeforeReversedList.next = last;

        ListNode newFirst = nodeBeforeReversedList.next;
        while (newFirst != null) {
            if (first.val == newFirst.val) {
                first = first.next;
                newFirst = newFirst.next;
            } else {
                palindrome = 0;
                break;
            }
        }
        return palindrome;
    }


}
