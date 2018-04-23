package com.dsalgo.interviewbit.binarysearch;

public class PowerFunction {
    public static void main(String[] args) {
        PowerFunction pf = new PowerFunction();
        //System.out.println(pf.pow(2,13,4));
        //System.out.println(Math.pow(2, 13));
        //System.out.println(pf.pow(2, 3, 3));
        //System.out.println(pf.pow(-2, 4, 3));
        //System.out.println(pf.pow(71045970, 41535484, 64735492));
        //System.out.println(pf.rem(71045970, 41535484, 64735492));
        //System.out.println(pf.rem(23896711, 66047642, 61157243));
        //System.out.println(pf.rem(7, 3, 4));
        System.out.println(pf.power(2, 3));
    }

    public int pow(int x, int n, int d) {
        int rem = (int) rem(x, n, d);
        if (rem < 0) {
            return rem + d;
        }
        return rem;
    }

    public long pow(int a, int b) {
        long result = 0;
        if (b == 0) {
            return 1;
        }
        if (b == 1) {
            //result = a;
            return a;
        }

        int counter = 1;
        while (counter <= b) {
            if (counter == 1) {
                result = a;
            } else {
                result *= result;
            }
            counter *= 2;
        }
        counter /= 2;
        int diff = b - counter;
        long tempRes = pow(a, diff);
        return result * tempRes;
    }

    public long rem(long a, long b, int c) {
        long r = 0;
        if (a == 0) {
            return 0;
        }
        if (b == 0) {
            return 1;
        }
        if (b == 1) {
            return a;
        }
        r = a % c;

        if (b % 2 == 0) {
            r = rem(r * r, b / 2, c);
        } else {
            r = rem(r * r, b / 2, c) * r;
        }
        //r = (r % c) * r;
        //r = (r * r) % c;
        r = r  % c;
        return r;
    }

    public int power(int x,  int y) {
        if (y == 0)
            return 1;
        else if (y % 2 == 0)
            return power(x, y / 2) * power(x, y / 2);
        else
            return x * power(x, y / 2) * power(x, y / 2);
    }
}
