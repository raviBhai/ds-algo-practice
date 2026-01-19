package com.dsalgo.graphs;

/*
Given 2D array of edges, with each edge having 3 entries - source, target, distance.
Given a threshold distance.
Find the city which has the minimum number of neighbours which are within the threshold.
If there are 2 cities with the same number of minimum neighbours who are within the threshold, then return the city with the higher number

Solution :
Use Floyd Warshall algo to get the distance of each city from another.
Else, use Dijkstra's and run it for each node to get distance of every city to another.

After calculating the distance from each city to each other, just iterate over the cities to check for minimum neighbours within the threshold
 */
public class _38_FindCityWithSmallestNeighboursAtAThreshold {

    private static final int INF = Integer.MAX_VALUE;

    private static int solve(int n, int[][] edges, int threshold) {
        int[][] distance = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                distance[i][j] = INF;
            }
            distance[i][i] = 0;
        }

        for (int[] edge : edges) {
            distance[edge[0]][edge[1]] = edge[2];
            distance[edge[1]][edge[0]] = edge[2];
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (distance[i][k] != INF && distance[k][j] != INF) {
                        distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
                    }
                }
            }
        }

        int minNeighbours = Integer.MAX_VALUE;
        int minCity = -1;
        for (int i = 0; i < n; i++) {
            int neighboursCountWithinThreshold = 0;
            for (int j = 0; j < n; j++) {
                if (distance[i][j] <= threshold) {
                    neighboursCountWithinThreshold++;
                }
            }
            if (neighboursCountWithinThreshold <= minNeighbours) {
                minNeighbours = neighboursCountWithinThreshold;
                minCity = i;
            }
        }
        return minCity;
    }

    public static void main(String[] args) {
        int[][] edges = {
                {0, 1, 3},
                {1, 2, 1},
                {1, 3, 4},
                {2, 3, 1}
        };
        System.out.println(solve(4, edges, 4));
    }
}
