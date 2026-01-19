package com.dsalgo.graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given input matrix with 0 and 1.
 * 1 represents land, and 0 represents water.
 * From any 1, you can go in 4 directions to another adjacent 1.
 * Find the number of 1's from which you cannot reach the boundary of the matrix.
 *
 * Example -
 * 0 0 0 1
 * 0 1 1 0
 * 0 0 0 1
 *
 * In above matrix, we have two 1's at (1,1) and (1,2) from which we cannot touch the boundary.
 *
 * Example -
 * 0 0 0 1
 * 0 1 1 0
 * 0 1 0 1
 *
 * In above matrix, we have zero 1's from which we cannot touch the boundary.
 *
 * Solution :
 * This is similar to _11_SurroundedRegions_Replace_Os_With_Xs
 * Get the 1's at the boundary and start traversal.
 * Maintain a visited matrix.
 * At the end, count of whichever 1's are not visited is the answer.
 * We can use bfs or dfs for traversal
 */
public class _12_NumberOfEnclaves {

    private static void bfs(int row, int col, int[][] input, boolean[][] visited, int[] xMoves, int[] yMoves) {
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(row, col));
        visited[row][col] = true;
        while (!queue.isEmpty()) {
            Pair<Integer, Integer> currentNode = queue.poll();
            for (int i = 0; i < xMoves.length; i++) {
                int newRow = currentNode.first + xMoves[i];
                int newCol = currentNode.second + yMoves[i];
                if (newRow >= 0 && newRow < input.length && newCol >= 0 && newCol < input[0].length) {
                    if (!visited[newRow][newCol] && input[newRow][newCol] == 1) {
                        visited[newRow][newCol] = true;
                        queue.add(new Pair<>(newRow, newCol));
                    }
                }
            }
        }
    }

    private static int solve(int[][] input) {

        boolean[][] visited = new boolean[input.length][input[0].length];

        int[] xMoves = {-1, 1, 0, 0};
        int[] yMoves = {0, 0, -1, 1};


        for (int i = 0; i < input[0].length; i++) {
            // top boundary
            if (!visited[0][i] && input[0][i] == 1) {
                bfs(0, i, input, visited, xMoves, yMoves);
            }

            // bottom boundary
            if (!visited[input.length-1][i] && input[input.length-1][i] == 1) {
                bfs(input.length-1, i, input, visited, xMoves, yMoves);
            }
        }

        for (int i = 0; i < input.length; i++) {
            // left column
            if (!visited[i][0] && input[i][0] == 1) {
                bfs(i, 0, input, visited, xMoves, yMoves);
            }

            // right column
            if (!visited[i][input[0].length-1] && input[i][input[0].length-1] == 1) {
                bfs(i, input[0].length-1, input, visited, xMoves, yMoves);
            }
        }

        int result = 0;
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length; j++) {
                if (input[i][j] == 1 && !visited[i][j]) {
                    result++;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] input1 = {
                {0, 0, 0, 1},
                {0, 1, 1, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 1}
        };
        System.out.println(solve(input1));  // output - 4

        int[][] input2 = {
                {0, 0, 0, 1},
                {0, 1, 1, 0},
                {0, 1, 1, 0},
                {0, 1, 0, 1}
        };
        System.out.println(solve(input2));  // output - 0
    }
}
