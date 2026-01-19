package com.dsalgo.trees;

public class _29_InsertInBinarySearchTree {

    public static Node insert(Node root, int key) {

        Node node = new Node(key);
        if (root == null) {
            return node;
        }
        Node current = root;
        while (true) {
            if (key < current.data) {
                if (current.left != null) {
                    current = current.left;
                } else {
                    current.left = node;
                    break;
                }
            } else {
                if (current.right != null) {
                    current = current.right;
                } else {
                    current.right = node;
                    break;
                }
            }
        }
        return root;
    }

    public static void main(String[] args) {

        /*


                10
                /\
               /  \
              5    13
             /\    /\
            3  6  11 14
           /\   \
          2  4   9


         */
        Node n1 = new Node(10);
        Node n2 = new Node(5);
        Node n3 = new Node(13);
        Node n4 = new Node(3);
        Node n5 = new Node(6);
        Node n6 = new Node(2);
        Node n7 = new Node(4);
        Node n8 = new Node(9);
        Node n9 = new Node(11);
        Node n10 = new Node(14);

        n1.left = n2;
        n1.right = n3;

        n2.left = n4;
        n2.right = n5;

        n4.left = n6;
        n4.right = n7;

        n5.right = n8;

        n3.left = n9;
        n3.right = n10;

        insert(n1, 8);
        insert(n1, 15);
        System.out.println(n1);
    }
}
