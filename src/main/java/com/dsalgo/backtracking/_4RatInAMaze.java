package com.dsalgo.backtracking;

import java.util.ArrayList;
import java.util.List;

public class _4RatInAMaze {

    private static final String[] dir = {"U", "D", "L", "R"};
    private static final int[] xMoves = {-1, 1, 0, 0};
    private static final int[] yMoves = {0, 0, -1, 1};

    /**
     * time complexity = workDonePerNode * numberOfNodes
     *
     * numberOfNodes = branches ^ depth
     *
     * branches = 4
     *
     * depth of the recursive tree = number of cells in the matrix = n^2
     *
     * Hence, numberOfNodes = 4 ^ n^2
     *
     * workDonePerNode =
     *     for loop over the dir array - constant time
     *     isValid - constant time
     *     Hence, O(1)
     *
     * Hence, time complexity = O(4 ^ n^2)
     *
     * @param maze
     * @param n
     * @param x
     * @param y
     * @param path
     * @param result
     */
    private static void solve(int[][] maze, int n, int x, int y, String path, List<String> result) {
        if (x == n - 1 && y == n - 1) {
            result.add(path);
            return;
        }
        for (int i = 0; i < dir.length; i++) {
            int nextX = x + xMoves[i];
            int nextY = y + yMoves[i];
            if (isValid(nextX, nextY, maze, n)) {
                path = path + dir[i];
                maze[x][y] = 0;
                solve(maze, n, nextX, nextY, path, result);
                path = path.substring(0, path.length() - 1);
                maze[x][y] = 1;
            }
        }
    }

    private static boolean isValid(int x, int y, int[][] maze, int n) {
        if (x < 0 || x >= n || y < 0 || y >= n || maze[x][y] == 0) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] maze = {
                {1, 1, 1, 1, 1},
                {1, 0, 0, 1, 0},
                {1, 0, 0, 1, 0},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 0, 1}
        };
        List<String> result = new ArrayList<>();
        solve(maze, 5, 0, 0, "", result);
        System.out.println(result);
    }
}
