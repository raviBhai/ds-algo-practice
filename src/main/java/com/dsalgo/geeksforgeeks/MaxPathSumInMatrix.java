package com.dsalgo.geeksforgeeks;

import java.util.ArrayList;
import java.util.List;

public class MaxPathSumInMatrix {
    private int[][] matrix = null;

    public MaxPathSumInMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public void solve() {
        int maxSum = 0;
        List<Integer> maxPath = new ArrayList<>();
        for(int i = 0; i < matrix[0].length; i++) {
            List<List<Integer>> allPaths = new ArrayList<>();
            List<Integer> path = new ArrayList<>();
            solve(0, i, path, allPaths);

            for (List<Integer> eachPath : allPaths) {
                int sum = 0;
                for (Integer j : eachPath) {
                    sum += j;
                }
                if (sum > maxSum) {
                    maxSum = sum;
                    maxPath = eachPath;
                }
            }
        }
        System.out.println("Max sum = " + maxSum);
        System.out.println("Max path = " + maxPath);
    }

    private void solve(int r, int c, List<Integer> path, List<List<Integer>> allPaths) {
        if (!isInvalid(r, c)) {
            path.add(matrix[r][c]);
            if (isLastRow(r)) {
                allPaths.add(path);
            } else {
                solve(r+1, c, new ArrayList<Integer>(path), allPaths);
                solve(r+1, c-1, new ArrayList<Integer>(path), allPaths);
                solve(r+1, c+1, new ArrayList<Integer>(path), allPaths);
            }
        }
    }

    private boolean isLastRow(int r) {
        if (r == matrix.length - 1) {
            return true;
        }
        return false;
    }

    private boolean isInvalid(int r, int c) {
        if (r >= matrix.length || c >= matrix[0].length || c < 0) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1,2,3},
                {9,8,7},
                {4,5,6}
        };
        MaxPathSumInMatrix maxPathSum = new MaxPathSumInMatrix(matrix);
        maxPathSum.solve();
    }
}
