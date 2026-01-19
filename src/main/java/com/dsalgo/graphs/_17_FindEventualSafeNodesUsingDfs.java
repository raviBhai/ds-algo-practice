package com.dsalgo.graphs;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Terminal node : a node from where there is no outgoing edge
 * Safe node : a node from where all possible outgoing paths lead to any of the terminal nodes. It is not necessary that all paths should lead to the same terminal node
 *
 * Given a graph, find all safe nodes.
 *
 * Solution:
 * If a graph does not have a cycle, all the nodes are safe nodes.
 * Because, eventually, every node will have a path that leads to such a node which does not have any further outgoing nodes.
 *
 * It means, if a graph has a cycle, there will be some nodes which are not safe.
 * All the nodes which are in the cycle or which connect to the cycle are not safe nodes.


0 -----------------> 1 ---> 2 ------------------> 3 --------> 4
                     ↑                            ↓           ↓
                     8 <------                    7 --------> 5 -----> 6
                     ↓       |
       11 -------->  9 ---> 10

 In the above graph:
    nodes which are part of the cycle - 8,9,10
    nodes which are connected to the cycle - 11

 So, 8,9,10,11 are not safe nodes

 We will use Cycle detection algorithm from _16_DetectCycleInDirectedGraphUsingDfs
 In the cycle detection algorithm, we maintain a pathVisited array.
 It is marked as visited when we visit the node and marked as unvisited when we are about to trace back.
 When we trace back, it means, this node (and further child nodes) do not have any cycle.
 It means, this node is a safe node.
 So, what we can do here is, we can maintain another array, safeNodes array.
 When we are about to start the traversal, we mark the node as unsafe, which means that, for now the node is unsafe until I do the traversal.
 If we successfully execute the child nodes, we will eventually back track from the current node.
 While back tracking, we will unmark pathVisited, and we know that it is a safe node, so here we will mark it as safe node in safeNodes array

 */
public class _17_FindEventualSafeNodesUsingDfs {

    private static boolean isCycle(List<List<Integer>> adjacencyList, int start, boolean[] visited, boolean[] pathVisited, boolean[] safeNodes) {

        visited[start] = true;
        pathVisited[start] = true;
        safeNodes[start] = false;


        List<Integer> neighbours = adjacencyList.get(start);
        for (int neighbour : neighbours) {
            if (!visited[neighbour]) {  // if node is not visited, pathVisited will also be not visited
                boolean isCycle = isCycle(adjacencyList, neighbour, visited, pathVisited, safeNodes);
                if (isCycle) {
                    return true;
                }
            } else if (visited[neighbour] && pathVisited[neighbour]) {  // if node is visited and pathVisited as well, then it is a cycle
                return true;
            }
        }

        safeNodes[start] = true;
        pathVisited[start] = false;
        return false;
    }

    private static List<Integer> solve(List<List<Integer>> adjacencyList) {
        boolean[] visited = new boolean[adjacencyList.size()];
        boolean[] pathVisited = new boolean[adjacencyList.size()];
        boolean[] safeNodes = new boolean[adjacencyList.size()];

        for (int i = 1; i < visited.length; i++) {
            if (!visited[i]) {
                isCycle(adjacencyList, i, visited, pathVisited, safeNodes);
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 1; i < safeNodes.length; i++) {
            if (safeNodes[i]) {
                result.add(i);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<List<Integer>> adjacencyList1 = new ArrayList<>();

        /**
         *

         1 ----> 2 ------------------> 3 --------> 4
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

         1 ----> 2 ------------------> 3 --------> 4
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
