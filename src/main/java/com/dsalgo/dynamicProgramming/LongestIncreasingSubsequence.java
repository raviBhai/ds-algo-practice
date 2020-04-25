package com.dsalgo.dynamicProgramming;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        int arr[] = {100, 10, 22, 9, 33, 21, 50, 41, 60, 80 };
        int n = arr.length;
        System.out.println("Length of lis is " + lis(arr));
    }

    public static int lis(int[] input) {
        int[] temp = new int[input.length];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = 1;
        }

        for (int i = 1; i < input.length; i++) {
            for (int j = 0; j < i; j++) {
                if (input[j] < input[i] && temp[i] < temp[j] + 1) {
                    temp[i] = temp[j] + 1;
                }
            }
        }

        int maxLis = Integer.MIN_VALUE;
        Map<Integer, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < temp.length; i++) {
            if (maxLis <= temp[i]) {
                maxLis = temp[i];
                map.put(maxLis, input[i]);
                //System.out.println(input[i]);       //Printing the increasing sub-sequence
            }
        }

        System.out.println(map);
        return maxLis;
    }
}
