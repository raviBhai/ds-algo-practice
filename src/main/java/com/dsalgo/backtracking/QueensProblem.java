package com.dsalgo.backtracking;

public class QueensProblem {
    int[][] chessBoard;
    int numOfQueens;

    public QueensProblem(int numOfQueens) {
        this.chessBoard = new int[numOfQueens][numOfQueens];
        this.numOfQueens = numOfQueens;
    }

    public void solve() {
        if (setQueen(0)) {
            System.out.println("Solution is possible");
            printQueens();
        } else {
            System.out.println("Solution not possible");
        }
    }

    public boolean setQueen(int colIndex) {
        if (colIndex == numOfQueens) {
            return true;
        } else {
            for (int rowIndex = 0; rowIndex < numOfQueens; rowIndex++) {
                if (isPlaceValid(rowIndex, colIndex)) {
                    chessBoard[rowIndex][colIndex] = 1;
                    if (setQueen(colIndex+1)) {
                        return true;
                    } else {
                        //Backtrack !!!
                        chessBoard[rowIndex][colIndex] = 0;
                    }
                }
            }
        }
        return false;
    }

    private boolean isPlaceValid(int rowIndex, int colIndex) {
        for (int i = 0; i < colIndex; i++) {
            if (chessBoard[rowIndex][i] == 1) {
                return false;
            }
        }

        for (int i = rowIndex, j = colIndex; i >= 0 && j >= 0; i--, j--) {
            if (chessBoard[i][j] == 1) {
                return false;
            }
        }

        for (int i = rowIndex, j = colIndex; i < chessBoard.length && j >= 0; i++, j--) {
            if (chessBoard[i][j] == 1) {
                return false;
            }
        }

        return true;
    }

    public void printQueens() {
        for (int i = 0; i < numOfQueens; i++) {
            for (int j = 0; j < numOfQueens; j++) {
                if (chessBoard[i][j] == 1) {
                    System.out.print(" * ");
                } else {
                    System.out.print(" - ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        QueensProblem queensProblem = new QueensProblem(4);
        queensProblem.solve();
    }
}
