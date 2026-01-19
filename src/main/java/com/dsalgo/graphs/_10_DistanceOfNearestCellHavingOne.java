package com.dsalgo.graphs;


import java.util.LinkedList;
import java.util.Queue;

/**
 * **Problem:**
 *
 * You are given a binary matrix containing only `0`s and `1`s.
 * From each cell, determine the minimum number of steps required to reach the nearest cell containing `1`.
 *
 * * You can only move **up, down, left, or right**—**not diagonally**.
 * * A cell that already contains `1` requires `0` steps.
 *
 * **Example Input:**
 *
 * ```
 * 0 0 0
 * 0 1 0
 * 1 0 1
 * ```
 *
 * **Expected Output:**
 *
 * ```
 * 2 1 2
 * 1 0 1
 * 0 1 0
 * ```
 *
 * ---
 *
 * **Optimized Solution Approach:**
 *
 * Instead of starting from every cell with `0` and searching for the nearest `1`, we **reverse the problem**:
 *
 * 1. Start from all cells that contain `1` and use them as starting points.
 * 2. From these cells, perform a Breadth-First Search (BFS) to compute the distance to the nearest `1` for every `0` in the matrix.
 * 3. BFS is ideal here because it explores nodes level by level, ensuring the shortest distance is found first.
 *    Using DFS would not work, as it could go deeper into the graph before finding shorter paths.
 *
 * Implementation Notes:
 *
 * * Use a queue for BFS, and for each node, keep track of the number of steps taken so far.
 * * Maintain a visited matrix to avoid revisiting nodes.
 * * There’s no need to worry about reaching a cell via a shorter path later.
 *   BFS ensures that the first time a node is visited, it’s reached in the minimum number of steps.
 *
 *   Why? Because:
 *   * If a cell `V1` is reached from a node `C1` in 3 steps, it means the traversal reached `V1` after exploring all nodes at distances 1 and 2.
 *   * If `V1` had been visited earlier from another starting point `C0`, then the number of steps from `C0` must have been **less than or equal to** the current 3 steps from `C1`.
 *   * So, the first visit always guarantees the shortest distance.
 *
 * ---
 */
public class _10_DistanceOfNearestCellHavingOne {

    private static int[][] solve(int[][] matrix) {
        int[][] distance = new int[matrix.length][matrix[0].length];
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];

        Queue<Triplet<Integer, Integer, Integer>> queue = new LinkedList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1) {
                    queue.add(new Triplet<>(i, j, 0));
                    visited[i][j] = true;
                }
            }
        }

        int[] xMoves = {-1, 1, 0, 0};
        int[] yMoves = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            Triplet<Integer, Integer, Integer> currentCell = queue.poll();

            //operate
            int row = currentCell.first;
            int col = currentCell.second;
            int steps = currentCell.third;
            distance[row][col] = steps;

            for (int i = 0; i < xMoves.length; i++) {
                int newRow = row + xMoves[i];
                int newCol = col + yMoves[i];
                if (newRow >= 0 && newRow < matrix.length && newCol >= 0 && newCol < matrix[0].length) {
                    if (!visited[newRow][newCol]) {
                        queue.add(new Triplet<>(newRow, newCol, steps + 1));
                        visited[newRow][newCol] = true;
                    }
                }
            }
        }

        return distance;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {0, 0, 0},
                {0, 1, 0},
                {1, 0, 1}
        };

        int[][] distance = solve(matrix);

        for (int i = 0; i < distance.length; i++) {
            for (int j = 0; j < distance[0].length; j++) {
                System.out.print(distance[i][j] + " ");
            }
            System.out.println();
        }
    }
}
