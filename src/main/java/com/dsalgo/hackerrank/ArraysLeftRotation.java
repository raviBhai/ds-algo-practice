package com.dsalgo.hackerrank;

import java.util.Scanner;

public class ArraysLeftRotation {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int a[] = new int[n];
        for(int i=0; i < n; i++){
            a[i] = in.nextInt();
        }
        int temp[] = new int[k];
        for(int i=0; i < n; i++){
            if (i < k) {
                temp[i] = a[i];
            } else {
                a[i - k] = a[i];
            }
        }

        for (int j = 0; j < temp.length; j++) {
            a[n-k+j] = temp[j];
        }

        for (int i = 0; i < n; i++) {
            System.out.print(a[i]);
            if (i < n-1) {
                System.out.print(" ");
            }
        }
    }
}
