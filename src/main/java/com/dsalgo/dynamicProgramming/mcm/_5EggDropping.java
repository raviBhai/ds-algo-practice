package com.dsalgo.dynamicProgramming.mcm;

public class _5EggDropping {

    public static int solve(int e, int f) {
        if (e == 1) {
            return f;
        }
        if (f == 0 || f == 1) {
            return f;
        }

        int min = Integer.MAX_VALUE;
        for (int k = 1; k <= f; k++) {
            int temp = 1 + Math.max(solve(e - 1, k - 1), solve(e, f - k));
            min = Math.min(min, temp);
        }
        return min;
    }

    public static void main(String[] args) {
        System.out.println(solve(2, 10));
    }
}

class MemoizationEggDropping {
    private static int[][] t;

    public static int solve(int e, int f) {
        if (e == 1) {
            return f;
        }
        if (f == 0 || f == 1) {
            return f;
        }
        if (t[e][f] != -1) {
            return t[e][f];
        }

        int min = Integer.MAX_VALUE;
        for (int k = 1; k <= f; k++) {
            int temp = 1 + Math.max(solve(e - 1, k - 1), solve(e, f - k));
            min = Math.min(min, temp);
        }
        t[e][f] = min;
        return min;
    }

    public static void main(String[] args) {
        int e = 2, f = 10;
        t = new int[e + 1][f + 1];
        init(t);
        System.out.println(solve(e, f));
    }

    public static void init(int[][] t) {
        for (int i = 0; i < t.length; i++) {
            for (int j = 0 ; j < t[0].length; j++) {
                t[i][j] = -1;
            }
        }
    }
}

class OptimizedMemoizationEggDropping {
    private static int[][] t;

    public static int solve(int e, int f) {
        if (e == 1) {
            return f;
        }
        if (f == 0 || f == 1) {
            return f;
        }
        if (t[e][f] != -1) {
            return t[e][f];
        }

        int min = Integer.MAX_VALUE;
        for (int k = 1; k <= f; k++) {
            int low = t[e - 1][k - 1] != -1 ? t[e - 1][k - 1] : solve(e - 1, k - 1);
            t[e - 1][k - 1] = low;
            int high = t[e][f - k] != -1 ? t[e][f - k] : solve(e, f - k);
            t[e][f - k] = high;
            int temp = 1 + Math.max(low, high);
            min = Math.min(min, temp);
        }
        t[e][f] = min;
        return min;
    }

    public static void main(String[] args) {
        int e = 2, f = 10;
        t = new int[e + 1][f + 1];
        init(t);
        System.out.println(solve(e, f));
    }

    public static void init(int[][] t) {
        for (int i = 0; i < t.length; i++) {
            for (int j = 0 ; j < t[0].length; j++) {
                t[i][j] = -1;
            }
        }
    }
}