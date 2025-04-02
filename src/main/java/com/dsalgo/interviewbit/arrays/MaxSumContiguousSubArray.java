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

    public static int maxSubArray2(final int[] A) {
        int sum=0, maxsum=Integer.MIN_VALUE;
        int ss = 0, s = 0, e = 0;
        for (int i = 0; i < A.length; i++) {
            sum = sum + A[i];
            if (maxsum < sum) {
                maxsum = sum;
                //we get maxsum here, so start and end should be calculated here
                e = i;
                s = ss;
            }
            if(sum < 0) {
                sum = 0;
                ss = i + 1;
            }

        }
        System.out.println("max sub array start - " + s);
        System.out.println("max sub array end - " + e);
        return maxsum;
    }
}
