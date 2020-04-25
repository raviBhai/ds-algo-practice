package com.dsalgo.practice.sorting;

import java.util.Arrays;

public class MergeSort {
    static int[] data = {5,4,3,2,1};
    public static void main(String[] args) {
        sort(data);
        System.out.println(Arrays.toString(data));
    }

    public static void sort(int[] data) {
        int[] workspace = new int[data.length];
        recMergeSort(workspace, 0, data.length - 1, "main");
    }

    private static void recMergeSort(int[] workspace, int lower, int upper, String flow) {
        System.out.println("Lower: " + lower + " :: Upper: " + upper + " Flow: " + flow);
        if (lower == upper) {
            return;
        } else {
            int mid = (lower + upper) / 2;
            recMergeSort(workspace, lower, mid, "left");
            recMergeSort(workspace, mid + 1, upper, "right");
            merge(workspace, lower, mid + 1, upper);
        }
    }

    private static void merge(int[] workspace, int leftStart, int rightStart, int rightEnd) {
        int leftEnd = rightStart - 1;
        int j = 0;
        int nElems = rightEnd - leftStart + 1;
        int lower = leftStart;

        System.out.println("LeftStart: " + leftStart + " :: LeftEnd: " + leftEnd);
        System.out.println("RightStart: " + rightStart + " :: RightEnd: " + rightEnd);

        while (leftStart <= leftEnd && rightStart <= rightEnd) {
            if (data[leftStart] < data[rightStart]) {
                workspace[j++] = data[leftStart++];
            } else {
                workspace[j++] = data[rightStart++];
            }
        }

        while (leftStart <= leftEnd) {
            workspace[j++] = data[leftStart++];
        }

        while (rightStart <= rightEnd) {
            workspace[j++] = data[rightStart++];
        }

        for (int i = 0; i < nElems; i++) {
            data[i + lower] = workspace[i];
        }
    }
}

//Minimum number of platforms required for a train uses the merge() function above.
// It needs only the first while loop. 2nd and 3rd while loops are not required, because
// we only have to traverse till the end of first array, which holds arrival time of all trains.
// Once we are done traversing the arrival times, we have figured out the maximum platforms required.