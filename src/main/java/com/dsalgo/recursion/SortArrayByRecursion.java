package com.dsalgo.recursion;

import java.util.ArrayList;
import java.util.List;

public class SortArrayByRecursion {

    public static void sort(List<Integer> list) {
        if (list.size() == 1) {
            return;
        }
        int last = list.get(list.size() - 1);
        list.remove(list.size() - 1);
        sort(list);
        insert(list, last);
    }

    private static void insert(List<Integer> list, int temp) {
        if (list.size() == 0 || list.get(list.size() - 1) <= temp) {
            list.add(temp);
            return;
        }
        int last = list.get(list.size() - 1);
        list.remove(list.size() - 1);
        insert(list, temp);
        list.add(last);
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(5);
        list.add(0);
        list.add(2);
        System.out.println("before sorting - " + list);
        sort(list);
        System.out.println("after sorting - " + list);
    }
}
