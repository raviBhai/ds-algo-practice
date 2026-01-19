package com.dsalgo.heap;

public class _1HeapBasics {
    /**
     * If it is asked to find Kth smallest/largest element, use heap.
     *
     * If Kth smallest is asked, use Max heap
     * If Kth largest is asked, use Min heap
     *
     * Use PriorityQueue to represent a heap. Do not create a Heap by yourself.
     *
     * PriorityQueue by default is in increasing order, which is Min heap.
     * PriorityQueue<Integer> maxHeap = new PriorityQueue<>();
     *
     * Max heap - declare PriorityQueue in reverse order
     * PriorityQueue<Integer> minHeap = new PriorityQueue<>(Collections.reverseOrder());
     *
     * To get the Kth smallest element -
     *  1. Traverse over the array
     *  2. Push the element on the maxHeap
     *  3. If heap size becomes greater than K, remove element from the heap
     *  4. After completing iteration over the array, return the top element from the heap
     *
     * Kth smallest/largest can be obtained by sorting the array.
     * But array sort time complexity is n*log(n)
     * With heap, if we maintain heap of size K, time complexity reduces to n*log(K)
     *
     * PriorityQueue internally does not maintain elements in a sorted order.
     * To get the elements in sorted order using a PriorityQueue,
     * first insert all the elements in the priorityQueue using offer()
     * and then remove all the elements from the priorityQueue using poll()
     * The elements will get removed in the sorted order from the priorityQueue.
     *
     * After the elements are inserted in the priorityQueue, it is not necessary that it will have elements in sorted order.
     *
     */
}
