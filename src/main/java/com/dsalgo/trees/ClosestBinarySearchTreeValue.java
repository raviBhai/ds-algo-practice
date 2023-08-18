package com.dsalgo.trees;

import com.dsalgo.practice.trees.Node;

public class ClosestBinarySearchTreeValue {

    void closest(Node node, int target) {

        int min = Integer.MAX_VALUE;
        int diff = 0;
        int result = 0;


        Node current = node;

        while (current != null) {

            diff = Math.abs(current.data - target);
            if (diff < min) {
                diff = min;
                result = current.data;
            }

            if (current.data < target) {
                current = current.rightChild;
            } else {
                current = current.leftChild;
            }

        }

        System.out.println(result);


    }
}
