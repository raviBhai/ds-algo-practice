package com.dsalgo.graphs;

import java.util.ArrayList;
import java.util.List;

public class _15_CheckBipartiteGraphUsingDfs {

    private static boolean isBipartite(int node, List<List<Integer>> adjacencyList, int[] colors, int color) {
        // color the current node
        colors[node] = color;

        List<Integer> neighbours = adjacencyList.get(node);
        for (int neighbour : neighbours) {
            if (colors[neighbour] != -1 && colors[neighbour] == colors[node]) {     // already colored neighbour with same color as that of current node
                return false;
            } else if (colors[neighbour] == -1) {
                boolean bipartite = isBipartite(neighbour, adjacencyList, colors, colors[node] == 0 ? 1 : 0);
                if (!bipartite) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean solve(List<List<Integer>> adjacencyList) {
        int[] colors = new int[adjacencyList.size()];

        for (int i = 1; i < colors.length; i++) {
            colors[i] = -1;
        }

        for (int i = 1; i < colors.length; i++) {
            if (colors[i] == -1) {
                boolean bipartite = isBipartite(i, adjacencyList, colors, 0); // mark start node with color 0
                if (!bipartite) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        /**
         *          3 --- 6
         *        /        \
         * 1 ---2           7 --- 8
         *       \        /
         *        4 --- 5
         */
        List<List<Integer>> adjacencyList1 = new ArrayList<>();
        int n1 = 8;
        for (int i = 0; i <= n1; i++) {
            adjacencyList1.add(new ArrayList<>());
        }

        adjacencyList1.get(1).add(2);

        adjacencyList1.get(2).add(1);
        adjacencyList1.get(2).add(3);
        adjacencyList1.get(2).add(4);

        adjacencyList1.get(3).add(2);
        adjacencyList1.get(3).add(6);

        adjacencyList1.get(4).add(2);
        adjacencyList1.get(4).add(5);

        adjacencyList1.get(5).add(4);
        adjacencyList1.get(5).add(7);

        adjacencyList1.get(6).add(3);
        adjacencyList1.get(6).add(7);

        adjacencyList1.get(7).add(6);
        adjacencyList1.get(7).add(5);
        adjacencyList1.get(7).add(8);

        adjacencyList1.get(8).add(7);

        System.out.println(solve(adjacencyList1));

        // ----------------------------------------------------------- //

        /**
         *          3 ----
         *        /        \
         * 1 ---2           6 --- 7
         *       \        /
         *        4 --- 5
         */
        List<List<Integer>> adjacencyList2 = new ArrayList<>();
        int n2 = 7; // 7 nodes are present in the above graph
        for (int i = 0; i <= n2; i++) {
            adjacencyList2.add(new ArrayList<>());
        }

        adjacencyList2.get(1).add(2);

        adjacencyList2.get(2).add(1);
        adjacencyList2.get(2).add(3);
        adjacencyList2.get(2).add(4);

        adjacencyList2.get(3).add(2);
        adjacencyList2.get(3).add(6);

        adjacencyList2.get(4).add(2);
        adjacencyList2.get(4).add(5);

        adjacencyList2.get(5).add(4);
        adjacencyList2.get(5).add(6);

        adjacencyList2.get(6).add(3);
        adjacencyList2.get(6).add(5);
        adjacencyList2.get(6).add(7);

        adjacencyList2.get(7).add(6);

        System.out.println(solve(adjacencyList2));
    }
}
