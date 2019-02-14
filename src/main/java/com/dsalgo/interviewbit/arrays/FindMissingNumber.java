package com.dsalgo.interviewbit.arrays;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class FindMissingNumber {

    public static void main(String[] args) {
        FindMissingNumber fm = new FindMissingNumber();
        ArrayList<Integer> A = new ArrayList<Integer>() {{
            add(3);
            add(4);
            add(5);
            //add(1);
        }};

        System.out.println(fm.firstMissingPositive(A));

    }


    public int firstMissingPositive(ArrayList<Integer> A) {

        if (A == null || A.isEmpty()) {
            return 1;
        }

        Set<Integer> set = new HashSet<>();
        for (Integer i : A) {
            set.add(i);
        }

        int length = A.size();
        for (int j = 1; j <= length; j++) {
            if (!set.contains(j)) {
                return j;
            }
        }

        return length + 1;
    }
}
