package com.dsalgo.trees;

import com.dsalgo.graphs.Pair;

import java.util.*;

/*

similar to _11_RightViewOfBinaryTree, just traverse opposite:

BFS : right to left
DFS : root, left, right
 */
public class _12_LeftVieewOfBinaryTree {

    public static List<Integer> bfs(Node root) {
        // node, level
        Deque<Pair<Node, Integer>> queue = new LinkedList<>();

        Map<Integer, Integer> map = new TreeMap<>();

        queue.offer(new Pair<>(root, 0));


        while (!queue.isEmpty()) {
            Pair<Node, Integer> poll = queue.poll();
            Node currentNode = poll.first;
            Integer level = poll.second;

            map.put(level, currentNode.data);

            if (currentNode.right != null) {
                queue.offer(new Pair<>(currentNode.right, level + 1));
            }

            if (currentNode.left != null) {
                queue.offer(new Pair<>(currentNode.left, level + 1));
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (Integer value : map.values()) {
            ans.add(value);
        }

        return ans;
    }

    public static void dfs(Node node, List<Integer> list, int level) {
        if (node == null) {
            return;
        }

        if (level == list.size()) {
            list.add(node.data);
        }
        dfs(node.left, list, level+1);
        dfs(node.right, list, level+1);
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

        System.out.println("BFS - ");
        System.out.println(bfs(n1));

        System.out.println("DFS - ");
        List<Integer> list = new ArrayList<>();
        dfs(n1, list, 0);
        System.out.println(list);
    }
}
