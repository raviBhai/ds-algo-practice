package com.dsalgo.slidingwindow;

import java.util.HashMap;
import java.util.Map;


/*

Given two strings s and p, the task is to find the smallest substring in s that contains all characters of p, including duplicates.
If no such substring exists, return "". If multiple substrings of the same length are found, return the one with the smallest starting index.

 */
public class _9MinimumWindowSubstring {

    private static Answer solve(String s, String t) {

        Map<Character, Integer> map = new HashMap<>();
        for (char ch : t.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
            /*if (map.containsKey(ch)) {
                map.put(ch, map.get(ch) + 1);
            } else {
                map.put(ch, 1);
            }*/
        }

        int i = 0, j = 0;
        int count = map.size();
        int min=Integer.MAX_VALUE;
        String ans="";

        while (j < s.length()) {
            char charAtJ = s.charAt(j);
            if (!map.containsKey(charAtJ)) {
                j++;
                // continue;
            } else {
                map.put(charAtJ, map.get(charAtJ) - 1);
                if (map.get(charAtJ) == 0) {
                    count--;
                }
                // j++ is required here, but it is done after the below if() block, because
                // when "j" is at a location where count has become "0", we should first move "i" to right to check the answer
                // before moving "j" to next place

                if (count == 0) {

                    // "j" is at the end of the window.
                    // keep increasing "i" till the count is 0.
                    // The char at "i" may or may not be present in the map
                    // but in either case, we keep increasing "i"
                    // When any value in the map becomes "1", we increase the count, and "i" would have also moved to the next place
                    // At this moment, we know that the char at "i-1" is part of the answer, i.e., the string between i-1 and j has the substring "t"
                    // Hence, we check for the potential final answer the moment count becomes 1
                    while (count == 0) {

                        char charAtI = s.charAt(i);
                        if (!map.containsKey(charAtI)) {
                            i++;
                        } else {
                            map.put(charAtI, map.get(charAtI) + 1);
                            if (map.get(charAtI) == 1) {
                                count++;
                            }
                            i++;
                        }

                        // check for potential answer the moment count becomes "1". Potential answer would be from i-1 to j
                        if (count == 1) {
                            // min > j - (i-1) + 1   //for eg, in a string from index 0 to 9, let's say j is at 7 and i at 5,
                            // the answer would be from i-1 i.e., 4 to 7, which is of length 4(4,5,6,7), hence 4 = j-(i-1)+1
                            if(min>j-i+2) {
                                ans=s.substring(i-1,j+1);
                                min=Math.min(min,j-i+2);
                            }
                        }
                    }
                }

                j++;
            }


        }
        return new Answer(ans, min);
    }

    public static void main(String[] args) {
        System.out.println(solve("TTTTA", "TTA"));
        System.out.println(solve("TIMETOPRACTICE", "TOC"));
        System.out.println(solve("AAA", "A"));
        System.out.println(solve("zoomlazapzo", "oza"));

        // input string S1 has 2 substrings of smallest length which has all the chars from S2 - zoa, oza
        // however, it should return zoa as it has a smaller starting index
        System.out.println(solve("zoomlazapzoxxxxxxxxxzoaxxxxxxoza", "oza"));
    }

}

class Answer {
    String str;
    int len;

    public Answer(String str, int len) {
        this.len = len;
        this.str = str;
    }

    @Override
    public String toString() {
        return "len - " + len + " - str - " + str;
    }
}