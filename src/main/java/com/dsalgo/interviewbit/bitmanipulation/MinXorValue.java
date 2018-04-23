package com.dsalgo.interviewbit.bitmanipulation;

import java.util.ArrayList;
import java.util.Collections;

public class MinXorValue {
    public static void main(String[] args) {
        MinXorValue mn = new MinXorValue();
        ArrayList<Integer> A = new ArrayList<Integer>(){{
            add(0);
            add(4);
            add(7);
            add(9);
        }};
        System.out.println(mn.findMinXor(A));
    }

    public int findMinXor(ArrayList<Integer> A) {
        Collections.sort(A);
        int min = A.get(0) ^ A.get(1);

        for (int i = 1; i < A.size() - 1; i++) {
            int tmp = A.get(i) ^ A.get(i + 1);
            if (min > tmp) {
                min = tmp;
            }
        }

        return min;
    }
}
