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

    public int diameter2(Node node, Height height) {
        if (node == null) {
            height.height = 0;
            return 0;
        }
        Height leftHeight = new Height();
        Height rightHeight = new Height();

        int lDia = diameter2(node.leftChild, leftHeight);
        int rDia = diameter2(node.rightChild, rightHeight);
        height.height = 1 + Math.max(leftHeight.height, rightHeight.height);

        int currDia = leftHeight.height + rightHeight.height + 1;
        return Math.max(currDia, Math.max(lDia, rDia));
    }

    /**
     * Passing diameter array as if integer is passed by reference.
     * Everytime height of a node is calculated, at the same time, diameter
     * of the tree at that node is calculated.
     * It is then compared with global diameter (which is array) to get max diameter
     */
    static int calculateHeight(Node node, int[] diameter) {
        if (node == null) {
            return 0;
        }

        // Recursively calculate the
        // height of left and right subtrees
        int leftHeight = calculateHeight(node.leftChild, diameter);
        int rightHeight = calculateHeight(node.rightChild, diameter);

        // Calculate the diameter at the current
        // node and update the global variable
        diameter[0] = Math.max(diameter[0], leftHeight + rightHeight + 1);

        // Return the height
        // of the current subtree
        return 1 + Math.max(leftHeight, rightHeight);
    }


    static int diameter3(Node node, int[] result) {
        if (node == null) {
            return 0;
        }
        int left = diameter3(node.leftChild, result);
        int right = diameter3(node.rightChild, result);

        int temp = 1 + Math.max(left, right);
        int ans = Math.max(temp, 1 + left + right);
        result[0] = Math.max(result[0], ans);

        return temp;
    }

    public static void main(String[] args) {
        Node root = Helper.getBTree();
        DiameterOfTree diameterOfTree = new DiameterOfTree();
        System.out.println(diameterOfTree.diameter(root));

        Height height = new Height();
        System.out.println(diameterOfTree.diameter2(root, height));

        int[] diameter = {Integer.MIN_VALUE};
        calculateHeight(root, diameter);
        System.out.println(diameter[0]);

        int[] arr1 = {Integer.MIN_VALUE};
        diameter3(root, arr1);
        System.out.println(arr1[0]);
    }
}

class Height {
    int height;
}