package com.dsalgo.interviewbit.linkedlist;

public class AddTwoNumbers {
    public static void main(String[] args) {
        ListNode a1 = new ListNode(1);
        //ListNode a2 = new ListNode(4);
        //ListNode a3 = new ListNode(3);
        //a1.next = a2;
        //a2.next = a3;

        ListNode b1 = new ListNode(9);
        ListNode b2 = new ListNode(9);
        ListNode b3 = new ListNode(9);
        b1.next = b2;
        b2.next = b3;

        a1.display();
        System.out.println();
        b1.display();
        System.out.println();
        AddTwoNumbers add = new AddTwoNumbers();
        ListNode node = add.addTwoNumbers(a1, b1);
        node.display();
    }

    public ListNode addTwoNumbers(ListNode A, ListNode B) {
        int digitSum = 0;
        int carry = 0;
        ListNode currentA = A;
        ListNode currentB = B;
        ListNode head = null;
        ListNode current = null;

        while (currentA != null || currentB != null) {
            digitSum = 0;
            if (currentA != null) {
                digitSum = currentA.val;
                currentA = currentA.next;
            }
            if (currentB != null) {
                digitSum = digitSum + currentB.val;
                currentB = currentB.next;
            }
            digitSum = digitSum + carry;
            carry = digitSum /10;
            digitSum = digitSum % 10;
            ListNode n = new ListNode(digitSum);
            if (head == null) {
                head = n;
                current = head;
            } else {
                current.next = n;
                current = n;
            }
        }
        if (carry != 0) {
            ListNode n = new ListNode(carry);
            current.next = n;
        }
        return head;
    }
}
