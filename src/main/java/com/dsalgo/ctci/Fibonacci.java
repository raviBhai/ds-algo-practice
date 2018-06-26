package com.dsalgo.ctci;

public class Fibonacci {
    public static void main(String[] args) {
/*
        System.out.println(getFibonacciNumberUsingRecursionAt(1));
        System.out.println(getFibonacciNumberUsingRecursionAt(2));
        System.out.println(getFibonacciNumberUsingRecursionAt(3));
        System.out.println(getFibonacciNumberUsingRecursionAt(4));
*/

        System.out.println(getFibonacciNumberUsingRecursionAt(8));

/*
        System.out.println(getFibonacciNumberUsingRecursionAt(6));
        System.out.println(getFibonacciNumberUsingRecursionAt(7));
        System.out.println(getFibonacciNumberUsingRecursionAt(8));

        System.out.println(getFibonacciNumberUsingMemoizationAt(7));
*/
    }

    public static int getFibonacciNumberUsingRecursionAt(int n) {
        if (n == 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }
        return getFibonacciNumberUsingRecursionAt(n-1) + getFibonacciNumberUsingRecursionAt(n-2);
    }

    public static int getFibonacciNumberUsingMemoizationAt(int n) {
        int[] temp = new int[n+1];
        return getFibonacciNumberUsingMemoizationAt(n, temp);
    }

    public static int getFibonacciNumberUsingMemoizationAt(int n, int[] temp) {
        if (n == 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }
        if (temp[n] > 0) {
            return temp[n];
        }
        temp[n] = temp[n - 1] + temp[n - 2];
        return getFibonacciNumberUsingMemoizationAt(n - 1, temp) + getFibonacciNumberUsingMemoizationAt(n - 2, temp);
    }
}
