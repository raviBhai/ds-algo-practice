package com.dsalgo.greedy;

import java.util.Arrays;

/*
Given arrival and departure time of trains.
start[i] and end[i] denotes arrival and departure time of ith train.

Find the minimum number of platforms required to station all the trains.

Algorithm -

If I were standing outside the station to observe the trains coming and going out of the station,
I can count the number of platforms required.

        int[] start = {900, 945, 955, 1100, 1500, 1800};
        int[] end = {920, 1200, 1130, 1150, 1900, 2000};

        Arrival - A
        Departure - D

I will be able to observe the trains as per the current time that is in my watch.
It means that, the first train that I observe is at 900 (A)
Hence platform count = 1

Next time is 920 (D)
Train leaves, platform count = 0

Next time is 945 (A)
platform count = 1

Next time is 955 (A)
platform count = 2

Next time is 1100 (A)
platform count = 3

Next time is 1130 (D)
Train leaves, hence platform count = 2

Next time is 1150 (D)
platform count = 1

Next time is 1200 (D)
platform count = 0

Next time is 1500 (A)
platform count = 1

Next time is 1900 (D)
platform count = 0

Next time is 1800 (A)
platform count = 1

Next time is 2000 (D)
platform count = 0

Max platform count = 3
Return 3

If we sort all the elements of start and end in one combined array, it will take extra space.
To avoid extra space, we can sort start and end independently, and then iterate over them to count the required platforms.

 */
public class _7_MinimumNumberOfPlatforms {

    private static int solve(int[] start, int[] end) {
        Arrays.sort(start);
        Arrays.sort(end);
        int i = 0, j = 0;
        int currentCount = 0;
        int maxCount = 0;
        while (i < start.length && j < end.length) {
            if (start[i] < end[j]) {
                i++;
                currentCount++;
            } else {
                j++;
                currentCount--;
            }
            maxCount = Math.max(maxCount, currentCount);
        }
        return maxCount;
    }

    public static void main(String[] args) {
        int[] start = {900, 945, 955, 1100, 1500, 1800};
        int[] end = {920, 1200, 1130, 1150, 1900, 2000};
        System.out.println(solve(start, end));
    }
}
