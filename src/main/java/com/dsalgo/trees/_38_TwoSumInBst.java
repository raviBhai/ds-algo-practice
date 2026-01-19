package com.dsalgo.trees;

/*
Given input BST and a number.
Check if two distinct numbers exist in BST such that their sum is equal to the given input number.

Approach 1 :
Traverse BST inorder and get an array.
This array is in sorted order.
Then do 2-sum problem using this array by traversing from first and last


Approach 2 :
With Approach 1, space complexity is O(N)
We can solve it in O(H) by creating 2 iterators, one ascending and one descending order.

 */

public class _38_TwoSumInBst {

    private static boolean solve(Node node, int key) {

        _37_BstIterator left = new _37_BstIterator(node, false);
        _37_BstIterator right = new _37_BstIterator(node, true);

        int leftNum = left.next();
        int rightNum = right.next();
        while (leftNum < rightNum) {
            if (leftNum + rightNum == key) {
                return true;
            } else if (leftNum + rightNum < key) {
                leftNum = left.next();
            } else {
                rightNum = right.next();
            }
        }
        return false;
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

        System.out.println(solve(n5, 13));
        System.out.println(solve(n5, 113));
    }

}
