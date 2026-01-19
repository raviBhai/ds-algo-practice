package com.dsalgo.graphs;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class _2_BFS {

    /**
     * Time complexity -
     *
     * Let's say the graph has n nodes and m edges.
     * Time complexity will be a result of both n and m.
     *
     * The while loop over the Q runs n times, as there are n nodes.
     * For every node, the for loop runs as many times as the number of neighbours of that node, which is the degree of the node.
     *
     * If we count the number of times the for loop is invoked for all the nodes combined, it will be the sum of degrees of all the nodes.
     * Sum of degree = 2 * numberOfEdges = 2*m
     *
     * Hence, total time complexity = total times while loop runs + total times for loop runs
     *                              = O(n) + O(2*m)
     *                              = O(n + m)
     *
     * @param adjacencyList
     * @param startNode
     */

    private static void bfs(List<List<Integer>> adjacencyList, int startNode) {
        boolean[] visited = new boolean[adjacencyList.size()];
        Deque<Integer> deque = new LinkedList<>();

        visited[startNode] = true;
        deque.addLast(startNode);

        while (!deque.isEmpty()) {
            int currentNode = deque.removeFirst();
            System.out.println(currentNode);
            List<Integer> neighbours = adjacencyList.get(currentNode);
            for (Integer neighbour : neighbours) {
                if (!visited[neighbour]) {
                    visited[neighbour] = true;
                    deque.addLast(neighbour);
                }
            }
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> adjacencyList = new ArrayList<>();
        int n = 10;

        for (int i = 0; i <= n; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        adjacencyList.get(1).add(2);
        adjacencyList.get(1).add(6);

        adjacencyList.get(2).add(1);
        adjacencyList.get(2).add(3);
        adjacencyList.get(2).add(4);

        adjacencyList.get(6).add(1);
        adjacencyList.get(6).add(7);
        adjacencyList.get(6).add(9);

        adjacencyList.get(3).add(2);

        adjacencyList.get(4).add(2);
        adjacencyList.get(4).add(5);

        adjacencyList.get(7).add(6);
        adjacencyList.get(7).add(8);

        adjacencyList.get(9).add(6);

        adjacencyList.get(5).add(4);
        adjacencyList.get(5).add(8);

        adjacencyList.get(8).add(7);
        adjacencyList.get(8).add(5);

        /*
                     1
                   /   \
                  /     \
                2        6
              /   \     /   \
            3      4   7    9
                  /     \
                 5 ----  8
        */


        bfs(adjacencyList, 1);

        System.out.println("********");
        bfs(adjacencyList, 6);
        // when start node is 6, get neighbours which are 1 distance away - 1,7,9, to perform bfs
        // then make current node as 1, and from 1, non visited neighbours which are 1 distance away are - 2
        // then make current node as 7, and from 7, non visited neighbours which are 1 distance away are - 8
        // then make current node as 2, and from 2, non visited neighbours which are 1 distance away are - 3,4
        // then make current node as 8, and from 8, non visited neighbours which are 1 distance away are - 5

    }
}
