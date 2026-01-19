package com.dsalgo.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/*
Given an undirected graph with non unit distance and a source node.
Find the minimum distance from the source node to the other nodes.

We can use similar approach as that used in _25_ShortestPathInUndirectedGraphWithUnitWeight with some modification.
In the earlier example with unit distance, when we reach a node, we add it to the queue.
If the node was reached the first time, it was minimum possible path for that node as the distance was unit.
However, in this example, as the distance is non unit, it is possible to reach the same node again through a string of nodes but with a lesser distacne.
It means, a target node can be reached in multiple paths with different distance.
So, we should pick the distance which is minimum.
To do so, we can use a PQ rather than normal Q.
The PQ can store a pair with first as distance and second as node

Dijkstra's algo does not work when weight on any edge is negative.

Time complexity : E*log(V)
E - no of edges, V - no of nodes
Watch this for explanation : https://www.youtube.com/watch?v=3dINsjyfooY&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=34

 */
public class _29_Dijkstra_Undirected_NonUnitDistance {

    private static final int INF = Integer.MAX_VALUE;

    private static int[] dijkstra(int source, List<List<Pair<Integer, Integer>>> adjacencyList) {
        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> a.first - b.first);
        pq.add(new Pair<>(0, source));
        int[] distance = new int[adjacencyList.size()];
        for (int i = 0; i < distance.length; i++) {
            distance[i] = INF;
        }
        distance[source] = 0;

        while (!pq.isEmpty()) {
            Pair<Integer, Integer> polled = pq.poll();
            Integer currentNode = polled.second;

            List<Pair<Integer, Integer>> neighbours = adjacencyList.get(currentNode);
            for (Pair<Integer, Integer> neighbour : neighbours) {   // neighbour : first=node, second=distance
                if (distance[currentNode] != INF && distance[currentNode] + neighbour.second < distance[neighbour.first]) {
                    distance[neighbour.first] = distance[currentNode] + neighbour.second;
                    pq.add(new Pair<>(distance[neighbour.first], neighbour.first));
                }
            }
        }

        return distance;
    }

    public static void main(String[] args) {
        /*
                   3
        0        /   \
        |\      /(3)  \
    (4) | \(4) /       \(2)
        |  \  /         \
        |   2 -----(6)---5
        |  /  \         /
        | /(2) \       /(3)
        |/      \(1)  /
        1        \   /
                  4

    0,1 - 4
    0,2 - 4
    1,2 - 2
    2,3 - 3
    2,4 - 1
    2,5 - 6
    3,5 - 2
    4,5 - 3

         */


        List<List<Pair<Integer, Integer>>> adjacencyList = new ArrayList<>();
        int N = 6;

        for (int i = 0; i < N; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        adjacencyList.get(0).add(new Pair<>(1, 4));
        adjacencyList.get(0).add(new Pair<>(2, 4));
        adjacencyList.get(1).add(new Pair<>(2, 2));
        adjacencyList.get(2).add(new Pair<>(3, 3));
        adjacencyList.get(2).add(new Pair<>(4, 1));
        adjacencyList.get(2).add(new Pair<>(5, 6));
        adjacencyList.get(3).add(new Pair<>(5, 2));
        adjacencyList.get(4).add(new Pair<>(5, 3));

        int[] dijkstra = dijkstra(0, adjacencyList);
        for (int i : dijkstra) {
            System.out.println(i);
        }

    }





}
