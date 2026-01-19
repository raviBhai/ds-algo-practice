package com.dsalgo.trees;

public class _5_MaximumSumPath {
}

/*

✅ Goal

Find the maximum sum path between any two nodes in a binary tree.
The path can start and end at any node (not necessarily leaves).


🧠 Core Idea

At every node, two things are happening:

1. We calculate the best path sum through that node (using both left and right children).
pathThroughNode = node.data + leftMax + rightMax

2. We return to the parent the best path sum that can be extended upward.
return node.data + max(leftMax, rightMax)
This ensures only one side (left or right) continues upward — because a parent can only extend the path through one child.

These 2 things are happening, because there are 2 candidates for best path:
1. Either the node is the curve point, and the max path is node.data+leftMax+rightMax
2. Or, the max path will go through parent of the node, and the node will be either in the left or right of its parent


public static int solve(Node node, int[] max) {
    if (node == null) {
        return 0;
    }

    int leftMax = Math.max(0, solve(node.left, max));
    int rightMax = Math.max(0, solve(node.right, max));

    max[0] = Math.max(max[0], node.data + leftMax + rightMax);

    return node.data + Math.max(leftMax, rightMax);
}


🧠 How It Works

Base case:
If the node is null, return 0.

Recursive calls:
Get the maximum downward path sum from the left and right subtrees.

int leftMax = Math.max(0, solve(node.left, max));
int rightMax = Math.max(0, solve(node.right, max));


If a subtree gives a negative value, we ignore it (replace with 0).
→ Because a negative path would only reduce our total.

Update global maximum (max[0]):
The path passing through the current node can include both children when the curve is at the current node:

node.data + leftMax + rightMax


So we update:

max[0] = Math.max(max[0], node.data + leftMax + rightMax);


Return value (this is key):
The return statement gives the maximum sum of a path that starts at this node and goes down one side (either left or right).

return node.data + Math.max(leftMax, rightMax);

This is what the parent will use to build its own path.



🔁 What’s Happening Conceptually

At every node:
We check the best path through that node (for the global answer). - curve through the node
We return the best path going down from that node (so the parent can extend it).

🌲 Example 1
        10
       /  \
      9    20
          /  \
         15   17


Path through 20 = 20 + 15 + 17 = 52
Path through 10 = 10 + 9 + 20 = 39
Global max = 52 → Path = 15 → 20 → 17


🌳 Example 2 (with negatives)
        15
       /  \
      10   20
          /  \
        -30  -15


LeftMax = 10
RightMax for 20 = max(0, -30) + max(0, -15) + 20 = 20
Path through 15 = 15 + 10 + 20 = 45
Negative branches are ignored, so they don’t reduce the total.


💬 One-line summary:
“Each node returns the best path going down,
but also checks if the best path through itself beats the global max.”

 */

/*

                -10
                /  \
               /    \
              /     20
             /      /\
            9      /  \
                  /    \
                 15    17


Max path is 15 + 20 + 17

We can calculate max path at every node, and then keep track of the max across all the nodes.
Max through 20 = 20 + 15 + 17
It means, max through a node = node.data + leftMAx + rightMax

Now, let's say the tree were this, instead of -10 at root, we had 10

                 10
                /  \
               /    \
              /     20
             /      /\
            9      /  \
                  /    \
                 15    17


Then the max path would be through 10 = 9 + 10 + 20 + 17
When we calculate max at 10, we do = value at root node (10) + leftMax + rightMax

When we try to calculate max at 10, we will recursively call left and right.
These recursive calls will ultimately return a result.
When the recursive call for 20 starts, its job is to return the max(20 + 15, 20 + 17)
That is, recursive call for 20 's job is to return the max of 20 + max(left, right)

So, the return statement's job is to return the max path either through left or right.

When we call the solve() function with the root node, we do not collect the return value of the solve function.
If we collect it for the root node, then it will return 10 + 20 + 17


However, it is possible that the tree has negative numbers.
In case of negative numbers, we may get max path when the path is not necessarily going from leaf to leaf.
It may go from {non-leaf to non-leaf} or {non-leaf to leaf} or {leaf to non-leaf}


            15
           /  \
          /    \
         /      \
        10      20
               / \
              /   \
            -30   -15

When leftMax and rightMax are returned, check if they are negative, if yes, return leftMax or rightMax as 0
So, 20's leftMax and rightMax will be 0


 */



class AnyNodeToAnyNode {

    public static int solve(Node node, int[] max) {
        if (node == null) {
            return 0;
        }
        int leftMax = Math.max(0,solve(node.left, max));
        int rightMax = Math.max(0, solve(node.right, max));
        max[0] = Math.max(max[0], node.data + leftMax + rightMax);
        return node.data + Math.max(leftMax, rightMax);
    }
}

/*

🌿 Maximum Path Sum (Leaf → Leaf)
🎯 Goal
Find the maximum path sum between two leaf nodes in a binary tree.

🧠 Key Difference from “Any Node → Any Node”
In this version, a valid path must start and end at leaf nodes.

That means:
- We can only consider a path through a node if that node has both left and right children.
- If a node has only one child, it cannot form a complete leaf-to-leaf path on its own.


🌳 Example 1: Both Children Exist
        10
       /  \
      5    3

Here, node 10 has both left and right children.
So, we can form a leaf-to-leaf path:
5 → 10 → 3
Max path sum = 5 + 10 + 3 = 18


🌲 Example 2: Only One Child
    10
    /
   5

Node 10 has only one child (5).
Since there is only one leaf (5), there is no valid leaf-to-leaf path.
The code correctly avoids updating max[0].
However, we still return the downward path sum (10 + 5)
so that the recursion works properly if this node is part of a larger tree.

🪄 Why the Return Statement Matters

Even if a node cannot form a leaf-to-leaf path, it still needs to return the best root-to-leaf path upward,
so its parent can continue building its path.
Hence: return node.data + (node.left == null ? rightMax : leftMax);
This ensures the function always provides a valid sum to the parent, even when only one child exists.


💬 Summary
“Only nodes with both left and right children can create a valid leaf-to-leaf path.
Others just pass their best downward path sum upward to help their parent.”


 */

class LeafToLeafNode {

    public static int solve(Node node, int[] max) {
        if (node == null) {
            return 0;
        }

        int leftMax = solve(node.left, max);
        int rightMax = solve(node.right, max);

        if (node.left != null && node.right != null) {
            max[0] = Math.max(max[0], node.data + leftMax + rightMax);
            return node.data + Math.max(leftMax, rightMax);
        } else {
            return node.data + (node.left == null ? rightMax : leftMax);
        }
    }
}