package com.dsalgo.backtracking;

import java.util.Stack;

public class PrintPathWithGoodNodesInATree {
    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1, true);
        TreeNode t2 = new TreeNode(2, true);
        TreeNode t3 = new TreeNode(3, true);
        TreeNode t4 = new TreeNode(4, false);
        TreeNode t5 = new TreeNode(5, false);
        TreeNode t6 = new TreeNode(6, true);
        TreeNode t7 = new TreeNode(7, false);

        t1.leftChild = t2;
        t1.rightChild = t3;
        t2.leftChild = t4;
        t2.rightChild = t5;
        t3.leftChild = t6;
        t3.rightChild = t7;

        printPaths(t1);
    }

    static void printPaths(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        if (root.isGood) {
            stack.push(root);
        } else {
            System.out.println("No good path exists");
        }
        if (isGoodPathPossible(root, stack)) {
            System.out.println("Good path exists");
            while (!stack.isEmpty()) {
                stack.pop().display();
            }
        } else {
            System.out.println("No good path exists");
        }

    }

    static boolean isGoodPathPossible(TreeNode node, Stack<TreeNode> stack) {
        if (node == null) {
            return true;
        } else {
            TreeNode left = node.leftChild;
            TreeNode right = node.rightChild;

            if (left != null && left.isGood) {
                //add to solution
                stack.push(left);

                if (left.leftChild == null && left.rightChild == null) {
                    return true;
                }

                //call isGoodPathPossible again on left
                if (isGoodPathPossible(left, stack)) {
                    return true;
                } else {
                    stack.pop();
                }
            }

            if (right != null && right.isGood) {
                //add to solution
                stack.push(right);

                if (right.leftChild == null && right.rightChild == null) {
                    return true;
                }

                //call isGoodPathPossible again on right
                if (isGoodPathPossible(right, stack)) {
                    return true;
                } else {
                    stack.pop();
                }
            }

            return false;
        }
    }
}
