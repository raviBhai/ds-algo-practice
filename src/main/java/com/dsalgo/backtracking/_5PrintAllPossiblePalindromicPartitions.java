package com.dsalgo.backtracking;

import java.util.ArrayList;
import java.util.List;

public class _5PrintAllPossiblePalindromicPartitions {

    public static void main(String[] args) {
        List<String> temp = new ArrayList<>();
        List<List<String>> result = new ArrayList<>();
        solve("aaba", 0, temp, result);
        System.out.println(result);
    }

    private static void solve(String s, int index, List<String> temp, List<List<String>> result) {
        if (index == s.length()) {
            result.add(new ArrayList<>(temp));    // If new ArrayList is not used, then during the backtract when an element is removed from temp, it is also removed from result.
            return;
        }
        for (int i = index; i < s.length(); i++) {
            String sub = s.substring(index, i + 1);
            if (isPalindrome(sub)) {    // if this if-clause is removed, this function will simply print all the possible partitions of the given input string
           // if (true) {
                temp.add(sub);
                solve(s, i + 1, temp, result);
                temp.remove(temp.size()-1);
            }
        }
    }

    public static boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i <= j) {
            if (s.charAt(i) == s.charAt(j)) {
                i++;
                j--;
            } else {
                return false;
            }
        }
        return true;
    }
}
