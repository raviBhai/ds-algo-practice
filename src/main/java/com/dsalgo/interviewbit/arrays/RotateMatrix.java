package com.dsalgo.interviewbit.arrays;

import java.util.ArrayList;
import java.util.Arrays;

public class RotateMatrix {
    public static void main(String[] args) {
       /* int[][] B = {
                {1,2,3,4},
                {6,7,8,9},
                {11,12,13,14},
                {16,17,18,19}

        };

        int[][] BB = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };

        rotate(B);

        for (int i=0; i<B.length; i++) {
            System.out.println(Arrays.toString(B[i]));
        }*/


        RotateMatrix rm = new RotateMatrix();
        ArrayList<Integer> a1 = new ArrayList<Integer>(){{
            add(1);
            add(2);
            add(3);
        }};

        ArrayList<Integer> a2 = new ArrayList<Integer>(){{
            add(4);
            add(5);
            add(6);
        }};

        ArrayList<Integer> a3 = new ArrayList<Integer>(){{
            add(7);
            add(8);
            add(9);
        }};

        ArrayList<ArrayList<Integer>> a = new ArrayList<ArrayList<Integer>>() {
            {
                add(a1);
                add(a2);
                add(a3);
            }
        };

        System.out.println(a);

        rm.rotate(a);

        System.out.println(a);

    }

    public void rotate(ArrayList<ArrayList<Integer>> a) {
        if (a.size() == 0 || a.get(0).size() == 0) {
            return;
        }

        //Do Transpose
        for (int i = 0; i < a.size(); i++) {
            for (int j = i; j < a.size(); j++) {
                int temp = a.get(i).get(j);
                a.get(i).set(j, a.get(j).get(i));
                a.get(j).set(i, temp);
            }
        }

        //Reverse rows
        int rowStart = 0, colStart = 0;
        int rowLength = a.size() - 1, colLength = a.size() - 1;

        for (int i = rowStart; i <= rowLength; i++) {
            for (int j = colStart; j <= colLength; j++) {
                int rear = a.size() - 1 - j;
                if (j < rear) {
                    int temp = a.get(i).get(j);
                    a.get(i).set(j, a.get(i).get(rear));
                    a.get(i).set(rear, temp);
                } else {
                    break;
                }
            }
        }
    }

    public static void rotate(int[][] A) {
        transpose(A);

        System.out.println("\nTransposed: ");
        for (int i=0; i<A.length; i++) {
            System.out.println(Arrays.toString(A[i]));
        }

        System.out.println("\nReversed: ");
        //reverseColumns(A);
        reverseRows(A);
    }

    private static void transpose(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j < a.length; j++) {
                int temp = a[i][j];
                a[i][j] = a[j][i];
                a[j][i] = temp;
            }
        }
    }

    private static void reverseColumns(int[][] a) {
        int rowStart = 0, colStart = 0;
        int rowLength = a.length - 1, colLength = a.length - 1;

        for (int i = colStart; i <= colLength; i++) {
            for (int j = rowStart; j <= rowLength; j++) {
                int rear = a.length - 1 - j;
                if (j < rear) {
                    int temp = a[j][i];
                    a[j][i] = a[rear][i];
                    a[rear][i] = temp;
                } else {
                    break;
                }
            }
        }
    }

    private static void reverseRows(int[][] a) {
        int rowStart = 0, colStart = 0;
        int rowLength = a.length - 1, colLength = a.length - 1;

        for (int i = rowStart; i <= rowLength; i++) {
            for (int j = colStart; j <= colLength; j++) {
                int rear = a.length - 1 - j;
                if (j < rear) {
                    int temp = a[i][j];
                    a[i][j] = a[i][rear];
                    a[i][rear] = temp;
                } else {
                    break;
                }
            }
        }
    }
}
