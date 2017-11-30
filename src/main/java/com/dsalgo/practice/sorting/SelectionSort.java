package com.dsalgo.practice.sorting;

import java.util.Arrays;

public class SelectionSort {
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
        int minimum;
        for (int outer = 0; outer < data.length; outer++) {
            minimum = outer;
            for (int inner = outer + 1; inner < data.length; inner++) {
                if (data[inner] < data[minimum]) {
                    minimum = inner;
                }
                comparisonCounter++;
            }
            swap(minimum, outer);
        }
    }

    public static void sort2(int[] data) {
        int out, in, min;
        for (out = 0; out < data.length; out++) {
            min = out;
            for (in = out+1; in < data.length; in++) {
                if (data[in] < data[min]) {
                    min = in;
                }
            }
            swap(min, out);
        }
    }

    public static void swap(int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
        swapCounter++;
    }
}
