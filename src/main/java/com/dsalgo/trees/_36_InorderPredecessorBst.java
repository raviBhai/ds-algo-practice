package com.dsalgo.trees;
/*

Given a BST, and an element.
Get the BST's inorder traversal in an array.
In that array, find the given input element, and then return the immediate previous element from the array.

                     5
                    /\
                   /  \
                  /    \
                 3      7
                /\      /\
               2  4    6  9
              /          / \
             1          8   10

Inorder traversal : 1, 2, 3, 4, 5, 6, 7, 8, 9, 10
Given input element = 8
Inorder successor will be 7


 */
public class _36_InorderPredecessorBst {

    private static int solve(Node node, int key) {
        int result = -1;

        while (node != null) {
            if (node.data < key) {
                result = node.data;
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return result;
    }

    public static void main(String[] args) {

        /*
                             5
                            /\
                           /  \
                          /    \
                         3      7
                        /\      /\
                       2  4    6  9
                      /          / \
                     1          8   10


         */

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

        n5.left = n3;
        n5.right = n7;
        n3.left = n2;
        n3.right = n4;
        n2.left = n1;
        n7.left = n6;
        n7.right = n9;
        n9.left = n8;
        n9.right = n10;

        System.out.println(solve(n5, 8));
    }
}
