package com.dsalgo.interviewbit.binarysearch;

public class SquareRoot {
    public static void main(String[] args) {
        SquareRoot sq = new SquareRoot();
        System.out.println(sq.sqrt(2147483647));
    }

    public int sqrt(int a) {
        if (a == 0) {
            return 0;
        }
        long temp = a;
        while (true) {
            temp = temp / 2;
            if (temp * temp > a) {
                continue;
            } else {
                break;
            }
        }

        long res = 1;
        for (long i = temp; i*i <= a; i++) {
            res = i;
        }
        return (int)res;
    }
}
