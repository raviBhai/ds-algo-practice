package com.dsalgo.interviewbit.arrays;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DuplicateInArray {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(4);
        list.add(1);
        list.add(2);
        list.add(5);
        System.out.println(repeatedNumber(list));
    }

    public static int repeatedNumber(final List<Integer> a) {
        if (a == null || a.isEmpty()) {
            return -1;
        }

        Set<Integer> s = new HashSet<>();
        for (int i : a) {
            if (s.add(i) == false) {
                return i;
            }
        }
        return -1;
    }
}
