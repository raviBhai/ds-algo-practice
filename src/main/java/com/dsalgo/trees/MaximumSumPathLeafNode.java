package com.dsalgo.trees;

import com.dsalgo.practice.trees.Node;

public class MaximumSumPathLeafNode {

    static int solve2(Node node, int[] result) {
        if (node == null) {
            return 0;
        }
        int left = solve2(node.leftChild, result);
        int right = solve2(node.rightChild, result);

        if (node.leftChild != null && node.rightChild != null) {
            int temp = Math.max(left, right) + node.data;

            int ans = left + right + node.data;
            result[0] = Math.max(result[0], ans);

            return temp;
        } else if (node.leftChild != null) {
            // Don't do temp = max(left, right) + data
            // because right will be 0 as right is null and left can be -ve
            // we want to consider -ve in this problem, hence no max is used
            int temp = left + node.data;
            int ans = left + node.data;
            result[0] = Math.max(result[0], ans);
            return temp;
        } else {
            int temp = right + node.data;
            int ans = right + node.data;
            result[0] = Math.max(result[0], ans);
            return temp;
        }
    }

    public static void main(String[] args) {
        int[] arr = {0};
        Node root = Helper.getBTree();
        solve2(root, arr);
        System.out.println(arr[0]);


        Node n1 = new Node(10);
        Node n2 = new Node(2);
        Node n3 = new Node(10);
        Node n4 = new Node(20);
        Node n5 = new Node(1);
        Node n6 = new Node(-25);
        Node n7 = new Node(3);
        Node n8 = new Node(4);

        n1.leftChild = n2;
        n1.rightChild = n3;

        n2.leftChild = n4;
        n2.rightChild = n5;

        n3.rightChild = n6;
        n6.leftChild = n7;
        n6.rightChild = n8;

        int[] arr2 = {0};
        solve2(n1, arr2);
        System.out.println(arr2[0]);

        Node a1 = new Node(1);
        Node a2 = new Node(2);
        a1.leftChild = a2;
        int[] arr3 = {0};
        solve2(a1, arr3);
        System.out.println(arr3[0]);
    }
}
