package com.dsalgo.ctci;

import java.util.ArrayList;
import java.util.List;

public class AllPathsInBlockedMatrix {

    private int[][] matrix;
    private int size;

    public AllPathsInBlockedMatrix(int[][] matrix) {
        this.matrix = matrix;
        this.size = matrix.length;
    }

    public void solve() {
        List<Integer> path = new ArrayList<>();
        paths(0, 0, path);
    }

    private void paths(int r, int c, List<Integer> path) {
        if (!isInvalidRowOrColumn(r, c) && matrix[r][c] != 0) {
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
                {7,0,9}
        };
        AllPathsInBlockedMatrix pathsInMatrix = new AllPathsInBlockedMatrix(matrix);
        pathsInMatrix.solve();
    }
}
