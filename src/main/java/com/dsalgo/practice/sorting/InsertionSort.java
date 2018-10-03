package com.dsalgo.practice.sorting;

import java.util.Arrays;

public class InsertionSort {
    static int[] data = {5,4,3,2,1};

    public static void main(String[] args) {
        sort(data);
        System.out.println(Arrays.toString(data));
    }

    public static void sort(int[] data) {
        for (int outer = 1; outer < data.length; outer++) {
            int temp = data[outer];
            int inner;
            for (inner = outer; inner > 0; inner--) {
                if (data[inner-1] > temp) {
                    data[inner] = data[inner-1];
                } else {
                    break;
                }
            }
            data[inner] = temp;
        }
    }

    public static void sort2(int[] data) {
        int out, in, temp;
        for (out = 1; out < data.length; out++) {
            temp = data[out];
            in = out;
            while (in > 0 && data[in - 1] > temp) {
                data[in] = data[in - 1];
                in--;
            }
            data[in] = temp;
        }
    }

    public static void sort3(int[] data) {
        int out, in, temp;
        for (out=1; out < data.length; out++) {
            temp = data[out];
            for (in = out; in > 0; in--) {
                if (data[in-1] > temp) {
                    data[in] = data[in - 1];
                }
            }
            data[in] = temp;
        }
    }
}
