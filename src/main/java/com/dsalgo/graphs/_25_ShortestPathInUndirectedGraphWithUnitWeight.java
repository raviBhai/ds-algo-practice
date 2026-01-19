package com.dsalgo.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*

Given input undirected graph with weights as 1 on each edge.
Given a source node.
Calculate the shortest distance from source node to all the other nodes.

Solution:
As this is undirected graph, we cannot use topo sort.
The distances on each edge are unit.
It means if we reach a node from a source node using BFS, it is the shortest path.
If we reach the same node again from the source node using BFS, and the same node was visited previously, it is not the shortest path.
As BFS ensures that a node is reached in a minimum number of steps from a source node, we will use BFS.

Just like _24_ShortestPathInDAG_TopoSort, we will maintain a dist[] array

Note: Had the distance on each edge been greater than 1, we cannot use the same approach.
Let's say, there are 3 nodes - a,b,c
  a ---- b
  \    /
   \  /
    c

 Distances:
 a to b : 10
 a to c : 2
 b to c : 3

 If we start from a and reach b directly, distance will be 10. However, if we reach via c, distance will be 2+3.
 So, in this case we cannot use BFS as used in this approach.
 We will use modified BFS or Dijkstra's algo for this.

 */
public class _25_ShortestPathInUndirectedGraphWithUnitWeight {

    private static final int INF = Integer.MAX_VALUE;

    private static int[] solve(int[][] edges, int N, int source) {
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        for (int[] edge: edges) {
            adjacencyList.get(edge[0]).add(edge[1]);
            adjacencyList.get(edge[1]).add(edge[0]);
        }
        int[] dist = new int[N];
        for (int i = 0; i < N; i++) {
            dist[i] = INF;
        }

        dist[source] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);

        while (!queue.isEmpty()) {
            Integer currentNode = queue.poll();
            List<Integer> neighbours = adjacencyList.get(currentNode);
            for (int neighbour : neighbours) {
                if (dist[currentNode] != INF && dist[currentNode] + 1 < dist[neighbour]) {
                    dist[neighbour] = 1 + dist[currentNode];
                    queue.add(neighbour);
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        /*
            1 --- 2 -----------      7
          / |                  |   / |
        0   |                  |  /  |
          \ |                  | /   |
           3 ----- 4 --- 5 --- 6 --- 8

         */

        int[][] edges = {
                {0, 1}, {0, 3}, {1, 3}, {1, 2}, {2, 6}, {3, 4}, {4, 5}, {5, 6}, {6, 7}, {6, 8}, {7, 8}
            };

        int[] dist = solve(edges, 9, 0);
        for (int d : dist) {
            System.out.println(d);
        }
        // expected output - 0,1,2,1,2,3,3,4,4
    };


}
