package com.dsalgo.backtracking;

public class KnightsTour {
    private int[][] solutionMatrix;
    private int[] xMoves = {2,1,-1,-2,-2,-1,1,2};
    private int[] yMoves = {1,2,2,1,-1,-2,-2,-1};
    private int boardSize;

    public KnightsTour(int boardSize) {
        this.boardSize = boardSize;
        this.solutionMatrix = new int[boardSize][boardSize];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0 ; j < boardSize; j++) {
                solutionMatrix[i][j] = -1;
            }
        }
    }

    public void solve() {
        solutionMatrix[0][0] = 1;
        if (solveTour(2, 0, 0)) {
            printSolution();
        } else {
            System.out.println("No solution");
        }
    }

    private boolean solveTour(int stepCount, int x, int y) {
        if (stepCount == boardSize * boardSize + 1) {
            return true;
        } else {
            for (int i = 0; i < xMoves.length; i++) {
                int nextX = x + xMoves[i];
                int nextY = y + yMoves[i];

                if (isStepValid(nextX, nextY)) {
                    solutionMatrix[nextX][nextY] = stepCount;
                    if (solveTour(stepCount + 1, nextX, nextY)) {
                        return true;
                    } else {
                        //Backtrack !!!
                        solutionMatrix[nextX][nextY] = -1;
                    }
                }
            }
        }
        return false;
    }

    private boolean isStepValid(int x, int y) {
        if (x < 0 || x >= boardSize) {
            return false;
        }

        if (y < 0 || y >= boardSize) {
            return false;
        }

        if (solutionMatrix[x][y] != -1) {
            return false;
        }

        return true;
    }

    private void printSolution() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0 ; j < boardSize; j++) {
                System.out.print(solutionMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        KnightsTour knightsTour = new KnightsTour(8);
        knightsTour.solve();
    }
}
