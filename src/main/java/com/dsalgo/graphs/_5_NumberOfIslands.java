package com.dsalgo.graphs;



import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically (or diagonally)
 * You may assume all four edges of the grid are all surrounded by water.
 */
//

public class _5_NumberOfIslands {

    private static final int[] xMoves = {-1, 1, 0, 0};
    private static final int[] yMoves = {0, 0, -1, 1};


    private static int countIslandsWithBfs(int[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    bfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private static int countIslandsWithDfs(int[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    dfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private static int countIslandsWithDfsWithoutDiagonal(int[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    dfsWithoutDiagonal(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private static int countIslandsWithDfsWithoutDiagonalAndMoves(int[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    dfsWithoutDiagonalAndMoves(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private static void bfs(int[][] grid, int row, int col) {

        grid[row][col] = 0;     // mark visited
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(row, col));

        int gridRows = grid.length;
        int gridCols = grid[0].length;

        while (!queue.isEmpty()) {
            Pair<Integer, Integer> currentNode = queue.poll();

            // get all neighbours - horizontal, vertical, diagonal
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (!(i == 0 && j == 0)) {      // not the current node
                        int newRow = currentNode.first + i;
                        int newCol = currentNode.second + j;
                        if (newRow >= 0 && newRow < gridRows && newCol >= 0 && newCol < gridCols) {
                            if (grid[newRow][newCol] == 1) {    // if neighbour is not visited
                                grid[newRow][newCol] = 0;   // mark the neighbour node as visited
                                queue.add(new Pair<>(newRow, newCol));
                            }
                        }
                    }
                }
            }
        }
    }

    private static void dfs(int[][] grid, int row, int col) {
        grid[row][col] = 0;     // mark visited

        int gridRows = grid.length;
        int gridCols = grid[0].length;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (!(i == 0 && j == 0)) {      // not the current node
                    int newRow = row + i;
                    int newCol = col + j;
                    if (newRow >= 0 && newRow < gridRows && newCol >= 0 && newCol < gridCols) {
                        if (grid[newRow][newCol] == 1) {    // if neighbour is not visited
                            dfs(grid, newRow, newCol);
                        }
                    }
                }
            }
        }
    }

    private static void dfsWithoutDiagonal(int[][] grid, int row, int col) {
        grid[row][col] = 0;     // mark visited

        int gridRows = grid.length;
        int gridCols = grid[0].length;

        for (int i = 0; i < xMoves.length; i++) {
            int newRow = row + xMoves[i];
            int newCol = col + yMoves[i];
            if (newRow >= 0 && newRow < gridRows && newCol >= 0 && newCol < gridCols) {
                if (grid[newRow][newCol] == 1) {    // if neighbour is not visited
                    dfsWithoutDiagonal(grid, newRow, newCol);
                }
            }
        }
    }

    private static void dfsWithoutDiagonalAndMoves(int[][] grid, int row, int col) {
        int gridRows = grid.length;
        int gridCols = grid[0].length;

        if (row >= 0 && row < gridRows && col >= 0 && col < gridCols) {
            if (grid[row][col] == 1) {    // if neighbour is not visited

                grid[row][col] = 0;     // mark visited

                dfsWithoutDiagonalAndMoves(grid, row - 1, col);
                dfsWithoutDiagonalAndMoves(grid, row + 1, col);
                dfsWithoutDiagonalAndMoves(grid, row, col + 1);
                dfsWithoutDiagonalAndMoves(grid, row, col - 1);
            }
        }
    }

    public static void main(String[] args) {
        int[][] grid1 = {
                { 1, 1, 1, 0, 0 },
                { 1, 1, 0, 0, 0 },
                { 1, 1, 0, 0, 0 },
                { 0, 0, 0, 0, 0 },
                { 1, 1, 0, 0, 1 }
        };
        System.out.println(countIslandsWithBfs(grid1));     // output - 3

        int[][] grid2 = {
                { 1, 1, 1, 0, 0 },
                { 1, 1, 0, 0, 0 },
                { 1, 1, 0, 0, 0 },
                { 0, 0, 0, 0, 0 },
                { 1, 1, 0, 0, 1 }
        };
        System.out.println(countIslandsWithDfs(grid2));     // output - 3

        int[][] grid3 = {
                { 1, 1, 1, 0, 0 },
                { 1, 1, 0, 0, 0 },
                { 1, 1, 0, 0, 0 },
                { 0, 0, 0, 0, 0 },
                { 1, 1, 0, 0, 1 }
        };
        System.out.println(countIslandsWithDfsWithoutDiagonal(grid3));     // output - 3

        int[][] grid4 = {
                { 1, 1, 1, 0, 0 },
                { 1, 1, 0, 0, 0 },
                { 1, 1, 0, 0, 0 },
                { 0, 0, 0, 0, 0 },
                { 1, 1, 0, 0, 1 }
        };
        System.out.println(countIslandsWithDfsWithoutDiagonalAndMoves(grid4));     // output - 3
    }
}
