package com.dsalgo.ctci;

public class MatrixSpiral {
    /*public static int[][] maze = {
            {1, 2, 3, 4, 5},
            {6, 7, 8, 9, 10},
            {11, 12, 13, 14, 15},
            {16, 17, 18, 19, 20},
            {21, 22, 23, 24, 25}
    };*/

    public static int[][] maze = {
            {1, 2, 3, 4},
            {6, 7, 8, 9},
            {11, 12, 13, 14},
            {16, 17, 18, 19}
    };

    public static void main(String[] args) {
        printSpiral();
    }

    public static void printSpiral() {
        int layers = maze.length / 2 ;
        for (int i = 0; i < layers; i++) {
            printLayer(i);
        }
        if (maze.length % 2 != 0) {
            System.out.print(maze[layers][layers] + " ");
        }
    }

    public static void printLayer(int layer) {
        right(layer, layer, maze.length - 1 - layer);
        down(maze.length -1-layer, layer, maze.length - 1 - layer);
        left(maze.length - 1 - layer, layer, maze.length - 1 - layer);
        up(layer, layer, maze.length - 1 - layer);
    }

    private static void up(int col, int minRow, int maxRow) {
        for (int i = maxRow; i > minRow; i--) {
            System.out.print(maze[i][col] + " ");
        }
    }

    private static void left(int row, int minCol, int maxCol) {
        for (int i = maxCol; i > minCol; i--) {
            System.out.print(maze[row][i] + " ");
        }
    }

    private static void down(int col, int minRow, int maxRow) {
        for (int i = minRow; i < maxRow; i++) {
            System.out.print(maze[i][col] + " ");
        }
    }

    private static void right(int row, int minCol, int maxCol) {
        for (int i = minCol; i < maxCol; i++) {
            System.out.print(maze[row][i] + " ");
        }
    }
}
