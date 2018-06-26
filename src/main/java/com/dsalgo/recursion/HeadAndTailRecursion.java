package com.dsalgo.recursion;

public class HeadAndTailRecursion {
    public static void main(String[] args) {
        System.out.println("head");
        head(9);
        System.out.println("tail");
        tail(9);
    }

    static void tail(int n) {
        if (n == 1) {
            return;
        }
        System.out.println(n);
        tail(n - 1);
    }

    static void head(int n) {
        if (n == 1) {
            return;
        }
        head(n - 1);
        System.out.println(n);
    }
}
