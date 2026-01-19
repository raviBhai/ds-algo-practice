package com.dsalgo.graphs;

import java.util.PriorityQueue;

/*

### Problem Statement

You are given a 2D matrix where each cell contains a value representing its height. You are also given a start cell and a target cell.

The **effort of a path** is defined as the maximum absolute difference in heights between two consecutive cells along that path.

Your task is to find the path from the start cell to the target cell that minimizes this effort.
Return the minimum effort, not the path.

**Example:**

```
Matrix:
1 2 2
3 8 2
5 3 5

Start = (0,0), End = (2,2)
```

Possible paths and their efforts:

* Path 1: 1 → 2 → 2 → 2 → 5
  Differences: 1,0,0,3 → Max = 3
* Path 2: 1 → 2 → 8 → 2 → 5
  Differences: 1,6,6,3 → Max = 6
* Path 3: 1 → 3 → 5 → 3 → 5
  Differences: 2,2,2,2 → Max = 2

Among all paths, the minimum effort is **2**.

---

### Solution Approach

This problem can be solved efficiently using a **modified Dijkstra’s algorithm**:

1. **Initialize:**

   * Create a `distance[][]` matrix to store the minimum effort required to reach each cell.
   * Set the start cell’s effort to 0.
   * Use a priority queue (min-heap) to store cells in the form `{effort, row, col}`, where effort is the minimum effort required so far.

2. **Process cells (BFS with PQ):**

   * Extract the cell with the smallest effort from the priority queue.
   * For each neighbor of this cell:

     * Calculate the effort to move into the neighbor as:
       `newEffort = max(currentEffort, abs(heightDifference))`
     * If this `newEffort` is smaller than the previously stored effort for the neighbor, update it and push the neighbor into the priority queue.

3. **Early stopping:**

   * If the target cell is removed from the priority queue, we can stop immediately.
   * This works because the priority queue guarantees that the first time we encounter the target cell, it will have the smallest possible effort.

4. **Return the result:**

   * The effort value for the target cell at this point is the answer.



 */
public class _32_PathWithMinimumEffort {
    private static final int[] xMoves = {-1, 1, 0, 0};
    private static final int[] yMoves = {0, 0, -1, 1};
    private static final int INF = Integer.MAX_VALUE;

    private static int solve(int[][] martix, int[] source, int[] target) {
        int[][] distance = new int[martix.length][martix[0].length];
        for (int i = 0; i < distance.length; i++) {
            for (int j = 0; j < distance[0].length; j++) {
                distance[i][j] = INF;
            }
        }
        distance[source[0]][source[1]] = 0;

        PriorityQueue<Triplet<Integer, Integer, Integer>> pq = new PriorityQueue<>((t1, t2) -> t1.first - t2.first);
        pq.add(new Triplet<>(0, 0, 0));

        while (!pq.isEmpty()) {
            Triplet<Integer, Integer, Integer> polled = pq.poll();
            Integer effortTillCurrent = polled.first;
            Integer currentRow = polled.second;
            Integer currentCol = polled.third;

            if (currentRow == target[0] && currentCol == target[1]) {
                return effortTillCurrent;
            }

            for (int i = 0; i < xMoves.length; i++) {
                int newRow = currentRow + xMoves[i];
                int newCol = currentCol + yMoves[i];
                if (newRow >= 0 && newRow < martix.length && newCol >= 0 && newCol < martix[0].length) {
                    int effortFromCurrentToNeighbour = Math.abs(martix[currentRow][currentCol] - martix[newRow][newCol]);
                    int maxEffortTillNeighbour = Math.max(effortTillCurrent, effortFromCurrentToNeighbour);
                    if (maxEffortTillNeighbour < distance[newRow][newCol]) {
                        distance[newRow][newCol] = maxEffortTillNeighbour;
                        pq.add(new Triplet<>(maxEffortTillNeighbour, newRow, newCol));
                    }
                }
            }

        }

        return -1;

    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 2},
                {3, 8, 2},
                {5, 3, 5}
        };

        int[] source = {0, 0};
        int[] target = {2, 2};
        System.out.println(solve(matrix, source, target));
    }
}
