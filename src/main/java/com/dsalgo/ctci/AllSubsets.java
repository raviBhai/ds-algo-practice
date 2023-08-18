package com.dsalgo.ctci;

import java.util.HashSet;
import java.util.Set;

public class AllSubsets {

    private static int count = 0;
    private static int count1 = 0;

    // time complexity is O(2^n)
    // Every call of Set previous = getSubsets() for current n will return a previous list of size 2^(n-1)
    // And then the previous list is iterated over.
    // Hence, time complexity is 2^(n-1) * (n-1) + 2^(n-2) * (n-2) + ... = ~ 2^n
    public Set<String> getSubsets(char[] arr, int start, int end) {
        Set<String> result = new HashSet<>();
        if (end < start) {
            result.add("");
        } else {
            Set<String> previous = getSubsets(arr, start, end - 1);
            char ch = arr[end];
            count1++;
            for (String s : previous) {
                result.add(s);
                result.add(s + String.valueOf(ch));
                count++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        char[] arr = {'a', 'b', 'c', 'd'};
        AllSubsets subsets = new AllSubsets();
        Set<String> result = subsets.getSubsets(arr, 0, arr.length - 1);
        for (String s : result) {
            System.out.println(s);
        }
        System.out.println(count);
        System.out.println(count1);
    }
}
