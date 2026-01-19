package com.dsalgo.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/*


---


**Problem Statement:**
Given a **connected, weighted, undirected graph**, compute the **sum of the weights** of the edges in its **Minimum Spanning Tree (MST)**.

---

### 💡 Algorithm (Prim’s Algorithm - Optimized with Min Heap / Priority Queue)

1. Use the following data structures:

   * A **min-priority queue (PQ)** that stores entries as `(weight, current_node, parent_node)`
   * A **visited array** to track which nodes are already included in the MST
   * A **variable `sum`** to keep track of the total weight of the MST

2. **Initialization:**

   * Start from any arbitrary node (commonly node `0`)
   * Push `(0, 0, -1)` into the PQ (0 weight to reach node 0, no parent)
   * Do **not** mark the node as visited when pushing into PQ

3. **Process the graph:**

   * While the PQ is not empty:

     1. Pop the top of the PQ — the node with the **minimum weight edge**
     2. If the node is already visited, skip it
     3. Otherwise:

        * Add the edge weight to `sum`
        * Mark the node as visited
     4. Iterate over all **unvisited neighbors** of the current node:

        * For each, push `(edge_weight, neighbor_node, current_node)` into the PQ
        * Do **not** mark neighbors as visited when pushing

4. After the loop, `sum` will contain the **total weight of the MST**

---

### ✅ Notes

* If you're **only interested in the total weight**, you can skip storing the MST edges (i.e., the `MST ArrayList` is unnecessary).
* If the graph is **disconnected**, Prim’s algorithm will only cover the connected component — make sure the graph is connected, or you'll need to run Prim’s on each component separately.

---

### ⏱️ Time Complexity

Assume:

* `V` = number of nodes (vertices)
* `E` = number of edges

**Time Complexity:**

Prim's algorithm using a **priority queue and adjacency list** has a time complexity of:

### 👉 `O(E log V)`

#### Why?

* Each node is added to the priority queue multiple times (once for each edge connected to it), but we only process it once (when it's first popped unvisited).
* We can have up to `E` edge insertions in the priority queue.
* But, at one time, the priority queue has at most `V` elements.
* Each insertion or extraction from the priority queue takes `O(log V)` time (since the queue is sorted by edge weight).
* Therefore, total complexity is `O(E log V)`.

---


 */
public class _40_MinimumSpanningTree_PrimAlgorithm {

    private static int solve(List<List<Pair<Integer, Integer>>> adjacencyList) {
        boolean[] visited = new boolean[adjacencyList.size()];
        PriorityQueue<Triplet<Integer, Integer, Integer>> pq = new PriorityQueue<>((a,b) -> a.first - b.first);
        int sum = 0;

        pq.offer(new Triplet<>(0, 0, -1));

        while (!pq.isEmpty()) {
            Triplet<Integer, Integer, Integer> polled = pq.poll();
            if (!visited[polled.second]) {

                // mark visited
                visited[polled.second] = true;

                // add to sum
                sum += polled.first;

                // add to MST arraylist
                // we can add the polled entry to MST arraylist but we are not doing it in this problem as it is not the requirement

                // iterate over neighbours
                List<Pair<Integer, Integer>> neighbours = adjacencyList.get(polled.second);
                for (Pair<Integer, Integer> neighbour : neighbours) {
                    if (!visited[neighbour.first]) {
                        pq.offer(new Triplet<>(neighbour.second, neighbour.first, polled.second));
                    }
                }
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        List<List<Pair<Integer, Integer>>> adjacencyList = new ArrayList<>();
        int n = 5;
        for (int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        adjacencyList.get(0).add(new Pair<>(1, 2));
        adjacencyList.get(0).add(new Pair<>(2, 1));

        adjacencyList.get(1).add(new Pair<>(0, 2));
        adjacencyList.get(1).add(new Pair<>(2, 1));

        adjacencyList.get(2).add(new Pair<>(0, 1));
        adjacencyList.get(2).add(new Pair<>(1, 1));
        adjacencyList.get(2).add(new Pair<>(4, 2));
        adjacencyList.get(2).add(new Pair<>(3, 2));

        adjacencyList.get(3).add(new Pair<>(2, 2));
        adjacencyList.get(3).add(new Pair<>(4, 1));

        adjacencyList.get(4).add(new Pair<>(2, 2));
        adjacencyList.get(4).add(new Pair<>(3, 1));

        System.out.println(solve(adjacencyList));
    }
}
