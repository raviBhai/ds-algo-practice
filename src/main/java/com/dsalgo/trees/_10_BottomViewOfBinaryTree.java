package com.dsalgo.trees;

import com.dsalgo.graphs.Pair;

import java.util.*;

/*

This is similar to vertical order level traversal.
We will use BFS.

We can use a Map which has key as Level and value as Node.
Whenever we encounter another value for the same level, we override it in the map.

Collision at same row and col is possible.
In case of collision, we need to take the right node
With BFS, we traverse from left to right, so in case of collision, the right value will override the left value in the map.


                 1
               /  \
              /    \
             /      \
            2        3
           / \       /\
          /   \     /  \
         /     \   /    \
        4      10 9     10
         \
          \
           \
            5

Output - 4, 5, 9, 3, 10

 */
public class _10_BottomViewOfBinaryTree {
    private static List<Integer> solve(Node root) {

        // node, level
        Deque<Pair<Node, Integer>> queue = new LinkedList<>();

        Map<Integer, Integer> map = new TreeMap<>();

        queue.offer(new Pair<>(root, 0));


        while (!queue.isEmpty()) {
            Pair<Node, Integer> poll = queue.poll();
            Node currentNode = poll.first;
            Integer level = poll.second;

            map.put(level, currentNode.data);

            if (currentNode.left != null) {
                queue.offer(new Pair<>(currentNode.left, level - 1));
            }
            if (currentNode.right != null) {
                queue.offer(new Pair<>(currentNode.right, level + 1));
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (Integer value : map.values()) {
            ans.add(value);
        }

        return ans;
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

        System.out.println(solve(n1));
    }
}
