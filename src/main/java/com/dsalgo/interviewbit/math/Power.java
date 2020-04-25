package com.dsalgo.interviewbit.math;

public class Power {

    public static void main(String[] args) {
        System.out.println(power(2,-2));
    }

    private static double power(int a, int b) {
        if (b == 0) {
            return 1;
        }

        double tmp = power(a, b / 2);

        if (b % 2 == 0) {
            return tmp * tmp;
        } else {
            if (b < 0) {
                return tmp * tmp / a;
            }
            return a * tmp * tmp;
        }
    }
}
