package com.dsalgo.graphs;

import java.util.ArrayList;
import java.util.List;

/*
Given an input matrix with 0 and 1.
0 is water and 1 is land.
Group of 1's connected horizontally and vertically (not diagonally) form an island.

Another input will be a list of <row, col> where we have to update 1 in the matrix,
and then count how many islands are there in the matrix after the update.
With every such input in the list, the number of islands may increase or decrease.
Maintain the number of islands with every such input, and return this.

Example -

0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0

List<row, col>
0,0 - 1
0,0 - 2
1,1 - 3
1,0 - 4
0,1 - 5
0,3 - 6
1,3 - 7
0,4 - 8
3,2 - 9
2,2 - 10
1,2 - 11
0,2 - 12

maintain a visited matrix

process 0,0 (1st entry)
1 0 0 0 0
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0

numOfIslands = 1
mark row, col as visited
visited[0][0] = true

process 0,0 (2nd entry)
skip as it is already visited

process 1,1 (3rd entry)
1 0 0 0 0
0 1 0 0 0
0 0 0 0 0
0 0 0 0 0

numOfIslands = 2
mark row, col as visited

process 1,0 (4th entry)
1 0 0 0 0
1 1 0 0 0
0 0 0 0 0
0 0 0 0 0

Here numOfIslands will become 1 as it connects with the other 2 islands
numOfIslands = 1
mark row, col as visited

process 0,1 (5th entry)
1 1 0 0 0
1 1 0 0 0
0 0 0 0 0
0 0 0 0 0

numOfIslands = 1
mark row, col as visited

Logic to update numOfIslands :
increase numOfIslands by 1 the moment we process an entry from the list and if it is not visited.
The get the adjacent 4 cells (up, down, left, right) which have 1.
If the current cell and the adjacent cell with 1 are not already connected, it means, the current cell will now get connected to the adjacent cell.
It means, we can reduce the numOfIslands count by 1.
However, if the current cell and the adjacent cell with 1 are already connected, it means, there will be no change in the count of numOfIslands

Given this, let's process the 4th and 5th entries again :

This is the state after processing 3rd entry  :
1 0 0 0 0
0 1 0 0 0
0 0 0 0 0
0 0 0 0 0

numOfIslands = 2

process 1,0 (4th entry)
Increment numOfIslands : 2 + 1 = 3
Get adjacent cells with a 1 : up, right
current and up are not already connected, hence reduce numOfIslands by 1 : 3 - 1 = 2, as well as, connect current and up
current and down are not already connected, hence reduce numOfIslands by 1 : 2 - 1 = 1, as well as, connect current and down
numOfIslands = 1

The matrix will now become :
1 0 0 0 0
1 1 0 0 0
0 0 0 0 0
0 0 0 0 0


process 0,1 (5th entry)
Increment numOfIslands : 1 + 1 = 2
Get adjacent cells with a 1 : down, left
current and down are not already connected, hence reduce numOfIslands by 1 : 2 - 1 = 1, as well as, connect current and down
The moment we connect current and down, current gets connected with other cells with which down was already connected, which were 0,0 and 1,0
current and left are already connected, hence no change in numOfIslands
numOfIslands = 1


The matrix will now become :
1 1 0 0 0
1 1 0 0 0
0 0 0 0 0
0 0 0 0 0


Keep track of numOfIslands in an array after processing every entry, and return the array.

Which data structure to use :

As we want to connect the components, we can use Disjoint Set.
Every cell in the matrix is a node.

As a node in disjoint set is represented by a number, we need to convert a cell (row, col) to a number.
cell 0,0 will be node 0
cell 0,1 will be node 1
cell 0,2 will be node 2
...
...
cell 2,1 will be node 11

numOfRows = 4
numOfColumns = 5
for 2,1 the node will be 2 * 5 + 1 = 11



 */
public class _47_NumberOfIslands_OnlineQueries_DisjointSet {

    private static final int[] xMoves = {-1, 1, 0, 0};
    private static final int[] yMoves = {0, 0, -1, 1};

    // rather than the input matrix, num of rows and columns (n, m) are given
    private static List<Integer> solve(int n, int m, int[][] operators) {
        DisjointSet ds = new DisjointSet(n * m);
        boolean[][] visited = new boolean[n][m];

        int numOfIslands = 0;
        List<Integer> ans = new ArrayList<>();
        for (int[] operator : operators) {
            int row = operator[0];
            int col = operator[1];

            if (visited[row][col]) {
                ans.add(numOfIslands);  // even if we are not processing the current operator, we still need to return the numOfIslands at this step
                continue;
            }

            visited[row][col] = true;
            numOfIslands++;

            int currentNode = row * m + col;

            for (int i = 0; i < xMoves.length; i++) {
                int adjR = row + xMoves[i];
                int adjC = col + yMoves[i];

                if (isValid(adjR, adjC, n, m)) {

                    // need to check if adjacent has a 1
                    // as we do not have an input matrix, we can use visited array
                    // the matrix would have 1 only if the cell was already visited
                    if (visited[adjR][adjC]) {
                        int adjNode = adjR * m + adjC;
                        if (ds.findParent(currentNode) != ds.findParent(adjNode)) {
                            numOfIslands--;
                            ds.unionBySize(currentNode, adjNode);
                        }
                    }
                }
            }
            ans.add(numOfIslands);

        }

        return ans;
    }

    private static boolean isValid(int r, int c, int n, int m) {
        if (r >= 0 && r < n && c >= 0 && c < m) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int n = 4, m = 5;
        int[][] operators = {
                {0,0},
                {0,0},
                {1,1},
                {1,0},
                {0,1},
                {0,3},
                {1,3},
                {0,4},
                {3,2},
                {2,2},
                {1,2},
                {0,2}
        };

        System.out.println(solve(n, m, operators));
    }
}
