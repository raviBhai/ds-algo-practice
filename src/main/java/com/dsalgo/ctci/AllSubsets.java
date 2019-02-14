package com.dsalgo.ctci;

import java.util.HashSet;
import java.util.Set;

public class AllSubsets {

    // time complexity is O(2^n * n)
    public Set<String> getSubsets(char[] arr, int start, int end) {
        Set<String> result = new HashSet<>();
        if (end < start) {
            result.add("");
        } else {
            Set<String> previous = getSubsets(arr, start, end - 1);
            char ch = arr[end];
            for (String s : previous) {
                result.add(s);
                result.add(s + String.valueOf(ch));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        char[] arr = {'a', 'b', 'c'};
        AllSubsets subsets = new AllSubsets();
        Set<String> result = subsets.getSubsets(arr, 0, arr.length - 1);
        for (String s : result) {
            System.out.println(s);
        }
    }
}
