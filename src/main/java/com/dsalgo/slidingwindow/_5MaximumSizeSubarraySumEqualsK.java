package com.dsalgo.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class _5MaximumSizeSubarraySumEqualsK {

}


class WhenAllNumbersAreGreaterThanZero {
    private static int solve(int[] arr, int k) {
        int i = 0, j = 0;
        int sum = 0;
        int maxLen = Integer.MIN_VALUE;

        while (j < arr.length) {
            sum = sum + arr[j];

            if (sum < k) {
                j++;
            } else if (sum == k) {
                maxLen = Math.max(maxLen, j - i + 1);
                j++;
            } else if (sum > k) {
                while (sum > k) {
                    sum = sum - arr[i];
                    i++;
                }
                if (sum == k) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
                j++;
            }
        }
        return maxLen;
    }
    public static void main(String[] args) {
        int[] arr = {4, 1, 1, 1, 2, 3, 5};
        System.out.println(solve(arr, 5));

        int[] arr1 = {1,2,3,4};
        System.out.println(solve(arr1, 9));
    }
}

class WhenNumbersCanBeLessThanOrEqualToZero {
    private static int solve(int[] arr, int k) {

        int sum = 0;

        // Initialization of the map with (0,-1) is required for the case when the answer window starts from 0th index
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        int maxLen = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            sum = sum + arr[i];

            if (map.containsKey(sum - k)) {
                maxLen = Math.max(maxLen, i - map.get(sum - k));
            }

            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }

        return maxLen;
    }

    public static void main(String[] args) {
        int[] arr = {4, 1, 1, -1, 2, 3, 5};
        System.out.println(solve(arr, 5));
    }
}