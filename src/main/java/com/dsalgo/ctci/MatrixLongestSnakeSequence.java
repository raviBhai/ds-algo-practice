package com.dsalgo.ctci;

public class MatrixLongestSnakeSequence {
    public static void main(String[] args) {
        int arrA [][] = {
                {1, 2, 1, 2},
                {7, 7, 2, 5},
                {6, 4, 3, 4},
                {1, 2, 2, 5}};
        maxSnakeSequence(arrA);
    }

    public static void maxSnakeSequence(int[][] maze) {
        int maxValue = 0;
        int maxRow = 0;
        int maxCol = 0;

        int[][] result = new int[maze.length][maze[0].length];
        result[0][0] = 1;
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {

                if (i > 0 && Math.abs(maze[i][j] - maze[i - 1][j]) == 1) {
                    result[i][j] = Math.max(result[i][j], result[i - 1][j] + 1);
                    if (maxValue < result[i][j]) {
                        maxValue = result[i][j];
                        maxRow = i;
                        maxCol = j;
                    }
                }

                if (j > 0 && Math.abs(maze[i][j] - maze[i][j - 1]) == 1) {
                    result[i][j] = Math.max(result[i][j], result[i][j - 1] + 1);
                    if (maxValue < result[i][j]) {
                        maxValue = result[i][j];
                        maxRow = i;
                        maxCol = j;
                    }
                }

            }
        }

        while (maxValue >= 1) {
            System.out.print(" " + maze[maxRow][maxCol]);
            if (maxRow > 0 && Math.abs(result[maxRow][maxCol] - result[maxRow - 1][maxCol]) == 1) {
                maxRow--;
            } else if (maxCol > 0 && Math.abs(result[maxRow][maxCol] - result[maxRow][maxCol - 1]) == 1) {
                maxCol--;
            }
            maxValue--;
        }
    }
}
