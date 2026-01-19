package com.dsalgo.graphs;

import java.util.Arrays;

/**
 * Given input matrix with O's and X's.
 * There can be clusters of Os connected together.
 * Find a cluster such that all the Os in the cluster are surrounded by Xs.
 * If a O is on the boundary, it is NOT surrounded by X.
 * Mark all such Os to X
 * Do this for all such clusters.
 *
 * Solution -
 * If a O is touching a boundary, then all the connected Os to it cannot be converted to X.
 * So, we can traverse all the four boundaries, and find Os.
 * Whenever an O is found, do bfs or dfs and mark the connected Os as visited in a separate visited matrix.
 *
 * Once traversal for all the 4 boundaries is done, there would be some Os which are not marked visited as these could not be reached from the boundary.
 * Convert these Os to X in the input matrix
 *
 * Optimization while traversing the boundary -
 * It is possible that other connected Os also lie on the same boundary.
 * Such Os will be marked as visited, so we need not do the traversal for such Os.
 */
public class _11_SurroundedRegions_Replace_Os_With_Xs {

    private static void dfs(int row, int col, char[][] matrix, boolean[][] visited, int[] xMoves, int[] yMoves) {
        visited[row][col] = true;
        for (int i = 0; i < xMoves.length; i++) {
            int newRow = row + xMoves[i];
            int newCol = col + yMoves[i];
            if (newRow >= 0 && newRow < matrix.length && newCol >= 0 && newCol < matrix[0].length) {
                if (!visited[newRow][newCol] && matrix[newRow][newCol] == 'O') {
                    dfs(newRow, newCol, matrix, visited, xMoves, yMoves);
                }
            }
        }
    }

    private static char[][] solve(char[][] input) {
        boolean[][] visited = new boolean[input.length][input[0].length];

        int[] xMoves = {-1, 1, 0, 0};
        int[] yMoves = {0, 0, -1, 1};


        for (int i = 0; i < input[0].length; i++) {
            // top boundary
            if (!visited[0][i] && input[0][i] == 'O') {
                dfs(0, i, input, visited, xMoves, yMoves);
            }

            // bottom boundary
            if (!visited[input.length-1][i] && input[input.length-1][i] == 'O') {
                dfs(input.length-1, i, input, visited, xMoves, yMoves);
            }
        }

        for (int i = 0; i < input.length; i++) {
            // left column
            if (!visited[i][0] && input[i][0] == 'O') {
                dfs(i, 0, input, visited, xMoves, yMoves);
            }

            // right column
            if (!visited[i][input[0].length-1] && input[i][input[0].length-1] == 'O') {
                dfs(i, input[0].length-1, input, visited, xMoves, yMoves);
            }
        }

        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length; j++) {
                if (input[i][j] == 'O' && !visited[i][j]) {
                    input[i][j] = 'X';
                }
            }
        }

        return input;
    }

    public static void main(String[] args) {
        char[][] input = {
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'O'},
                {'X', 'O', 'X', 'O'}
        };
        char[][] output = solve(input);
        for (char[] row : output) {
            System.out.println(Arrays.toString(row));
        }
    }
}
