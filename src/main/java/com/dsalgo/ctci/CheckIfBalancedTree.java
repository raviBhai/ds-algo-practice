package com.dsalgo.ctci;

import com.dsalgo.practice.trees.Node;

public class CheckIfBalancedTree {
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
        Node n11 = new Node(11);

        n1.leftChild = n2;
        n1.rightChild = n3;

        n2.leftChild = n4;
        n2.rightChild = n5;

        n3.leftChild = n6;
        n3.rightChild = n7;

        n4.leftChild = n8;
        n4.rightChild = n9;

        n5.leftChild = n10;

        //n10.leftChild = n11;

        System.out.println(isBalanced(n1));
    }

    public static boolean isBalanced(Node n) {
        if (n == null) {
            return true;
        }

        int heightDiff = Math.abs(height(n.leftChild) - height(n.rightChild));
        if (heightDiff > 1) {
            return false;
        } else {
            return isBalanced(n.leftChild) && isBalanced(n.rightChild);
        }
    }

    public static int height(Node n) {
        if (n == null) {
            return 0;
        }
        return 1 + Math.max(height(n.leftChild), height(n.rightChild));
    }
}
