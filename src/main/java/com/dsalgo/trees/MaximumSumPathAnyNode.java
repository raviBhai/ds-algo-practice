package com.dsalgo.trees;

import com.dsalgo.practice.trees.Node;

public class MaximumSumPathAnyNode {

    static int solve(Node node, int[] result) {
        if (node == null) {
            return 0;
        }
        int left = solve(node.leftChild, result);
        int right = solve(node.rightChild, result);

        int temp = Math.max(
                        Math.max(left, right) + node.data,
                        node.data
                    );
        int ans = Math.max(temp, left + right + node.data);
        result[0] = Math.max(result[0], ans);

        return temp;
    }

    public static void main(String[] args) {
        int[] arr = {0};
        Node root = Helper.getBTree();
        solve(root, arr);
        System.out.println(arr[0]);
    }
}
