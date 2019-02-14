package com.dsalgo.onlinetests;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MaxesNum {

    static int[] counts(int[] nums, int[] maxes) {
        int[] result = new int[maxes.length];
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0 ; i < maxes.length; i++) {
            indexMap.put(i, maxes[i]);
        }
        Arrays.sort(nums);
        Arrays.sort(maxes);

        Map<Integer, Integer> countMap = new HashMap<>();
        int j = 0, count = 0;

        for (int i = 0; i < maxes.length; i++) {
            while (j < nums.length && maxes[i] >= nums[j]) {
                count++;
                j++;
            }
            countMap.put(maxes[i], count);
        }

        for (int i = 0; i < result.length; i++) {
            result[i] = countMap.get(indexMap.get(i));
        }

        return result;
    }

    static int[] counts_2(int[] nums, int[] maxes) {
        int len = maxes.length;
        int[] result = new int[len];
        Arrays.sort(nums);
        int max;
        for(int out = 0; out < len; out++) {
            int count = 0;
            max = maxes[out];

            for(int in = 0; in < nums.length; in++) {
                if(max >= nums[in]) {
                    count++;
                } else {
                    break;
                }
            }
            result[out] = count;
        }


        return result;
    }

    static int[] counts_1(int[] nums, int[] maxes) {
        int len = maxes.length;
        int[] result = new int[len];

        int max;
        for(int out = 0; out < len; out++) {
            int count = 0;
            max = maxes[out];
            for(int in = 0; in < nums.length; in++) {
                if(max >= nums[in]) {
                    count++;
                }
            }
            result[out] = count;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1,4,2,4};
        int[] maxes = {5,3};
        int[] res = counts(nums, maxes);

        System.out.println(Arrays.toString(res));
    }
}
