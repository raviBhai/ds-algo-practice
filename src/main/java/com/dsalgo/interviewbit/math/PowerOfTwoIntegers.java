package com.dsalgo.interviewbit.math;

public class PowerOfTwoIntegers {
    public static void main(String[] args) {
        PowerOfTwoIntegers p = new PowerOfTwoIntegers();
        System.out.println(p.isPower(8));
    }

    public int isPower(int A) {

        if (A == 1) {
            return 1;
        }

        for (int i = 2; i*i <= A; i++) {
            int temp = A;
            while (temp % i == 0) {
                temp = temp / i;
            }
            if (temp == 1) {
                return 1;
            }
        }
        return 0;
    }
}
