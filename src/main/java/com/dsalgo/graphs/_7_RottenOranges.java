package com.dsalgo.graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given input matrix which has numbers 0,1,2
 * These numbers represent oranges.
 * 0 - empty cell
 * 1 - fresh orange
 * 2 - rotten orange
 *
 * In 1 day, all immediate neighbours of a rotten orange can rot.
 * In the next day, the next round of oranges will rot.
 *
 * Return the number of days in which all the oranges rot.
 * Return -1 if all the oranges do not rot
 *
 *
 * Solution_1 -
 * Use 2 Queues.
 * Initially, iterate over all the grid and push all the rotten oranges in Q1
 * While the Q1 is not empty, poll element from Q1, get fresh neighbours, and push them in Q2
 * While Q2 is not empty, poll element from Q2, get fresh neighbours, and push them in Q1
 *
 * 1 day is taken to empty the queue, as the queue has the elements which rot in a day.
 * Keep iterating over Q1 and Q2 until both are empty
 *
 * while (!Q1.isEmpty() || !Q2.isEmpty()) {
 *
 *     while (!Q1.isEmpty()) {
 *         // get fresh, rot them, and put to Q2
 *     }
 *     days++
 *
 *     while (!Q2.isEmpty()) {
 *          // get fresh, rot them, and put to Q1
 *     }
 *     days++
 * }
 *
 *
 * Solution_2 : Preferred approach
 *
 * This is implemented in the below code.
 * When pushing a row, col over the Q, push the dayNumber as well.
 * Keep track of the max dayNumber
 *
 */
public class _7_RottenOranges {

    private static int rotOranges(int[][] grid) {
        Queue<Triplet<Integer, Integer, Integer>> queue = new LinkedList<>();

        int freshOranges = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new Triplet<>(i, j, 0));
                } else if (grid[i][j] == 1) {
                    freshOranges++;
                }
            }
        }

        int[] xMoves = {0, -1, 0, 1};
        int[] yMoves = {-1, 0, 1, 0};
        int result = 0;
        int rottenOrangesCount = 0;

        while (!queue.isEmpty()) {
            Triplet<Integer, Integer, Integer> currentNode = queue.poll();
            result = Math.max(result, currentNode.third);

            for (int i = 0; i < xMoves.length; i++) {
                int newRow = currentNode.first + xMoves[i];
                int newCol = currentNode.second + yMoves[i];
                int newDay = currentNode.third + 1;

                if (newRow >= 0 && newRow < grid.length && newCol >= 0 && newCol < grid[0].length) {
                    if (grid[newRow][newCol] == 1) {    // if fresh
                        grid[newRow][newCol] = 2;   // rot the orange
                        queue.add(new Triplet<>(newRow, newCol, newDay));
                        rottenOrangesCount++;
                    }
                }
            }
        }

        if (freshOranges != rottenOrangesCount) {
            return -1;
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] grid1 = {
                {0, 1, 2},
                {0, 1, 1},
                {2, 1, 1}
        };
        System.out.println(rotOranges(grid1));      // output : 2

        int[][] grid2 = {
                {0, 1, 2},
                {1, 1, 0},
                {2, 0, 1}
        };
        System.out.println(rotOranges(grid2));      // output : -1, as we cannot rot the last orange
    }
}

