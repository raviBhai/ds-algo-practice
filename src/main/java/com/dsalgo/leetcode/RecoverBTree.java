package com.dsalgo.leetcode;

public class RecoverBTree {

    public static void main(String[] args) {
        RecoverBTree rb = new RecoverBTree();
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);

        n2.left = n3;
        n2.right = n1;

        rb.recoverTree(n2);
    }

    public void recoverTree(TreeNode root) {


        solve(root, null, null, new State());

        System.out.println("here");

    }

    private void solve(TreeNode n, TreeNode max, TreeNode min, State state) {

        if (n == null) {
            return;
        }

        if (max != null && max.val < n.val) {
            swap(max, n);
            state.done = true;
        } else if (min != null && min.val >= n.val) {
            swap(min, n);
            state.done = true;
        } else {
            if (state.done) {
                return;
            }
            solve(n.left, n, min, state);
            if (state.done) {
                return;
            }
            solve(n.right, max, n, state);
            if (state.done) {
                return;
            }

        }
    }

    private void swap (TreeNode a, TreeNode b) {
        int temp = a.val;
        a.val = b.val;
        b.val = temp;
    }
}
class State {
    boolean done;
}

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;

    TreeNode(int val) { this.val = val; }
}