package com.dsalgo.ctci;

import java.util.Arrays;

import com.dsalgo.practice.trees.BinaryTree;
import com.dsalgo.practice.trees.Node;

public class SortedArrayToBst {
    static BinaryTree binaryTree = new BinaryTree();
    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40, 50, 60, 70, 80};
        convert_1(arr);
        System.out.println();
        convert_2(arr);
    }

    private static void convert_2(int[] arr) {
        Node node = convertToBst(arr, 0, arr.length - 1);
        binaryTree.inOrder(node);
    }

    private static void convert_1(int[] arr) {
        convertToBst(arr);
        binaryTree.inOrder(binaryTree.root);
    }

    public static void convertToBst(int[] array) {
        int middle = getMiddleElement(array);
        int data = array[middle];
        addToBst(data);
        if (array.length != 0 && array.length != 1) {
            int[] leftArray = Arrays.copyOfRange(array, 0, middle);
            int[] rightArray = Arrays.copyOfRange(array, middle + 1, array.length);

            if (leftArray.length != 0) {
                convertToBst(leftArray);
            }

            if (rightArray.length != 0) {
                convertToBst(rightArray);
            }
        }
    }

    public static Node convertToBst(int[] array, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start + end) / 2;
        Node n = new Node(array[mid]);
        n.leftChild = convertToBst(array, start, mid - 1);
        n.rightChild = convertToBst(array, mid + 1, end);
        return n;
    }

    public static int getMiddleElement(int[] array) {
        int length = array.length;
        if (length != 0) {
            length = length / 2;
        }
        return length;
    }

    public static void addToBst(int data) {
        binaryTree.insert(data);
    }
}
