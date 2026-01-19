package com.dsalgo.trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*

Perform preorder traversal without using recursion.

Solution:
Use stack.
Put root on the stack.
While the stack is not empty
    - pop and make it current
    - print it
    - push pop.right on the stack
    - push pop.left on the stack

 */
public class _3_Preorder_Iterative {

    public static List<Integer> solve(Node root) {
        List<Integer> result = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            result.add(pop.data);
            if (pop.right != null) {
                stack.push(pop.right);
            }
            if (pop.left != null) {
                stack.push(pop.left);
            }
        }


        return result;
    }

    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        Node n8 = new Node(8);
        Node n9 = new Node(9);
        Node n10 = new Node(10);

        n1.left = n2;
        n1.right = n3;

        n2.left = n4;
        n2.right = n5;

        n3.left = n6;
        n3.right = n7;

        n4.left = n8;
        n4.right = n9;

        n5.left = n10;

        System.out.println(solve(n1));
    }
}
