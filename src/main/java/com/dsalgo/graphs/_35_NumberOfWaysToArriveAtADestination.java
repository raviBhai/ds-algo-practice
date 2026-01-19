package com.dsalgo.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/*



---

### Problem:

Given a  weighted graph, along with a source and target node, find the number of distinct shortest paths from the source to the target.

### Solution:

To determine the number of shortest paths to a node, we can use the following approach:

**Key Idea:**
The number of shortest paths to a node equals the sum of the number of shortest paths to all its predecessor nodes.

We can apply **Dijkstra's algorithm** to solve this problem, with a slight modification. We will maintain an additional array to keep track of the number of distinct shortest paths to each node.

**Steps:**

1. **Initialization:**

   * Set `numberOfWays[start] = 1`, since there is exactly one way to reach the starting node (by starting from it).
2. **Main Algorithm (Dijkstra’s approach):**

   * While iterating over the neighbors of each node, if a neighbor’s shortest distance is updated (i.e., if a shorter path to it is found), we will also update the number of ways to reach that neighbor. Specifically, we will set `numberOfWays[neighbor] = numberOfWays[currentNode]`, since the shortest path to the neighbor now passes through the `currentNode`.
3. **Handling Multiple Shortest Paths:**

   * If a neighbor has already been reached through another path, its distance might already be updated.
   * If we encounter the neighbor again via a different path and find that the distance to it through `currentNode` is the same as its existing shortest distance, it means we have found an additional shortest path to the neighbor.
   * In this case, we don’t update the distance to the neighbor, but we add the number of ways to reach `currentNode` to `numberOfWays[neighbor]`, i.e.,
     `numberOfWays[neighbor] += numberOfWays[currentNode]`.

---

This way, we can efficiently track both the shortest distances and the number of distinct shortest paths to each node.

---


 */
public class _35_NumberOfWaysToArriveAtADestination {

    private static final int INF = Integer.MAX_VALUE;

    private static int solve(int source, int target, int n, int[][] roads) {
        List<List<Pair<Integer, Integer>>> adjacencyList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        for (int[] road : roads) {
            adjacencyList.get(road[0]).add(new Pair<>(road[1], road[2]));
            adjacencyList.get(road[1]).add(new Pair<>(road[0], road[2]));
        }
        int[] distance = new int[n];
        int[] numberOfWays = new int[n];
        Arrays.fill(distance, INF);
        distance[source] = 0;
        numberOfWays[source] = 1;

        // PQ of distance, node
        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> a.first - b.first);
        pq.offer(new Pair<>(0, source));

        while (!pq.isEmpty()) {
            Pair<Integer, Integer> polled = pq.poll();
            Integer distanceTillCurrent = polled.first;
            Integer currentNode = polled.second;

            List<Pair<Integer, Integer>> neighbours = adjacencyList.get(currentNode);
            for (Pair<Integer, Integer> neighbour : neighbours) {
                if (distanceTillCurrent + neighbour.second < distance[neighbour.first]) {
                    distance[neighbour.first] = distanceTillCurrent + neighbour.second;
                    numberOfWays[neighbour.first] = numberOfWays[currentNode];

                    pq.offer(new Pair<>(distance[neighbour.first], neighbour.first));
                } else if (distanceTillCurrent + neighbour.second == distance[neighbour.first]) {
                    numberOfWays[neighbour.first] = numberOfWays[neighbour.first] + numberOfWays[currentNode];
                }
            }
        }
        return numberOfWays[target];
    }

    /**
     * <p>Here is an image related to this class:</p>
     *  <img src="images/numberOfWaysToArriveAtADestination.png" alt="My Image Description" width="300"/>
     * @param args
     */
    public static void main(String[] args) {

      // refer image in java docs comment for the input graph

        int[][] roads = {
                {0,1,1},
                {0,2,2},
                {0,3,1},
                {0,4,2},
                {1,5,2},
                {2,5,1},
                {3,5,2},
                {3,7,3},
                {3,6,2},
                {4,6,1},
                {5,8,1},
                {6,8,1},
                {7,8,1}
        };

        System.out.println(solve(0, 8, 9, roads));



    }
}
