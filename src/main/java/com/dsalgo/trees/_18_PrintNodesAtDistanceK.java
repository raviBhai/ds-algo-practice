package com.dsalgo.trees;

import com.dsalgo.graphs.Pair;

import java.util.*;

/*

Given root node and target node and a distance k.
From the target node, get a list of nodes which are at distance k.

                 1
                / \
               /   \
              /     \
             /       \
            /         \
          2            3
         / \          / \
        /   \        /   \
       /     \      6     7
      /       \
     4         5
    / \       /
   8   9     10

 To get distance from a target node, you can move left, right as well as up, that is, towards parent.

 As in binary tree, we do not have reference of a parent, we will traverse the tree and create a map which has node to its parent mapping.
 We can use BFS to traverse the tree and mark the parent.

 After this, we start doing BFS from the target node,
 get adjacent nodes and check which are not visited.
 Along with the nodes, we can maintain a distance in the queue with every node.
 When we reach the distance, we capture that in result list and do not move ahead from that node.


 */
public class _18_PrintNodesAtDistanceK {

    public static List<Integer> solve(Node root, Node target, int k) {

        Map<Node, Node> nodeToParent = markParents(root);

        Deque<Pair<Node, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair<>(target, 0));
        Set<Node> visited = new HashSet<>();

        visited.add(target);

        List<Integer> result = new ArrayList<>();

        while (!queue.isEmpty()) {
            Pair<Node, Integer> polled = queue.poll();
            if (polled.second == k) {
                result.add(polled.first.data);
                continue;
            }
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

        System.out.println(solve(n1, n2, 2));
    }
}
