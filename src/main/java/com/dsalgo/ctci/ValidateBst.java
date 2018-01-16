package com.dsalgo.ctci;

import com.dsalgo.practice.trees.Node;

public class ValidateBst {
    public static void main(String[] args) {
       /* Node n1 = invalidBst_1();
        System.out.println(isBst_valid(n1, null, null));

        Node n2 = validBst();
        System.out.println(isBst_valid(n2, null, null));*/

        Node n3 = invalidBst_2();
        System.out.println(isBst_valid(n3, null, null));
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

    public static Node invalidBst_1() {
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
        return n1;
    }

    public static Node invalidBst_2() {
        Node n1 = new Node(20);
        Node n2 = new Node(10);
        Node n3 = new Node(30);
        Node n4 = new Node(25);


        n1.leftChild = n2;
        n1.rightChild = n3;

        n2.rightChild = n4;

        return n1;
    }

    /**
     * This won't work for all cases such as one mentioned below.
     *      20
     *     /  \
     *    10  30
     *     \
     *     25
     * @param node
     * @return
     */
    public static boolean isBst_invalid(Node node) {
        if (node == null) {
            return true;
        }
        if ((node.leftChild != null && node.data < node.leftChild.data)
                || (node.rightChild != null && node.data > node.rightChild.data)) {
            return false;
        }
        return isBst_invalid(node.leftChild) && isBst_invalid(node.rightChild);
    }

    public static boolean isBst_valid(Node n, Integer min, Integer max) {
        if (n == null) {
            return true;
        }
        if ((max != null && max < n.data) || (min != null && min >= n.data)) {
            return false;
        }
        return isBst_valid(n.leftChild, min, n.data) && isBst_valid(n.rightChild, n.data, max);
    }

}
