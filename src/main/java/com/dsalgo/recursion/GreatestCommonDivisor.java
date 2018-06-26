package com.dsalgo.recursion;

public class GreatestCommonDivisor {
    public static void main(String[] args) {
        System.out.println(gcd(54, 36));
        System.out.println(gcd_recursion(54, 36));
    }


    //Euclidean algorithm
    static int gcd(int n1, int n2) {
        int temp;
        while (n2 != 0) {
            temp = n2;
            n2 = n1 % n2;
            n1 = temp;
        }
        return n1;
    }

    static int gcd_recursion(int n1, int n2) {
        if (n2 == 0) {
            return n1;
        }
        return gcd_recursion(n2, n1 % n2);
    }
}
