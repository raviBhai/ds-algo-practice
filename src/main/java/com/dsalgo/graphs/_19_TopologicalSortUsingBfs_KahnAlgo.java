package com.dsalgo.graphs;
/*

Topological sort using BFS is done using Kahn's algorithm.

Here, we want to start from a node which does not have an incoming edge.
In DFS, we wanted to start from a node which did not have an outgoing edge.

As there can be multiple nodes which do not have an incoming edge, there can be multiple starting points.
So, we can calculate the indegree of all the nodes and start which those which have indegree as 0.

Take these nodes and push to queue.
From the queue, remove the first node, and add it to the result list.
At the same time, we want this removed first node to be removed from the graph.
It would mean that this will change the indegree of its neighbours who can be reached from this node.
So, for each of the neighbours, we will decrement their indegree by 1. If the indegree of a neighbour becomes 0, we will push it to the queue.
We will repeat this process until the queue is empty.
If the result list is not equal to the number of nodes, it means that there is a cycle in the graph and we cannot perform a topological sort.

*/


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _19_TopologicalSortUsingBfs_KahnAlgo {

    public static List<Integer> topo(List<List<Integer>> adjacencyList) {
        // here we do not have to explicitly handle connected components because we are calculating the indegree of all the nodes initially.
        // this indegree will be done for the nodes in all the components.
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

        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            Integer node = queue.poll();
            result.add(node);

            List<Integer> neighbours = adjacencyList.get(node);
            for (int neighbour : neighbours) {
                indegree[neighbour]--;
                if (indegree[neighbour] == 0) {
                    queue.add(neighbour);
                }
            }
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

        System.out.println(topo(adjacencyList));
    }
}
