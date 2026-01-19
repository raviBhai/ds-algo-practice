package com.dsalgo.graphs;

/*


---

### Dijkstra’s Algorithm vs Bellman-Ford Algorithm

* **Dijkstra's algorithm** doesn't work when there are negative edge weights.

  * In such cases, **Bellman-Ford** is used.

* **Dijkstra's algorithm** works with both directed and undirected graphs.

  * **Bellman-Ford algorithm**, however, only works with directed graphs.

  If you are given an undirected graph, you can convert it into a directed graph:

  **Example:**

  * For an undirected edge between nodes `a` and `b` with a weight of 5, represented as:


    a --- (5) --- b

  * You convert this into a directed graph with two edges:


    a -----(5)-----> b
    ↑______(5)_______|

  * This means you now have two directed edges: `a -> b` with weight 5, and `b -> a` with weight 5.

---

### Negative Cycles in Graphs

* **Negative cycles** in a graph are problematic for Bellman-Ford as well.

  * If there’s a negative cycle, **Bellman-Ford cannot find a solution** because it’s impossible to get a valid shortest path.

---

### Bellman-Ford Algorithm Process

* **No priority queue** is used in Bellman-Ford. Instead, the algorithm uses **edge relaxation**.
* In **Dijkstra's algorithm**, edge relaxation works like this:


  if (distance[currentNode] + distanceToNeighbor < distance[neighbor]) {
      distance[neighbor] = distance[currentNode] + distanceToNeighbor
  }

* In **Bellman-Ford**, there’s no priority queue to maintain a current node. Instead, we relax all the edges repeatedly.

---

### How Bellman-Ford Works

1. **Input Format:** The graph is represented as an array of edges. Each edge contains 3 elements: `source`, `target`, and `distance`.

2. **Relaxation Process:**

   * You iterate **N-1 times** over all the edges to relax them, where N is the number of nodes
   * For each edge, you check if the current known distance to a node can be shortened by taking the current edge.
   * If it can, update the shortest distance for that node.

   The pseudocode for this is:


   for (int i = 0; i < N - 1; i++) {
       for (int[] edge : edges) {
           if (distance[edge[0]] + edge[2] < distance[edge[1]]) {
               distance[edge[1]] = distance[edge[0]] + edge[2];
           }
       }
   }


---

### Why Do We Iterate N-1 Times?

Let’s consider an example graph where the edge weights are all 1:

```
0 ----> 1 ----> 2 ----> 3 ----> 4
```

The edge list would look like:


edges[][] = {
    {3, 4, 1},
    {2, 3, 1},
    {1, 2, 1},
    {0, 1, 1}
}


* **Initialize distances:** Set all distances to `Integer.MAX_VALUE`, except for the source node which is set to 0.

**Relaxation process:**

* On the **first iteration**, only `distance[0]` can be updated because the other distances are still `Integer.MAX_VALUE`.

* On the **second iteration**, only `distance[1]` can be updated.

* This continues until **the N-1-th iteration**, where all nodes are reached.

* In the worst case, you need to perform **N-1 iterations** to guarantee the shortest paths are found.

---

### Checking for Negative Cycles

* After **N-1 iterations**, if you perform **one more iteration** and find that any distance can still be updated, then the graph contains a negative cycle.

  * **Why?** Because if there’s no negative cycle, there should be no updates after N-1 iterations.
  * If an update occurs during the N-th iteration, it indicates a negative cycle, and you should return `-1` to signal that there’s no valid solution.

---


 */

import java.util.Arrays;

public class _36_BellmanFordAlgorithm {

    private static final int INF = Integer.MAX_VALUE;

    private static int[] solve(int n, int[][] edges, int source) {

        int[] distance = new int[n];
        Arrays.fill(distance, INF);

        distance[source] = 0;

        // loop n-1 times
        for (int i = 1; i <= n-1; i++) {
            for (int[] edge : edges) {
                if (distance[edge[0]] != INF && distance[edge[0]] + edge[2] < distance[edge[1]]) {
                    distance[edge[1]] = distance[edge[0]] + edge[2];
                }
            }
        }

        // check for negative cycle
        for (int[] edge : edges) {
            if (distance[edge[0]] != INF && distance[edge[0]] + edge[2] < distance[edge[1]]) {
                int[] temp = new int[1];
                temp[0] = -1;
                return temp;
            }
        }

        return distance;
    }

    /**
     * <p>Here is an image related to this class:</p>
     *  <img src="images/bellmanFord.png" alt="My Image Description" width="300"/>
     * @param args
     */
    public static void main(String[] args) {

        // refer image in java docs comment for the input graph

        int[][] edges = {
                {0, 1, 5},
                {1, 2, -2},
                {1, 5, -3},
                {2, 4, 3},
                {3, 2, 6},
                {3, 4, -2},
                {5, 3, 1}
        };

        int[] distance = solve(6, edges, 0);
        for (int i : distance) {
            System.out.println(i);
        }
    }
}
