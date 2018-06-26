package com.dsalgo.backtracking;

public class HamiltonianCycle {
    private int numOfVertices;
    private int[] hamiltonianPath;
    private int[][] adjacencyMatrix;

    public HamiltonianCycle(int[][] adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix;
        this.hamiltonianPath = new int[adjacencyMatrix.length];
        this.numOfVertices = adjacencyMatrix.length;
        this.hamiltonianPath[0] = 0;
    }

    public void solve() {
        if (findFeasibleSolution(1)) {
            showHamiltonianPath();
        } else {
            System.out.println("No feasible solution");
        }
    }

    private boolean findFeasibleSolution(int position) {
        //if all vertices visited and if first and last are connected
        if (position == numOfVertices) {
            if (adjacencyMatrix[hamiltonianPath[position - 1]][hamiltonianPath[0]] == 1) {
                return true;
            } else {
                return false;
            }
        } else {
            for (int vertexIndex = 1; vertexIndex < numOfVertices; ++vertexIndex) {
                if (isFeasible(vertexIndex, position)) {
                    hamiltonianPath[position] = vertexIndex;
                    if (findFeasibleSolution(position + 1)) {
                        return true;
                    } else {
                        //Backtrack !!!
                        hamiltonianPath[position] = -1;
                    }
                }
            }
        }
        return false;
    }

    private boolean isFeasible(int vertexIndex, int actualPosition) {

        //first criterion: whether two nodes are connected?
        //position-1 gives the last node added in hamiltonian path
        if (adjacencyMatrix[vertexIndex][hamiltonianPath[actualPosition - 1]] == 0) {
            return false;
        }

        //second criterion: whether we have visited it or not?
        for (int i = 0; i < actualPosition; i++) {
            if (hamiltonianPath[i] == vertexIndex) {
                return false;
            }
        }

        return true;

    }

    private void showHamiltonianPath() {
        System.out.print("Hamiltonian cycle: ");
        for (int i = 0; i < hamiltonianPath.length; i++) {
            System.out.print(hamiltonianPath[i] + " ");
        }
        System.out.print(hamiltonianPath[0]);
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {0,1,0},
                {1,0,1},
                {0,1,0}
        };

        HamiltonianCycle cycle = new HamiltonianCycle(matrix);
        cycle.solve();

        int[][] matrix2 = {
                {0,1,1,1,0,0},
                {1,0,1,0,1,0},
                {1,1,1,1,0,1},
                {1,0,1,0,0,1},
                {0,1,0,0,0,1},
                {0,1,1,1,1,1}
        };

        cycle = new HamiltonianCycle(matrix2);
        cycle.solve();
    }
}
