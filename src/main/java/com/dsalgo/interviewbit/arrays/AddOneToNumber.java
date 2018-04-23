package com.dsalgo.interviewbit.arrays;

import java.util.Arrays;

public class AddOneToNumber {
    public static void main(String[] args) {
        int[] A = {9,9,9};
        int[] B = plusOne(A);
        System.out.println(Arrays.toString(B));
    }

    public static int[] plusOne(int[] A) {
        int[] B = new int[A.length + 1];

        int carry = 1;
        int num;
        for (int i = B.length-1; i >=0 ; i--) {
            if (i >= 1) {
                num = A[i-1];
            } else {
                num = 0;
            }
            num = num + carry;
            B[i] = num%10;
            carry = num/10;
        }

        int count=0;
        while (B[count] == 0) {
            count++;
        }
        int[] C = new int[B.length - count];
        for (int j = count; j < B.length; j++) {
            C[j-count] = B[j];
        }
        return C;
    }
}
