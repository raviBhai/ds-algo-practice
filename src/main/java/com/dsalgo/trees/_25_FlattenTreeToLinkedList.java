package com.dsalgo.trees;

/*

Given a tree, traverse it in pre-order.
The result of the pre-order traversal should now be the tree.
That means, we need to flatten the tree, such that it looks like a linked list
Do this without creating a new linked list.

             1
            /\
           /  \
          /    \
         2      5
        /\       \
       3  4       6
                 /
                7

    Pre-order traversal will be - 1,2,3,4,5,6,7

    Algorithm:
    Just do the dry run of the below code


 */

public class _25_FlattenTreeToLinkedList {

    static Node prev = null;

    public static void solve(Node node) {
        if (node == null) {
            return;
        }
        solve(node.right);
        solve(node.left);
        node.right = prev;
        node.left = null;
        prev = node;
    }

    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);

        n1.left = n2;
        n1.right = n5;

        n2.left = n3;
        n2.right = n4;

        n5.right = n6;
        n6.left = n7;

        solve(n1);
        System.out.println(prev);
    }
}
