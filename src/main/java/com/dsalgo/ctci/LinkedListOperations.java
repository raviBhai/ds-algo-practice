package com.dsalgo.ctci;

import java.util.HashMap;
import java.util.Map;

import com.dsalgo.practice.linkedlists.LinkList;
import com.dsalgo.practice.linkedlists.single.Link;
import com.dsalgo.practice.stacksqueues.ArrayStack;
import com.dsalgo.practice.stacksqueues.Stack;

public class LinkedListOperations {
    public static void main(String[] args) {

        //removeDuplicatesFromLinkedList();

        //returnKthToTheLastFromLinkedList();

        //deleteMiddleNode();

        //partition();

        //reverse();

        //sumOfNumbers();

        //isPalindrome();

        //isLoopInListAndGetTheLoopStart();

        isIntersection();
    }

    private static void isIntersection() {
        Link a1 = new Link(1);
        Link a2 = new Link(2);
        Link a3 = new Link(3);
        Link a4 = new Link(4);
        Link a5 = new Link(5);

        Link b1 = new Link(1);
        Link b2 = new Link(2);
        Link b3 = new Link(3);

        Link ab1 = new Link(6);
        Link ab2 = new Link(7);
        Link ab3 = new Link(8);

        a1.next = a2;
        a2.next = a3;
        a3.next = a4;
        a4.next = a5;
        a5.next = ab1;
        ab1.next = ab2;
        ab2.next = ab3;

        b1.next = b2;
        b2.next = b3;
        b3.next = ab1;
        ab1.next = ab2;
        ab2.next = ab3;

        Link head1 = a1;
        Link head2 = b1;

        findIntersection(head1, head2);
    }

    private static void findIntersection(Link head1, Link head2) {
        int l1 = length(head1);
        int l2 = length(head2);
        TailAndSize tns1 = getTailAndSize(head1);
        TailAndSize tns2 = getTailAndSize(head2);

        if (tns1.tail != tns2.tail) {
            System.out.println("The linked lists DO NOT intersect");
            return;
        }
        int diff = Math.abs(l1 - l2);
        Link longer = tns1.size > tns2.size ? head1 : head2;
        Link shorter = tns1.size > tns2.size ? head2 : head1;

        longer = moveByDiff(longer, diff);

        while (longer != shorter) {
            shorter = shorter.next;
            longer = longer.next;
        }
        System.out.println("Intersecting element is : ");
        longer.displayLink();
    }

    private static Link moveByDiff(Link longer, int diff) {
        while (diff != 0) {
            longer = longer.next;
            diff--;
        }
        return longer;
    }

    private static TailAndSize getTailAndSize(Link head) {
        int counter = 1;
        while (head.next != null) {
            head = head.next;
            counter++;
        }
        return new TailAndSize(head, counter);
    }

    private static int length(Link head) {
        int length = 0;
        while (head != null) {
            head = head.next;
            length++;
        }
        return length;
    }

    private static void isLoopInListAndGetTheLoopStart() {
        Link l1 = new Link(1);
        Link l2 = new Link(2);
        Link l3 = new Link(3);
        Link l4 = new Link(4);
        Link l5 = new Link(5);
        Link l6 = new Link(6);
        Link l7 = new Link(7);
        Link l8 = new Link(8);

        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;
        l6.next = l7;
        l7.next = l8;
        l8.next = l3;

        Link head = l1;

        isLoopInListAndGetTheLoopStart(head);
    }

