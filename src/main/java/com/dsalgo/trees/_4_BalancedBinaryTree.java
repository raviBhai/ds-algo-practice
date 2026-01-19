package com.dsalgo.trees;

/*

For any node, if the difference in height of left and right nodes is greater than 1, then it is not balanced.

Brute force way to check is for every node, get the height of left and right and check the diff, if > 1, then return false,
else check for the next set of nodes.

public static boolean isBalanced(Node n) {
        if (n == null) {
            return true;
        }

        int heightDiff = Math.abs(height(n.leftChild) - height(n.rightChild));
        if (heightDiff > 1) {
            return false;
        } else {
            return isBalanced(n.leftChild) && isBalanced(n.rightChild);
        }
}

Time complexity of brute force :
let's say we start with root node, we get height of left and right of root node.
To get these heights, we traverse the entire tree.
So, we traversed the entire tree when we were at root node.

Now, we move to the left of the root node, and again check height of left and right tree.
So, again we travers the entire tree.

So, for every node, we traverse the entire tree (due to height() function).
Hence, time complexity is O(N^2)

Optimization :

We can modify the height function to have the optimization.
We will only use the modified height() function to check if the binary tree is balanced, hence we will rename height() function to check() function
IF a node is balanced, that is, if the diff between left and right is not > 1, then we return the height,
else, we return -1

Below will be the height function:

public static int check(Node node) {
        if (node == null) {
            return 0;
        }
        int lh = check(node.left);


        int rh = check(node.right);


        if (Math.abs(lh - rh) > 1) {
            return -1;
        }
        return 1 + Math.max(lh, rh);
    }

This code works, but it can be further optimized for early exits.

Let's say we are at root node, and the root node has a valid left and right subtree.
But the left subtree is not a balanced subtree.
so, when the check() function is called with root node, we will get left height as -1, that is, lh will be -1.
As we know that left subtree is not balanced, we don;t have to go and check the right subtree.
That is, we don't have to get the value of rh. We can check if lh is -1, then exit.
Similarly, we can put this condition for rh as well.

public static int check(Node node) {
        if (node == null) {
            return 0;
        }
        int lh = check(node.left);
        if (lh == -1) return -1;    // early exit

        int rh = check(node.right);
        if (rh == -1) return -1;    // early exit

        if (Math.abs(lh - rh) > 1) {
            return -1;
        }
        return 1 + Math.max(lh, rh);
    }



 */

/**
 * <p>Here is an image related to this class:</p>
 *  <img src="images/balancedTreeQuestion.jpeg" alt="My Image Description" width="300"/>
 * @param args
 */

public class _4_BalancedBinaryTree {

    public static boolean isBalanced(Node root) {
        return check(root) != -1;
    }

    public static int check(Node node) {
        if (node == null) {
            return 0;
        }
        int lh = check(node.left);
        if (lh == -1) return -1;    // early exit

        int rh = check(node.right);
        if (rh == -1) return -1;    // early exit

        if (Math.abs(lh - rh) > 1) {
            return -1;
        }
        return 1 + Math.max(lh, rh);
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

        n1.left = n2;
        n1.right = n4;

        n2.left = n3;

        n3.left = n9;

        n4.left = n5;
        n4.right = n6;

        n6.right = n7;

        n7.right = n8;

        System.out.println(isBalanced(n1));
    }
}
