package com.dsalgo.ctci;

import java.util.ArrayList;
import java.util.List;

public class AllPathsInMatrix {

    private int[][] matrix;
    private int size;

    public AllPathsInMatrix(int[][] matrix) {
        this.matrix = matrix;
        this.size = matrix.length;
    }

    public void solve() {
        List<Integer> path = new ArrayList<>();
        paths(0, 0, path);
    }

    private void paths(int r, int c, List<Integer> path) {
        if (!isInvalidRowOrColumn(r, c)) {
            path.add(matrix[r][c]);
            if (isLastPosition(r, c)) {
                System.out.println(path);
            } else {
                paths(r + 1, c, new ArrayList<Integer>(path));
                paths(r, c+1, new ArrayList<Integer>(path));
            }
        }
    }

    private boolean isLastPosition(int r, int c) {
        if (r == size - 1 && c == size - 1) {
            return true;
        }
        return false;
    }

    private boolean isInvalidRowOrColumn(int r, int c) {
        if (r >= size || c >= size) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };
        AllPathsInMatrix pathsInMatrix = new AllPathsInMatrix(matrix);
        pathsInMatrix.solve();
    }
}

class AllPathsInMatrixBackTracking {
    private static final String[] dir = {"D", "R"};
    private static final int[] xMoves = {1, 0};
    private static final int[] yMoves = {0, 1};

    private static void solve(int[][] maze, int n, int x, int y, String path, List<String> result) {
        if (x == n - 1 && y == n - 1) {
            result.add(path);
            return;
        }
        for (int i = 0; i < dir.length; i++) {
            int nextX = x + xMoves[i];
            int nextY = y + yMoves[i];
            if (isValid(nextX, nextY, maze, n)) {
                path = path + maze[nextX][nextY];
                solve(maze, n, nextX, nextY, path, result);
                path = path.substring(0, path.length() - 1);
            }
        }
    }

    private static boolean isValid(int x, int y, int[][] maze, int n) {
        if (x < 0 || x >= n || y < 0 || y >= n) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };
        List<String> result = new ArrayList<>();
        String path = "" + matrix[0][0];
        solve(matrix, 3, 0, 0, path, result);
        System.out.println(result);
    }
}