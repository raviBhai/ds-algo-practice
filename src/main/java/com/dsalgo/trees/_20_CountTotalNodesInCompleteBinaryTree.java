package com.dsalgo.trees;

/*
Given a complete binary tree.
Count number of nodes in less than O(n) time.

Solution :
If we count nodes using inorder traversal, it takes O(n) time,

In a perfect tree, where every level has all the nodes, if h is the height of the tree, number of nodes will be 2^h - 1.
If h = 3, number of nodes will be 7.

In a complete tree, we can start at the root node, call it current node.
Get left height and right height.

If
left and right height is same, then from  the current node, going down, we have a perfect tree.
Height from the current node = leftHeight + 1
This can be rightHeight + 1 as well as both leftHeight and rightHeight are same.
Number of nodes from the current node = 2^currentHeight - 1

Else
number of nodes from the current node will be = 1 + solve(currentNode.left) + solve(currentNode.right)
That is, count the current node, and apply the same logic for the left and right subtrees.

How to get height :
As it is complete tree, to get left height, we can go extreme left and maintain a counter.
We can do the same to get right height.

Time complexity :
At a node, we get left and right height.
Time to get height = O(log(N))
If heights are same, we do not traverse the nodes again and use the formula 2^h - 1
However, in some cases, we might have to solve it for left and right subtree again.

As this is a complete tree, leaf level nodes are at the left,
so in cases when heights do not match and we recurse, we recurse mostly for left, but for right subtree, it becomes a perfect tree which does not need to recurse further.
Hence, on an average, we traverse only left, for which time taken will be log(N)

So, overall time complexity will be O(log(N) * log(N))






















## 💡 Efficient Node Counting in a Complete Binary Tree

The goal is to count the nodes in a complete binary tree in a time complexity faster than the standard O(N) traversal.

A complete binary tree is a binary tree in which every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible.

### Algorithm Overview

The core idea is to recursively check if the current subtree rooted at `root` is a perfect binary tree.

1.  Base Case: If `root` is `null`, the count is 0.
2.  Calculate Heights: Determine the left height (`lh`) by traversing only the left child path to the deepest node. Determine the right height (`rh`) by traversing only the right child path to the deepest node.
3.  Perfect Tree Check (Optimization):
    * If lh == rh, the subtree rooted at `root` is a perfect binary tree (where all levels are full).
    * The height of this perfect tree is h = lh + 1 (accounting for the `root` node).
    * The total number of nodes is calculated directly using the formula: {2^h - 1}. No further recursion is needed for this subtree.
4.  Recurse (Standard Counting):
    * If lh != rh, the subtree is not perfect.
    * The total count is calculated recursively: 1 + solve(root.left) + solve(root.right).

### Height Calculation

The `leftHeight` and `rightHeight` functions are highly efficient because they only traverse one path (either extreme left or extreme right). Since the tree has N nodes, the maximum height is h = log_2 N. Therefore, calculating a height takes O(log N) time.



---

That's a great request! Let's simplify the time complexity explanation for your complete binary tree node counting algorithm.

The algorithm's time complexity is O((log N)^2).

---

## ⏱️ Simplified Time Complexity: O((log N)^2)

The fast time complexity comes from a neat trick: you only do full recursion down one path of the tree, while the other path is quickly solved with a formula.

Here is the breakdown of why the time complexity is O((log N)^2):

### 1. Work Done at Each Step: O(log N)

At every node you visit, you do two main operations:

* Calculate Left Height (`lh`): You traverse all the way down the left side. This path is at most the height of the tree, which is (log N) steps.
* Calculate Right Height (`rh`): You traverse all the way down the right side. This path is also at most (log N) steps.

Therefore, the work done per recursive call is O(log N).

### 2. Number of Recursive Calls: O(log N)

The algorithm is structured to avoid descending into both subtrees for very long:

* The Smart Skip: Because it's a complete binary tree, when the left height (lh) and right height (rh) are different, one of the child subtrees must be a perfect binary tree.
    * When recursion happens (in the `else` block), one branch immediately hits the lh == rh condition on the next step, using the 2^h - 1 formula to finish in O(1) time (after its height checks).
    * The recursion, therefore, only needs to continue down one path to find the non-perfect part of the tree.

Since the tree has (log N) levels, the deepest path you follow with continuous recursion is only {log N} steps long (one node per level).

### 3. Combining the Factors

You have:

* Work per Step: O(log N)
* Number of Steps (Depth of Recursion): O(log N)

text{Total Time} = ({Work per Step}) * ({Number of Steps})
text{Total Time} = O(log N) * O(log N) = O((log N)^2)

This is much faster than the standard {O(N)} time for traversing all nodes.

---


 */
public class _20_CountTotalNodesInCompleteBinaryTree {

    private static int solve(Node root) {
        if (root == null) return 0;
        int lh = leftHeight(root.left);
        int rh = rightHeight(root.right);
        if (lh == rh) {
            int currentNodeHeight = lh + 1;
            return (int) Math.pow(2, currentNodeHeight) - 1;
        } else {
            return 1 + solve(root.left) + solve(root.right);
        }
    }

    private static int leftHeight(Node node) {
        int count = 1;
        while (node.left != null) {
            count++;
            node = node.left;
        }
        return count;
    }

    private static int rightHeight(Node node) {
        int count = 1;
        while (node.right != null) {
            count++;
            node = node.right;
        }
        return count;
    }



    /**
     * <p>Here is an image related to this class:</p>
     *  <img src="images/_20_CountTotalNodesInCompleteBinaryTree.png" alt="My Image Description" width="300"/>
     * @param args
     */
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
        Node n11 = new Node(11);

        n1.left = n2;
        n1.right = n3;

        n2.left = n4;
        n2.right = n5;

        n4.left = n8;
        n4.right = n9;

        n5.left = n10;
        n5.right = n11;

        n3.left = n6;
        n3.right = n7;

        System.out.println(solve(n1));
    }
}
