
## 🌳 Reconstructing a Unique Binary Tree from Preorder and Inorder Traversal

The problem involves constructing a unique **Binary Tree** given its **Preorder** and **Inorder** traversals. This is possible because:
* The **Preorder** traversal uniquely identifies the **root** of any subtree (it's always the first element).
* The **Inorder** traversal, once the root is identified, uniquely divides the remaining elements into the **left subtree** and the **right subtree**.

### 🔍 Example Traversals

| Traversal | Elements |
| :--- | :--- |
| **Inorder** | 40, 20, 50, **10**, 60, 30 |
| **Preorder** | **10**, 20, 40, 50, 30, 60 |

### 🛠️ Core Algorithm: Recursive Approach

The reconstruction process is inherently **recursive**. The main idea is to use the Preorder array to find the current **root**, and then use that root's position in the Inorder array to split the remaining elements for the left and right subtrees.

#### **Step 1: Identify the Root**

The **first element** in the current **Preorder** array segment is always the **Root** of the current subtree.

* **Initial Call:**
    * `Preorder[0]` = **10**
    * $\implies$ **Root = 10**

#### **Step 2: Find the Split Point in Inorder**

Find the **Root** element (**10**) in the **Inorder** array. Everything to the **left** of the root in Inorder belongs to the **Left Subtree**, and everything to the **right** belongs to the **Right Subtree**.

| Inorder | **Left Subtree (Size: 3)** | **Root (10)** | **Right Subtree (Size: 2)** |
| :--- | :--- | :--- | :--- |
| | 40, 20, 50 | 10 | 60, 30 |

#### **Step 3: Determine the Sub-arrays (Recursive Data)**

The **size** of the left subtree elements (3) dictates which elements in the *remaining* Preorder array belong to the left subtree.

**A. Left Subtree Data**
* **Inorder Left:** **40, 20, 50**
* **Preorder Left:** The **next 3 elements** after the root (**10**) in the original Preorder array.
    * **20, 40, 50**

**B. Right Subtree Data**
* **Inorder Right:** **60, 30**
* **Preorder Right:** The remaining elements in the Preorder array.
    * **30, 60**

---

### 🚀 Recursive Calls (Detailed Example)

The algorithm now makes two recursive calls with the sub-array data:

#### **Call for Left Subtree (Root = 20)**

* **Preorder Left:** **20**, 40, 50 $\implies$ **New Root = 20**
* **Inorder Left:** 40, **20**, 50
* Find **20** in Inorder Left:
    * Elements to the left of 20: **40** (Size: 1)
    * Elements to the right of 20: **50** (Size: 1)

* **Next Left Call (Root = 40):**
    * Inorder: **40**
    * Preorder: **40**
* **Next Right Call (Root = 50):**
    * Inorder: **50**
    * Preorder: **50**

#### **Call for Right Subtree (Root = 30)**

* **Preorder Right:** **30**, 60 $\implies$ **New Root = 30**
* **Inorder Right:** 60, **30**
* Find **30** in Inorder Right:
    * Elements to the left of 30: **60** (Size: 1)
    * Elements to the right of 30: **NULL** (Size: 0)

* **Next Left Call (Root = 60):**
    * Inorder: **60**
    * Preorder: **60**
* **Next Right Call (Root = NULL):**
    * Inorder: **NULL**
    * Preorder: **NULL**



---

### 🧠 Optimization: Using Indices and a Hash Map

To avoid creating new sub-arrays in each recursive call, the algorithm should maintain **index boundaries** (`preStart`, `preEnd`, `inStart`, `inEnd`) for the current sub-arrays.

* **Index Pointers:** Maintain `preStart`, `preEnd` for the current segment of the Preorder array, and `inStart`, `inEnd` for the Inorder array.
* **Inorder Lookup Map:** To efficiently find the root in the Inorder array (Step 2), use a **Hash Map** (`Map<Element, Index>`) to store:
  $$\text{Inorder Element} \to \text{Index}$$
    * This converts the linear search for the root in the Inorder array from $O(N)$ to **$O(1)$**, significantly speeding up the overall algorithm to **$O(N)$** time complexity.

The crucial calculation in the recursive call is determining the split:

$$\text{Length of Left Subtree} = \text{Root Index in Inorder} - \text{inStart}$$

This length is then used to calculate the new `preEnd` and `preStart` for the Left and Right subtrees.

