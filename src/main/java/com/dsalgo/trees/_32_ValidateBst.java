package com.dsalgo.trees;

/*

Given input BST, check if it is valid BST.

Invalid BST example :

         5
        /\
       1  6
          /\
         4  8

4 is less than 6 but should have been greater than 5, hence invalid BST
4 should have been between 5 and 6

Algorithm :
1. Go at every node and check if the node is in a range.
2. If no, return false
3. If yes, recurse for left and right

Start range  [-INF, +INF]

Current Node : 5
Range [-INF, +INF]
Is between range - yes
Recurse for left : 1, with range [-INF, 5]
Recurse for right : 6, with range [5, +INF]

Current Node : 1
Range [-INF, 5]
Is between range - yes

Current Node : 6
Range [5, +INF]
Is between range - yes
Recurse for left : 4, with range [5, 6]
Recurse for right : 8, with range [6, +INF]

Current Node : 4
Range [5, 6]
Is between range - no
Return FALSE


 */

public class _32_ValidateBst {

    private static boolean validate(Node root) {
        return isValid(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static boolean isValid(Node node, int min, int max) {

        if (node == null) {
            return true;
        }

        if (node.data >= max || node.data <= min) {
            return false;
        }

        return isValid(node.left, min, node.data) && isValid(node.right, node.data, max);

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

        System.out.println(validate(n1));

        /*

                 5
                /\
               1  6
                  /\
                 4  8

         */

        Node a1 = new Node(5);
        Node a2 = new Node(1);
        Node a3 = new Node(6);
        Node a4 = new Node(4);
        Node a5 = new Node(8);

        a1.left = a2;
        a1.right = a3;

        a3.left = a4;
        a3.right = a5;

        System.out.println(validate(a1));

    }

}
