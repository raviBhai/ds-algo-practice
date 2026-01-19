package com.dsalgo.graphs;

import java.util.*;

/*
Given input graph. Get the nodes which are eventually safe. Get them in sorted order.

Solution -
This was done using DFS in _17_FindEventualSafeNodesUsingDfs

Now, we want to do it with BFS.
It can be done with topological sort.

Topo sort can be done with -
    1. DFS
          - we will not do this as we want to do with BFS.

    2. BFS
        - we need to use indegree or outdegree
        - indegree
            - when indegree is used, we start with nodes having indegree as 0.
              However, a node can have indegree 0 but may not be safe.
              So, this approach will not work.
        - outdegree
            - similar to indegree, when outdegree is used, we start with nodes having outdegree as 0.
              The terminal nodes have outdegree as 0. And the terminal nodes are always safe nodes.
              So, we can take a Queue, initialize it with nodes having outdegree as 0.
              Then remove a node from the Queue, add it to the result, then remove it from the graph.
              As we remove this node from the graph, it will impact the outdegree of its parent nodes.
              Get parent node of the current node, and reduce their outdegree by 1.
              If outdegree is 0 of the parent node, add it to the Queue.

Another approach using Topo sort + BFS + indegree :
Reverse the directions of all the edges of the input graph and then perform BFS using indegree approach

Below solution is with outdegree
 */
public class _22_EventualSafeNodesUsingBfs {


    private static List<Integer> solve(List<List<Integer>> adjacencyList) {
        int[] outdegree = new int[adjacencyList.size()];
        for (int i = 0; i < adjacencyList.size(); i++) {
            outdegree[i] += adjacencyList.get(i).size();
        }

        // store parent of every node. A node can have multiple parents.
        Map<Integer, List<Integer>> nodeToParent = new HashMap<>();
        for (int i = 0; i < adjacencyList.size(); i++) {
            for (Integer child : adjacencyList.get(i)) {
                if (nodeToParent.get(child) == null) {
                    nodeToParent.put(child, new ArrayList<>());
                }
                nodeToParent.get(child).add(i);
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < outdegree.length; i++) {
            if (outdegree[i] == 0) {
                queue.add(i);   // add node to Queue if its outdegree is 0
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            Integer node = queue.poll();
            result.add(node);
            List<Integer> parents = nodeToParent.get(node);
            if (parents != null) {   // some nodes might not have a parent
                for (int parent : parents) {
                    outdegree[parent]--;
                    if (outdegree[parent] == 0) {
                        queue.add(parent);
                    }
                }
            }
        }

        Collections.sort(result);

        return result;
    }

    public static void main(String[] args) {
                /*

                0 -----------------> 1 ---> 2 ------------------> 3 --------> 4
                                     ↑                            ↓           ↓
                                     8 <------                    7 --------> 5 -----> 6
                                     ↓       |
                       11 -------->  9 ---> 10


                // 8,9,10,11 are not safe nodes

                 */
        List<List<Integer>> adjacencyList1 = new ArrayList<>();
        int n = 12;     // number of nodes
        for (int i = 0; i < n; i++) {
            adjacencyList1.add(new ArrayList<>());
        }

        adjacencyList1.get(0).add(1);

        adjacencyList1.get(1).add(2);

        adjacencyList1.get(2).add(3);

        adjacencyList1.get(3).add(4);
        adjacencyList1.get(3).add(7);

        adjacencyList1.get(4).add(5);

        adjacencyList1.get(5).add(6);

        // adjacencyList1.get(6).add(7);

        adjacencyList1.get(7).add(5);

        adjacencyList1.get(8).add(1);
        adjacencyList1.get(8).add(9);

        adjacencyList1.get(9).add(10);

        adjacencyList1.get(10).add(8);

        adjacencyList1.get(11).add(9);

        System.out.println(solve(adjacencyList1));
    }
}
