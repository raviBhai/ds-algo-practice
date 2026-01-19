package com.dsalgo.graphs;

import java.util.*;
/*

Given a weighted directed graph which represents cities and flightCost between them.
Given source and target city and K as max stops.
Determine minimum flightCost to reach from source to target city with at max K stops.

Solution :
We can think of Dijkstra's algo.
There we use a PQ which orders entries based on distance.
As we have to check for stops, the entry in the PQ can have 3 things - distance, stops, node
However, when we get an entry from the PQ based on the minimum distance, it is possible that the polled entry
already has taken more stops than K.
Hence, we cannot use PQ with distance sorted.

In the below example, distances are
a -> b : 2
b -> c : 3
a -> c : 8
If K was 0, and we had used PQ with distance sorted, we will get the shortest path as a -> b -> c, which would have taken 1 stop.
This is incorrect, as it could not reach c with 0 stops.

a ----> b -----> c
|________________↑

To solve this, we can use a normal Q and push source on it.
While getting neighbours, we know that the stops for all neighbours, will increase by 1, and will push them on the Q.
Similarly, the next set of neighbours will have 1 more stop.

While iterating over the neighbours, we can check if the distance from source to current to neighbour is less than distance[neighbour]
as well as if the stops taken is <= k, then push this on the queue.

Intuition :

In this problem, we are trying to reach from source to target in shortest-distance-path but with at max K-stops.

In Dijkstra's we try to reach from source to target in shortest-distance-path but the path can itself be very long, you can take as many stops as you want.
Dijkstra's is done using PQ. It can be done without PQ (use normal Q), but it will take more iterations over the Q compared to PQ
It will then give the shortest-distance-path

With Q, we give preference to nearest neighbours to become the currentNode, as well as, we reduce the distance for the neighbours.
But the moment we take more than K-stops for a neighbour, we do not look for further neighbours from that neighbour.
Also, we do not stop even when we encounter the target. Because, the distance is non-unit and we are using a normal Q, hence, we need to let it run for all the iterations
The distance for the target keeps getting updated so that it is as small as possible.

This is exactly similar to the algo used in _25_ShortestPathInUndirectedGraphWithUnitWeight, with the only addition of skipping the node the moment we encounter more than K-stops
As we are not doing any ordering in the Q, there is no need to have cost in the elements of the Q.
In the below code, we are storing cost on the Q, but it is not required.

 */
public class _33_CheapestFlightsWithinKStops {
    private static final int INF = Integer.MAX_VALUE;

    private static int solve(int[][] edges, int numOfNodes, int source, int target, int k) {
        List<List<Pair<Integer, Integer>>> adjacencyList = new ArrayList<>();
        for (int i = 0; i <= numOfNodes; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            adjacencyList.get(edge[0]).add(new Pair<>(edge[1], edge[2]));
        }

        // Triplet of - stops, node, cost
        Queue<Triplet<Integer, Integer, Integer>> queue = new LinkedList<>();
        queue.add(new Triplet<>(0, source, 0));

        int[] distance = new int[numOfNodes];
        Arrays.fill(distance, INF);
        distance[source] = 0;

        while (!queue.isEmpty()) {
            Triplet<Integer, Integer, Integer> polled = queue.poll();
            if (polled.first > k) {
                continue;
            }
            for (Pair<Integer, Integer> pair : adjacencyList.get(polled.second)) {
                int nextNode = pair.first;
                int cost = pair.second;
                // if (distance[polled.second] + cost < distance[nextNode] && polled.first <= k) {    // polled.first <= k can be removed from this check as it has been taken care of in the if-clause just before the for loop
                if (distance[polled.second] + cost < distance[nextNode]) {
                    distance[nextNode] = distance[polled.second] + cost;
                    queue.add(new Triplet<>(polled.first + 1, nextNode, distance[nextNode]));
                }
            }

        }

        return distance[target] == INF ? -1 : distance[target];
    }

    public static void main(String[] args) {
        int[][] edges = {
                {0,1,5},
                {0,3,2},
                {3,1,2},
                {1,2,5},
                {1,4,1},
                {4,2,1}
        };
        int numOfNodes = 5;
        int source = 0;
        int target = 2;
        int k = 2;
        int answer = solve(edges, numOfNodes, source, target, k);
        System.out.println(answer);

    }
}
