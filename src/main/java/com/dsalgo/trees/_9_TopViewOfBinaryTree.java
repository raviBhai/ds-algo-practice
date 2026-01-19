package com.dsalgo.trees;

import com.dsalgo.graphs.Pair;

import java.util.*;

/*


We can use logic similar to that of vertical order traversal.
We need to maintain only the vertical levels. We do not have to maintain horizontal levels as there will be no collisions for the top view.
For any level, the first encountered node during traversal will be part of the answer. Hence, we can store the answer in a Map whose key is level and value is node

                 1
               /  \
              /    \
             /      \
            2        3
           / \       /\
          /   \     /  \
         /     \   /    \
        4      10 9     10
         \       \
          \       \
           \       \
            5       \
             \       \
              \       \
               \       \
                6       11


Can we use DFS (preorder/inorder/postorder) ?
Inorder of the above tree : 1, 2, 4, 5, 6, 10, 11, 3, 9, 10
Nodes 11 and 3 are the same vertical level, however, we will end up visiting 11 before 3.
Hence, we cannot use DFS here, we have to use BFS

 */
public class _9_TopViewOfBinaryTree {

    private static List<Integer> solve(Node root) {

        // node, level
        Deque<Pair<Node, Integer>> queue = new LinkedList<>();

        Map<Integer, Integer> map = new TreeMap<>();

        queue.offer(new Pair<>(root, 0));


        while (!queue.isEmpty()) {
            Pair<Node, Integer> poll = queue.poll();
            Node currentNode = poll.first;
            Integer level = poll.second;

            if (!map.containsKey(level)) {
                map.put(level, currentNode.data);
            }
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
