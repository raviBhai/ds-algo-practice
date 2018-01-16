package com.dsalgo.ctci;

public class MagicIndex {
    public static void main(String[] args) {
        int[] a = {0,1,5,7,9,10};
        System.out.println(getMagicIndex(a));
    }

    public static int getMagicIndex(int[] a) {
        int lower = 0;
        int upper = a.length - 1;
        int current;

        while (true) {
            current = (lower + upper) / 2;
            if (a[current] == current) {
                return current;
            } else if (lower > upper) {
                return -1;
            } else {
                if (a[current] > current) {
                    upper = current - 1;
                } else {
                    lower = current + 1;
                }
            }
        }
    }
}
