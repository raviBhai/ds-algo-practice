package com.dsalgo.ctci;

import com.dsalgo.practice.trees.Node;

public class FindCommonAncestor {
    public static void main(String[] args) {
        Node node = validBst();

        Node common = findCommonAncestor_Bst(25, 20, node);
        System.out.println(common.data);

        /**
         * If each node has a link to its parent, we could trace p and q's paths up until they intersect.
         * This is essentially the same problem as question 2.7 which find the intersection of two linked lists.
         * The "linked list" in this case is the path from each node up to the root.
         */
    }

    public static Node findCommonAncestor_Bst(int k1, int k2, Node root) {
        Node current = root;
        if (current == null) {
            return null;
        }
        while (true) {
            if (k1 == current.data || k2 == current.data) {
                return current;
            } else if (k1 < current.data && k2 < current.data) {
                current = current.leftChild;
            } else if (k1 > current.data && k2 > current.data) {
                current = current.rightChild;
            } else {
                return current;
            }
        }
    }

    public static Node findCommonAncestor_NotABst(Node root, Node p, Node q) {
        if (!covers(root, p) || !covers(root, q)) {
            return null;
        }
        return ancestorHelper(root, p, q);
    }

    private static Node ancestorHelper(Node root, Node p, Node q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        boolean pIsOnLeft = covers(root.leftChild, p);
        boolean qIsOnLeft = covers(root.leftChild, q);
        if (pIsOnLeft != qIsOnLeft) {
            return root;
        }
        Node child = pIsOnLeft ? root.leftChild : root.rightChild;
        return ancestorHelper(child, p, q);
    }

    public static boolean covers(Node root, Node p) {
        if (root == null) {
            return false;
        }
        if (root == p) {
            return true;
        }
        return covers(root.leftChild, p) || covers(root.rightChild, p);
    }

    public static Node validBst() {
        Node n1 = new Node(15);
        Node n2 = new Node(10);
        Node n3 = new Node(30);
        Node n4 = new Node(5);
        Node n5 = new Node(20);
        Node n6 = new Node(40);
        Node n7 = new Node(7);
        Node n8 = new Node(25);
        Node n9 = new Node(35);

        n1.leftChild = n2;
        n1.rightChild = n3;

        n2.leftChild = n4;
        n4.rightChild = n7;

        n3.leftChild = n5;
        n3.rightChild = n6;

        n5.rightChild = n8;

        n6.leftChild = n9;

        return n1;
    }
}
