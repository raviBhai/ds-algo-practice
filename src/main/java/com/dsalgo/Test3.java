package com.dsalgo;

import java.util.Arrays;

public class Test3 {
    public static void main(String[] args) {
        int[][] callsTimes = new int[3][2];
        callsTimes[0][0] = 1481122000;
        callsTimes[0][1] = 1481122020;

        callsTimes[1][0] = 1481122000;
        callsTimes[1][1] = 1481122040;

        callsTimes[2][0] = 1481122030;
        callsTimes[2][1] = 1481122035;


        System.out.println(howManyAgentsToAdd(1, callsTimes));
    }

    static int howManyAgentsToAdd(int noOfCurrentAgents, int[][] callsTimes) {
        /*
         * Write your code here.
         */


        int length = callsTimes.length;
        int[] start = new int[length];
        int[] end = new int[length];

        for (int i = 0; i < length; i++) {
            start[i] = callsTimes[i][0];
            end[i] = callsTimes[i][1];
        }

        Arrays.sort(start);
        Arrays.sort(end);


        int tempExecutivesNeeded = 1;
        int totalExecutivesNeeded = 1;
        int i = 1, j = 0;

        while (i < length && j < length) {
            if (start[i] <= end[j]) {
                tempExecutivesNeeded++;
                i++;

                if (tempExecutivesNeeded > totalExecutivesNeeded) {
                    totalExecutivesNeeded = tempExecutivesNeeded;
                }
            } else {
                tempExecutivesNeeded--;
                j++;
            }
        }

        if (noOfCurrentAgents >= totalExecutivesNeeded) {
            return 0;
        } else {
            return totalExecutivesNeeded - noOfCurrentAgents;
        }

    }
}
