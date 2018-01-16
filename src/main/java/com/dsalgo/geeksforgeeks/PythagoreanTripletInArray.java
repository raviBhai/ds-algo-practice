package com.dsalgo.geeksforgeeks;

import java.util.Arrays;

public class PythagoreanTripletInArray {
    public static void main(String[] args) {
        int[] in = {3, 1, 4, 6, 5};
        boolean res = isTripletPresent(in);
        System.out.println(res);
    }

    private static boolean isTripletPresent(int[] in) {
        Arrays.sort(in);
        for (int i = 0; i<in.length; i++) {
            in[i] = in[i] * in[i];
        }
        boolean isPresent = false;
        int length = in.length;
        for (int i = length-1; i >= 2; i--) {
            int sum = in[i];
            int j = i-1;
            isPresent = findSum(0, j, sum, in);
            if (isPresent) {
                return true;
            }
        }
        return false;
    }

    private static boolean findSum(int start, int end, int sum, int[] in) {
        while (true) {
            if (start >= end) {
                return false;
            } else if (in[start] + in[end] == sum) {
                return true;
            } else if (in[start] + in[end] > sum) {
                end--;
            } else if (in[start] + in[end] < sum) {
                start++;
            }
        }
    }
}
