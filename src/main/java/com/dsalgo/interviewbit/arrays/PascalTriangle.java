package com.dsalgo.interviewbit.arrays;

import java.util.Arrays;

public class PascalTriangle {
    public static void main(String[] args) {
        int[][] res = generate(5);
        for (int i = 0; i<res.length; i++) {
            System.out.println(Arrays.toString(res[i]));
        }

        int[] krow = getRow(5);
        System.out.println(Arrays.toString(krow));
    }

    public static int[][] generate(int A) {
        if (A == 0) {
            int[][] B = new int[0][0];
            return B;
        }
        int[][] B = new int[A][];
        B[0] = new int[1];
        B[0][0] = 1;

        for (int i = 1; i < A; i++) {
            int[] arr = new int[i+1];
            B[i] = arr;
            int[] prev = B[i - 1];
            int a = 0;
            int b = 0;
            for (int j=0; j < arr.length; j++) {
                if (j < prev.length) {
                    a = prev[j];
                }
                if (j > 0) {
                    b = prev[j - 1];
                }
                arr[j] = a + b;
                a = 0;
                b = 0;
            }
        }

        return B;
    }

    public static int[] getRow(int A) {
        int[][] B = new int[A+1][];
        B[0] = new int[1];
        B[0][0] = 1;

        for (int i = 1; i <= A; i++) {
            int[] arr = new int[i+1];
            B[i] = arr;
            int[] prev = B[i - 1];
            int a = 0;
            int b = 0;
            for (int j=0; j < arr.length; j++) {
                if (j < prev.length) {
                    a = prev[j];
                }
                if (j > 0) {
                    b = prev[j - 1];
                }
                arr[j] = a + b;
                a = 0;
                b = 0;
            }
        }
        return B[A];
    }
}
