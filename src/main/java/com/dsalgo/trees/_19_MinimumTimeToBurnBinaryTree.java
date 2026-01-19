package com.dsalgo.trees;

import com.dsalgo.graphs.Pair;

import java.util.*;

/*
Given root, and another start node.
Root is the reference of the B-tree.
Start node is the node from where we need to start burning the tree.
Find minimum time to burn the binary tree.
In time 1, first level of adjacent nodes can be burnt.
In time 2, next level of adjacent nodes can be burnt.
Find the total time taken to burn all the nodes in the tree.

Solution:
From start node, we can get adjacent nodes.
Adjacent nodes include - left, right, parent

Use BFS similar to that used in _18_PrintNodesAtDistanceK
 */
public class _19_MinimumTimeToBurnBinaryTree {
    public static Integer solve(Node root, Node target) {

        Map<Node, Node> nodeToParent = markParents(root);

        Deque<Pair<Node, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair<>(target, 0));
        Set<Node> visited = new HashSet<>();

        visited.add(target);

        int result = Integer.MIN_VALUE;

        while (!queue.isEmpty()) {
            Pair<Node, Integer> polled = queue.poll();
            result = Math.max(result, polled.second);
            Node current = polled.first;
            List<Node> neighbours = getNeighbours(current, nodeToParent);
            for (Node neighbour : neighbours) {
                if (!visited.contains(neighbour)) {
                    queue.offer(new Pair<>(neighbour, polled.second + 1));
                    visited.add(neighbour);
                }
            }
        }

        return result;

    }

    private static Map<Node, Node> markParents(Node node) {
        Map<Node, Node> nodeToParent = new HashMap<>();
        Deque<Node> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            Node left = current.left;
            Node right = current.right;

            if (left != null) {
                nodeToParent.put(left, current);
                queue.offer(left);
            }
            if (right != null) {
                nodeToParent.put(right, current);
                queue.offer(right);
            }
        }
        return nodeToParent;
    }

    private static List<Node> getNeighbours(Node node, Map<Node, Node> nodeToParent) {
        List<Node> neighbours = new ArrayList<>();
        if (node.left != null) {
            neighbours.add(node.left);
        }
        if (node.right != null) {
            neighbours.add(node.right);
        }
        if (nodeToParent.containsKey(node)) {
            neighbours.add(nodeToParent.get(node));
        }
        return neighbours;
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

        n4.left = n8;
        n4.right = n9;

        n5.left = n10;

        System.out.println(solve(n1, n2));
    }
}
