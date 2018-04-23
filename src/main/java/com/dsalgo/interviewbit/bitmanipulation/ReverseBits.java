package com.dsalgo.interviewbit.bitmanipulation;

public class ReverseBits {
    public static void main(String[] args) {
        ReverseBits rb = new ReverseBits();
        System.out.println(rb.reverse(4294967295l));
    }

    public long reverse(long a) {
        long rev = 0;
        long l;
        for (int i = Integer.SIZE-1; i >=0; i--) {
            l = a & (1l << i);
            if (l == (1l << i)) {
                rev = rev + (1l << (Integer.SIZE-1 - i));
            }
        }
        return rev;
    }
}
