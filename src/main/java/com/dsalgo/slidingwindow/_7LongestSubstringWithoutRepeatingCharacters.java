package com.dsalgo.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * Get the longest substring without repeating characters in it.
 * This can be rephrased as longest substring with all unique characters.
 * This is similar to question - _6LongestSubstringWithKUniqueCharacters
 * Instead of "k" unique characters, "all" unique characters is asked in this question.
 *
 *
 * When all the characters in the substring are required to be unique, it means
 * all the characters in the window j-i+1 are unique.
 * Hence, map size can at max be j-i+1
 *
 * If map size becomes less than j-i+1, then a duplicate character is encountered.
 */
public class _7LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        System.out.println(solve("aabacbebebe"));   // ans is - 4 -> acbe
    }

    private static int solve(String input) {
        int i = 0, j = 0;
        int max = Integer.MIN_VALUE;
        Map<Character, Integer> map = new HashMap<>();

        while (j < input.length()) {
            if (map.containsKey(input.charAt(j))) {
                map.put(input.charAt(j), map.get(input.charAt(j)) + 1);
            } else {
                map.put(input.charAt(j), 1);
            }

            if (map.size() == j - i + 1) {
                max = Math.max(max, j - i + 1);
                j++;
            } else if (map.size() < j - i + 1) {
                while (map.size() < j - i + 1) {
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
}
