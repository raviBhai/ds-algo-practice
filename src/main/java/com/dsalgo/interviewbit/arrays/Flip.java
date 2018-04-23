package com.dsalgo.interviewbit.arrays;

import java.util.Arrays;

public class Flip {
    public static void main(String[] args) {
        String A = "000";
        int[] res = flip_1(A);
        System.out.println(Arrays.toString(res));
    }

    public static int[] flip_2(String A) {
        int left = 0;
        int right = A.length() - 1;
        int leftPtr = 0, rightPtr = 0;

        char leftChar, rightChar;
        int leftDigit, rightDigit;
        char[] arr = A.toCharArray();
        while (true) {

            while ((leftPtr <= right) && (arr[leftPtr] - '0' != 0)) {
                leftPtr++;
            }

            while ((rightPtr >= left) && (arr[rightPtr] - '0' != 0)) {
                rightPtr++;
            }
        }

        //return null;
    }

    public static int[] flip_1(String A) {
        int[] res = {};
        int start = 0, end = 0;
        int currentStart = 0, currentEnd = 0;

        char currentChar;
        int currentDigit;
        int maxZerosSoFar = 0;
        int maxZerosEndingHere = 0;
        for (int i = 0; i < A.length(); i++) {
            currentChar = A.charAt(i);
            currentDigit = currentChar - '0';

            if (currentDigit == 0) {
                if (maxZerosEndingHere == 0) {
                    currentStart = i+1;
                    currentEnd = i+1;
                } else {
                    currentEnd += 1;
                }
                if (start == 0 && end == 0) {
                    start = currentStart;
                    end = currentEnd;
                } else if ((currentEnd - currentStart) > (end - start)) {
                    start = currentStart;
                    end = currentEnd;
                }
                maxZerosEndingHere += 1;
                if (maxZerosSoFar < maxZerosEndingHere) {
                    maxZerosSoFar = maxZerosEndingHere;
                }
            }

            if (currentDigit == 1) {
                maxZerosEndingHere = 0;
            }
        }

        if (maxZerosSoFar == 0) {
            return res;
        }
        res = new int[2];
        res[0] = start;
        res[1] = end;
        return res;
    }
}
