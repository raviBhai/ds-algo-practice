package com.dsalgo.ctci;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import com.dsalgo.practice.trees.Node;

public class TraverseTreeLevelWise {
    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        Node n8 = new Node(8);
        Node n9 = new Node(9);
        Node n10 = new Node(10);

        n1.leftChild = n2;
        n1.rightChild = n3;

        n2.leftChild = n4;
        n2.rightChild = n5;

        n3.leftChild = n6;
        n3.rightChild = n7;

        n4.leftChild = n8;
        n4.rightChild = n9;

        n5.leftChild = n10;

        System.out.println("Attempt_1");
        attempt_1(n1);

        System.out.println("Attempt_2");
        attempt_2(n1);

        System.out.println("Attempt_3");
        attempt_3(n1);

        System.out.println("Attempt_4");
        attempt_4(n1);

        System.out.println("Attempt_5");
        List<LinkedList<Integer>> lists = new ArrayList<>();
        createLevelLinkedList_depthFirst(n1, 0, lists);

        List<LinkedList<Node>> nodeLists = new ArrayList<>();
        createLevelLinkedList_breadthFirst(n1, nodeLists);

    }

    private static void createLevelLinkedList_breadthFirst(Node root, List<LinkedList<Node>> lists) {
        LinkedList<Node> current = new LinkedList<>();
        current.add(root);

        while (current.size() > 0) {
            lists.add(current);
            LinkedList<Node> parent = current;
            current = new LinkedList<>();
            for (Node n : parent) {
                if (n.leftChild != null) {
                    current.add(n.leftChild);
                }
                if (n.rightChild != null) {
                    current.add(n.rightChild);
                }
            }
        }
    }

    /**
     * Creates a list of linked list, where each linked list contains elements at each level.
     * This is similar to pre-order tree traversal.
     * This, again, is depth-first search as we travel till the end(depth) for a given node.
     * @param root
     * @param level
     * @param lists
     */
    private static void createLevelLinkedList_depthFirst(Node root, int level, List<LinkedList<Integer>> lists) {
        if (root != null) {
            LinkedList<Integer> list = null;
            if (lists.size() == level) {
                list = new LinkedList();
                lists.add(list);
            } else {
                list = lists.get(level);
            }
            list.add(root.data);
            createLevelLinkedList_depthFirst(root.leftChild, level+1, lists);
            createLevelLinkedList_depthFirst(root.rightChild, level+1, lists);
        }
    }

    /**
     * Uses 2 stacks.
     * Print elements level-wise in distinct line.
     * Elements get printed in zig-zag order. Order is right-left-right.
     * This is breadth-first search, but uses more space.
     * @param root
     */
    private static void attempt_4(Node root) {
        Stack<Node> s1 = new Stack<>();
        Stack<Node> s2 = new Stack<>();

        s1.push(root);

        while (!s1.isEmpty() || !s2.isEmpty()) {
            while (!s1.isEmpty()) {
                Node n = s1.pop();
                System.out.print(n.data);
                if (n.rightChild != null) s2.push(n.rightChild);
                if (n.leftChild != null) s2.push(n.leftChild);
            }
            System.out.println();
            while (!s2.isEmpty()) {
                Node n = s2.pop();
                System.out.print(n.data);
                if (n.leftChild != null) s1.push(n.leftChild);
                if (n.rightChild != null) s1.push(n.rightChild);
            }
            System.out.println();
        }
    }

    /**
     * Uses 2 stacks.
     * Print elements level-wise in distinct line.
     * Elements get printed in zig-zag order. Order is left-right-left.
     * This is breadth-first search, but uses more space.
     * @param root
     */
    private static void attempt_3(Node root) {
        Stack<Node> s1 = new Stack<>();
        Stack<Node> s2 = new Stack<>();

        s1.push(root);

        while (!s1.isEmpty() || !s2.isEmpty()) {
            while (!s1.isEmpty()) {
                Node n = s1.pop();
                System.out.print(n.data);
                if (n.leftChild != null) s2.push(n.leftChild);
                if (n.rightChild != null) s2.push(n.rightChild);
            }
            System.out.println();
            while (!s2.isEmpty()) {
                Node n = s2.pop();
                System.out.print(n.data);
                if (n.rightChild != null) s1.push(n.rightChild);
                if (n.leftChild != null) s1.push(n.leftChild);
            }
            System.out.println();
        }
    }

    /**
     * Uses 2 queues.
     * Print elements level-wise in distinct line.
     * Elements get printed from left to right for all levels.
     * This is breadth-first search, but uses more space.
     * @param root
     */
    private static void attempt_2(Node root) {
        Queue<Node> q1 = new ArrayDeque<>();
        Queue<Node> q2 = new ArrayDeque<>();

        q1.add(root);

        while (!q1.isEmpty() || !q2.isEmpty()) {
            while (!q1.isEmpty()) {
                Node n = q1.remove();
                System.out.print(n.data);
                if (n.leftChild != null) q2.add(n.leftChild);
                if (n.rightChild != null) q2.add(n.rightChild);
            }
            System.out.println("");
            while (!q2.isEmpty()) {
                Node n = q2.remove();
                System.out.print(n.data);
                if (n.leftChild != null) q1.add(n.leftChild);
                if (n.rightChild != null) q1.add(n.rightChild);
            }
            System.out.println("");
        }
    }

    /**
     * Uses 1 queue.
     * Prints all the elements level-wise, but without a new line between two levels.
     * The output is in 1 line only.
     * @param n1
     */
    private static void attempt_1(Node n1) {
        Queue<Node> nodeQueue = new ArrayDeque<>();

        nodeQueue.add(n1);
        while (!nodeQueue.isEmpty()) {
            Node n = nodeQueue.remove();
            System.out.print(n.data);

            if (n.leftChild != null) {
                nodeQueue.add(n.leftChild);
            }
            if (n.rightChild != null) {
                nodeQueue.add(n.rightChild);
            }
        }
        System.out.println();
    }
}
