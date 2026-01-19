package com.dsalgo.graphs;

import java.util.HashSet;
import java.util.Set;

/*
Given input matrix with 1 and 0
Connected 1's form an island.
There can be multiple islands in the matrix.
Now, if we replace any one of a "0" by "1" such that it connects a few adjacent components, we will get a new component.
The matrix can still have multiple components, each with an ulp which has a size.
Different components will have different sizes.

We need to replace a "0" such that we get the largest component and return the size of it.

Example :
1 1 0 1 1
1 1 0 1 1
1 1 0 1 1
0 0 1 0 0
0 0 1 1 1
0 0 1 1 1

There are 3 components of size 6,6,7
If we replace 0 with 1 at 0,2 we will have 2 components with size 13,7
If we replace 0 with 1 at 1,2 we will have 2 components with size 13,7
If we replace 0 with 1 at 2,2 we will have 1 component with size 20

We need to return 20

Solution:
As we are talking about connecting the components, we can think of Disjoint Set.
Every cell in the matrix will be a node.
Get nodeNumber from row,col using the formula = row * m + col

Initially, all the 1's which are adjacent should be put in the Disjoint set and be part of one component
To do so, iterate over the matrix and if "1" is found, then get neighbouring 4 cells.
If the neighbouring cell is 1 and is not connected with the currentCell, connect the two cells.

Now we do brute force wherever there is a 0.
Find the cell which has a 0, and get adjacent cells which has 1.
At the cell (0,2), we can take the right cell, then take its ulp from which we can get the component size at the right, which is 6
Then take the left cell whose component size is 6
To result component size will become 6+6+1

We can check the same with other 0's to get the max component size.

Edge case 1:
At cell (3,3) if we go down and left, we will get the same component.
It means, from the 4 adjacent cells, we need to get unique components.
Unique components mean unique ulp
So, we can go up,down,left,right and get the ulp and store them in a set so that we get unique ulp only.
Then we can iterate over the set and sum the size of each ulp.

Edge case 2:
All the cells in the matrix can be 1.
In this case, we will not be able to process anything for a cell which has a "0"
So, just before returning, we can check for this scenario.
Get the cell at 0,0 and get its ulp and then the size of the ulp

Then return the max value

 */
public class _48_MakingALargerIsland {

    private static final int[] xMoves = {-1, 1, 0, 0};
    private static final int[] yMoves = {0, 0, -1, 1};

    private static int solve(int[][] matrix) {
        int max = Integer.MIN_VALUE;

        int n = matrix.length;
        int m = matrix[0].length;
        DisjointSet ds = new DisjointSet(n * m);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int nodeNum = i * m + j;
                if (matrix[i][j] == 1) {
                    for (int k = 0; k < xMoves.length; k++) {
                        int adjR = i + xMoves[k];
                        int adjC = j + yMoves[k];
                        if (isValid(adjR, adjC, n, m) && matrix[adjR][adjC] == 1) {
                            int adjNodeNum = adjR * m + adjC;
                            if (ds.findParent(nodeNum) != ds.findParent(adjNodeNum)) {
                                ds.unionBySize(nodeNum, adjNodeNum);
                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 0) {
                    Set<Integer> parents = new HashSet<>();
                    //int nodeNum = i * m + j;
                    for (int k = 0; k < xMoves.length; k++) {
                        int adjR = i + xMoves[k];
                        int adjC = j + yMoves[k];
                        if (isValid(adjR, adjC, n, m) && matrix[adjR][adjC] == 1) {
                            int adjNodeNum = adjR * m + adjC;
                            int ulp = ds.findParent(adjNodeNum);
                            parents.add(ulp);
                        }
                    }
                    int sizeAtCurrentZero = 0;
                    for (Integer ulp : parents) {
                        sizeAtCurrentZero += ds.size[ulp];
                    }
                    max = Math.max(max, sizeAtCurrentZero + 1);
                }
            }
        }

        max = Math.max(max, ds.size[ds.findParent(0)]);

        return max;
    }

    private static boolean isValid(int r, int c, int n, int m) {
        if (r >= 0 && r < n && c >= 0 && c < m) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 1, 0, 1, 1},
                {1, 1, 0, 1, 1},
                {1, 1, 0, 1, 1},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 1, 1},
                {0, 0, 1, 1, 1},
        };
        System.out.println(solve(matrix));
    }

}
