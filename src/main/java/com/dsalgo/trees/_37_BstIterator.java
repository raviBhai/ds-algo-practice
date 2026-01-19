package com.dsalgo.trees;


import java.util.Stack;


/*
Approach 1 :
Get inorder and store in an array as ascending order.
Now maintain a pointer at 0th index.

When next() is called, return value at the pointer and increment it.
When hasNext() is called, check if pointer is within the array.

Cons : Space complexity is O(N)

If asked to solve in less space, O(H), use the below solution

 */
public class _37_BstIterator {

    Stack<Node> stack = null;
    boolean isReverse = false;

    _37_BstIterator(Node node, boolean isReverse) {
        stack = new Stack<>();
        this.isReverse = isReverse;

        if (isReverse) {
            while (node != null) {
                stack.push(node);
                node = node.right;
            }
        } else {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

    }

    boolean hasNext() {
        return !stack.isEmpty();
    }

    int next() {
        Node top = stack.pop();

        if (isReverse) {
            Node current = top.left;
            while (current != null) {
                stack.push(current);
                current = current.right;
            }
        } else {
            Node current = top.right;
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
        }

        return top.data;
    }
}
