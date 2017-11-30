package com.dsalgo.practice.sorting;

import java.util.Arrays;

public class BubbleSort {
    static int[] data = {5,4,3,2,1};
    static int comparisonCounter = 0;
    static int swapCounter = 0;

    public static void main(String[] args) {
        sort(data);
        System.out.println(Arrays.toString(data));
        System.out.println("No of comparisons : " + comparisonCounter);
        System.out.println("No of swaps : " + swapCounter);
    }

    public static void sort(int[] data) {
        for (int outer = data.length - 1; outer > 0; outer--) {
            for (int inner = 0; inner < outer; inner++) {
                if (data[inner] > data[inner + 1]) {
                    swap(inner, inner+1);
                }
                comparisonCounter++;
            }
        }
    }

    public static void sort2(int[] data) {
        boolean isSorted = false;
        int length = data.length - 1;
        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < length; i++) {
                if (data[i] > data[i + 1]) {
                    swap(i, i+1);
                    isSorted = false;
                }
            }
            length--;
        }
    }



    public static void swap(int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
        swapCounter++;
    }
}
