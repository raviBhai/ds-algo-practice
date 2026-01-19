package com.dsalgo.trees;

import com.dsalgo.graphs.Triplet;

import java.util.*;

/*

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
             \
              \
               \
                6



Traverse above tree vertical level wise.
10, 9 are the same vertical level. In this case, take the smaller number first.
So, nodes at this vertical level would be - 1, 9, 10, 6

To do so, we can associate a row and a column number with each node.
row - horizontal
col - vertical

Root node can have row,col as 0,0
Left of root node can have row+1,col-1 = 1,-1
Right of root node can have row+1,col+1 = 1,1

While associating every node with row,col, put them in the below data structure -
Map<Integer, Map<Integer, PriorityQueue<Integer>>>

Outer map's key is column number
Within each column, we can have a scenario when there are nodes at the same row - for eg, 10,9 are in the same column and same row.
So, we can group nodes in the same row which are in the same column

Inner map's key is row number

As within this group we want the elements to be sorted, we can store them in a priority queue.

Traversal :
We can do inorder traversal and pass 3 args with each recursive call - node, row, col
And then, for every node, we can populate the map.

Inorder will be DFS

Else, we can do a BFS using a queue.

 */
public class _8_VerticalOrderTraversal {

    public static List<List<Integer>> solve(Node node) {

        Deque<Triplet<Node, Integer, Integer>> queue = new LinkedList<>();
        queue.offer(new Triplet<>(node, 0, 0));

        Map<Integer, Map<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();

        while (!queue.isEmpty()) {
            Triplet<Node, Integer, Integer> poll = queue.poll();
            Node currentNode = poll.first;
            int row = poll.second;
            int col = poll.third;

            if (!map.containsKey(col)) {
                map.put(col, new TreeMap<>());
            }
            if (!map.get(col).containsKey(row)) {
                map.get(col).put(row, new PriorityQueue<>());
            }
            map.get(col).get(row).offer(currentNode.data);

            if (currentNode.left != null) {
                queue.offer(new Triplet<>(currentNode.left, row+1, col-1));
            }

            if (currentNode.right != null) {
                queue.offer(new Triplet<>(currentNode.right, row+1, col+1));
            }
        }

        List<List<Integer>> result = new ArrayList<>();
        for (Integer colKey : map.keySet()) {
            Map<Integer, PriorityQueue<Integer>> innerMap = map.get(colKey);
            List<Integer> list = new ArrayList<>();
            for (Integer rowKey : innerMap.keySet()) {
                PriorityQueue<Integer> pq = innerMap.get(rowKey);
                while (!pq.isEmpty()) {
                    list.add(pq.poll());
                }
            }
            result.add(list);
        }

        return result;
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
