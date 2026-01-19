package com.dsalgo.graphs;


import java.util.ArrayList;
import java.util.List;

public class _3_DFS {

    public static void dfs(int node, boolean[] visited, List<List<Integer>> adjacencyList, List<Integer> result) {
        visited[node] = true;
        result.add(node);

        List<Integer> neighbours = adjacencyList.get(node);
        for (int neighbour : neighbours) {
            if (!visited[neighbour]) {
                dfs(neighbour, visited, adjacencyList, result);
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

        boolean[] visited = new boolean[adjacencyList.size()];
        List<Integer> result = new ArrayList<>();
        dfs(1, visited, adjacencyList, result);
        System.out.println(result);
    }
}
