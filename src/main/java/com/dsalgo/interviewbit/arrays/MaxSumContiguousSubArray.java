package com.dsalgo.interviewbit.arrays;

public class MaxSumContiguousSubArray {
    public static void main(String[] args) {
        final int[] A = {-1, 0, 0 -1};
        System.out.println(maxSubArray(A));
    }

    public static int maxSubArray(final int[] A) {
        int sum=0, maxsum=Integer.MIN_VALUE;
        for (int i = 0; i < A.length; i++) {
            sum = sum + A[i];
            if (maxsum < sum) {
                maxsum = sum;
            }
            if(sum < 0) {
                sum = 0;
            }

        }
        return maxsum;
    }
}
