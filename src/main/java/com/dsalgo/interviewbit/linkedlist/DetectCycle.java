package com.dsalgo.interviewbit.linkedlist;

public class DetectCycle {
    public static void main(String[] args) {
        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(4);
        ListNode a3 = new ListNode(3);
        ListNode a4 = new ListNode(4);
        a1.next = a2;
        a2.next = a3;
        a3.next = a4;
        //a4.next = a3;

        DetectCycle detectCycle = new DetectCycle();
        ListNode cycleStartNode = detectCycle.detectCycle(a1);
        if (cycleStartNode != null) {
            cycleStartNode.displayNode();
        } else {
            System.out.println("null");
        }

    }

    public ListNode detectCycle(ListNode a) {
        ListNode cycleStartNode = null;
        ListNode slow = a;
        ListNode fast = a;
        boolean isCycle = false;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                isCycle = true;
                break;
            }
        }

        if (isCycle) {
            ListNode start = a;
            while (start != slow) {
                start = start.next;
                slow = slow.next;
            }
            cycleStartNode = slow;
        }
        return cycleStartNode;
    }
}
