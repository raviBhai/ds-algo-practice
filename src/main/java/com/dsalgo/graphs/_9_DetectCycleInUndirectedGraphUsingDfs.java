package com.dsalgo.graphs;

import java.util.ArrayList;
import java.util.List;

public class _9_DetectCycleInUndirectedGraphUsingDfs {

    private static boolean isCycle(int source, List<List<Integer>> adjacencyList, boolean[] visited, int parent) {
        visited[source] = true;
        List<Integer> neighbours = adjacencyList.get(source);
        for (int neighbour : neighbours) {
            if (neighbour == parent) {
                continue;
            } else if (neighbour != parent && visited[neighbour]) {
                return true;
            } else if (neighbour != parent && !visited[neighbour]) {
                // visited[neighbour] = true;
                boolean isCycleInNeighbour = isCycle(neighbour, adjacencyList, visited, source);
                if (isCycleInNeighbour) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isCycle(List<List<Integer>> adjacencyList) {
        boolean[] visited = new boolean[adjacencyList.size()];
        for (int i = 1; i < visited.length; i++) {
            if (!visited[i]) {
                boolean isCycle = isCycle(i, adjacencyList, visited, -1);
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
        //adjacencyList2.get(8).add(9);

        adjacencyList2.get(9).add(7);
        //adjacencyList2.get(9).add(8);

        // if above 2 commented lines (8 to 9 and 9 to 8) are uncommented, there will be cycle
        // with the above comments, we avoided the cycle in the graph
        System.out.println(isCycle(adjacencyList2));
    }
}
