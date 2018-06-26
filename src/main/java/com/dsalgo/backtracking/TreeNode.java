package com.dsalgo.backtracking;

public class TreeNode {
    int data;
    boolean isGood;
    TreeNode leftChild;
    TreeNode rightChild;

    public TreeNode(int data, boolean isGood) {
        this.data = data;
        this.isGood = isGood;
    }
    public void display() {
        System.out.println(data);
    }
}
