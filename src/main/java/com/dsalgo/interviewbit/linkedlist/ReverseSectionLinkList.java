package com.dsalgo.interviewbit.linkedlist;

public class ReverseSectionLinkList {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);

        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;

        System.out.println("before reverse:");
        l1.display();

        ListNode listNode = reverseKGroup(l1, 2);

        System.out.println("\nafter reverse:");
        listNode.display();


    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k <= 1) {
            return head;
        }

        ListNode current = head;
        ListNode previous = null;
        ListNode next = null;
        ListNode first = null;
        ListNode justBeforeStart = null;
        boolean isFirstOpr = false;
        while (true) {
            if (!hasEnoughNodesAfter(current, k)) {
                break;
            } else {
                int count = k;
                previous = null;
                ListNode start = current;
                while (count > 0) {
                    next = current.next;
                    current.next = previous;
                    previous = current;
                    current = next;
                    count--;
                }
                if(!isFirstOpr) {
                    first = previous;
                    isFirstOpr = true;
                }
                start.next = next;
                if (justBeforeStart != null) {
                    justBeforeStart.next = previous;
                }
                justBeforeStart = start;
            }
        }
        return first;
    }

    public static boolean hasEnoughNodesAfter(ListNode node, int k) {
        while (k > 1 && node != null) {
            node = node.next;
            k--;
        }
        return node != null;
    }
}
