package com.dsalgo.heap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class _6TopKFrequentNumbers {

    private static void solve(int[] arr, int k) {

        Map<Integer, Integer> map = new HashMap<>();

        for (int i : arr) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        PriorityQueue<Pair1> minHeap = new PriorityQueue<>((a, b) -> a.first - b.first);

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {

            minHeap.offer(new Pair1(entry.getValue(), entry.getKey()));

            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        while (!minHeap.isEmpty()) {
            System.out.println(minHeap.poll().second);
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 1, 3, 2, 2, 4};
        solve(arr, 2);
    }
}

class Pair1 {
    int first;
    int second;

    public Pair1(int first, int second) {
        this.first = first;
        this.second = second;
    }
}

class WithoutPair {
    private static void solve(int[] arr, int k) {

        Map<Integer, Integer> map = new HashMap<>();

        for (int i : arr) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        // By default, heap is minHeap/ascending order.
        // for ascending order, do ->  e1 - e2
        // here we want to sort by frequency which is value, hence doing e1.getValue() - e2.getValue()
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>((e1, e2) -> e1.getValue() - e2.getValue());

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {

            minHeap.offer(entry);

            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        while (!minHeap.isEmpty()) {
            System.out.println(minHeap.poll().getKey());
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 1, 3, 2, 2, 4};
        solve(arr, 2);
    }
}