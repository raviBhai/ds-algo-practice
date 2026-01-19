package com.dsalgo.trees;

/*

Given a BST.
Find Kth largest or smallest node in the BST.

Solution :
When BST is traversed in inorder, that is, left-root-right, it gives all elements in ascending order
When BST is traversed in reverse inorder, that is, right-root-left, it gives all elements in descending order

While doing the traversal, maintain a counter and increment its value when you are on the node.
When the counter value is equal to k, capture the node's value as your result and stop the traversal

 */

public class _31_KthLargestSmallestInBst {

    static int count;
    static int result;

    private static void kthSmallest(Node node, int k) {
        if (node != null) {
            kthSmallest(node.left, k);
            count++;
            if (count == k) {
                result = node.data;
                return;
            }
            kthSmallest(node.right, k);
        }
    }

    private static void kthLargest(Node node, int k) {
        if (node != null) {
            kthLargest(node.right, k);
            count++;
            if (count == k) {
                result = node.data;
                return;
            }
            kthLargest(node.left, k);
        }
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

        count = 0;
        result = -1;
        kthSmallest(n1, 3);
        System.out.println(result);
        count = 0;
        result = -1;
        kthLargest(n1, 3);
        System.out.println(result);
    }
}
