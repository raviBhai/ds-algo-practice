package com.dsalgo.trees;



public class _21_Help {

    /*


    Can you construct a unique binary tree when the following traversals are given :

    1. Pre-order
    2. Post-order

    Example:
    Pre-order: 1 2 3
    Post-order: 3 2 1

    We can construct below 2 trees with the above 2 traversals.
    Hence, we CANNOT have a unique binary tree.

        1
       /
      2
     /
    3

        1
       /
      2
       \
        3

-----------------------------------------------------------------------------------------------

    Can you construct a unique binary tree when the following traversals are given :

    1. In-order
    2. Pre-order

    Example:
    In-order:  9 3 15 20 7
    Pre-order: 3 9 20 15 7


    We CAN have a unique binary tree.
    Algorithm:
    Pre-order's first element will give root - 3
    Now check in-order, and elements to the left of 3 will be on the root's left and elements to the right of tree will be on the root's right

                 3
                / \
               /   \
              9   15,20,7


    Now, we go back to pre-order and get the next element which is 9
    This will be the root of its own subtree.
    Then we go back to in-order to get left and right elements on this 9 as root.
    There is nothing on the left of 9, and nothing on the right of 9 and left of 3
    Hence 9 wll not have any child elements.

                 3
                / \
               /   \
              9   15,20,7

    Now we go back to pre-order traversal, and get the next element which is 20
    This will be the root of its own subtree.
    Then we go back to in-order to get left and right elements on this 20 as root.
    We have 15 on the left of 20 and right of 3
    We have 7 on the right of 20

                 3
                / \
               /   \
              9    20
                   /\
                  /  \
                 15   7

    Summary:
    It was possible to construct the unique binary tree because we had in-order traversal as the input.
    With just pre-order traversal, we would know only about the root of the tree.
    It is also important to know about what is on the left and right of the root.



     */
}
