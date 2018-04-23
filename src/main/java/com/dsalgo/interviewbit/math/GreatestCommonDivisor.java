package com.dsalgo.interviewbit.math;

public class GreatestCommonDivisor {
    public static void main(String[] args) {
        GreatestCommonDivisor gcd = new GreatestCommonDivisor();
        System.out.println(gcd.gcd(2, 0));
    }

    public int gcd(int A, int B) {
        if (A == 0 || B == 0) {
            return A > B ? A : B;
        }

        int gcd = 1;
        int smaller = A < B ? A : B;
        for (int i = 2; i <= smaller; i++) {
            if (A % i == 0 && B % i == 0) {
                gcd = i;
            }
        }
        return gcd;
    }
}
