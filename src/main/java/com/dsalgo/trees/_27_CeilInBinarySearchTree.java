package com.dsalgo.trees;

/*

Given root of a BST and a key.
Find the smallest possible value which is greater than key from the BST.
If the key is present in the BST, return the key


                10
                /\
               /  \
              5    13
             /\    /\
            3  6  11 14
           /\   \
          2  4   9

In this tree, if key is 8, we should return 9 as it is the smallest number greater than 8


 */
public class _27_CeilInBinarySearchTree {

    public static int solve(Node root, int key) {

        int ceil = -1;
        Node current = root;
        while (current != null) {
            if (current.data == key) {
                ceil = key;
                return ceil;
            }
            if (key > current.data) {
                current = current.right;
            } else {
                ceil = current.data;
                current = current.left;
            }
        }
        return ceil;
    }

    public static void main(String[] args) {
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

        System.out.println(solve(n1, 8));   // 9
        System.out.println(solve(n1, 12));  // 13
        System.out.println(solve(n1, 11));  // 11
    }
}
