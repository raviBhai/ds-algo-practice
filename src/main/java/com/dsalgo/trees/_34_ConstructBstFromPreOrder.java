package com.dsalgo.trees;

/*

Given preorder traversed array, construct a BST and return its root.

Approach 1:
Input preorder : { 8, 5, 1, 7, 10, 12 }
Preorder is root-left-right, hence first element will be the root.
Make 8 as root.
Then traverse the array from the next element and use _29_InsertInBinarySearchTree to insert every node in BST.
Time complexity :
 - _29_InsertInBinarySearchTree.insert() takes O(N) to insert 1 node
 - hence, to insert N nodes, time complexity will become O(N * N)

Approach 2:
A BST's inorder is always having elements in sorted order.
Input preorder : { 8, 5, 1, 7, 10, 12 }
If we sort this array we get : { 1, 5, 7, 8, 10, 12 }
Now, we have both preorder and the inorder.
We can now use _22_ConstructTreeFromInOrderAndPreOrder to construct the BST.
Time complexity :
 - Sorting - O( N * log(N) )
 - _22_ConstructTreeFromInOrderAndPreOrder - O(N)
So time complexity becomes = O( N * log(N) ) + O(N)

Approach 3:
Can we do it in O(N) ?
We traverse the array and try to create a node for every element.
Then, once the node is created, we try to create a left and a right node.
While traversing the array, before creating the node from an array element, we maintain an upper bound.
If the current array element is less than the upper bound, then we create the node.
Now, we want to create the left node.
To do so, left node will also need an upper bound, which will be the current node's value
Similarly, we will want to create a right node, which will need an upper bound. The right node's upper bound will be current node's upper bound.



 */


public class _34_ConstructBstFromPreOrder {

    private static Node solve(int[] preorder) {
        int[] arr = {0};
        return solve(preorder, arr, Integer.MAX_VALUE);
    }

    private static Node solve(int[] preorder, int[]i, int bound) {
        if (i[0] == preorder.length || preorder[i[0]] > bound) {
            return null;
        }
        Node node = new Node(preorder[i[0]]);
        i[0]++;
        node.left = solve(preorder, i, node.data);
        node.right = solve(preorder, i, bound);
        return node;
    }

    public static void main(String[] args) {
        int[] preorder = {8, 5, 1, 7, 10, 12};
        Node root = solve(preorder);
        System.out.println(_3_Preorder_Iterative.solve(root));
    }
}
