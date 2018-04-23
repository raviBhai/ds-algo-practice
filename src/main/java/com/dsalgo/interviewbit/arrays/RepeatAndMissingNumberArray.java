package com.dsalgo.interviewbit.arrays;

import java.util.Arrays;

public class RepeatAndMissingNumberArray {
    public static void main(String[] args) {
        int[] A = {3, 1, 2, 3, 5};
        System.out.println(Arrays.toString(repeatedNumber_causesOverflow(A)));
        System.out.println(Arrays.toString(repeatedNumber_withIndex(A)));
    }

    public static int[] repeatedNumber_causesOverflow(final int[] A) {
        int n = A.length;
        int expectedSum = n*(n+1)/2;
        int expectedSquareSum = n*(n+1)*(2*n+1)/6;

        int actualSum = 0;
        int actualSquareSum = 0;

        for (int i=0; i<A.length; i++) {
            actualSum += A[i];
            actualSquareSum += A[i] * A[i];
        }
        int[] res = new int[2];
        int r, m;

        int diffSquare = expectedSquareSum - actualSquareSum;
        int diffSum = expectedSum - actualSum;

        m = (diffSquare + diffSum) / 2;
        r = diffSquare - m;

        res[0] = r;
        res[1] = m;

        return res;
    }

    public static int[] repeatedNumber_withIndex(final int[] A) {
        int[] B = new int[2];
        for (int i=0; i<A.length; i++) {
            int index = Math.abs(A[i]) - 1;
            if (A[index] < 0) {
                B[0] = Math.abs(A[i]);
            } else {
                A[index] = -A[index];
            }
        }
        for (int i=0; i<A.length; i++) {
            if (A[i] > 0) {
                B[1] = i+1;
                break;
            }
        }
        return B;
    }
}
