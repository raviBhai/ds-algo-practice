package com.dsalgo.interviewbit.twoPointers;

import java.util.ArrayList;
import java.util.Arrays;

public class ClosestThreeSum {
    public static void main(String[] args) {
        ClosestThreeSum cts = new ClosestThreeSum();

        ArrayList<Integer> A = new ArrayList<>();
        A.add(-1);
        A.add(2);
        A.add(1);
        A.add(-4);
        System.out.println(cts.threeSumClosest(A, -2));


    }

    public int threeSumClosest(ArrayList<Integer> A, int B) {
        Integer[] arr = new Integer[A.size()];
        arr = (Integer[]) A.toArray(arr);
        Arrays.sort(arr);

        int diff = 0;
        int sec, third;
        int intermediateDiff;
        int result = 0;
        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length - 2; i++) {
            diff = B - arr[i];
            sec = i+1;
            third = arr.length - 1;
            while (sec < third) {
                intermediateDiff = Math.abs(diff - (arr[sec] + arr[third]));
                if (intermediateDiff == 0) {
                    result = arr[i] + arr[sec] + arr[third];
                    minDiff = intermediateDiff;
                    break;
                }
                if (intermediateDiff < minDiff) {
                    minDiff = intermediateDiff;
                    result = arr[i] + arr[sec] + arr[third];
                }
                if (arr[sec] + arr[third] > diff) {
                    third--;
                } else if (arr[sec] + arr[third] < diff) {
                    sec++;
                }
            }
        }


        return result;
    }
}
