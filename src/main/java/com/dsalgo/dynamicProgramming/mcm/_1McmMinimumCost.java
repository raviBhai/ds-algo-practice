package com.dsalgo.dynamicProgramming.mcm;

public class _1McmMinimumCost {

    public static int solve(int[] arr, int i, int j) {
        if (i >= j) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (int k = i; k <= j-1; k++) {
            int temp = solve(arr, i, k)
                    + solve(arr, k+1, j)
                    + arr[i-1] * arr[k] * arr[j];

            min = Math.min(min, temp);
        }
        return min;
    }

    public static void main(String[] args) {
        int[] arr = {40, 20, 30, 10, 30};
        //int[] arr = {10, 20, 30, 40, 50};
        System.out.println(solve(arr, 1, arr.length - 1));
    }
}

class MemoizationMcmMinimumCost {

    private static int[][] t;

    public static int solve(int[] arr, int i, int j) {
        if (i >= j) {
            return 0;
        }
        if (t[i][j] != -1) {
            return t[i][j];
        }
        int min = Integer.MAX_VALUE;
        for (int k = i; k <= j-1; k++) {
            int temp = solve(arr, i, k)
                    + solve(arr, k+1, j)
                    + arr[i-1] * arr[k] * arr[j];

            min = Math.min(min, temp);
        }
        t[i][j] = min;
        return t[i][j];
    }

    public static void main(String[] args) {
        int[] arr = {40, 20, 30, 10, 30};
        //int[] arr = {10, 20, 30, 40, 50};
        t = new int[arr.length + 1][arr.length + 1];
        init(t);
        System.out.println(solve(arr, 1, arr.length - 1));
        System.out.println("");
    }

    private static void init(int[][] t) {
        for (int i = 0; i < t.length; i++) {
            for (int j = 0 ; j < t[0].length; j++) {
                t[i][j] = -1;
            }
        }
    }
}
