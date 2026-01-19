package com.dsalgo.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class _6LongestSubstringWithKUniqueCharacters {

    private static int solve(String input, int k) {
        int i = 0, j = 0;
        Map<Character, Integer> map = new HashMap<>();
        int max = Integer.MIN_VALUE;

        while (j < input.length()) {

            if (map.containsKey(input.charAt(j))) {
                map.put(input.charAt(j), map.get(input.charAt(j)) + 1);
            } else {
                map.put(input.charAt(j), 1);
            }

            if (map.size() < k) {
                j++;
            } else if (map.size() == k) {
                max = Math.max(max, j - i + 1);
                j++;
            } else if (map.size() > k) {
                while (map.size() > k) {
                    map.put(input.charAt(i), map.get(input.charAt(i)) - 1);
                    if (map.get(input.charAt(i)) == 0) {
                        map.remove(input.charAt(i));
                    }
                    i++;
                }
                j++;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(solve("aabacbebebe", 3));
    }
}
