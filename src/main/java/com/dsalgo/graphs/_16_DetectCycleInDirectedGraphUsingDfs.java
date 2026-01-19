package com.dsalgo.graphs;

import java.util.ArrayList;
import java.util.List;


/**
 * We need to detect a cycle in a directed graph.
 *
 * A cycle exists in a directed graph if we can start from a node and, by following directed edges,
 * return to the same node.
 *
 * Example 1:
 *   1 ---> 2
 *   ↑      ↓
 *   ------ 3
 *
 *   This graph contains a cycle since we can start at node 1 and return to 1.
 *
 * Example 2:
 *   1 ---> 2
 *   ↓      ↓
 *   ------ 3
 *
 *   This graph does NOT contain a cycle, even though node 3 is reachable from multiple paths.
 *
 * To detect a cycle, we must check whether a node is visited again **within the same DFS path**.
 * If a node is revisited in a different path, it does not indicate a cycle.
 *
 * Consider this example:
 *
 *   1 ---> 2 ------------------> 3 --------> 4
 *          ↑                     ↓           ↓
 *          8 <------             7 --------> 5 -----> 6
 *          ↓       |
 *          9 ---> 10
 *
 * Suppose we start DFS from node 1:
 *   Path 1: 1 → 2 → 3 → 4 → 5 → 6
 *   After backtracking to 3, DFS continues:
 *   Path 2: 1 → 2 → 3 → 7 → 5 → 6
 *
 * To track this, we maintain two arrays:
 *   1. `visited[]`     – marks nodes that have been visited at least once.
 *   2. `pathVisited[]` – marks nodes currently in the current DFS recursion stack (path).
 *
 * When we visit a node for the first time, we mark it as visited in both arrays.
 * When we backtrack (i.e., finish exploring all paths from that node), we unmark it in `pathVisited`
 * but keep it marked in `visited`.
 *
 * The key condition for detecting a cycle:
 *   → If during DFS we encounter a node that is both `visited` and `pathVisited`,
 *     it means we have found a cycle.
 *
 * Example DFS flow:
 *
 * dfs(node_3) {
 *     // Start processing node 3
 *     neighbours = getNeighbours(3);
 *     for (node : neighbours) {
 *         boolean isCycle = dfs(node);
 *         if (isCycle) {
 *             // A cycle is detected in a descendant path
 *             // node_3 execution will return from here and will not end successfully
 *             return true;
 *         }
 *     }
 *     // Finished processing node 3 successfully (no cycle found from here)
 *     // Backtrack: unmark from current path
 *     pathVisited[3] = false;
 *     return false;
 * }
 *
 * When a node’s DFS completes successfully, it means all possible paths from that node
 * have been explored without finding a cycle. At that point, we backtrack and unmark it
 * from `pathVisited`.
 */

public class _16_DetectCycleInDirectedGraphUsingDfs {

    private static boolean isCycle(List<List<Integer>> adjacencyList, int start, boolean[] visited, boolean[] pathVisited) {

        visited[start] = true;
        pathVisited[start] = true;


        List<Integer> neighbours = adjacencyList.get(start);
        for (int neighbour : neighbours) {
            if (!visited[neighbour]) {  // if node is not visited, pathVisited will also be not visited
                boolean isCycle = isCycle(adjacencyList, neighbour, visited, pathVisited);
                if (isCycle) {
                    return true;
                }
            } else if (visited[neighbour] && pathVisited[neighbour]) {  // if node is visited and pathVisited as well, then it is a cycle
                return true;
            }
        }


        pathVisited[start] = false;
        return false;
    }

    private static boolean solve(List<List<Integer>> adjacencyList) {
        boolean[] visited = new boolean[adjacencyList.size()];
        boolean[] pathVisited = new boolean[adjacencyList.size()];

        for (int i = 1; i < visited.length; i++) {
            if (!visited[i]) {
                boolean isCycle = isCycle(adjacencyList, i, visited, pathVisited);
                if (isCycle) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        List<List<Integer>> adjacencyList1 = new ArrayList<>();

        /**
         *

         1 ---> 2 ------------------> 3 --------> 4
                ↑                     ↓           ↓
                8 <------             7 --------> 5 -----> 6
                ↓       |
                9 ---> 10

         *
         */

        int n = 10;
        for (int i = 0; i <= n; i++) {
            adjacencyList1.add(new ArrayList<>());
        }
        adjacencyList1.get(1).add(2);

        adjacencyList1.get(2).add(3);

        adjacencyList1.get(3).add(4);
        adjacencyList1.get(3).add(7);

        adjacencyList1.get(4).add(5);

        adjacencyList1.get(5).add(6);

        // adjacencyList1.get(6).add(7);

        adjacencyList1.get(7).add(5);

        adjacencyList1.get(8).add(2);
        adjacencyList1.get(8).add(9);

        adjacencyList1.get(9).add(10);

        adjacencyList1.get(10).add(8);

        System.out.println(solve(adjacencyList1));  // cycle present


        List<List<Integer>> adjacencyList2 = new ArrayList<>();

        /**
         *

         1 ---> 2 ------------------> 3 --------> 4
                 ↑                    ↓           ↓
                 8 ------             7 --------> 5 -----> 6
                 ↓       ↓
                 9 ---> 10

         *
         */

        int n2 = 10;
        for (int i = 0; i <= n2; i++) {
            adjacencyList2.add(new ArrayList<>());
        }
        adjacencyList2.get(1).add(2);

        adjacencyList2.get(2).add(3);

        adjacencyList2.get(3).add(4);
        adjacencyList2.get(3).add(7);

        adjacencyList2.get(4).add(5);

        adjacencyList2.get(5).add(6);

        // adjacencyList2.get(6).add(7);

        adjacencyList2.get(7).add(5);

        adjacencyList2.get(8).add(2);
        adjacencyList2.get(8).add(9);
        adjacencyList2.get(8).add(10);

        adjacencyList2.get(9).add(10);


        System.out.println(solve(adjacencyList2));  // cycle not present
    }
}
