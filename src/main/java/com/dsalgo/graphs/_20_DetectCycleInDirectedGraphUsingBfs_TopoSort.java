package com.dsalgo.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*

Given input directed graph, return true if it has a cycle.

As Topological sort can be performed only on DAG, not on a graph which has a cycle, we can do a topo sort on the input graph.
If the graph does not have a cycle, then the topo sort will have all the vertices in the graph.
It the topo sort is not covering all the vertices, it means the graph has a cycle

 */
public class _20_DetectCycleInDirectedGraphUsingBfs_TopoSort {

    private static boolean topo(List<List<Integer>> adjacencyList) {
        int[] indegree = new int[adjacencyList.size()];
        for (List<Integer> neighbours : adjacencyList) {
            for (int neighbour : neighbours) {
                indegree[neighbour]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        //List<Integer> result = new ArrayList<>();
        int topoCount = 0;
        while (!queue.isEmpty()) {
            Integer node = queue.poll();

            // rather than storing the path for the topo sort, we will maintain the count
            //result.add(node);
            topoCount++;

            List<Integer> neighbours = adjacencyList.get(node);
            for (int neighbour : neighbours) {
                indegree[neighbour]--;
                if (indegree[neighbour] == 0) {
                    queue.add(neighbour);
                }
            }
        }
        return topoCount != adjacencyList.size();
    }

    public static void main(String[] args) {
        /*
         5 ---> 0 <--- 4
         ↓             ↓
         2 ---> 3 ---> 1

         */
        int n1 = 6;
        List<List<Integer>> adjacencyList1 = new ArrayList<>();
        for (int i = 0; i < n1; i++) {
            adjacencyList1.add(new ArrayList<>());
        }
        // adjacencyList.get(0).add(0);

        // adjacencyList.get(1).add(0);

        adjacencyList1.get(2).add(3);

        adjacencyList1.get(3).add(1);

        adjacencyList1.get(4).add(0);
        adjacencyList1.get(4).add(1);

        adjacencyList1.get(5).add(0);
        adjacencyList1.get(5).add(2);

        System.out.println(topo(adjacencyList1));    // return false, does not have a cycle

        // ------------ ---------------------------------------------------------------------------- //

        /*
        0 ---> 1 ----> 2 ----> 3
               ↑       ↓
                ------ 4
         */
        int n2 = 5;
        List<List<Integer>> adjacencyList2 = new ArrayList<>();
        for (int i = 0; i < n2; i++) {
            adjacencyList2.add(new ArrayList<>());
        }

        adjacencyList2.get(0).add(1);

        adjacencyList2.get(1).add(2);

        adjacencyList2.get(2).add(3);
        adjacencyList2.get(2).add(4);

        adjacencyList2.get(4).add(1);

        System.out.println(topo(adjacencyList2));    // return true, has a cycle
    }
}
