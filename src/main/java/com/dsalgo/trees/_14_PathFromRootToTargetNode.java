package com.dsalgo.trees;

import java.util.ArrayList;
import java.util.List;

/*
Print path from root to a target node. Target node can be a leaf or non-leaf node.

Algo:
Maintain a list and keep adding elements to it while traversing the tree.


                1
         2          3
    4       5   6       7
  8   9  10


Let's say target is 10, and we do preorder traversal.
It would mean we go root, left, right
It means we would encounter 8 and 9 before getting 10.
8 and 9 will be added to the list we maintain, but then we need to remove 8 and 9 from the list the moment we backtrack.

At every node, add it to the list.
If the node is the target, return true.
Else, check if either of left or right is having the path.
If yes, return true,
If both left and right do not have the target, then whatever action we did at current node should be reversed.
At current node, action we took was to add it to the list, so we need to revert this action.

 */
public class _14_PathFromRootToTargetNode {

    public static boolean solve(Node node, List<Integer> list, int target) {
        if (node == null) {
            return false;
        }
        list.add(node.data);
        if (node.data == target) {
            return true;
        }
        if (solve(node.left, list, target) || solve(node.right, list, target)) {
            return true;
        }
        list.remove(list.size() - 1);
        return false;
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

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

        n1.left = n2;
        n1.right = n3;

        n2.left = n4;
        n2.right = n5;

        n3.left = n6;
        n3.right = n7;

        n4.left = n8;
        n4.right = n9;

        n5.left = n10;

        if (n1 == null) {
            System.out.println(list);
        } else {
            solve(n1, list, 10);
            System.out.println(list);
        }
    }
}
