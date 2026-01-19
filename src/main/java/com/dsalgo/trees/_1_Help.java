package com.dsalgo.trees;

public class _1_Help {
    /*

    Types of binary trees :

---------------------------------------------------------------------------------------------------------
    1. Full binary tree:
     - All nodes have either 0 or 2 children

        0
       / \
      1   2
         / \
        3   4

---------------------------------------------------------------------------------------------------------

    2. Complete binary tree:
    - All levels are completely filled, except the last level.
    - Nodes at last level are all to the left, if few nodes are not present in the last level

    Not a complete binary tree:
        0
       / \
      1   2
         / \
        3   4

    Below 2 are complete binary tree
        0
       / \
      1   2
     / \
    3   4

          0
        /  \
       /    \
      1      2
     / \     /\
    3   4   5  6

    IMPORTANT :
    1. Inorder traversal on a complete binary tree has time complexity of O(n), where n is the number of nodes.
    2. Inorder traversal on a complete binary tree has space complexity of O(h), where h is the height of the tree, this space is utilized by stack used during recursion.
    3. Height of a complete tree is O(log(n)).
---------------------------------------------------------------------------------------------------------

    3. Perfect binary tree:
    - All leaf nodes are at the same level

      Not a perfect binary tree:
        0
       / \
      1   2
     / \
    3   4

    Leaf nodes are: 3,4,2
    All 3 leaf nodes are not at the same level, so this is not a perfect binary tree

    Below is perfect binary tree:
          0
        /  \
       /    \
      1      2
     / \     /\
    3   4   5  6
---------------------------------------------------------------------------------------------------------

    4. Balanced binary tree:
    - If N nodes are present, height of the tree is at max log(N)
    - IF 8 nodes are present, max tree height is 3

---------------------------------------------------------------------------------------------------------

    5. Degenrate tree:
    - when every node has only one child.
    - this is similar to linked list

     */
}
