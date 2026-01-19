package com.dsalgo.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * An undirected graph has a cycle if you start from a node, traverse the graph and end at the same start node.
 *
 *     2 --- 4 --- 6 --- 10
 *   /              \
 *  1               7
 *   \             /
 *    3 --- 5 --- 8
 *    |
 *    9
 *
 * If we traverse this graph using bfs starting from node 1, we start traversing it in different directions -  2 and 3
 * At the end, both 6 and 8 will try to reach 7. In such a case, we can say that a cycle exists.
 * The adjacent nodes of 6 are 4,7,10; and we came to 6 from 4, so 4 is parent
 * Node 6 : Adjacent nodes - 4 (parent), 7, 10
 *
 * While performing bfs, when the current node becomes 6, we get the adjacent nodes - 4,7,10
 * Out of these, we ignore the parent - 4
 * And of the remaining nodes, we need to check if any of the remaining node is already visited.
 * If yes, it means, someone was able to reach that node through some other path which did not go through 6.
 * This could happen only when there is a cycle in the graph.
 *
 * To figure out who is the parent of a node among its neighbours, we can store the parent along with the node on the Queue
 *
 * Connected components -
 *
 * Above graph has only one component. It is possible that a graph has multiple connected components.
 *
 * 1 --- 2 ---- 3
 *
 * 4 ----5 ---- 6
 *
 * 7 --- 8
 *  \  /
 *   9
 *
 * Above is a graph which has 3 components, which are not connected to each other.
 * Input can be this graph, and it has a cycle in its 3rd component.
 *
 * In this case, we will traverse over all the nodes just like in _4_NumberOfProvinces
 * Let's say we maintain a visited array - 1,2,3,4,5,6,7,8,9
 * When we start traversing from 1, both 2 and 3 will be marked as visited.
 * Hence, we can put an if() check before starting to traverse from a node.
 *
 * Next, we will traverse from 4 which will mark 5 and 6 as visited.
 *
 * Then we start traversing from 7, which will mark 8 and 9 as visited as well as can tell us that it has a cycle
 *
 */
public class _8_DetectCycleInUndirectedGraphUsingBfs {

    private static boolean isCycle(int source, List<List<Integer>> adjacencyList, boolean[] visited) {
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(source, -1));  // -1 is the parent of the first node when the bfs traversal starts
        visited[source] = true;

        while (!queue.isEmpty()) {
            Pair<Integer, Integer> currentNode = queue.poll();
            int currentNodeParent = currentNode.second;
            List<Integer> neighbours = adjacencyList.get(currentNode.first);
            for (int neighbour : neighbours) {
                if (neighbour == currentNodeParent) {
                    continue;
                } else if (neighbour != currentNodeParent && visited[neighbour]) {
                    return true;
                } else if (neighbour != currentNodeParent && !visited[neighbour]) {
                    visited[neighbour] = true;
                    queue.add(new Pair<>(neighbour, currentNode.first));
                }
            }
        }
        return false;
    }

    private static boolean isCycle(List<List<Integer>> adjacencyList) {
        boolean[] visited = new boolean[adjacencyList.size()];
        for (int i = 1; i < visited.length; i++) {
            if (!visited[i]) {
                boolean isCycle = isCycle(i, adjacencyList, visited);
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
         *     2 --- 4 --- 6 --- 10
         *   /              \
         *  1               7
         *   \             /
         *    3 --- 5 --- 8
         *    |
         *    9
         */
        int n = 10;
        for (int i = 0; i <= n; i++) {
            adjacencyList1.add(new ArrayList<>());
        }
        adjacencyList1.get(1).add(2);
        adjacencyList1.get(1).add(3);

        adjacencyList1.get(2).add(1);
        adjacencyList1.get(2).add(4);

        adjacencyList1.get(3).add(1);
        adjacencyList1.get(3).add(5);
        adjacencyList1.get(3).add(9);

        adjacencyList1.get(4).add(2);
        adjacencyList1.get(4).add(6);

        adjacencyList1.get(5).add(3);
        adjacencyList1.get(5).add(8);

        adjacencyList1.get(6).add(4);
        adjacencyList1.get(6).add(7);
        adjacencyList1.get(6).add(10);

        adjacencyList1.get(7).add(6);
        adjacencyList1.get(7).add(8);

        adjacencyList1.get(8).add(5);
        adjacencyList1.get(8).add(7);

        adjacencyList1.get(9).add(3);

        adjacencyList1.get(10).add(6);

        System.out.println(isCycle(adjacencyList1));

        // ------------------------------------------------------ //

        /**
         * 1 --- 2 ---- 3
         *
         * 4 ----5 ---- 6
         *
         * 7 --- 8
         *  \  /
         *   9
         *
         */
        List<List<Integer>> adjacencyList2 = new ArrayList<>();
        int m = 9;
        for (int i = 0; i <= n; i++) {
            adjacencyList2.add(new ArrayList<>());
        }
        adjacencyList2.get(1).add(2);

        adjacencyList2.get(2).add(1);
        adjacencyList2.get(2).add(3);

        adjacencyList2.get(3).add(2);

        adjacencyList2.get(4).add(5);

        adjacencyList2.get(5).add(4);
        adjacencyList2.get(5).add(6);

        adjacencyList2.get(6).add(5);

        adjacencyList2.get(7).add(8);
        adjacencyList2.get(7).add(9);

        adjacencyList2.get(8).add(7);
        adjacencyList2.get(8).add(9);

        adjacencyList2.get(9).add(7);
        adjacencyList2.get(9).add(8);

        System.out.println(isCycle(adjacencyList2));
    }
}
