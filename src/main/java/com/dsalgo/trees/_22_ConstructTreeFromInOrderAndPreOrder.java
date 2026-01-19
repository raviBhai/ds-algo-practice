package com.dsalgo.trees;

import java.util.HashMap;
import java.util.Map;

/*

Given inorder and preorder traversals, construct a unique binary tree from thesee.

Algorithm :
Inorder  : 40, 20, 50, 10, 60, 30
Preorder : 10, 20, 40, 50, 30, 60

Get first element from preorder and make it root.
root = 10

To populate left and right of this root,
    1. get inorder and preorder for left node
    2. get inorder and preorder for right node
And solve it recursively.

Go to inorder, whatever is left of 10 will be root's left and whatever is right if 10 will be root's right
inorder for left = 40, 20, 50
size of these elements = 3
it means, preorder for left will be next 3 elements from 10 = 20, 40, 50

inorder left = 40, 20, 50
preorder left = 20, 40, 50

Similarly,
inorder right = 60, 30
preorder right = 30, 60

we make recursive calls with this data

Left will be called with these :
inorder left = 40, 20, 50
preorder left = 20, 40, 50

Take root = 20
inorder left = 40
preorder left = 40

inorder right = 50
preorder right = 50

Right will be called with these :
inorder right = 60, 30
preorder right = 30, 60

Take root = 30
inorder left = 60
preorder left = 60

inorder right = NULL
preorder right = NULL


During recursive calls, to get preorder and inorder from left and right, we will maintain indexes - preStart, preEnd, inStart, inEnd
As we get first element from preorder traversal, mark it root, and then look for this element in inorder to get left and right inorders,
to do the lookup in the inorder traversal, we can maintain a map which stores inorder element and its index

 */

public class _22_ConstructTreeFromInOrderAndPreOrder {

    private static Node solve(int[] inorder, int[] preorder) {

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        Node root = solve(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, map);
        return root;
    }

    private static Node solve(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd, Map<Integer, Integer> map) {

        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }

        Node node = new Node(preorder[preStart]);

        int inIndex = map.get(node.data);
        int lengthOfInOrder = inIndex - inStart;     // length of inOrder - lengthOfInOrder

        // preStart for left will be current preStart + 1
        // preEnd for left will be next numberOfInOrderElements from the current preStart  = preStart + lengthOfInOrder
        // inStart will be inStart passed from function
        // inEnd will be the one previous element left to current inIndex, which is inIndex - 1
        node.left = solve(preorder, preStart+1, preStart + lengthOfInOrder, inorder, inStart, inIndex-1, map);

        // preStart for right will be 1 + preEnd for left = preStart + lengthOfInOrder + 1
        // preEnd will be preEnd passed from function
        // inStart will be next element of inIndex = inIndex + 1
        // inEnd will be inEnd passed from the function
        node.right = solve(preorder, preStart + lengthOfInOrder + 1, preEnd, inorder, inIndex + 1, inEnd, map);

        return node;
    }

    public static void main(String[] args) {
        int[] inorder = {40, 20, 50, 10, 60, 30};
        int[] preorder = {10, 20, 40, 50, 30, 60};
        Node root = solve(inorder, preorder);
        System.out.println(root.data);
    }
}
