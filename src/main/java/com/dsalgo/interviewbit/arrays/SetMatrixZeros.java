package com.dsalgo.interviewbit.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SetMatrixZeros {
    public static void main(String[] args) {
        SetMatrixZeros sz = new SetMatrixZeros();


        ArrayList<Integer> a1 = new ArrayList<Integer>(){{
            add(0);
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

        //sz.setZeroes(a);
        sz.setZeroes(a);

        System.out.println(a);



        int[][] B = {
                {0,2,3},
                {4,5,6},
                {7,8,9}
        };

        sz.setZeroes2(B);

        for (int i = 0; i < B.length; i++) {
            System.out.println(Arrays.toString(B[i]));
        }
    }

    public void setZeroes(ArrayList<ArrayList<Integer>> a) {

        if (a == null || a.isEmpty() || a.get(0) == null || a.get(0).isEmpty()) {
            return;
        }

        List<Integer> rows = new ArrayList<>();
        List<Integer> cols = new ArrayList<>();

        for (int i = 0; i < a.size(); i++) {
            for(int j = 0; j < a.get(i).size(); j++) {
                if (a.get(i).get(j) == 0) {
                    rows.add(i);
                    cols.add(j);
                }
            }
        }

        for (int i = 0; i < a.size(); i++) {
            for(int j = 0; j < a.get(i).size(); j++) {
                if (rows.contains(i) || cols.contains(j)) {
                    a.get(i).set(j, 0);
                }
            }
        }
    }

    public void setZeroes2(int[][] a) {
        boolean isFirstRowZero = false;
        boolean isFirstColZero = false;

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                if (i == 0 && a[i][j] == 0) {
                    isFirstRowZero = true;
                }

                if (j == 0 && a[i][j] == 0) {
                    isFirstColZero = true;
                }

                if (a[i][j] == 0) {
                    a[0][j] = 0;
                    a[i][0] = 0;
                }
            }
        }
        for (int i = 1; i < a.length; i++) {
            for (int j = 1; j < a[0].length; j++) {
                if (a[0][j] == 0 || a[i][0] == 0) {
                    a[i][j] = 0;
                }
            }
        }
        if (isFirstRowZero) {
            for (int i = 0; i < a[0].length; i++) {
                a[0][i] = 0;
            }
        }
        if (isFirstColZero) {
            for (int i = 0; i < a.length; i++) {
                a[i][0] = 0;
            }
        }

    }
}
