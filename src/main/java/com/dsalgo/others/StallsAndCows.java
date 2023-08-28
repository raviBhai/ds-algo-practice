package com.dsalgo.others;

import java.util.Arrays;

// https://www.geeksforgeeks.org/assign-stalls-to-k-cows-to-maximize-the-minimum-distance-between-them/
public class StallsAndCows {

    public static void main(String[] args) {

    }

    int solve(int[] stalls, int cows) {
        int left = 1;
        Arrays.sort(stalls);
        int right = stalls[stalls.length - 1];

        int result = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (isPossible(cows, mid, stalls)) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

    boolean isPossible(int cows, int dist, int[] stalls) {
        int lastPlace = -1;
        for (int i = 0; i < stalls.length; i++) {
            if (i == 0) {
                cows--;
                lastPlace = i;
            } else if (stalls[i] - stalls[lastPlace] >= dist) {
                cows--;
                lastPlace = i;
            }
            if (cows == 0) {
                return true;
            }
        }
        return false;
    }
}
