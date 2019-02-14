package com.dsalgo.interviewbit.bitmanipulation;

public class PowerOfTwo {

    public static void main(String[] args) {
        int a = 8;

        int i = a & (a - 1);

        if (i == 0) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }

    }
}
