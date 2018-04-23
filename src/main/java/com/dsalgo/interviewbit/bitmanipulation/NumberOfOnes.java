package com.dsalgo.interviewbit.bitmanipulation;

public class NumberOfOnes {

    public static void main(String[] args) {
        NumberOfOnes nm = new NumberOfOnes();
        System.out.println(nm.numSetBits(4294967295l));
        System.out.println(nm.numSetBits(3l));
    }

    public int numSetBits(long a) {
        int count = 0;

        while (a != 0) {
            a = a & (a - 1);
            count++;
        }

        return count;
    }
}
