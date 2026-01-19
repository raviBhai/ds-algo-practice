package com.dsalgo.graphs;

/*

Given input matrix which represents a graph.
Get the shortest distance from any node to any node.

Solution :
Dijkstra's algo is used to get shortest distance from source node to other nodes.
It does not give the shortest distance from every node to every other node.
To do so,  we will have to run Dijkstra's algo for every node and build the desired output.

We can use Floyd Warshall algorithm.

For every node, check the path through every other node.
If the path through the other node is less that the current path, then update

matrix[i][j] = min(matrix[i][j], matrix[i][k] + matrix[k][j])

Negative cycle :
If the sum of paths in a cycle is negative, it is negative cycle.
It means that shortest distance from one node to the same node is negative.
In reality, shortest distance from one node to the same node will be 0.
Using this logic, we can check for the negative cycle.

Time complexity : n^3

Dijkstra's time complexity is E*log(V)
If we had use Dijkstra's here, time complexity would have been V * E * log(V)


Input :
input matrix will have -1 when the paths are not reachable.
We will initially convert -1 to INF,
Then run the algo, during which some INF will be replaced by shortest distances.
And before returning, will convert remaining INF to -1 back

 */
public class _37_FloydWarshallAlgorithm {

    private static final int INF = Integer.MAX_VALUE;

    private static void solve (int[][] matrix) {

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == -1) {
                    matrix[i][j] = INF;
                }
                if (i == j) {
                    matrix[i][j] = 0;
                }
            }
        }

        for (int k = 0; k < matrix.length; k++) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    if (matrix[i][k] != INF && matrix[k][j] != INF) {
                        matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
                    }
                }
            }
        }

        // check for negative cycle
        // we can check only diagonal as it represents path from same node to same node
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][i] < 0) {
                System.out.println("Negative cycle detected, no solution can exist for this input");
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == INF) {
                    matrix[i][j] = -1;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {0, 2, -1, -1},
                {1, 0, 3, -1},
                {-1, -1, 0, -1},
                {3, 5, 4, 0}
        };
        solve(matrix);

        for (int[] row : matrix) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}
