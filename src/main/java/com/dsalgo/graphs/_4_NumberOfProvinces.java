package com.dsalgo.graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a graph which has connected components.
 * These components are not connected to each other, but all the components are part of the graph.
 *
 * Example - Assume a graph has nodes 1 to 8
 * Nodes - 1,2,3,4,5,6,7,8
 * This is how the nodes are connected :
 * 1 --- 2 ---- 3
 * 4 ----5 ---- 6
 * 7 --- 8
 *
 * Here, we see that there are 3 connected components.
 *
 * Return 3 as the output
 */
public class _4_NumberOfProvinces {

    public static int countProvinces(List<List<Integer>> adjacencyList) {
        int count = 0;
        boolean[] visited = new boolean[adjacencyList.size()];
        for (int i = 1; i < visited.length; i++) {
            if (!visited[i]) {
                count++;
                dfs(i, visited, adjacencyList);
            }
        }
        return count;
    }

    public static void dfs(int node, boolean[] visited, List<List<Integer>> adjacencyList) {
        visited[node] = true;

        List<Integer> neighbours = adjacencyList.get(node);
        for (int neighbour : neighbours) {
            if (!visited[neighbour]) {
                dfs(neighbour, visited, adjacencyList);
            }
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> adjacencyList = new ArrayList<>();
        int n = 8;
        for (int i = 0; i <= n; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        adjacencyList.get(1).add(2);
        adjacencyList.get(2).add(1);
        adjacencyList.get(2).add(3);
        adjacencyList.get(3).add(2);

        adjacencyList.get(4).add(5);
        adjacencyList.get(5).add(4);
        adjacencyList.get(5).add(6);
        adjacencyList.get(6).add(5);

        adjacencyList.get(7).add(8);
        adjacencyList.get(8).add(7);

        System.out.println(countProvinces(adjacencyList));
    }
}
