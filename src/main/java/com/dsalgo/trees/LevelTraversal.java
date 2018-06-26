package com.dsalgo.trees;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import com.dsalgo.practice.trees.Node;

public class LevelTraversal {
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

        n1.leftChild = n2;
        n1.rightChild = n3;

        n2.leftChild = n4;
        n2.rightChild = n5;

        n3.leftChild = n6;
        n3.rightChild = n7;

        n4.leftChild = n8;
        n4.rightChild = n9;

        n5.leftChild = n10;

        LevelTraversal lt = new LevelTraversal();
        lt.horizontalLevelTraverse(n1);
        lt.verticalLevelTraverse(n1);
        lt.diagonalLevelTraversal(n1);
    }

    public void horizontalLevelTraverse(Node root) {
        Map<Integer, LinkedList<Node>> map = new HashMap<>();
        horizontalLevelTraverseHelper(root, 1 , map);
        System.out.println(map);
    }

    private void horizontalLevelTraverseHelper(Node node, Integer level, Map<Integer, LinkedList<Node>> map) {
        if (node != null) {
            if (map.get(level) == null) {
                LinkedList<Node> list = new LinkedList<>();
                list.add(node);
                map.put(level, list);
            } else {
                LinkedList<Node> list = map.get(level);
                list.add(node);
            }
            horizontalLevelTraverseHelper(node.leftChild, level+1, map);
            horizontalLevelTraverseHelper(node.rightChild, level+1, map);
        }
    }

    public void verticalLevelTraverse(Node root) {
        Map<Integer, LinkedList<Node>> map = new HashMap<>();
        verticalLevelTraverseHelper(root, 1 , map);
        System.out.println(map);
    }

    private void verticalLevelTraverseHelper(Node node, Integer level, Map<Integer, LinkedList<Node>> map) {
        if (node != null) {
            if (map.get(level) == null) {
                LinkedList<Node> list = new LinkedList<>();
                list.add(node);
                map.put(level, list);
            } else {
                LinkedList<Node> list = map.get(level);
                list.add(node);
            }
            verticalLevelTraverseHelper(node.leftChild, level-1, map);
            verticalLevelTraverseHelper(node.rightChild, level+1, map);
        }
    }

    public void diagonalLevelTraversal(Node root) {
        Map<Integer, LinkedList<Node>> map = new HashMap<>();
        diagonalLevelTraversalHelper(root, 1, map);
        System.out.println(map);
    }

    private void diagonalLevelTraversalHelper(Node node, Integer level, Map<Integer, LinkedList<Node>> map) {
        if (node != null) {
            if (map.get(level) == null) {
                LinkedList<Node> list = new LinkedList<>();
                list.add(node);
                map.put(level, list);
            } else {
                LinkedList<Node> list = map.get(level);
                list.add(node);
            }
            diagonalLevelTraversalHelper(node.leftChild, level + 1, map);
            diagonalLevelTraversalHelper(node.rightChild, level, map);
        }
    }

}
/*

            1
         2     3
       4   5  6  7
      8 9  10

 */
