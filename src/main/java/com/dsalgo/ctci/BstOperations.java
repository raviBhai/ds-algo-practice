package com.dsalgo.ctci;

import com.dsalgo.practice.trees.BinaryTree;
import com.dsalgo.practice.trees.Node;

public class BstOperations {
    public static void main(String[] args) {
        Node n = validBst();

        BinaryTree binaryTree = new BinaryTree();
        binaryTree.inOrder(n);

        System.out.println();
        printAsc(n);

        System.out.println();
        printDesc(n);
    }

    public static void printAsc(Node n) {
        if (n != null) {
            printAsc(n.leftChild);
            System.out.print(n.data + " ");
            printAsc(n.rightChild);
        }
    }

    public static void printDesc(Node n) {
        if (n != null) {
            printDesc(n.rightChild);
            System.out.print(n.data + " ");
            printDesc(n.leftChild);
        }
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
