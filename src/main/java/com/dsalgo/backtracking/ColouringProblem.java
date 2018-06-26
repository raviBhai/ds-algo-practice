package com.dsalgo.backtracking;

public class ColouringProblem {
    private int numOfVertices;
    private int numOfColors;
    private int[][] adjacencyMatrix;
    private int[] colors;

    public ColouringProblem(int[][] adjacencyMatrix, int numOfColors) {
        this.adjacencyMatrix = adjacencyMatrix;
        this.numOfColors = numOfColors;
        this.numOfVertices = adjacencyMatrix.length;
        this.colors = new int[numOfVertices];   //this is output array where every index represents color of the vertex at that index
    }

    public void solve() {
        if (solveProblem(0)) {
            showResults();
        } else {
            System.out.println("No solution");
        }
    }

    public void showResults() {
        for (int i = 0; i < numOfVertices; i++) {
            System.out.println("Node " + (i + 1) + " has color index: " + colors[i]);
        }
    }

    public boolean solveProblem(int nodeIndex) {
        if (nodeIndex == numOfVertices) {
            return true;
        } else {
            for (int colorIndex = 1; colorIndex <= numOfColors; colorIndex++) {
                if (isColorValid(nodeIndex, colorIndex)) {
                    colors[nodeIndex] = colorIndex;
                    if (solveProblem(nodeIndex + 1)) {
                        return true;
                    } else {
                        // Backtrack !!!
                        colors[nodeIndex] = 0;
                    }
                }
            }
            return false;
        }
    }

    private boolean isColorValid(int nodeIndex, int colorIndex) {
        //check if neighbouring node has the same color
        for (int i = 0; i < numOfVertices; i++) {
            if (adjacencyMatrix[nodeIndex][i] == 1) {
                if (colors[i] == colorIndex) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {0,1,0,1,0},
                {1,0,1,1,0},
                {0,1,0,1,0},
                {1,1,1,0,1},
                {0,0,0,1,0}
        };
        ColouringProblem colouringProblem = new ColouringProblem(matrix, 3);
        colouringProblem.solve();
    }
}
