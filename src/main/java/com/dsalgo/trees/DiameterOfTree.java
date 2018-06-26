package com.dsalgo.trees;

import com.dsalgo.practice.trees.Node;

public class DiameterOfTree {

    public int diameter(Node node) {
        if (node == null) {
            return 0;
        }
        int lht = height(node.leftChild);
        int rht = height(node.rightChild);
        int currDia = lht + rht + 1;
        int lDia = diameter(node.leftChild);
        int rDia = diameter(node.rightChild);
        return Math.max(currDia, Math.max(lDia, rDia));
    }

    public int height(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.leftChild), height(node.rightChild));
    }

    public static void main(String[] args) {
        Node root = Helper.getBTree();
        DiameterOfTree diameterOfTree = new DiameterOfTree();
        System.out.println(diameterOfTree.diameter(root));
    }
}
