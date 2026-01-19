package com.dsalgo.graphs;

import java.util.LinkedList;
import java.util.Queue;

/*

Given input matrix with 0 and 1, a source cell and a target cell.
Find the shortest distance from source to target cell.
You can move up, down, left, right.
You cannot move to a cell with 0.

Solution :
As we have to find shortest distance from source to target, we can use Dijkstra's algo.
Here, the distance between cells/nodes is unit distance.
Hence, we can use a Q rather than a PQ

This will be similar to _25_ShortestPathInUndirectedGraphWithUnitWeight

 */
public class _31_ShortestDistanceInBinaryMaze {

    private static final int[] xMoves = {-1, 1, 0, 0};
    private static final int[] yMoves = {0, 0, -1, 1};
    private static final int INF = Integer.MAX_VALUE;

    private static int solve(int[][] grid, int[] source, int[] destination) {
        int[][] distance = new int[grid.length][grid[0].length];
        for (int i = 0; i < distance.length; i++) {
            for (int j = 0; j < distance[0].length; j++) {
                distance[i][j] = INF;
            }
        }

        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(source[0], source[1]));

        distance[source[0]][source[1]] = 0;

        while (!queue.isEmpty()) {
            Pair<Integer, Integer> polled = queue.poll();
            int currentRow = polled.first;
            int currentCol = polled.second;

            // if current cell is equal to the target cell, then we have reached the shortest distance
            if (currentRow == destination[0] && currentCol == destination[1]) {
                return distance[currentRow][currentCol];
            }

            for (int i = 0; i < xMoves.length; i++) {
                int newRow = currentRow + xMoves[i];
                int newCol = currentCol + yMoves[i];

                if (newRow >= 0 && newRow < grid.length && newCol >= 0 && newCol < grid[0].length && grid[newRow][newCol] != 0) {
                    int distanceFromSourceToCurrent = distance[currentRow][currentCol];
                    if (distanceFromSourceToCurrent != INF && distanceFromSourceToCurrent + 1 < distance[newRow][newCol]) {
                        distance[newRow][newCol] = distanceFromSourceToCurrent + 1;
                        queue.add(new Pair<>(newRow, newCol));
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] grid = {
                {1, 1, 1, 1},
                {1, 1, 0, 1},
                {1, 1, 1, 1},
                {1, 1, 0, 0},
                {1, 0, 0, 0}
        };

        int[] source = {0, 1};
        int[] destination = {2, 2};
        System.out.println(solve(grid, source, destination));
    }
}
