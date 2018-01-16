package com.dsalgo.ctci;

import com.dsalgo.practice.trees.Node;

public class CheckSubtree {

    public static void main(String[] args) {

    }

    public static boolean containsTree(Node n1, Node n2) {
        if (n2 == null) {
            return true;
        }
        return subTree(n1, n2);
    }

    private static boolean subTree(Node n1, Node n2) {
        if (n1 == null) {
            return false;
        } else if ((n1.data == n2.data) && matchTree(n1, n2)) {
            return true;
        }
        return subTree(n1.leftChild, n2) || subTree(n1.rightChild, n2);
    }

    private static boolean matchTree(Node n1, Node n2) {
        if (n1 == null && n2 == null) {
            return true;
        } else if (n1 == null || n2 == null) {
            return false;
        } else if (n1.data != n2.data) {
            return false;
        } else {
            return matchTree(n1.leftChild, n2.leftChild) && matchTree(n1.rightChild, n2.rightChild);
        }
    }
}
