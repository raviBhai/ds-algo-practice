package com.dsalgo.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Given input strings S1 and S2
 *
 * S1 = aabacbaaadxcba
 * S2 = aaab
 *
 * S2 can have 4! anagrams.
 *
 * Check how many such anagrams are in S1
 *
 */

public class _3CountOccurrencesOfAnagrams {


    /**
     *
     * Get a map from S2. Map will have count of distinct characters in S2.
     *
     * Slide a window over S1. Window start is "i" and window end is "j"
     *
     * When the window is increasing or sliding, check if the char at j is in the map.
     * If yes, then decrement the counter for the key in the map.
     *
     * Later on, when the window slides and "i" goes to the next location, we should do the reverse
     * Check if char at "i" is in the map, if yes, then increment the counter
     *
     * Just before sliding, that is in the "capture answer" section, if all the keys in the map has value 0,
     * then anagram is encountered. Hence, increment the result count.
     *
     * To check if all the keys in the map has 0 as value, do not iterate over the map.
     * Maintain a variable "numberOfZeros" and init it with 0.
     * When a key count reaches 0 in the map, then increment the "numberOfZeros" variable.
     * If the "numberOfZeros" is same as map.size(), it means, all the keys in the map have 0 as value.
     *
     * Later on, when the window slides and "i" goes to the next location, we should do the reverse
     * While incrementing the value of char at "i" in the map, check if the existing value is 0.
     * If yes, then decrement the "numberOfZeros" by 1
     *
     * @param s1
     * @param s2
     * @return
     */
    private static int solve(String s1, String s2) {

        int i = 0, j = 0;
        int k = s2.length();
        Map<Character, Integer> map = new HashMap<>();
        for (Character ch : s2.toCharArray()) {
            if (map.containsKey(ch)) {
                map.put(ch, map.get(ch) + 1);
            } else {
                map.put(ch, 1);
            }
        }
        int numberOfZeros = 0;
        int result = 0;

        while (j < s1.length()) {

            // calculate
            if (map.containsKey(s1.charAt(j))) {
                map.put(s1.charAt(j), map.get(s1.charAt(j)) - 1);
                if (map.get(s1.charAt(j)) == 0) {
                    numberOfZeros++;
                }
            }

            if (j - i + 1 < k) {
                j++;
            } else if (j - i + 1 == k) {

                // capture answer
                if (numberOfZeros == map.size()) {  // all chars have value as zero
                    result++;
                }

                // slide the window
                if (map.containsKey(s1.charAt(i))) {
                    if (map.get(s1.charAt(i)) == 0) {
                        numberOfZeros--;
                    }
                    map.put(s1.charAt(i), map.get(s1.charAt(i)) + 1);
                }

                i++;
                j++;
            }

        }
        return result;
    }

    public static void main(String[] args) {
        String s1 = "aabacbaaadxcba";
        String s2 = "aaab";
        System.out.println(solve(s1, s2));
    }

}
