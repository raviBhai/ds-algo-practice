package com.dsalgo.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class _1PermutationsOfString {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        char[] str = {'a', 'b', 'c'};
        solve(str, 0, list);
        System.out.println(list);

        list = new ArrayList<>();
        char[] str2 = {'a', 'a', 'c'};
        solve(str2, 0, list);
        System.out.println(list);
    }

    /**
     * Time complexity -
     *
     * work done = workDonePerNode * numberOfNodes
     *
     * numberOfNodes = sum of numberOfNodes at each level
     * = 1 + 3 + (3*2) + (3*2*1)
     * = 1 + n + n*(n-1) + n*(n-1)*(n-2) + ... + n!
     *
     * this will be of the order of n! as we can ignore other terms in the  sum
     *
     *
     * workDonePerNode
     * - at every node, a for loop is being invoked which runs from index to length of the str.
     * the index starts from 0 and moves to n. So, worst case, we can say that the loop runs through the entire str.
     * Hence, time complexity of this loop is O(n)
     *
     * Hence, workDonePerNode = O(n)
     *
     *
     * Hence, overall TC = workDonePerNode * numberOfNodes
     *                   = O(n*n!)
     *
     *
     * @param str
     * @param index
     * @param list
     */
    private static void solve(char[] str, int index, List<String> list) {
        if (index == str.length - 1) {
            list.add(new String(str));
            return;
        }
        Set<Character> set = new HashSet<>();
        for (int i = index; i < str.length; i++) {
            if (!set.contains(str[i])) {
                set.add(str[i]);
                swap(str, index, i);
                solve(str, index + 1, list);
                swap(str, index, i);
            }
        }
    }

    private static void swap(char[] str, int index, int i) {
        char ch = str[index];
        str[index] = str[i];
        str[i] = ch;
    }
}
