package com.dsalgo.ctci;

public class Factorial {
    public static void main(String[] args) {
        long fact = factorial(14);
        System.out.println(fact);
    }

    private static long factorial(long num) {
        if (num == 0 || num == 1) {
            return 1;
        }
        return num * factorial(num - 1);
    }
}
