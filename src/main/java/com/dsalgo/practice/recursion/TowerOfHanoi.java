package com.dsalgo.practice.recursion;

public class  TowerOfHanoi {
    static int nDisks = 3;
    public static void main(String[] args) {
        doTower(nDisks, "A", "B", "C");
    }

    public static void doTower(int n, String from, String inter, String to) {
        if (n == 1) {
            System.out.println("Disk " + n + " From : " + from + " To : " + to);
            return;
        }

        doTower(n - 1, from, to, inter);
        System.out.println("Disk " + n + " From : " + from + " To : " + to);
        doTower(n - 1, inter, from, to);
    }
}
