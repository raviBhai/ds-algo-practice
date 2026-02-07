package com.dsalgo.arrays;


/*

Given input array which as +ve and -ve elements. Return a subarray which has maximum sum of contiguous elements.

Algorithm :
Kadane's algorithm

Iterate over the array elements from the start and keep adding the current element to the variable sum.
If sum is less than max sum until now, then update max sum with the current sum.

We can then carry forward the current sum when we move to the next element.
However, we should carry forward the sum only if the sum is positive number because, if it is positive, then only it can
make further sum greater than the current sum.

 */
public class _10_MaximumSumSubarray {

    private static int solve(int[] array) {
        int sum = 0;
        int maxSum = Integer.MIN_VALUE;

        int tempStart = -1, start = -1, end = -1;

        for (int i = 0; i < array.length; i++) {
            if (sum == 0) {
                tempStart = i;
            }
            sum = sum + array[i];
            if (maxSum < sum) {
                maxSum = sum;
                start = tempStart;
                end = i;
            }
            if (sum < 0) {
                sum = 0;
            }
        }

        System.out.println("Max sum subArray start - " + start + "  - and end - " + end);

        return maxSum;
    }

    public static void main(String[] args) {
        int[] array = {-2, -3, 4, -1, -2, 1, 5, -3};
        System.out.println(solve(array));
    }
}
