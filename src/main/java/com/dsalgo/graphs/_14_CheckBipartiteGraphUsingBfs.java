package com.dsalgo.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * A graph is bipartite if all the nodes can be colored with 2 colors such that adjacent nodes have different colors.
 *
 * A linear graph which does not have a cycle is always bipartite.
 *
 * A cyclic graph may or may not be bipartite.
 *
 * Cyclic graph with cycle length as odd can never be bipartite.
 * Cyclic graph with cycle length as even may be bipartite.
 *
 * Examples -
 *
 * 1. Linear graph
 *          3 --- 6
 *        /
 * 1 ---2
 *       \
 *        4 --- 5
 *
 *
 * Color this graph with 2 colors  - Red, Green (R, G)
 * Node : color
 * 1 : R
 * 2 : G
 * 3 : R
 * 4 : R
 * 5 : G
 * 6: G
 *
 * 2. Cyclic graph with even cycle length. Cycle length is 6 (2-3-6-7-5-4)
 *          3 --- 6
 *        /        \
 * 1 ---2           7 --- 8
 *       \        /
 *        4 --- 5
 *
 *
 * Color this graph with 2 colors  - Red, Green (R, G)
 * Node : color
 * 1: R
 * 2 : G
 * 3 : R
 * 4 : R
 * 5 : G
 * 6 : G
 * 7 : R
 * 8 : G
 *
 *
 * 3. Cyclic graph with odd cycle length. Cycle length is 5 (2-3-7-5-4)
 *          3 -----
 *        /        \
 * 1 ---2           7 --- 8
 *       \        /
 *        4 --- 5
 *
 *
 * Color this graph with 2 colors  - Red, Green (R, G)
 * Node : color
 * 1:  R
 * 2 : G
 * 3 : R
 * 4 : R
 * 5 : G
 * 7 : Can't color this node. Both neighbours (3 and 5) have different colors.
 * This is not a bipartite graph.
 *
 *
 * Solution :
 * Perform BFS. Instead of visited array, keep a colored array. Initialize colored array with -1.
 * Use 2 colors - 0 and 1
 *
 * Color the start node and push to the queue.
 * Get adjacent nodes, color the uncolored nodes, and push them to the queue.
 * The adjacent nodes which are already colored should have different color than the current node.
 * If not, this is not a bipartite graph.
 *
 */
public class _14_CheckBipartiteGraphUsingBfs {

    private static boolean isBipartite(int start, List<List<Integer>> adjacencyList, int[] colors) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        colors[start] = 0;

        while (!queue.isEmpty()) {
            Integer currentNode = queue.poll();
            List<Integer> neighbours = adjacencyList.get(currentNode);
            for (int neighbour : neighbours) {
                if (colors[neighbour] == -1) {
                    queue.add(neighbour);
                    colors[neighbour] = colors[currentNode] == 0 ? 1 : 0;   // mark opposite color as that of currentNode
                } else if (colors[neighbour] != -1 && colors[neighbour] == colors[currentNode]) {   // already colored neighbour with same color as that of current node
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

        // check all the components
        for (int i = 1; i < colors.length; i++) {
            if (colors[i] == -1) {
                boolean isBipartite = isBipartite(i, adjacencyList, colors);
                // if any component is not bipartite, then graph is also not bipartite
                if (!isBipartite) {
                    return false;
                }
            }
        }

        // if all the components are bipartite, then graph is also bipartite
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
