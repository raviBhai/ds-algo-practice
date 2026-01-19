package com.dsalgo.trees;

import java.util.HashMap;
import java.util.Map;

/*

Construct a binary tree when inorder and postorder traversals are given.

This is similar to _22_ConstructTreeFromInOrderAndPreOrder

## 🔄 Constructing a Binary Tree from Inorder and Postorder

The fundamental approach remains recursive, relying on the Inorder traversal to define the left and right boundaries of subtrees.

| Traversal | Role                                                  |   Key Position                            |
| Postorder | Identifies the Root of the current subtree.           |   The last element is the root.           |
| Inorder   | Divides the elements into Left and Right subtrees.    |   Elements to the left/right of the root. |


### Core Changes in Sub-Array Determination

1.  Identify the Root:
    * The Root of the current subtree is the last element of the current Postorder array segment.

2.  Split Inorder:
    * Locate the Root in the Inorder array. This splits the Inorder array into `Inorder Left` and `Inorder Right`.

3.  Split Postorder:
    * Determine the size of the `Inorder Left` part.
    * Postorder Left: This segment starts at the beginning of the current Postorder array and contains a number of elements equal to the size of `Inorder Left`.
    * Postorder Right: The remaining elements (before the Root, which is the last element) form the `Postorder Right` segment.



In short: Postorder Left comes first, followed by Postorder Right, and then the Root (last).
You use the size determined by the Inorder Left array to find the boundary between Postorder Left and Postorder Right.



 */
public class _23_ConstructTreeFronInORderAndPostOrder {

    private static Node solve(int[] inorder, int[] postOrder) {

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        Node root = solve(postOrder, 0, postOrder.length - 1, inorder, 0, inorder.length - 1, map);
        return root;
    }

    private static Node solve(int[] postorder, int postStart, int postEnd, int[] inorder, int inStart, int inEnd, Map<Integer, Integer> map) {

        if (postStart > postEnd || inStart > inEnd) {
            return null;
        }

        Node node = new Node(postorder[postEnd]);
        int inIndex = map.get(node.data);

        int numsLength = inIndex - inStart;

        node.left = solve(postorder, postStart, postStart + numsLength - 1, inorder, inStart, inIndex - 1, map);
        node.right = solve(postorder, postStart + numsLength, postEnd - 1, inorder, inIndex + 1, inEnd, map);

        return node;

    }

    public static void main(String[] args) {
        int[] inorder = {40, 20, 50, 10, 60, 30};
        int[] postorder = {40, 50, 20, 60, 30, 10};
        Node root = solve(inorder, postorder);
        System.out.println(root.data);
    }
}
