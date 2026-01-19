package com.dsalgo.heap;

import java.util.Collections;
import java.util.PriorityQueue;

public class _2FindKthSmallestElement {

    public static int solve(int[] arr, int k) {

        // Declare max heap
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (int i : arr) {
            maxHeap.offer(i);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        return maxHeap.peek();
    }

    public static void main(String[] args) {
        int[] arr = {7, 10, 4, 3, 20, 15};
        System.out.println(solve(arr, 3));
    }
}
