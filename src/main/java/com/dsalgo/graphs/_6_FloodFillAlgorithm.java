package com.dsalgo.graphs;

/**
 * Given input matrix which has different integers (0,1,2,3,...).
 * An integer represents a color.
 *
 * Given another input - row, column
 *
 * Take the color at the given input row and col, find the nearby neighbours with the same color, and change the color of the neighbours.
 * Do this for the neighbours of the neighbours.
 *
 * Neighbours are horizontal and vertical, not diagonal
 *
 */
public class _6_FloodFillAlgorithm {

    private static void floodFill(int[][] grid, int row, int col, int newColor) {
        int[] xMoves = {0, -1, 0, 1};
        int[] yMoves = {-1, 0, 1, 0};
        int initialColor = grid[row][col];
        dfs(grid, row, col, initialColor, xMoves, yMoves, newColor);

        print(grid);
    }

    private static void dfs(int[][] grid, int row, int col, int initialColor, int[] xMoves, int[] yMoves, int newColor) {
        grid[row][col] = newColor;

        int rowLimit = grid.length;
        int colLimit = grid[0].length;

        for (int i = 0; i < xMoves.length; i++) {
            int newRow = row + xMoves[i];
            int newCol = col + yMoves[i];
            if (newRow >= 0 && newRow < rowLimit && newCol >= 0 && newCol < colLimit) {
                if (grid[newRow][newCol] == initialColor) {
                    /**
                     * IMPORTANT -
                     * in this problem, we are modifying the given input matrix. If we are told to not modify the input matrix,
                     * we will have to copy the input matrix to another matrix, let's call it copiedMatrix and pass that to dfs() function.
                     * In that case, we will have to add one more if() check here just before calling dfs()
                     * The new if() check will be to tell whether the neighbour is already colored. IF not, then call dfs()
                     * if (copiedMatrix[newRow][newCol] != newColor)
                     */
                    dfs(grid, newRow, newCol, initialColor, xMoves, yMoves, newColor);
                }
            }
        }
    }

    private static void print(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] grid = {
                {1, 1, 1},
                {2, 2, 0},
                {2, 2, 2}
        };
        floodFill(grid, 2, 0, 3);
    }

}


