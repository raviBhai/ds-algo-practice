package com.dsalgo.practice.heap;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        int[] array = {5, 2, 7, 34, 67, 12, 1};
        Heap heap = new Heap(20);

        //Ascending order
        for (int i = 0; i < array.length; i++) {
            heap.insertMinHeap(array[i]);
        }
        for (int i = 0; i < array.length; i++) {
            array[i] = heap.removeMinHeap().data;
        }

        //Min Heap - ascending order
        System.out.println(Arrays.toString(array));

        //Descending order
        for (int i = 0; i < array.length; i++) {
            heap.insertMaxHeap(array[i]);
        }
        for (int i = 0; i < array.length; i++) {
            array[i] = heap.removeMaxHeap().data;
        }

        //Max Heap - descending order
        System.out.println(Arrays.toString(array));
    }
}
