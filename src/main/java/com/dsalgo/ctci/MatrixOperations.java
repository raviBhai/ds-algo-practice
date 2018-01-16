package com.dsalgo.ctci;

import java.util.ArrayList;
import java.util.Arrays;

public class MatrixOperations {
    public static void main(String[] args) {

    }

    public static int countPathsWithoutObstruction_recursion(int r, int c) {
        //Consider that matrix starts at (1,1) instead of (0,0)
        if (r == 1 || c == 1) {
            return 1;
        }
        int paths = countPathsWithoutObstruction_recursion(r - 1, c)
                + countPathsWithoutObstruction_recursion(r, c - 1);
        return paths;
    }

    public static int countPathsWithoutObstruction_DP1(boolean[][] maze) {
        int[][] result = new int[maze.length][maze[0].length];

        result[0][0] = 1;
        for (int i = 0; i < maze.length; i++) {
            result[0][i] = 1;
        }

        for (int i = 0; i < maze[0].length; i++) {
            result[i][0] = 1;
        }

        for (int i = 1; i < maze.length; i++) {
            for (int j = 1; j < maze[0].length; j++) {
                result[i][j] = result[i-1][j] + result[i][j-1];
            }
        }
        return result[result.length-1][result[0].length-1];
    }

    public static int countPathsWithoutObstruction_DP2(int r, int c) {
        Integer[][] temp = new Integer[r + 1][c + 1];
        for (int i = 0; i <= r; i++) {
            Arrays.fill(temp[i], -1);
        }
        int paths = countPathsWithoutObstruction_DP2_Helper(r, c, temp);
        return paths;
    }

    private static int countPathsWithoutObstruction_DP2_Helper(int r, int c, Integer[][] temp) {
        if (r == 1 || c == 1) {
            return 1;
        }
        if (temp[r][c] != -1) {
            return temp[r][c];
        }
        temp[r][c] = countPathsWithoutObstruction_DP2_Helper(r - 1, c, temp)
                + countPathsWithoutObstruction_DP2_Helper(r, c - 1, temp);
        return temp[r][c];
    }

    public static void getAllPathsWithoutObstruction_recursion(int r, int c, int nRows, int nCols, int[][]A, ArrayList<Integer> path,
                                                               ArrayList<ArrayList<Integer>> paths) {
        if (r > nRows - 1 || c > nCols - 1) {
            return;
        }
        path.add(A[r][c]);
        if (r == nRows - 1 && c == nCols - 1) {
            paths.add(path);
        }

        getAllPathsWithoutObstruction_recursion(r, c + 1, nRows, nCols, A, new ArrayList<>(path), paths);
        getAllPathsWithoutObstruction_recursion(r + 1, c, nRows, nCols, A, path, paths);
    }

    public static int countPathsWithObstruction_recursion(boolean[][] maze, int r, int c) {

        if (r == 0 && c == 0) {
            return 1;
        }

        int left = 0;
        if (r != 0 && maze[r - 1][c]) {
            left = countPathsWithObstruction_recursion(maze, r - 1, c);
        }

        int up = 0;
        if (c != 0 && maze[r][c - 1]) {
            up = countPathsWithObstruction_recursion(maze, r, c - 1);
        }

        return left + up;
    }

    public static int countPathsWithObstruction_DP(boolean[][] maze) {
        int[][] result = new int[maze.length][maze[0].length];
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j]) {
                    result[i][j] = 1;
                } else {
                    result[i][j] = -1;
                }
            }
        }
        return countPathsWithObstruction_DP_Helper(result);
    }

    private static int countPathsWithObstruction_DP_Helper(int[][] result) {
        for (int i = 1; i < result.length; i++) {
            for (int j = 1; j < result[0].length; j++) {
                if (result[i][j] != -1) {
                    result[i][j] = 0;
                    if (result[i - 1][j] > 0) {
                        result[i][j] = result[i][j] + result[i-1][j];
                    }
                    if (result[i][j-1] > 0) {
                        result[i][j] = result[i][j] + result[i][j-1];
                    }
                }
            }
        }
        return result[result.length - 1][result[0].length - 1];
    }

    public static ArrayList<Point> getPathWithObstruction_recursion(boolean[][] maze) {
        if (maze == null || maze.length == 0) {
            return null;
        }
        ArrayList<Point> path = new ArrayList<>();
        if (getPathWithObstruction_recursion_Helper(maze, path, 0, 0)) {
            return path;
        }
        return null;
    }

    private static boolean getPathWithObstruction_recursion_Helper(boolean[][] maze, ArrayList<Point> path, int r, int c) {

        if (r > maze.length - 1 || c > maze[0].length - 1) {
            return false;
        }

        if (!maze[r][c]) {
            return false;
        }

        boolean isEndVertex = (r == maze.length - 1) && (c == maze[0].length - 1);
/*
        if (r == maze.length - 1 && c == maze[0].length - 1) {
            Point p = new Point(r, c);
            path.add(p);
            return true;
        }
*/

        if (isEndVertex || getPathWithObstruction_recursion_Helper(maze, path, r + 1, c) || getPathWithObstruction_recursion_Helper(maze, path, r, c + 1)) {
            Point p = new Point(r, c);
            path.add(p);
            return true;
        }

        return false;
    }

}
