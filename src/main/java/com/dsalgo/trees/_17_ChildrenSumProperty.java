package com.dsalgo.trees;

/*


             40
            /  \
           /    \
         10     20
         / \    / \
        2   5  30 40



## 📝 Children Sum Property Modification

The goal is to modify the values of nodes in a binary tree so that every internal node satisfies the **Children Sum Property**.

### Children Sum Property Definition

The **Children Sum Property** states that for any non-leaf node, its value must be equal to the sum of the values of its immediate left and right children.

$$\text{Node Value} = \text{Left Child Value} + \text{Right Child Value}$$

### Modification Constraint

The constraint for modification is critical: **we can only increment a node's value; we cannot decrement it.**

-----

## 🛠️ Restructured Algorithm

The modification process uses **Post-Order Traversal** (Left, Right, then Node) to ensure child values are finalized before the parent is updated.
The algorithm proceeds in two distinct phases for each non-leaf node: a **Downward Pass (Correction)** and an **Upward Pass (Recalculation)**.

### Phase 1: Downward Pass (Correction)

This phase ensures the parent node's value is distributed *down* to the children if the parent is too large. It happens **before** the recursive calls.

1.  **Calculate Children Sum:** Find S = \text{Left Child Value} + \text{Right Child Value}$.

2.  **Compare and Correct:**

      * **Case 1: `Current Node Value > S`** (Parent is too large)
          * Increment the **children's** values to match the parent. Set $\text{Left Child Value} = \text{Current Node Value}$ and $\text{Right Child Value} = \text{Current Node Value}$ (only if they exist). *This ensures the constraint of only incrementing.*
      * **Case 2: `Current Node Value $\leq S$`** (Parent is too small or equal)
          * Increment the **current node's** value to match the sum. Set $\text{Current Node Value} = S$.

3.  **Recurse:** Apply the algorithm recursively to the left subtree, then the right subtree.

### Phase 2: Upward Pass (Recalculation)

This phase ensures the parent node's value accurately reflects the final (and potentially modified) sum of its children's values. It happens **after** the recursive calls return.

1.  **Recalculate Total Sum:** Find T = \text{Final Left Child Value} + \text{Final Right Child Value}$.
2.  **Update Parent:** If the node is **not a leaf node** (i.e., it has at least one child), update its value: $\text{Current Node Value} = T.

-----

## 🏃 Step-by-Step Dry Run

**Initial Tree:**

```
             40 (A)
            /  \
           /    \
          /      \
      10(B)      20(C)
       / \       / \
      /   \     /   \
    2(D) 5(E) 30(F) 40(G)
```

We will trace the function calls, focusing on Phase 1 (Correction) and Phase 2 (Recalculation).

### 1\. `solve(Node A: 40)`

| Step | Action (Phase 1) | Details | Tree State (Relevant Nodes) |
| :---: | :--- | :--- | :--- |
| **A1** | Calculate Sum | $S = B(10) + C(20) = 30$ | B: 10, C: 20 |
| **A2** | Correction | A(40) \> S(30). **Case 1.** | **B: 40, C: 40** (Children incremented) |
| **A3** | Recurse Left | Call `solve(Node B: 40)` | |

### 2\. `solve(Node B: 40)`

| Step | Action (Phase 1) | Details | Tree State (Relevant Nodes) |
| :---: | :--- | :--- | :--- |
| **B1** | Calculate Sum | $S = D(2) + E(5) = 7$ | D: 2, E: 5 |
| **B2** | Correction | B(40) \> S(7). **Case 1.** | **D: 40, E: 40** (Children incremented) |
| **B3** | Recurse Left | Call `solve(Node D: 40)` | |

### 3\. `solve(Node D: 40)`

| Step | Action (Phase 1 & 2) | Details | Tree State (Relevant Nodes) |
| :---: | :--- | :--- | :--- |
| **D1** | Correction | D is a **leaf**. Sum $S=0$. D(40) \> S(0). No children to update. | D: 40 |
| **D2** | Recurse | Calls `solve(null)` (left/right) | |
| **D3** | Recalculation | D is a **leaf** (no children). Skip Phase 2 update (`node.left != null || node.right != null` is false). | D: 40 |
| **D4** | Return | Returns to B. | |

### 4\. `solve(Node E: 40)`

| Step | Action (Phase 1 & 2) | Details | Tree State (Relevant Nodes) |
| :---: | :--- | :--- | :--- |
| **B4** | Recurse Right | Call `solve(Node E: 40)` | |
| **E1** | Correction | E is a **leaf**. Sum $S=0$. E(40) \> S(0). No children to update. | E: 40 |
| **E3** | Recalculation | E is a **leaf**. Skip Phase 2 update. | E: 40 |
| **E4** | Return | Returns to B. | |

### 5\. `solve(Node B: 40)` - Completion

| Step | Action (Phase 2) | Details | Tree State (Relevant Nodes) |
| :---: | :--- | :--- | :--- |
| **B5** | Recalculate | $T = D(40) + E(40) = 80$. | D: 40, E: 40 |
| **B6** | Update Parent | B is non-leaf. Set B(40) = T(80). | **B: 80** |
| **B7** | Return | Returns to A. | |

### 6\. `solve(Node C: 40)`

| Step | Action (Phase 1) | Details | Tree State (Relevant Nodes) |
| :---: | :--- | :--- | :--- |
| **A4** | Recurse Right | Call `solve(Node C: 40)` | |
| **C1** | Calculate Sum | $S = F(30) + G(40) = 70$ | F: 30, G: 40 |
| **C2** | Correction | C(40) $\leq$ S(70). **Case 2.** | **C: 70** (Parent incremented) |
| **C3** | Recurse Left | Call `solve(Node F: 30)` | |

### 7\. `solve(Node F: 30)`

| Step | Action (Phase 1 & 2) | Details | Tree State (Relevant Nodes) |
| :---: | :--- | :--- | :--- |
| **F1** | Correction | F is a **leaf**. Skip children update. | F: 30 |
| **F3** | Recalculation | F is a **leaf**. Skip Phase 2 update. | F: 30 |
| **F4** | Return | Returns to C. | |

### 8\. `solve(Node G: 40)`

| Step | Action (Phase 1 & 2) | Details | Tree State (Relevant Nodes) |
| :---: | :--- | :--- | :--- |
| **C4** | Recurse Right | Call `solve(Node G: 40)` | |
| **G1** | Correction | G is a **leaf**. Skip children update. | G: 40 |
| **G3** | Recalculation | G is a **leaf**. Skip Phase 2 update. | G: 40 |
| **G4** | Return | Returns to C. | |

### 9\. `solve(Node C: 70)` - Completion

| Step | Action (Phase 2) | Details | Tree State (Relevant Nodes) |
| :---: | :--- | :--- | :--- |
| **C5** | Recalculate | $T = F(30) + G(40) = 70$. | F: 30, G: 40 |
| **C6** | Update Parent | C is non-leaf. Set C(70) = T(70). | **C: 70** |
| **C7** | Return | Returns to A. | |

### 10\. `solve(Node A: 40)` - Final Completion

| Step | Action (Phase 2) | Details | Tree State (Relevant Nodes) |
| :---: | :--- | :--- | :--- |
| **A5** | Recalculate | $T = B(80) + C(70) = 150$. | B: 80, C: 70 |
| **A6** | Update Parent | A is non-leaf. Set A(40) = T(150). | **A: 150** |
| **A7** | Return | Function finishes. | |

-----

## 🌳 Final Tree State

The resulting tree satisfies the children sum property at every non-leaf node:

```
      150 (A)
     /   \
    /     \
  80 (B)  70 (C)
  / \    /  \
40(D) 40(E) 30(F) 40(G)
```

**Verification:**

  * **Node A:** $80 + 70 = 150$ (Matches A)
  * **Node B:** $40 + 40 = 80$ (Matches B)
  * **Node C:** $30 + 40 = 70$ (Matches C)

  */

public class _17_ChildrenSumProperty {

    public static void solve(Node node) {
        if (node != null) {
            int sum = 0;
            if (node.left != null) {
                sum += node.left.data;
            }
            if (node.right != null) {
                sum += node.right.data;
            }
            if (node.data > sum) {
                if (node.left != null) node.left.data = node.data;
                if (node.right != null) node.right.data = node.data;
            } else {
                node.data = sum;
            }
            solve(node.left);
            solve(node.right);

            int total = 0;
            if (node.left != null) total += node.left.data;
            if (node.right != null) total += node.right.data;
            if (node.left != null || node.right != null) node.data = total;
        }
    }
}
