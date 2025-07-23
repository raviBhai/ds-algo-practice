package com.dsalgo.slidingwindow;

/**
 * Given an array of size n and a window of size k.
 * Find the maximum sum of any sub-array of size k in the given array
 */
public class _1MaxSumSubArrayOfSizeK {

    public static void main(String[] args) {
        int[] array = {1, -1, 5, -2, 3, 6, -7};

        // output should be 7 - window is {-2,3,6}
        System.out.println(solve(array, 3));
    }

    /**
     * Take i as startPointer and j as endPointer of the window
     * Window size will be j-i+1. For eg, if j=10 and i = 8, and window size was 3, it means the window has 3 elements - 8th, 9th, 10th
     * Hence window size (3) = j(10) - i(8) + 1
     *
     * Start with i=0 and j=0, and keep increasing j until window size is reached.
     * When the window size is reached, calculate the max sum, and then move i and j to the next position such that we slide the window.
     * As we will slide the window, we should reduce the current sum by deleting value of element at start from the current sum
     * @param array
     * @param k
     * @return
     */
    private static int solve(int [] array, int k) {
        int i = 0, j= 0;
        int max = Integer.MIN_VALUE;
        int sum = 0;
        while (j < array.length) {
            sum = sum + array[j];
            if (j - i + 1 < k) {
                j++;
            } else if (j - i + 1 == k) {
                max = Math.max(max, sum);
                sum = sum - array[i];
                i++;
                j++;
            }
        }
        return max;
    }
}