    private static void isLoopInListAndGetTheLoopStart(Link head) {
        Link slow = head;
        Link fast = head;
        boolean isLoop = false;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                isLoop = true;
                break;
            }
        }

        if (isLoop) {
            System.out.println("Loop is present in the link list");
            Link start = head;
            while (start != slow) {
                start = start.next;
                slow = slow.next;
            }
            slow.displayLink();
        } else {
            System.out.println("Loop is not present in the link list");
        }
    }

    private static void isPalindrome() {
        LinkList list1 = new LinkList();
        list1.insertFirst(1);
        list1.insertFirst(2);
        list1.insertFirst(3);
        list1.insertFirst(3);
        list1.insertFirst(2);
        list1.insertFirst(1);
        list1.displayList();
        System.out.println();

        isPalindromeUsingReversal(list1);
        isPalindromeUsingStack(list1);
    }

    private static void isPalindromeUsingStack(LinkList list) {
        boolean isPalindrome = true;
        Stack<Integer> stack = new ArrayStack<Integer>(10);
        Link slow = list.getFirst();
        Link fast = list.getFirst();

        //fast is set to null when list length is even. It is set to last link when lenght is odd.
        while (fast != null && fast.next != null) {
            stack.push(slow.data);
            slow = slow.next;
            fast = fast.next.next;
        }

        //if list length is odd, then skip middle element
        if (fast != null) {
            slow = slow.next;
        }

        while (slow != null) {
            Integer data = stack.pop();
            if (data.intValue() != slow.data) {
                isPalindrome = false;
            }
            slow = slow.next;
        }
        System.out.println("Is Palindrome: " + isPalindrome);
    }

    private static void isPalindromeUsingReversal(LinkList list1) {
        Link head1 = list1.getFirst();
        Link head2 = reverseAndClone(head1);
        boolean isPalindrome = isEqual(head1, head2);
        System.out.println("Is Palindrome: " + isPalindrome);
    }

    private static boolean isEqual(Link head1, Link head2) {
        while (head1 != null && head2 != null) {
            if (head1.data != head2.data) {
                return false;
            }
            head1 = head1.next;
            head2 = head2.next;
        }
        return true;
    }

    private static Link reverseAndClone(Link link) {
        Link head = null;
        while (link != null) {
            Link n = new Link(link.data);
            if (head != null) {
                n.next = head;
            }
            head = n;
            link = link.next;
        }
        return head;
    }

    private static void sumOfNumbers() {
        LinkList list1 = new LinkList();
        list1.insertFirst(6);
        list1.insertFirst(1);
        list1.insertFirst(7);
        list1.displayList();
        System.out.println();

        LinkList list2 = new LinkList();
        list2.insertFirst(4);
        list2.insertFirst(9);
        list2.insertFirst(5);
        list2.displayList();
        System.out.println();

        //617 + 495
        sumBackward(list1, list2);

        //716 + 594
        sumForwardWithRecursion(list1, list2);

        //716 + 594
        sumForwardWithStack(list1, list2);
    }

    private static void sumForwardWithStack(LinkList list1, LinkList list2) {
        Stack<Integer> stack1 = getStackFrom(list1);
        Stack<Integer> stack2 = getStackFrom(list2);
        int d1, d2, sum = 0, carry = 0;
        Link head = null;
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            if (!stack1.isEmpty()) {
                d1 = stack1.pop();
                sum = d1;
            }
            if (!stack2.isEmpty()) {
                d2 = stack2.pop();
                sum = sum + d2;
            }
            sum = sum + carry;
            carry = sum / 10;
            sum = sum % 10;

            Link n = new Link(sum);
            if (head != null) {
                n.next = head;
            }
            head = n;
        }
        if (carry != 0) {
            Link n = new Link(carry);
            n.next = head;
            head = n;
        }
        System.out.println();
        head.displayChain();
    }

    private static Stack<Integer> getStackFrom(LinkList list) {
        Link current = list.getFirst();
        Stack<Integer> stack = new ArrayStack<Integer>(5);
        while (current != null) {
            stack.push(current.data);
            current = current.next;
        }
        return stack;
    }

    private static void sumForwardWithRecursion(LinkList list1, LinkList list2) {
        int l1 = length(list1);
        int l2 = length(list2);

        if (l1 < l2) {
            padFront(list1, l2 - l1);
        } else {
            padFront(list2, l1 - l2);
        }
        PartialSum partialSum = new PartialSum();
        partialSum = sumWithRecursion(list1.getFirst(), list2.getFirst(), partialSum);
        if (partialSum.carry != 0) {
            Link n = new Link(partialSum.carry);
            n.next = partialSum.head;
            partialSum.head = n;
        }
        partialSum.head.displayChain();
    }

    private static PartialSum sumWithRecursion(Link current1, Link current2, PartialSum partialSum) {
        if (current1 == null && current2 == null) {
            return null;
        }
        sumWithRecursion(current1.next, current2.next, partialSum);
        int sum = current1.data + current2.data + partialSum.carry;
        partialSum.carry = sum / 10;
        sum = sum % 10;
        Link n = new Link(sum);

        if (partialSum.head != null) {
            n.next = partialSum.head;
        }
        partialSum.head = n;
        return partialSum;
    }

    private static int length(LinkList list) {
        int length = 0;
        Link current = list.getFirst();
        while (current != null) {
            current = current.next;
            length++;
        }
        return length;
    }

    private static void padFront(LinkList list, int length) {
        for (int i = 0; i < length; i++) {
            list.insertFirst(0);
        }
    }


    private static void sumBackward(LinkList list1, LinkList list2) {
        LinkList result = new LinkList();
        Link current1 = list1.getFirst();
        Link current2 = list2.getFirst();
        Link head = null, tail = null;
        int d1 = 0, d2 = 0, sum = 0, carry = 0;
        while (current1 != null || current2 != null) {
            if (current1 != null) {
                d1 = current1.data;
                sum = d1;
                current1 = current1.next;
            }
            if (current2 != null) {
                d2 = current2.data;
                sum = sum + d2;
                current2 = current2.next;
            }
            sum = sum + carry;
            carry = sum / 10;
            sum = sum % 10;
            result.insertFirst(sum);
            Link n = new Link(sum);
            if (tail != null) {
                tail.next = n;
            }
            if (head == null) {
                head = n;
            }
            tail = n;
            sum = 0;
        }
        if (carry != 0) {
            result.insertFirst(carry);
            Link n = new Link(carry);
            tail.next = n;
        }
        System.out.println("Display list:");
        head.displayChain();
        System.out.println();
        result.displayList();
        System.out.println();
    }

    private static void reverse() {
        LinkList list = new LinkList();
        System.out.println("List empty : " + list.isEmpty());

        list.insertFirst(1);
        list.insertFirst(2);
        list.insertFirst(10);
        list.insertFirst(5);
        list.insertFirst(8);
        list.insertFirst(5);
        list.insertFirst(3);

        list.displayList();

        reverse3(list);
        System.out.println();
        System.out.println("Reversed list:");
        list.displayList();
    }

    private static void reverse3(LinkList list) {
        Link previous = null, next = null, current = list.getFirst();
        while (current != null) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        list.setFirst(previous);
    }

    private static void reverse4(LinkList list) {
        Link tempCurrent, current = list.getFirst();
        Link tempPrevious, previous = null;
        while (current != null) {
            tempCurrent = current;
            tempPrevious = previous;
            previous = current;
            current = current.next;
            tempCurrent.next = tempPrevious;
        }
        list.setFirst(previous);
    }

    private static void partition() {
        LinkList list = new LinkList();
        System.out.println("List empty : " + list.isEmpty());

        list.insertFirst(1);
        list.insertFirst(2);
        list.insertFirst(10);
        list.insertFirst(5);
        list.insertFirst(8);
        list.insertFirst(5);
        list.insertFirst(3);

        list.displayList();

        partitionAt(5, list);
    }

    private static void partitionAt(int partitionData, LinkList list) {
        LinkList smallerElems = new LinkList();
        LinkList largerElems = new LinkList();
        Link current = list.getFirst();

        while (current != null) {
            if (current.data < partitionData) {
                smallerElems.insertFirst(current.data);
            } else {
                largerElems.insertFirst(current.data);
            }
            current = current.next;
        }
        Link smallerEnd = smallerElems.getFirst();
        while (smallerEnd.next != null) {
            smallerEnd = smallerEnd.next;
        }
        smallerEnd.next = largerElems.getFirst();

        System.out.println();
        System.out.println("Partitioned list is:");
        smallerElems.displayList();
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

class PartialSum {
    Link head;
    int carry = 0;
}

class TailAndSize {
    Link tail;
    int size;

    public TailAndSize(Link tail, int size) {
        this.tail = tail;
        this.size = size;
    }
}
