package com.dsalgo.trees;

import java.util.ArrayList;
import java.util.List;

/*

This is similar to FindCommonAncestor
However, in FindCommonAncestor, time complexity is O(n^2)
We will try to solve with O(n)

Input - root, p, q

Solution 1:
Using _14_PathFromRootToTargetNode, get paths frm root,p and root,q
Then traverse the paths and check what is the last common elements in the path, that will be the answer.
However, this takes extra space to hold the paths.

Solution 2:
From every node, go to left and right.
The left and right calls will return a node or a null.
While going left, if any of p,q is present on left, the left call will return p or q (whichever is present)
Similarly, while going right, if any of p,q is present on right, the right call will return p or q (whichever is present)
If both left and right have returned a non-null value, it means the current node is the answer.
If both left and right do not have p,q under them, both left and right calls will return null, at this time, we will return null from current node's call.
if either of left and right return a non-null, then we will return the non-null value from the current node's call

Approach

We use **recursion** to explore every node’s left and right subtrees and determine where `p` and `q` are located.

#### **Step-by-step logic:**

1. **Base Case:**

   * If the current node is `null`, there’s nothing to explore → return `null`.
   * If the current node itself is `p` or `q`, we’ve found one of the target nodes → return the current node.

2. **Recursive Exploration:**

   * Recursively call `solve(node.left, p, q)` to search the **left subtree**.
   * Recursively call `solve(node.right, p, q)` to search the **right subtree**.
   * Each call can return either:

     * `null` (if neither `p` nor `q` is found in that subtree)
     * or a reference to either `p`, `q`, or an ancestor node.

3. **Combining Results:**

   * After both recursive calls:

     * **Case 1:** If **both `left` and `right` are non-null**, it means:

       * One target (`p` or `q`) was found in the left subtree.
       * The other was found in the right subtree.
       * Therefore, the **current node** is their **lowest common ancestor** → return `node`.

     * **Case 2:** If **only one side (left or right)** is non-null, it means:

       * Both `p` and `q` are located in that same subtree.
       * Propagate the non-null result upward → return `left != null ? left : right`.

     * **Case 3:** If **both are null**, it means:

       * Neither `p` nor `q` exists under this node.
       * Return `null`.



 */
public class _15_LowestCommonAncestor {

    private static Node solve(Node node, Node p, Node q) {
        if (node == null || node == p || node == q) {
            return node;
        }
        Node left = solve(node.left, p, q);
        Node right = solve(node.right, p, q);

        if (left != null && right != null) {
            return node;
        }
        return left != null ? left : right; // either both left and right are null or either of them is null
    }


    public static void main(String[] args) {

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

        System.out.println(solve(n1, n4, n5));  // 2
        System.out.println(solve(n1, n6, n7));  // 3
        System.out.println(solve(n1, n10, n7));  // 1
        System.out.println(solve(n1, n4, n8));  // 4
    }

}
