package com.dsalgo.ctci;

import com.dsalgo.practice.trees.NodeP;

public class InOrderSuccessor {
    public static void main(String[] args) {

    }

    public static NodeP inOrderSuccessor(NodeP node) {
        if (node == null) {
            return null;
        }
        if (node.rightChild != null) {
            return getLeftMostChild(node.rightChild);
        } else {
            NodeP p = node.parent;
            while (p != null && p.rightChild == node) {
                node = p;
                p = p.parent;
            }
            return p;
        }
    }

    private static NodeP getLeftMostChild(NodeP node) {
        if (node == null) {
            return null;
        }
        while (node.leftChild != null) {
            node = node.leftChild;
        }
        return node;
    }
}
