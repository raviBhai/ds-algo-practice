package com.dsalgo.trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class _6_ZigzagTraversal {

    public static void solve(Node root) {

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        boolean isLeftToRight = true;

        while (!stack.isEmpty()) {

            int size = stack.size();
            List<Node> level = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                level.add(stack.pop());
            }

            for (int i = 0; i < level.size(); i++) {

                Node current = level.get(i);
                System.out.print(current.data + ", ");

                if (isLeftToRight) {
                    if (current.left != null) stack.push(current.left);
                    if (current.right != null) stack.push(current.right);
                } else {
                    if (current.right != null) stack.push(current.right);
                    if (current.left != null) stack.push(current.left);
                }
            }

            System.out.println();
            isLeftToRight = !isLeftToRight;
        }

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

        solve(n1);

    }
}
