package com.dsalgo.backtracking;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * It is not required to have an else block while backtracking.
     * If the if-block is true, the backtracking will not happen. It is same as having the else block
     *
     * @param rowIndex
     * @param colIndex
     * @return
     */
    public boolean setQueen2(int colIndex) {
        if (colIndex == numOfQueens) {
            return true;
        } else {
            for (int rowIndex = 0; rowIndex < numOfQueens; rowIndex++) {
                if (isPlaceValid(rowIndex, colIndex)) {
                    chessBoard[rowIndex][colIndex] = 1;
                    if (setQueen(colIndex+1)) {
                        return true;
                    }
                    //Backtrack !!!
                    chessBoard[rowIndex][colIndex] = 0;
                }
            }
        }
        return false;
    }

    public void solve(int colIndex, List<Integer> temp, List<List<Integer>> result) {
        if (colIndex == numOfQueens) {
            result.add(new ArrayList<>(temp));
            return;
        }
        for (int rowIndex = 0; rowIndex < numOfQueens; rowIndex++) {
            if (isPlaceValid(rowIndex, colIndex)) {
                chessBoard[rowIndex][colIndex] = 1;
                temp.add(rowIndex + 1);
                solve(colIndex+1, temp, result);
                temp.remove(temp.size()-1);
                chessBoard[rowIndex][colIndex] = 0;
            }
        }
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

        queensProblem = new QueensProblem(4);
        List<List<Integer>> result = new ArrayList<>();
        queensProblem.solve(0, new ArrayList<>(), result);
        System.out.println(result);
    }
}
