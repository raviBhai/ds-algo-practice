package com.dsalgo.practice.sorting;

import java.util.Arrays;

public class QuickSort {
    static int[] data = {5,4,3,2,1};

    public static void main(String[] args) {
        sort(0, data.length - 1);
        System.out.println(Arrays.toString(data));
    }

    public static void sort(int left, int right) {
        if (right - left <= 0) {
            return;
        } else {
            int pivot = data[right];
            int partition = partitionIt(left, right, pivot);
            sort(left, partition - 1);
            sort(partition + 1, right);
        }
    }

    public static int partitionIt(int left, int right, int pivot) {
        int leftPtr = left - 1;
        int rightPtr = right;

        while (true) {
            while (leftPtr < right && data[++leftPtr] < pivot) {
                ;
            }

            while (rightPtr > 0 && data[--rightPtr] > pivot) {
                ;
            }

            if (leftPtr >= rightPtr) {
                break;
            } else {
                swap(leftPtr, rightPtr);
            }
        }
        swap(leftPtr, right);
        return leftPtr;
    }

    public static void swap(int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

}
