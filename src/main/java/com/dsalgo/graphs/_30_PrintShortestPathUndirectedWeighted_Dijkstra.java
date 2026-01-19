package com.dsalgo.graphs;


import java.util.*;

/*
Given undirected weighted graph, a source and a target node. Print the shortest path to reach from source to target.

    1 ---(2)--- 2 ----(5)----5
    |           |            |
    |(1)        |(4)         |
    |           |            |
    4 ---(3)----3------(1)---|

    from,   to,     distance
    1,      2,      2
    1,      4,      1
    2,      5,      5
    2,      3,      4
    3,      5,      1
    3,      4,      3

    If source=1, target=5, shortest path will be 1,4,3,5 as the distance traversed would be 5

    Use same approach as used in _29_Dijkstra_Undirected_NonUnitDistance
    Store the parent of each node in a new array/map whenever shorter distance for a node is encountered

 */
public class _30_PrintShortestPathUndirectedWeighted_Dijkstra {

    private static final int INF = Integer.MAX_VALUE;

    private static List<Integer> solve(int[][] edges, int numOfNodes, int source, int target) {
        List<List<Pair<Integer, Integer>>> adjacencyList = new ArrayList<>();
        for (int i = 0; i <= numOfNodes; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            adjacencyList.get(edge[0]).add(new Pair<>(edge[1], edge[2]));
            adjacencyList.get(edge[1]).add(new Pair<>(edge[0], edge[2]));
        }

        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>((p1, p2) -> p1.first - p2.first);
        pq.offer(new Pair<>(0, source));

        int[] distance = new int[numOfNodes + 1];   // using +1 as the nodes are from 1 to 5, not 0 to 5, hence adding extra element at index 0 which will never be used
        Arrays.fill(distance, INF);
        distance[source] = 0;

        int[] parent = new int[numOfNodes + 1];
        parent[source] = source;

        while (!pq.isEmpty()) {
            Pair<Integer, Integer> polled = pq.poll();
            int currentNode = polled.second;
            List<Pair<Integer, Integer>> neighbours = adjacencyList.get(currentNode);
            for (Pair<Integer, Integer> neighbour : neighbours) {
                if (distance[currentNode] != INF && distance[currentNode] + neighbour.second < distance[neighbour.first]) {
                    distance[neighbour.first] = distance[currentNode] + neighbour.second;
                    pq.offer(new Pair<>(distance[neighbour.first], neighbour.first));
                    parent[neighbour.first] = currentNode;
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        result.add(target);
        while (target != source) {
            result.add(parent[target]);
            target = parent[target];
        }
        Collections.reverse(result);
        return result;
    }

    public static void main(String[] args) {
        int[][] edges = {
                {1, 2, 2},
                {1, 4, 1},
                {2, 5, 5},
                {2, 3, 4},
                {3, 5, 1},
                {3, 4, 3}
        };
        List<Integer> path = solve(edges, 5, 1, 5);
        System.out.println(path);
    }
}
