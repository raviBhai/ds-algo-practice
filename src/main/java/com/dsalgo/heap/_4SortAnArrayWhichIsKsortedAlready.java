package com.dsalgo.heap;


import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given an input array and int K, the input array is K-sorted already.
 * IF the array were sorted properly, then an element at index "i" can be found either at "i", or in next K elements or in previous K elements.
 * Element at "i" can be in the range of [i-k, i+k] both included.
 * Return a properly sorted array.
 *
 * array - 6,5,3,2,8,10,9
 * K = 3
 * If the above array were sorted, the element at 0th index should be 2.
 * Element at 0th location is found in the next 3 elements.
 * Similarly, element at 1st index in sorted array should be 3, which is found in the next 3 elements.
 *
 *
 */
public class _4SortAnArrayWhichIsKsortedAlready {

    private static List<Integer> solve(int[] arr, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        List<Integer> result = new ArrayList<>();

        for (int i : arr) {
            minHeap.offer(i);

            if (minHeap.size() > k) {
                result.add(minHeap.poll());
            }
        }

        while (!minHeap.isEmpty()) {
            result.add(minHeap.poll());
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arr = {6, 5, 3, 2, 8, 10, 9};
        System.out.println(solve(arr, 3));
    }
}
