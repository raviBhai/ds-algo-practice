package com.dsalgo.backtracking;

public class MazeProblem {
    private int[][] mazeTable;
    private int[][] solutionTable;
    private int mazeSize;

    public MazeProblem(int[][] mazeTable) {
        this.mazeTable = mazeTable;
        this.mazeSize = mazeTable.length;
        this.solutionTable = new int[mazeSize][mazeSize];
    }

    public void solve() {
        if (solveMaze(0, 0)) {
            printSolution();
        } else {
            System.out.println("No solution");
        }
    }

    //Also check AllPathsInBlockedMatrix.java in this repo. That solution gives all the paths in this matrix
    private boolean solveMaze(int x, int y) {
        if (isFinished(x, y)) {
            return true;
        } else {
            if (isValid(x, y)) {

                solutionTable[x][y] = 1;

                if (solveMaze(x + 1, y)) {
                    return true;
                }

                if (solveMaze(x, y + 1)) {
                    return true;
                }

                //Backtrack !!!
                solutionTable[x][y] = 0;
            }
        }
        return false;
    }

    private boolean isValid(int x, int y) {
        if (x < 0 || x >= mazeSize) {
            return false;
        }
        if (y < 0 || y >= mazeSize) {
            return false;
        }
        if (mazeTable[x][y] != 1) {
            return false;
        }
        return true;
    }

    private boolean isFinished(int x, int y) {
        if (x == mazeSize - 1 && y == mazeSize - 1) {
            if (mazeTable[x][y] == 1) {
                solutionTable[x][y] = 1;
                return true;
            }
        }
        return false;
    }

    private void printSolution() {
        for (int i = 0; i < mazeSize; i++) {
            for (int j = 0; j < mazeSize; j++) {
                if (solutionTable[i][j] == 1) {
                    System.out.print(" s ");
                } else {
                    System.out.print(" - ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1,0,1},
                {1,1,1},
                {1,0,1}

/*
                {1,1,1,1,1},
                {1,0,0,1,0},
                {1,0,0,1,0},
                {1,1,1,1,1},
                {1,1,1,0,1}
*/
        };
        MazeProblem maze = new MazeProblem(matrix);
        maze.solve();
    }
}
