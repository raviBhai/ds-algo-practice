package com.dsalgo.interviewbit.arrays;

import java.util.Arrays;

public class MaxNonNegativeSubArray {
    public static void main(String[] args) {
        //int[] A = {1, 2, 5, -7, 2, 3, -1, 4, 11, 0};
        //int[] A = {1, 2, 5, -7, 2, 3, -1, 2, 1, 2, 3};
        //int[] A = {1, 2, 5, -7, 2, 3, -1, 2, 1, 5};
        //int[] A = {1,2,3,4,5};
        //int[] A = {-1,-2,-3};
        //int[] A = {1, 2, 5, -7, 2, 5};
        //int[] A = {0, 0, -1, 0};
        int[] A = {1967513926, 1540383426, -1303455736, -521595368};
        System.out.println(Arrays.toString(maxset(A)));
    }

    public static int[] maxset(int[] A) {
        long maxSum = 0;
        int start = -1;
        int end = -1;
        int count = 0;
        int currentStart = -1;
        int currentEnd = -1;
        long sumTillHere = 0;

        for (int i=0; i<A.length; i++) {
            if (A[i] >= 0) {
                sumTillHere += A[i];
                count++;
            }
            if (maxSum < sumTillHere) {
                maxSum = sumTillHere;
                end = i;
                start = end - count + 1;
            } else if (maxSum == sumTillHere) {
                currentEnd = i;
                currentStart = currentEnd - count + 1;
                if ((end - start) < (currentEnd - currentStart)) {
                    start = currentStart;
                    end = currentEnd;
                }
            }
            if (A[i] < 0){
                count = 0;
                sumTillHere = 0;
            }
        }

        System.out.println("s-"+start);
        System.out.println("e-"+end);
        if (start == -1 || end == -1) {
            return new int [0];
        }

        int[] B = new int[end - start + 1];
        for (int i=start; i<=end; i++) {
            B[i-start] = A[i];
        }

        return B;
    }
}
