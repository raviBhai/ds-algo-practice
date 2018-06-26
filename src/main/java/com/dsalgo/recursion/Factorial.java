package com.dsalgo.recursion;

public class Factorial {
    public static void main(String[] args) {
       // System.out.println(factorial(5));
        System.out.println(goodFactorial(5));
    }

    static int factorial(int n) {
        if (n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    static int goodFactorial(int n) {
        return factorialHelper(1, n);
    }

    static int factorialHelper(int accumulator, int n) {
        if (n == 1) {
            return accumulator;
        }
        return factorialHelper(accumulator * n, n - 1);
    }
}
