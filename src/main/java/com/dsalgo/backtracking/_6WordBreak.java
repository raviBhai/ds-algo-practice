package com.dsalgo.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class _6WordBreak {

    private static final Set<String> dict = new HashSet<String>() {
        {
            add("cats");
            add("and");
            add("dogs");
            add("cat");
            add("sand");
        }
    };


    public static void main(String[] args) {
        List<String> temp = new ArrayList<>();
        List<List<String>> result = new ArrayList<>();
        solve("catsanddogs", 0, temp, result);
        System.out.println(result);
    }

    private static void solve(String s, int index, List<String> temp, List<List<String>> result) {
        if (index == s.length()) {
            result.add(new ArrayList<>(temp));    // If new ArrayList is not used, then during the backtract when an element is removed from temp, it is also removed from result.
            return;
        }
        for (int i = index; i < s.length(); i++) {
            String sub = s.substring(index, i + 1);
            if (dict.contains(sub)) {
                temp.add(sub);
                solve(s, i + 1, temp, result);
                temp.remove(temp.size()-1);
            }
        }
    }
}
