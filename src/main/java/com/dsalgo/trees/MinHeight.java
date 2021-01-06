package com.dsalgo.trees;

import com.dsalgo.practice.trees.Node;

import java.util.Deque;
import java.util.LinkedList;

public class MinHeight {

    public static Node getBTree() {

        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);

        //n1.leftChild = n2;
        n1.rightChild = n3;

        //n3.leftChild = n4;
        //n3.rightChild = n5;

        return n1;
    }

    public static void main(String[] args) {
        System.out.println(minDepth(getBTree()));
    }

    public static int minDepth(Node root) {

        if (root == null) {
            return 0;
        }
        Deque<Element> q = new LinkedList<>();
        Element e1 = new Element(root, 1);
        q.addLast(e1);

        Element current = null;
        while(!q.isEmpty()) {
            current = q.removeFirst();
            if (current.node.leftChild == null && current.node.rightChild == null) {
                return current.level;
            } else {
                if (current.node.leftChild != null) {
                    Element le = new Element(current.node.leftChild, current.level+1);
                    q.addLast(le);
                }
                if (current.node.rightChild != null) {
                    Element re = new Element(current.node.rightChild, current.level+1);
                    q.addLast(re);
                }
            }
        }
        return 0;

    }

}

class Element {
    Node node;
    int level;

    public Element(Node node, int level) {
        this.node = node;
        this.level = level;
    }
}