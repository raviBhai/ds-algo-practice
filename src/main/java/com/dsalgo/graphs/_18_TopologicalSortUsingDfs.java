package com.dsalgo.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**

 Given input graph, return one of the topological sort order.

 5 ---> 0 <--- 4
 ↓             ↓
 2 ---> 3 ---> 1


 Topological sort orders can be -
 5,4,2,3,1,0
 4,5,2,3,1,0

 Return any of the above order.

 Topological sort can be performed only on DAG, not on a graph which has a cycle

 Solution -
 To perform topological sort, initially, we would want to get the node from which there are no outgoing edges.
 Then we would want to get a node from which all the outgoing edges go to the node in the previous step.

 Let's say we traverse the graph using DFS. We trace back from the node when there are no further outgoing edges.
 Then we trace back from the node when all its neighbours are visited.
 Before tracing back, we can push the node on the stack.
 The stack will thus have topological order in reverse.
 Pop the stack and get the topological sort order.

 */
public class _18_TopologicalSortUsingDfs {

    private static void dfs(int node, boolean[] visited, Stack<Integer> stack, List<List<Integer>> adjacencyList) {
        visited[node] = true;
        List<Integer> neighbours = adjacencyList.get(node);
        for (int neighbour : neighbours) {
            if (!visited[neighbour]) {
                dfs(neighbour, visited, stack, adjacencyList);
            }
        }
        stack.push(node);
    }

    public static List<Integer> topologicalSort(List<List<Integer>> adjacencyList) {
        boolean[] visited = new boolean[adjacencyList.size()];
        Stack<Integer> stack = new Stack<>();

        // this adjacency list is 0 based. 0th node is also in the graph
        for (int i = 0; i < adjacencyList.size(); i++) {
            if (!visited[i]) {
                dfs(i, visited, stack, adjacencyList);
            }
        }
        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }

    public static void main(String[] args) {
        /*
         5 ---> 0 <--- 4
         ↓             ↓
         2 ---> 3 ---> 1

         */
        int n1 = 6;
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < n1; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        // adjacencyList.get(0).add(0);

        // adjacencyList.get(1).add(0);

        adjacencyList.get(2).add(3);

        adjacencyList.get(3).add(1);

        adjacencyList.get(4).add(0);
        adjacencyList.get(4).add(1);

        adjacencyList.get(5).add(0);
        adjacencyList.get(5).add(2);

        System.out.println(topologicalSort(adjacencyList));
    }
}
