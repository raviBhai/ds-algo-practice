package com.dsalgo.graphs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given input matrix with 0 and 1.
 * 0 is water and 1 is land.
 * Group of connected 1's are island.
 * Single 1 is also an island.
 * You can move horizontally and vertically, not diagonally.
 *
 * Count number of distinct islands.
 * Two islands are identical if their shape is same.
 *
 * Example_1 -
 * 1 1 0 1 1
 * 1 0 0 0 0
 * 0 0 0 1 1
 * 1 1 0 1 0
 *
 * In the above example,
 * the islands at top left and bottom right have the same shape, hence they are identical.
 * the islands at top right and bottom left have the same shape, hence they are identical.
 * Output - 2
 *
 * Example_2 -
 * 1 1 0 1 1
 * 1 0 0 0 0
 * 0 0 0 0 1
 * 1 1 0 1 1
 * In the above example, the islands at top left and bottom right are not same.
 * But the islands at top right and bottom left have the same shape, hence they are identical.
 * Output - 3
 *
 * Solution -
 * To get the count of islands (for now, do not think of distinct) :
 * Traverse the matrix and when a 1 is encountered, do bfs or dfs and visit connected 1's.
 * Count the number of bfs/dfs traversals done.
 * This is same as _5_NumberOfIslands
 *
 * To get distinct islands :
 * If we can get a shape of the island, and store the shape in a set.
 * Next time, when an island of same shape is encountered, we can check if the set already has the shape.
 * Then, at last, we can return the size of the set.
 *
 * How to get the shape of an island :
 * From the above example_1, we will get the shape of the island at top left.
 * 1 1
 * 1
 *
 * We have the coordinates of the start node.Track it.
 * Then, we move to other non-visited connected 1's in a defined order. Track the coordinates of the other 1's being visited.
 *
 * BFS: traverse in right-down-left-up
 * Start - 0,0
 * Next node (move right from start) - 0,1
 * Next node (move down from start) - 1,0
 * Next node (move left from start) - invalid position as it goes outside the matrix
 * Next node (move up from start) - invalid position as it goes outside the matrix
 *
 * After tracking the coordinates : 0,0 : 0,1 : 1,0
 *
 *
 * Similarly, if we start from the node at the 2,3 in Example_1 above, we will get island of same shape.
 * Start : 2,3
 * Next node (move right from start) - 2,4
 * Next node (move down from start) - 3,3
 * Next node (move left from start) - 2,2 : invalid position as this is not 1
 * Next node (move up from start) - 1,3 : invalid position as this is not 1
 *
 * After tracking the coordinates : 2,3 : 2,4 : 3,3
 *
 * So now, we have to somehow figure out a way to tell these 2 tracking orders are identical.
 * Tracking order_1 : 0,0 : 0,1 : 1,0
 * Tracking order_2 : 2,3 : 2,4 : 3,3
 *
 * We can take start node as base, and when we move to the next, we can subtract the row and column of the base from the next node.
 * Subtract for start node as well.
 *
 * Tracking order_2 : 2,3 : 2,4 : 3,3
 * Base: 2,3
 *
 * start node : 2,3
 * Subtract : 2,3 - 2,3 = 0,0
 *
 * next node : 2,4
 * Subtract : 2,4 - 2,3 = 0,1
 *
 * next node : 3,3
 * Subtract : 3,3 - 2,3 = 1,0
 *
 * After subtraction, the order is : 0,0 : 0,1 : 1,0
 *
 * We can get the same order when we do the subtraction for Tracking order_1
 *
 * So, while doing a bfs/dfs, maintain this order while doing the subtraction.
 * This gives the shape of the island.
 *
 * We will maintain the tracking order as List, and add every list to the set.
 * Set<List<String>>
 * In java, if we create 2 different ArrayLists which has the same data, and add them to set, set will have only one entry.
 */
public class _13_NumberOfDistinctIslands {

    private static final int[] xMoves = {-1, 1, 0, 0};
    private static final int[] yMoves = {0, 0, -1, 1};

    private static void dfs (int row, int col, int[][] matrix, boolean[][] visited, List<String> trackingOrder, int baseRow, int baseCol) {
        visited[row][col] = true;
        trackingOrder.add((row - baseRow) + "," + (col - baseCol));
        for (int i = 0; i < xMoves.length; i++) {
            int nextRow = row + xMoves[i];
            int nextCol = col + yMoves[i];
            if (nextRow >= 0 && nextRow < matrix.length && nextCol >= 0 && nextCol < matrix[0].length) {
                if (!visited[nextRow][nextCol] && matrix[nextRow][nextCol] == 1) {
                    //visited[nextRow][nextCol] = true;
                    dfs(nextRow, nextCol, matrix, visited, trackingOrder, baseRow, baseCol);
                }
            }
        }
    }

    private static int countDistinctIslands (int[][] matrix) {
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        Set<List<String>> set = new HashSet<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (!visited[i][j] && matrix[i][j] == 1) {
                    List<String> trackingOrder = new ArrayList<>();
                    dfs(i, j, matrix, visited, trackingOrder, i, j);
                    set.add(trackingOrder);
                }
            }
        }
        return set.size();
    }

    public static void main(String[] args) {
        int[][] grid1 = {
                {1, 1, 0, 1, 1},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 1, 1},
                {1, 1, 0, 1, 0}
        };
        System.out.println(countDistinctIslands(grid1));    // output - 2

        int[][] grid2 = {
                {1, 1, 0, 1, 1},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1},
                {1, 1, 0, 1, 1}
        };
        System.out.println(countDistinctIslands(grid2));    // output - 3
    }
}
