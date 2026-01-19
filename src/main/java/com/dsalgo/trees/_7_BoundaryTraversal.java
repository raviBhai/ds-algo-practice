package com.dsalgo.trees;

import java.util.ArrayList;
import java.util.List;

/*

Boundary traversal anti-clockwise


3 steps :
1. Traverse left boundary without leaf nodes
2. Traverse tree and collect Leaf Nodes. Traverse tree in this order - root, left, right
3. Traverse right boundary without leaf nodes but in reverse order

IF root is the only node in the tree, it is leaf node and will be collected in step 2

Follow-up question:
For clockwise:
1. Traverse right boundary without leaf nodes
2. [IMPORTANT] Traverse tree for leaf nodes, but in this order - root, right , left
3. Traverse left boundary without leaf nodes but in reverse order

 */
public class _7_BoundaryTraversal {

    static boolean isLeaf(Node node) {
        return node.left == null && node.right == null;
    }

    // Function to collect left boundary nodes which are not leaf nodes
    // (top-down order)
    static void collectLeft(Node node, List<Integer> res) {
        // exclude leaf node
        if (node != null && !isLeaf(node)) {
            res.add(node.data);
            if (node.left != null) {
                collectLeft(node.left, res);
            } else if (node.right != null) {
                collectLeft(node.right, res);
            }
        }
    }

    // Function to collect all leaf nodes
    static void collectLeaves(Node node, List<Integer> res) {
        if (node != null) {
            // Add leaf nodes
            if (isLeaf(node)) {
                res.add(node.data);
            }
            collectLeaves(node.left, res);
            collectLeaves(node.right, res);
        }

    }

    // Function to collect right boundary nodes which are not leaf nodes
    // (bottom-up order)
    static void collectRight(Node node, List<Integer> res) {
        // exclude leaf nodes
        if (node != null && !isLeaf(node)) {
            if (node.right != null) {
                collectRight(node.right, res);
            } else if (node.left != null) {
                collectRight(node.left, res);
            }
            res.add(node.data);
        }
    }

    // Function to find Boundary Traversal of Binary Tree
    static List<Integer> boundaryTraversal(Node root) {
        List<Integer> res = new ArrayList<>();

        if (root == null)
            return res;

        // Add root data if it's not a leaf
        if (!isLeaf(root))
            res.add(root.data);

        // Collect left boundary
        collectLeft(root.left, res);

        // Collect leaf nodes
        collectLeaves(root, res);

        // Collect right boundary
        collectRight(root.right, res);

        return res;
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
        Node n10 = new Node(10);

        n1.left = n2;
        n1.right = n3;

        n2.left = n4;
        n2.right = n5;

        n3.left = n6;
        n3.right = n7;


        n4.right = n9;

        n5.left = n10;

        n7.left = n8;

        System.out.println(boundaryTraversal(n1));

    }
}
