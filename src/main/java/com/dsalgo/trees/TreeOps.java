package com.dsalgo.trees;

import com.dsalgo.practice.trees.Node;

public class TreeOps {

    public void inorder(Node node) {
        if (node != null) {
            inorder(node.leftChild);
            System.out.println(node.data);
            inorder(node.rightChild);
        }
    }

    public static void main(String[] args) {
        Node root = Helper.getBTree();
        TreeOps ops = new TreeOps();
        ops.inorder(root);
    }
}
