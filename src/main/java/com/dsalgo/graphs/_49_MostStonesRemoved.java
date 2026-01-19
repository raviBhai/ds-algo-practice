package com.dsalgo.graphs;

import java.util.HashSet;
import java.util.Set;

/*
Given a 2D array with each entry having a row,col number
It means that in a matrix, at this row,col we have a stone

We can remove a stone from a row,col only if there is another stone either in the same row or same column
Calculate the maximum number of stones removed.

Example :
Assume 1 as stone and 0 as empty cell
1 0 1 0
0 0 0 1
0 0 0 0
1 0 1 0
0 0 0 1

we can remove from 0,0 as it has another stone in same row or column
After removal, we have this matrix
0 0 1 0
0 0 0 1
0 0 0 0
1 0 1 0
0 0 0 1

we can remove from 0,2 as it has another stone in same column
After removal, we have this matrix
0 0 0 0
0 0 0 1
0 0 0 0
1 0 1 0
0 0 0 1

we can remove from 1,3 as it has another stone in same column
After removal, we have this matrix
0 0 0 0
0 0 0 0
0 0 0 0
1 0 1 0
0 0 0 1

we can remove from 3,0 as it has another stone in same row
After removal, we have this matrix
0 0 0 0
0 0 0 0
0 0 0 0
0 0 1 0
0 0 0 1

Now we cannot remove anything.

We removed 4 stones, we have to return this as output

Algorithm :
We see that we can group the stones which share same row or cols.
This is similar to components.
We see that the input matrix has 2 components.

From every component, we can remove all stones but not the last one.
If component size is 4, we can remove 4-1 = 3 stones
If component size is 1, we can remove 1-1 = 0 stones

Given n as the number of stones.
Let's say there are 4 components with sizes as x1, x2, x3, x4
x1 + x2 + x3 + x4 = n

Max num of stones removed
 = (x1 - 1) + (x2 - 1) + (x3 - 1) + (x4 - 1)
 = (x1 + x2 + x3 + x4) - (1 + 1 + 1 + 1)
 = n - nc   // nc is the number of components

So, we need to find the number of components from the given 2D array.

As we are thinking of components, we can use Disjoint Set.
Rather than having a cell as a Node in the Disjoint Set, we can have rows and columns as nodes in the disjoint set.
Example matrix has 5 rows and 4 columns
Rows : 0 1 2 3 4
Cols : 0 1 2 3

Convert Row number to Node number : keep it same : 0 -> 0, 1 -> 1, 2 -> 2, 3 -> 3, 4 -> 4
Convert Col number to Node number : it cannot start with 0 as 0 is already taken as node number in the rows.
So, for columns, we can start from 5
0 -> (0 + maxRows + 1) = 5
1 -> (1 + maxRows + 1) = 6
2 -> (2 + maxRows + 1) = 7
3 -> (3 + maxRows + 1) = 8

Node numbers are : 0,1,2,3,4,5,6,7,8

Now, we iterate over the input 2D array which has row,col
From row,col we will get 2 node numbers and connect them using disjoint set

Input will be :
0,0
0,2
1,3
3,0
3,2
4,3

Get maxRow and maxCol from this input. These maxRow and maxCol will be treated as matrix dimension.
Init disjoint set with ( maxRow + maxCol + 1 )
maxRow = 4
maxCol = 3
Disjoint set size = 8

Nodes in Disjoint set :
0,1,2,3,4,5,6,7,8

Start processing the input:
Take 0,0
nodeNumbers : row 0 -> 0, col 0 -> 5
connect 0,5
0 ----- 5

Take 0,2
nodeNumbers : row 0 -> 0, col 2 -> 7
connect 0,7
0 ----- 5
0 ----- 7

Take 1,3
nodeNumbers : row 1 -> 1, col 3 -> 8
connect 1,8
0 ----- 5
0 ----- 7
1 ----- 8

Take 3,0
nodeNumbers : row 3 -> 3, col 0 -> 5
connect 3,5
but 5's ulp is 0, so connect 3 to 0
0 ----- 5
0 ----- 7
0 ----- 3
1 ----- 8


Take 3,2
nodeNumbers : row 3 -> 3, col 2 -> 7
connect 3,7
both are already connected via 0
0 ----- 5
0 ----- 7
0 ----- 3
1 ----- 8

Take 4,3
nodeNumbers : row 4 -> 4, col 3 -> 8
connect 4,8
but 8's ulp is 1, so connect 4 to 1
0 ----- 5
0 ----- 7
0 ----- 3
1 ----- 8
1 ----- 4

Nodes 2 and 6 are NOT connected to any other nodes.
From the input matrix, node 2 is row 2 and node 6 is column 2, both are empty.

So, while connecting the nodes, we need to keep track of how many nodes actually got connected, so that we can ignore nodes 2 and 6
We can use a set to keep track of the nodes which are getting connected.

From this set, we can get the unique ulp across all the nodes in the set.
This number of unique ulp will be the number of connected components

 */
public class _49_MostStonesRemoved {

    private static int solve(int[][] input, int n) {
        int maxRow = -1;
        int maxCol = -1;
        for (int[] coordinate : input) {
            maxRow = Math.max(maxRow, coordinate[0]);
            maxCol = Math.max(maxCol, coordinate[1]);
        }
        DisjointSet ds = new DisjointSet(maxRow + maxCol + 1);
        Set<Integer> nodesHavingStones = new HashSet<>();
        for (int[] coordinate : input) {
            int row = coordinate[0];
            int col = coordinate[1];

            int rowNodeNumber = row;
            int colNodeNumber = col + maxRow + 1;
            ds.unionBySize(rowNodeNumber, colNodeNumber);
            nodesHavingStones.add(rowNodeNumber);
            nodesHavingStones.add(colNodeNumber);
        }
        int numberOfComponents = 0;
        for (int node : nodesHavingStones) {
            if (ds.findParent(node) == node) {
                numberOfComponents++;
            }
        }
        return n - numberOfComponents;
    }

    public static void main(String[] args) {
        int[][] input = {
                { 0, 0 },
                { 0, 2 },
                { 1, 3 },
                { 3, 0 },
                { 3, 2 },
                { 4, 3 }
        };
        int n = 6;
        System.out.println(solve(input, n));

        int[][] input2 = {
                { 0, 0 },
                { 1, 1 },
                { 2, 2 },
                { 2, 3 },
                { 2, 4 },
                { 3, 2 }
        };
        int n2 = 6;
        System.out.println(solve(input2, n2));
    }
}
