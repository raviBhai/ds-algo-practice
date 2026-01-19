package com.dsalgo.trees;

import com.dsalgo.graphs.Pair;

import java.util.Deque;
import java.util.LinkedList;

/*

             1
            /\
           /  \
          3    7
         /      \
        8        4


## 💡 Algorithm Description: Maximum Binary Tree Width

The algorithm uses **Breadth-First Search (BFS)**, specifically **Level Order Traversal**, combined with a specialized **indexing scheme** to calculate the maximum width of a binary tree.
The width at any level is defined as the distance between the leftmost and rightmost non-null nodes, including any null positions between them.

### Key Concepts:

1.  **Complete Tree Indexing:** We treat the tree as if it were a complete binary tree. Using a **0-based indexing** system where the root is at index 0, the index of a node's children are:
      * Left Child: $2i + 1$
      * Right Child: $2i + 2$
2.  **Width Calculation:** For any given level, the width is calculated as:
    $$\text{Width} = (\text{Index of Rightmost Node}) - (\text{Index of Leftmost Node}) + 1$$
3.  **Overflow Prevention (Normalization):** The core challenge is that for highly skewed trees, the indices ($2i+1$ and $2i+2$) can quickly become extremely large, leading to an **integer overflow**. To solve this, the algorithm performs **index normalization** at the start of processing *each level*.
      * It identifies the **minimum index ($\min$)** present on the current level (always the index of the first node in the queue).
      * Before calculating the indices for the next level's children, the current node's index ($i$) is adjusted: $i' = i - \min$.
      * The new indices for the children are then calculated using this normalized index $i'$. This resets the index count for the level, effectively keeping the index values small and preventing overflow while preserving the **relative distance** between nodes.

### Steps of the Algorithm:

1.  **Initialize:** Start a queue with the root node and its initial index (0). Initialize `maxWidth` to a minimum value.
2.  **Level Traversal:** While the queue is not empty, process one level at a time.
      * **Find Minimum Index:** Get the index of the front element in the queue. This is the $\min$ index for the current level.
      * **Process Nodes:** Iterate through all nodes currently in the queue (the current level's nodes).
          * **Normalize:** Subtract the $\min$ index from the node's original index.
          * **Track Boundaries:** Record the normalized index of the **first** node (`first`) and the **last** node (`last`) of the level.
          * **Enqueue Children:** If a left or right child exists, calculate its **new index** using the normalized index ($i'$), and enqueue the child along with its new index.
      * **Calculate Width:** After processing the level, compute the width: $last - first + 1$.
      * **Update Max:** Update `maxWidth` with the maximum value found so far.
3.  **Return:** Return the final `maxWidth`.

-----

## 🏃 Dry Run Example

Let's use the sample tree:

```
             1
            /\
           /  \
          3    7
         /      \
        8        4
```

**Initialization:** `ans` = $MIN\_VALUE$. Queue: `[(1, 0)]` (Node, Index)

### 1\. Level 1

  * `size`: 1. `min`: **0**.
  * **Node 1 (Index 0):**
      * Normalized Index: $0 - 0 = \mathbf{0}$.
      * `first` = 0, `last` = 0.
      * Enqueue Children: (3, $2*0+1=1$), (7, $2*0+2=2$).
  * **Width:** $0 - 0 + 1 = \mathbf{1}$.
  * `ans` updated to **1**.
  * Queue: `[(3, 1), (7, 2)]`

### 2\. Level 2

  * `size`: 2. `min`: **1** (from node 3).
  * **Node 3 (Index 1):**
      * Normalized Index: $1 - 1 = \mathbf{0}$.
      * `first` = 0.
      * Enqueue Child: (8, $2*0+1=1$).
  * **Node 7 (Index 2):**
      * Normalized Index: $2 - 1 = \mathbf{1}$.
      * `last` = 1.
      * Enqueue Child: (4, $2*1+2=4$).
  * **Width:** $1 - 0 + 1 = \mathbf{2}$.
  * `ans` updated to **2**.
  * Queue: `[(8, 1), (4, 4)]`

### 3\. Level 3

  * `size`: 2. `min`: **1** (from node 8).
  * **Node 8 (Index 1):**
      * Normalized Index: $1 - 1 = \mathbf{0}$.
      * `first` = 0.
      * No children to enqueue.
  * **Node 4 (Index 4):**
      * Normalized Index: $4 - 1 = \mathbf{3}$.
      * `last` = 3.
      * No children to enqueue.
  * **Width:** $3 - 0 + 1 = \mathbf{4}$.
  * `ans` updated to **4**.
  * Queue: `[]`

**Result:** The dry run confirms the maximum width is **4**.

-----

## 💻 Code Analysis

The provided Java code perfectly implements the described algorithm.

```java
public static int solve(Node root) {
    // 1. Initialization
    Deque<Pair<Node, Integer>> queue = new LinkedList<>();
    queue.offer(new Pair<>(root, 0));
    int ans = Integer.MIN_VALUE;

    // 2. Level Traversal
    while (!queue.isEmpty()) {
        int size = queue.size();

        // Find Minimum Index (Normalization anchor)
        int min = queue.peek().second;

        int first = 0, last = 0;

        // Process Nodes (iterate through the current level)
        for (int i = 0; i < size; i++) {
            Pair<Node, Integer> poll = queue.poll();
            Node node = poll.first;
            Integer index = poll.second;

            // Normalize Index
            index = index - min;

            // Enqueue Children (using the normalized index)
            if (node.left != null) {
                // Left Child Index: 2 * i' + 1
                queue.offer(new Pair<>(node.left, 2 * index + 1));
            }
            if (node.right != null) {
                // Right Child Index: 2 * i' + 2
                queue.offer(new Pair<>(node.right, 2 * index + 2));
            }

            // Track Boundaries
            if (i == 0) {
                first = index; // The normalized index of the first node
            }
            if (i == size - 1) {
                last = index;  // The normalized index of the last node
            }
        }

        // Calculate Width and Update Max
        // Width = last - first + 1
        ans = Math.max(ans, last - first + 1);
    }

    // 3. Return
    return ans;
}
```

The use of `index = index - min;` inside the loop for both boundary tracking (`first`, `last`) and child index generation is the **key step** that ensures the algorithm correctly finds the width while mitigating the risk of integer overflow.



 */
public class _16_WidthOfBinaryTree {

    public static int solve(Node root) {
        Deque<Pair<Node, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair<>(root, 0));
        int ans = Integer.MIN_VALUE;
        while (!queue.isEmpty()) {
            int size = queue.size();
            int min = queue.peek().second;
            int first = 0, last = 0;
            for (int i = 0; i < size; i++) {
                Pair<Node, Integer> poll = queue.poll();
                Node node = poll.first;
                Integer index = poll.second;
                index = index - min;
                if (node.left != null) {
                    queue.offer(new Pair<>(node.left, 2 * index + 1));
                }
                if (node.right != null) {
                    queue.offer(new Pair<>(node.right, 2 * index + 2));
                }
                if (i == 0) {
                    first = index;
                }
                if (i == size - 1) {
                    last = index;
                }
            }
            ans = Math.max(ans, last - first + 1);
        }
        return ans;
    }
}