package com.dsalgo.ctci;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Permutations {
    static List<String> list = new ArrayList<>();
    static int counter = 0;
    public static void main(String[] args) {
        permutations("abcd");
        System.out.println(list);
        System.out.println(counter);
    }

    public static void permutations(String input) {
        for (int i = 0; i < input.length(); i++) {
            permutations(input.charAt(i));
        }
    }

    private static void permutations(char ch) {
        if (list.isEmpty()) {
            String s = "" + ch;
            list.add(s);
            counter++;
        } else {
            List<String> buffer = list;
            list = new ArrayList<>();
            for (String s : buffer) {
                append(s, ch);
            }
        }
    }

    private static void append(String s, char ch) {
        for (int i = 0; i <= s.length(); i++) {
            String s1 = s.substring(0, i) + ch + s.substring(i);
            list.add(s1);
            counter++;
        }
    }
}

// time complexity is O(n^2 * n!)
class RecursiveSol {
    public static Set<String> permutation (char[] arr, int start, int end) {
        Set<String> set = new HashSet<>();
        if (end < start) {
            set.add("");
        } else if (end == start) {
            set.add("" + arr[end]);
        } else {
            Set<String> previousSet = permutation(arr, start, end - 1);
            char ch = arr[end];
            for (String prevStr : previousSet) {
                for (int i = 0; i <= prevStr.length(); i++) {
                    String s = prevStr.substring(0, i) + ch + prevStr.substring(i);
                    set.add(s);
                }
            }
        }
        return set;
    }

    public static void main(String[] args) {
        String str = "abc";
        Set<String> set = permutation(str.toCharArray(), 0 , str.length()-1);
        System.out.println(set);
    }
}
