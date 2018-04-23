package com.dsalgo.interviewbit.twoPointers;

import java.util.ArrayList;

public class MergeTwoSortedLists {
    public static void main(String[] args) {
        MergeTwoSortedLists mts = new MergeTwoSortedLists();
        ArrayList<Integer> a = new ArrayList<Integer>() {{
/*
            add(1);
            add(5);
            add(8);
*/
        }};
        ArrayList<Integer> b = new ArrayList<Integer>() {{
            /*add(6);
            add(9);
            add(9);*/
        }};

        mts.merge(a, b);

        System.out.println(a);
    }

    public void merge(ArrayList<Integer> a, ArrayList<Integer> b) {
        ArrayList<Integer> tmp = new ArrayList<Integer>();

        int i = 0;
        int j = 0;

        while (i < a.size() && j < b.size()) {
            if (a.get(i) < b.get(j)) {
                tmp.add(a.get(i));
                i++;
            } else {
                tmp.add(b.get(j));
                j++;
            }
        }

        if (i == a.size()) {
            while (j < b.size()) {
                tmp.add(b.get(j));
                j++;
            }
        } else if (j == b.size()) {
            while (i < a.size()) {
                tmp.add(a.get(i));
                i++;
            }
        }

        a.clear();
        for (int k : tmp) {
            a.add(k);
        }

    }

}
