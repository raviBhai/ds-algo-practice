package com.dsalgo.graphs;

/*
Given adjacency matrix, find the number of components

Solution:
Use disjoint set and add the edges to it.
In the disjoint set, after the edges are added, the parent array always have the ultimate parent for each node.

For one component, all the nodes will have the same ultimate parent.

So, if we calculate the unique ultimate parents in the parent array, it will represent the number of components.
Another way to look at it is, get how many nodes are there who are ultimate parent of themselves, these will be the number of components.

 */
public class _44_NumberOfProvinces_DisjointSet {

    private static int solve(int[][] adjMatrix) {
        DisjointSet ds = new DisjointSet(adjMatrix.length);
        for (int i = 0; i < adjMatrix.length; i++) {
            for (int j = 0; j < adjMatrix[0].length; j++) {
                if (adjMatrix[i][j] != 0) {

                    // adjMatrix has nodes from 1 to 7, and i would iterate from 0 to 6, hence need to do i+1, j+1
                    ds.unionBySize(i+1, j+1);
                }
            }
        }
        int[] parent = ds.parent;
        int count = 0;
        for (int i = 1; i < parent.length; i++) {   // starting from i=1 as the matrix is having the nodes from 1 to 7, not from 0 to 7. parent[0] will also be 0 as we never updated parent[0] and we do not want to count parent[0]
            if (parent[i] == i) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {

        // 3 components : 1----2----3 ;   4----5 ;    6----7
        int[][] adjMatrix = {
                {0,1,0,0,0,0,0},
                {1,0,1,0,0,0,0},
                {0,1,0,0,0,0,0},
                {0,0,0,0,1,0,0},
                {0,0,0,1,0,0,0},
                {0,0,0,0,0,0,1},
                {0,0,0,0,0,1,0}
        };

        System.out.println(solve(adjMatrix));
    }
}
