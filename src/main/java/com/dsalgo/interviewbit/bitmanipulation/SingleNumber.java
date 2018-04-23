package com.dsalgo.interviewbit.bitmanipulation;

import java.util.ArrayList;
import java.util.List;

public class SingleNumber {
    public static void main(String[] args) {
        SingleNumber sn = new SingleNumber();
        List<Integer> A = new ArrayList<>();
        A.add(1);
        A.add(2);
        A.add(2);
        A.add(3);
        A.add(1);
        System.out.println(sn.singleNumber(A));
    }

    public int singleNumber(final List<Integer> A) {
        int single = A.get(0);

        for (int i = 1; i < A.size(); i++) {
            single = single ^ A.get(i);
        }

        return single;
    }
}
