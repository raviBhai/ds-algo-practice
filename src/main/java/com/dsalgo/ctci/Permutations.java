package com.dsalgo.ctci;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Permutations {
    static List<String> list = new ArrayList<>();
    public static void main(String[] args) {
        permutations("abc");
        System.out.println(list);
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
        }
    }
}
