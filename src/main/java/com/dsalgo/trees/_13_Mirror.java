package com.dsalgo.trees;

public class _13_Mirror {

    public static boolean isMirror(Node root) {
        if (root == null) {
            return true;
        }
        return isMirror(root.left, root.right);
    }

    private static boolean isMirror(Node n1, Node n2) {
        if (n1 == null && n2 == null) {
            return true;
        } else if (n1 == null || n2 == null) {
            return false;
        } else if (n1.data != n2.data) {
            return false;
        } else {
            return isMirror(n1.left, n2.right) && isMirror(n1.right, n2.left);
        }
    }

    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(2);
        Node n4 = new Node(3);
        Node n5 = new Node(4);
        Node n6 = new Node(4);
        Node n7 = new Node(3);

        n1.left = n2;
        n1.right = n3;

        n2.left = n4;
        n2.right = n5;

        n3.left = n6;
        n3.right = n7;

        System.out.println(isMirror(n1));
    }
}
