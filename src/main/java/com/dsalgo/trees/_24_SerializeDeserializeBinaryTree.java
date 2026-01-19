package com.dsalgo.trees;

import java.util.Deque;
import java.util.LinkedList;


/*
Given input node, write 2 functions - serialize and deserialize.

Serialize - convert tree to string.
Algorithm:
Algorithm (Serialization)
Setup: Initialize a Queue with the root node and a StringBuilder for the result.
Traversal: Iterate while the Queue is not empty.
Processing:
    Dequeue the current Node.
    If the Node is null, append the null marker (#) to the result string and continue.
    If the Node is not null, append its value.
    Crucially: Enqueue both the left child (node.left) and the right child (node.right), even if they are null, to maintain level-order structure.
    Finalize: Remove the trailing comma from the resulting string.

             1
            /\
           /  \
          /    \
         2     13
               /\
              /  \
             4    5

    Result : 1,2,13,#,#,4,5,#,#,#,#


Deserialize - convert string to tree
Algorithm:
Split the string on comma (",") so that we get an array
Maintain index pointer to the array, it starts at 0.
Take 0th entry and create a node and add to the queue.
Iterate while the queue is not empty
Take entries at index+1 and index+2 from the array.
If these are not #, then create a node, add them as left and right of the polled element, and push them to the queue



 */
public class _24_SerializeDeserializeBinaryTree {

    public static String serialize(Node root) {

        StringBuilder sb = new StringBuilder();
        Deque<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            if (poll == null) {
                sb.append("#").append(",");
                continue;
            }
            sb.append(poll.data).append(",");
            queue.add(poll.left);
            queue.add(poll.right);
        }

        return sb.toString().substring(0, sb.length() - 1);
    }

    public static Node deserialize(String data) {
        String[] dataArray = data.split(",");
        int index = 0;
        Node node = new Node(Integer.parseInt(dataArray[index]));
        Deque<Node> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            String dataLeft = dataArray[++index];
            if (!dataLeft.equals("#")) {
                Node left = new Node(Integer.parseInt(dataArray[index]));
                poll.left = left;
                queue.add(left);
            }

            String dataRight = dataArray[++index];
            if (!dataRight.equals("#")) {
                Node right = new Node(Integer.parseInt(dataArray[index]));
                poll.right = right;
                queue.add(right);
            }
        }
        return node;
    }

    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(13);
        Node n4 = new Node(4);
        Node n5 = new Node(5);

        n1.left = n2;
        n1.right = n3;

        n3.left = n4;
        n3.right = n5;

        String s = serialize(n1);
        System.out.println(s);
        Node node = deserialize(s);
        System.out.println(serialize(node));
    }

}
