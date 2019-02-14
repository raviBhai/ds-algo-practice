package com.dsalgo.onlinetests;

import java.util.*;

public class Ubs {

    public static void main(String[] args) {
        String s = "aabcd";
        System.out.println(test(s, 1));

    }

    public static String test(String S, int K) {

        int[] count = new int[26];

        for(int i = 0; i < S.length(); i++) {
            count[S.charAt(i) - 'a'] ++;
        }

        Set<Integer> set = new HashSet<>();
        for (Integer i : count) {
            if (i != 0) {
                set.add(i);
            }
        }

        List<Integer> list = new ArrayList<>();
        list.addAll(set);
        Collections.sort(list);


        if (K > list.size()) {
            return "-1";
        } else {
            int freq = list.get(list.size() - K);

            char ch = ' ';
            for (int i = 0; i < count.length; i++) {
                if (count[i] == freq) {
                    ch = (char)(i + 'a');
                    break;
                }
            }
            return String.valueOf(ch);
        }
    }
}

