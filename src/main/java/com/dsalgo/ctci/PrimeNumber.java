package com.dsalgo.ctci;

public class PrimeNumber {
    public static void main(String[] args) {
        boolean isPrime = isPrimeNumber(13);
        System.out.println(isPrime);
    }

    private static boolean isPrimeNumber(int input) {
        for (int i = 2; i*i <= input; i++) {
            if (input % i == 0) {
                return false;
            }
        }
        return true;
    }
}
