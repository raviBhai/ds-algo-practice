package com.dsalgo.dynamicProgramming;

import java.util.HashMap;
import java.util.Map;

public class Fibonacci {
    public static void main(String[] args) {
        System.out.println(fib_recursion(8));
        System.out.println(fib_dp_memoization(8));
        System.out.println(fib_dp_tabulation(8));
        System.out.println(fib_dp_2(8));
        System.out.println(fib_computation(8));
    }

    static int fib_recursion(int n) {
        if (n == 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }
        return fib_recursion(n - 1) + fib_recursion(n - 2);
    }

    static int[] temp = new int[100];
    static int fib_dp_memoization(int n) {
        for (int i = 0; i < temp.length; i++) {
            temp[i] = -1;
        }
        return fib_dp_helper(n);
    }


    static int fib_dp_helper(int n) {
        if (temp[n] == -1) {
            if (n <= 1) {
                temp[n] = n;
            } else {
                temp[n] = fib_dp_helper(n - 1) + fib_dp_helper(n - 2);
            }
        }
        return temp[n];
    }


    static int fib_dp_tabulation(int n) {
        int[] f = new int[n + 1];
        f[0] = 0;
        f[1] = 1;
        for (int i = 2; i <= n; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f[n];
    }

    static int fib_dp_2(int n) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        map.put(1, 1);
        return fib_dp_2_helper(n, map);
    }

    static int fib_dp_2_helper(int n, Map<Integer, Integer> map) {
        if (map.containsKey(n)) {
            return map.get(n);
        }
        map.put(n - 1, fib_dp_2_helper(n - 1, map));
        map.put(n - 2, fib_dp_2_helper(n - 2, map));
        map.put(n, map.get(n - 1) + map.get(n - 2));
        return map.get(n);
    }

    static int fib_computation(int n) {
        int a = 0, b = 1, c = 0;
        while (n > 1) {
            c = a + b;
            a = b;
            b = c;
            n--;
        }
        return c;
    }
}
