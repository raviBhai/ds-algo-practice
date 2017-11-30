package com.dsalgo.practice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class RankString {
    public static void main(String[] args) {
        String s = "eren";
        System.out.println(findRank1(s));
    }

    static int findRank(String input) {

        Set<String> set = new TreeSet<String>();
        for (int out = 0; out < input.length(); out++) {
            for (int in = input.length(); in > out; in--) {
                String sub = input.substring(out, in);
                set.add(sub);
            }
        }
        System.out.println(set);
        List<String> list = new ArrayList<String>();
        for(String s: set) {
            list.add(s);
        }

        return list.indexOf(input)+1;
    }

    static int findRank1(String input) {

        Map<Character, Integer> map = new TreeMap<Character, Integer>();
        char[] arr = input.toCharArray();
        for(int i=0; i<arr.length; i++) {
            if (map.get(arr[i]) == null) {
                map.put(arr[i], arr.length - i);
            } else{
                int rank = map.get(arr[i]);
                map.put(arr[i], rank+1);
            }
        }

        char start = input.charAt(0);
        int res=0;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            int rank = map.get(entry.getKey());
            res = res + rank;
            if (entry.getKey() == start) {
                break;
            }
        }
        return res;
    }

}
