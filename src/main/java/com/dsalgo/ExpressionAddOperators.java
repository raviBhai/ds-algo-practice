package com.dsalgo;

import java.util.ArrayList;
import java.util.List;

public class ExpressionAddOperators {

    static List<String> res = new ArrayList<>();

    public static void main(String[] args) {
        String s = "123";
        solve(s, new ArrayList<>(), 0, s.length());
        System.out.println(res);
    }

    private static void solve(String inp, List<String> oldList, int ii, int len) {

        if (ii == len) {
            res.addAll(oldList);
            return ;
        }

        List<String> newList = new ArrayList<>();
        for (int i = 0; i < inp.length(); i++) {
            String prefix = inp.substring(0, i + 1);
            if (oldList.isEmpty()) {
                newList.add(prefix + "+");
                newList.add(prefix + "-");
                newList.add(prefix + "*");
            } else {
                for (String old : oldList) {
                    newList.add(old + prefix + "+");
                    newList.add(old + prefix + "-");
                    newList.add(old + prefix + "*");
                }
                //oldList.removeAll(oldList);
                //oldList.addAll(newList);
            }
            solve(inp.substring(i+1), (newList), i+1, len);
        }
        //return oldList;
    }
}
