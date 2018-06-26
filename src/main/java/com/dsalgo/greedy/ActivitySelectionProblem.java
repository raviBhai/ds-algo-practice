package com.dsalgo.greedy;

import java.util.Arrays;
import java.util.Scanner;

public class ActivitySelectionProblem {

    public static void main(String[] args) {
        int s[] =  {1, 3, 0, 5, 8, 5};
        int f[] =  {2, 4, 6, 7, 9, 9};
        int n = s.length;

        printMaxActivities(s, f);
    }

    private static void printMaxActivities(int[] s, int[] f) {
        int n = s.length;
        int i = 0;
        System.out.println(i);

        for (int j = 1; j < n; j++) {
            if (s[j] >= f[i]) {
                System.out.println(j);
                i = j;
            }
        }
    }
}
