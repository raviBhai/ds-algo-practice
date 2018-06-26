package com.dsalgo.dynamicProgramming;

import java.util.HashMap;
import java.util.Map;

public class BinomialCoefficient {
    public int[][] temp;
    public Map<String, Integer> map = new HashMap<>();

    public static void main(String[] args) {
        BinomialCoefficient binomialCoefficient = new BinomialCoefficient();
        System.out.println(binomialCoefficient.solve(5,2));
        System.out.println(binomialCoefficient.solve_dp_1(5,2));
        System.out.println(binomialCoefficient.solve_dp_2(5,2));
    }

    public int solve(int n, int k) {
        if (k == 0 || k == n) {
            return 1;
        }

        return solve(n - 1, k) + solve(n - 1, k - 1);
    }

    public int solve_dp_1(int n, int k) {
        temp = new int[n+1][k+1];
        for (int i =0; i <= n; i++) {
            for (int j=0; j <= k; j++) {
                temp[i][j] = -1;
            }
        }
        return solve_dp_1_helper(n, k);
    }

    public int solve_dp_1_helper(int n, int k) {
        if (temp[n][k] == -1) {
            if (k == 0 || k == n) {
                temp[n][k] = 1;
            } else {
                temp[n][k] = solve_dp_1_helper(n-1, k) + solve_dp_1_helper(n-1, k-1);
            }
        }
        return temp[n][k];
    }

    public int solve_dp_2(int n, int k) {
        if (!map.containsKey(getKey(n, k))) {
            if (k == 0 || k == n) {
                map.put(getKey(n, k), 1);
            } else {
                map.put(getKey(n - 1, k), solve_dp_2(n - 1, k));
                map.put(getKey(n - 1, k - 1), solve_dp_2(n - 1, k - 1));
                map.put(getKey(n, k), map.get(getKey(n - 1, k)) + map.get(getKey(n - 1, k - 1)));
            }
        }
        return map.get(getKey(n, k));
    }

    private String getKey(int n, int k) {
        return n + "," + k;
    }
}
