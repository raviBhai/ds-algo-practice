package com.dsalgo.dynamicProgramming;

public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        int arr[] = { 10, 22, 9, 33, 21, 50, 41, 60, 80 };
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
        for (int i = 0; i < temp.length; i++) {
            if (maxLis < temp[i]) {
                maxLis = temp[i];
                System.out.println(input[i]);       //Printing the increasing sub-sequence
            }
        }

        return maxLis;
    }
}
