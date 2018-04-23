package com.dsalgo.interviewbit.math;

public class TrailingZeroesInFactorial {
    public static void main(String[] args) {
        TrailingZeroesInFactorial tzf = new TrailingZeroesInFactorial();
        System.out.println(tzf.trailingZeroes(125));
    }

    public int trailingZeroes(int A) {
        int mulOf5 = A/5;
        int mulOf2 = A/2;
        int powOf5, powOf2;

        for (int i = 2; i <= A; i++) {
            powOf5 = (int)Math.pow(5, i);
            if (powOf5 <= A) {
                mulOf5 += A / powOf5;
            } else {
                break;
            }
        }

        for (int i = 2; i <= A; i++) {
            powOf2 = (int)Math.pow(2, i);
            if (powOf2 <= A) {
                mulOf2 += A / powOf2;
            } else {
                break;
            }
        }

        return mulOf5 < mulOf2 ? mulOf5 : mulOf2;
    }
}
