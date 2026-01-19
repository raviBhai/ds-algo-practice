package com.dsalgo.trees;

public class _33_LowestCommonAncestorBst {

    private static Node solve(Node root, Node p, Node q) {
        if (root == null) {
            return root;
        }
        if (root.data > p.data && root.data > q.data) {
            return solve(root.left, p, q);
        }
        if (root.data < p.data && root.data < q.data) {
            return solve(root.right, p, q);
        }
        return root;
    }

    public static void main(String[] args) {
                /*


                10
                /\
               /  \
              5    13
             /\    /\
            3  6  11 14
           /\   \
          2  4   9


         */
        Node n1 = new Node(10);
        Node n2 = new Node(5);
        Node n3 = new Node(13);
        Node n4 = new Node(3);
        Node n5 = new Node(6);
        Node n6 = new Node(2);
        Node n7 = new Node(4);
        Node n8 = new Node(9);
        Node n9 = new Node(11);
        Node n10 = new Node(14);

        n1.left = n2;
        n1.right = n3;

        n2.left = n4;
        n2.right = n5;

        n4.left = n6;
        n4.right = n7;

        n5.right = n8;

        n3.left = n9;
        n3.right = n10;

        System.out.println(solve(n1, n6, n8));



    }
}
