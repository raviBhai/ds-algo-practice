package com.dsalgo;

public class Test1 {
    public static void main(String[] args) {
        System.out.println(triangle(100, 90, 200 ));
    }

    static int triangle(int a, int b, int c) {
        /*
         * Write your code here.
         */

        if (a <= 0 || b <= 0 || c <= 0) {
            return 0;
        }

        if ((a + b > c) && (a + c > b) && (b + c > a)) {
            return checkEquilateral(a, b, c);
        }

        return 0;
    }

    static int checkEquilateral(int a, int b, int c) {
        if (a == b && b == c) {
            return 1;
        } else {
            return 2;
        }
    }
}
