package com.dsalgo.ctci;

import com.dsalgo.practice.trees.Node;

import java.util.ArrayList;
import java.util.List;

public class CheckIfPathSumsToANumberBTree {


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

        n1.leftChild = n2;
        n1.rightChild = n3;

        n2.leftChild = n4;
        n2.rightChild = n5;

        n3.leftChild = n6;
        n3.rightChild = n7;

        n4.leftChild = n8;
        n4.rightChild = n9;

        n5.leftChild = n10;


        //System.out.println(hasSum(n1, 16));

        List<Integer> path = new ArrayList<>();
        State state = new State(false);
        paths(n1, 16, state, path);
        System.out.println(state.isSum);


    }

    public static boolean hasSum(Node node, int sum) {
        if (node == null && sum == 0) {
            return true;
        } else {
            boolean ans = false;
            if (node.leftChild == null && node.rightChild == null) {
                if (node.data == sum) {
                    return true;
                }
            }

            if (node.leftChild != null) {
                ans = ans || hasSum(node.leftChild, sum - node.data);
            }

            if (node.rightChild != null) {
                ans = ans || hasSum(node.rightChild, sum - node.data);
            }
            return ans;
        }
    }

    private static void paths(Node root, int target, State state, List<Integer> path) {
        if (root == null) {
            return;
        }
        path.add(root.data);
        System.out.println(root.data);
        target = target - root.data;
        if (root.leftChild == null && root.rightChild == null) {
            System.out.println(path);
            if (target == 0) {
                state.isSum = true;
            }
        } else {
            paths(root.leftChild, target, state, new ArrayList<Integer>(path));

            if (state.isSum) {
                return;
            }
            paths(root.rightChild, target, state, new ArrayList<Integer>(path));

            if (state.isSum) {
                return;
            }
        }


    }


}

class State {
    boolean isSum = false;

    public State(boolean isSum) {
        this.isSum = isSum;
    }
}