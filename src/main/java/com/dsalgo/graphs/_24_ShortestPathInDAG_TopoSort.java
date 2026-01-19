package com.dsalgo.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*

Given a directed acyclic graph with weights on its edges.
Given a source node.
Find the shortest path from the source node to all the other nodes.

           0 ----(2)-------> 1 ---(1)--> 3
           ↑(3)                          ↑
6 --(2)--> 4 --(1)--> 2 --------(3)-------
↓(3)       ↑
5 --(1)----

src,    dest,   weight
6,      4,      2
6,      5,      3
5,      4,      1
4,      0,      3
4,      2,      1
0,      1,      2
1,      3,      1
2,      3,      3

Solution:
Dijkstra's algorithm can be used to find the shortest path for any cyclic or acyclic graph.
But, if we are given an acyclic graph, rather than going for Dijkstra's algorithm, use Topo sort.

Finding the shortest path to a vertex is easy if we already know the shortest path to all the vertices that lead to the current vertex.

Algorithm:
1. Do Topo sort on the input graph using dfs. Have the sort order in a stack.
2. Maintain a dist[] array whose meaning is to store the shortest distance from the source node to ith node in dist[] array.
3. Initialize dist[source] = 0, as the shortest distance from the source node to dist[source] node will always be 0.
4. Pop an element from the stack, call it as currentNode.
5. distance from source node to currentNode will be dist[currentNode]
6. From the currentNode, get its neighbours, let's say a and b. We can also get weights from currentNode to a and b from the input itself.
7. For every such neighbour, we have distance from currentNode to neighbour and distance from source to currentNode.
8. So, distanceFromSourceTo_a (via current) = distanceFromSourceToCurrentNode + distanceFromCurrentNodeTo_a
9. Also, we have existing distanceFromSourceTo_a from dist[a].
10. Take minimum of dist[a] and  distanceFromSourceTo_a (via current), and update in dist[a]
11. Return dist as the output

 */
public class _24_ShortestPathInDAG_TopoSort {

    private static final int INF = Integer.MAX_VALUE;

    // topo sort using dfs
    private static void topoSort(int node, Stack<Integer> stack, List<List<Pair<Integer, Integer>>> adjacencyList, boolean[] visited) {
        visited[node] = true;
        List<Pair<Integer, Integer>> neighbours = adjacencyList.get(node);
        for (Pair<Integer, Integer> neighbour : neighbours) {
            if (!visited[neighbour.first]) {
                topoSort(neighbour.first, stack, adjacencyList, visited);
            }
        }
        stack.push(node);
    }

    private static int[] solve(int N, int[][] edges, int source) {
        // adj list can store destination and weight in the pair object
        List<List<Pair<Integer, Integer>>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        for (int i = 0; i < edges.length; i++) {
            int src = edges[i][0];
            int dest = edges[i][1];
            int wt = edges[i][2];

            adjacencyList.get(src).add(new Pair<>(dest, wt));
        }

        boolean[] visited = new boolean[adjacencyList.size()];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                topoSort(i, stack, adjacencyList, visited);
            }
        }

        int[] dist = new int[N];
        for (int i = 0; i < N; i++) {
            dist[i] = INF;
        }
        dist[source] = 0;
        while (!stack.isEmpty()) {
            Integer currentNode = stack.pop();
            int distanceFromSourceToCurrentNode = dist[currentNode];
            List<Pair<Integer, Integer>> neighbours = adjacencyList.get(currentNode);
            for (Pair<Integer, Integer> neighbour : neighbours) {
                if (distanceFromSourceToCurrentNode != INF && distanceFromSourceToCurrentNode + neighbour.second < dist[neighbour.first]) {
                    dist[neighbour.first] = distanceFromSourceToCurrentNode + neighbour.second;
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        /*
                       0 ----(2)-------> 1 ---(1)--> 3
                       ↑(3)                          ↑
            6 --(2)--> 4 --(1)--> 2 --------(3)-------
            ↓(3)       ↑
            5 --(1)----

         */
        int N = 7;
        int[][] edges = {{6, 4, 2}, {6, 5, 3}, {5, 4, 1}, {4, 0, 3}, {4, 2, 1}, {0, 1, 2}, {1, 3, 1}, {2, 3, 3}};

        int[] dist = solve(N, edges, 6);
        for (int d : dist) {
            System.out.println(d);
        }
        // expected output - 5,7,3,6,2,3,0

        System.out.println("************");

        dist = solve(N, edges, 4);
        for (int d : dist) {
            System.out.println(d);
        }
        // expected output - 3,5,1,4,0,INF,INF
    }
}
