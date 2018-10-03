package com.dsalgo.recursion;

import java.util.Arrays;

public class LongestPathInMatrix {
    private int[][] mat;
    private int[][] pathsMat;

    public LongestPathInMatrix(int[][] mat) {
        this.mat = mat;
        this.pathsMat = new int[mat.length][mat[0].length];
    }

    public void solve() {
        int rows = mat.length;
        int cols = mat[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                pathsMat[i][j] = getPath(i, j);
            }
        }
    }

        /*
    All the numbers in the input matrix are unique.
    Hence there is atmost one possible path.
    Hence, if, else if, else if ladder is used.
     */
    public int getPath(int i, int j) {
        int path = 1;

        if (isRightValid(i, j)) {
            path = 1 + getPath(i, j + 1);
        } else if (isLeftValid(i, j)) {
            path = 1 + getPath(i, j - 1);
        } else if (isUpValid(i, j)) {
            path = 1 + getPath(i - 1 , j);
        } else if (isDownValid(i, j)) {
            path = 1 + getPath(i + 1, j);
        }

        return path;
    }

    public boolean isRightValid(int i, int j) {
        if (i >= mat.length || j >= mat[0].length) {
            return false;
        }
        if (j + 1 >= mat[0].length) {
            return false;
        }
        if (mat[i][j] + 1 == mat[i][j + 1]) {
            return true;
        }
        return false;
    }

    public boolean isLeftValid(int i, int j) {
        if (i >= mat.length || j >= mat[0].length) {
            return false;
        }
        if (j <= 0) {
            return false;
        }
        if (mat[i][j] + 1 == mat[i][j - 1]) {
            return true;
        }
        return false;
    }

    public boolean isUpValid(int i, int j) {
        if (i >= mat.length || j >= mat[0].length) {
            return false;
        }
        if (i <= 0) {
            return false;
        }
        if (mat[i][j] + 1 == mat[i - 1][j]) {
            return true;
        }
        return false;
    }

    public boolean isDownValid(int i, int j) {
        if (i >= mat.length || j >= mat[0].length) {
            return false;
        }
        if (i + 1 >= mat.length) {
            return false;
        }
        if (mat[i][j] + 1 == mat[i + 1][j]) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int  mat[][] = {
                {1, 2, 9},
                {5, 3, 8},
                {4, 6, 7}
            };
        LongestPathInMatrix longestPathInMatrix = new LongestPathInMatrix(mat);
        longestPathInMatrix.solve();
        for (int i = 0; i < mat.length; i++) {
            System.out.println(Arrays.toString(mat[i]));
        }

        System.out.println("Paths: ");

        for (int i = 0; i < longestPathInMatrix.pathsMat.length; i++) {
            System.out.println(Arrays.toString(longestPathInMatrix.pathsMat[i]));
        }
    }
}
